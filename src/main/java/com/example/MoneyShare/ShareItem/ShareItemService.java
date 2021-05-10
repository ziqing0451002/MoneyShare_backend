package com.example.MoneyShare.ShareItem;
import com.example.MoneyShare.ShareMember.ShareMemberService;
import com.example.MoneyShare.CommentModel.SerialNumberMaker;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;
@Service
public class ShareItemService {

    private ShareItemRepository shareItemRepository;
    private SerialNumberMaker serialNumberMaker;
    private ShareMemberService shareMemberService;

    public ShareItemService(ShareItemRepository shareItemRepository, SerialNumberMaker serialNumberMaker, ShareMemberService shareMemberService) {
        this.shareItemRepository = shareItemRepository;
        this.serialNumberMaker = serialNumberMaker;
        this.shareMemberService = shareMemberService;
    }

    public List<com.example.MoneyShare.ShareItem.ShareItem> getShareItem(){
        return shareItemRepository.findAll();
    }

    public boolean addShareItem(ShareItem shareItem){
        int count = 0;
        BigInteger idInit = serialNumberMaker.IdCount(count);
        shareItem.setItemId(idInit);

        while (shareItemRepository.existsById(shareItem.getItemId())){
            count = count + 1;
            BigInteger id = serialNumberMaker.IdCount(count);
            shareItem.setItemId(id);
        }

        boolean exists = shareItemRepository.existsById(shareItem.getItemId());
        if (exists){
            throw new IllegalStateException("listId:" + shareItem.getItemId() + "已被使用");
        }else if (shareItem.getItemName() == null || shareItem.getItemName().length() <= 0){
            throw new IllegalStateException("品項名稱不得為空");
        }else if (shareItem.getItemCost() == null || shareItem.getItemCost() <= 0){
            throw new IllegalStateException("品項花費不得為空");
        }else if (shareItem.getItemCreater()== null || shareItem.getItemCreater().length() <= 0){
            throw new IllegalStateException("代墊人不得為空");
        }else if (shareItem.getItemMember()== null || shareItem.getItemMember().length() <= 0){
            throw new IllegalStateException("參與人不得為空");
        }else if (shareItem.getShareListId()== null){
            throw new IllegalStateException("所屬shareList不得為空");
        }else{
            //紀錄創建時間以及初始化最終修改時間
            Long datetime = System.currentTimeMillis();
            Timestamp timestamp = new Timestamp(datetime);
            shareItem.setCreatedTime(timestamp);
            shareItem.setUpdatedTime(timestamp);
            //將分帳參與人紀錄至ShareMember
            shareMemberService.addreShareMemberLoop(shareItem.getItemMember(),shareItem.getShareListId(),shareItem.getItemId(),shareItem.getItemName(),shareItem.getItemCost(),shareItem.getItemCreater());
            shareItemRepository.save(shareItem);

            return true;
        }
    }

    public void deletShareItem(BigInteger itemId,String itemCreater){
        ShareItem shareItem = shareItemRepository.findById(itemId).orElseThrow(
                () -> new IllegalStateException("listId:" + itemId + "不存在")
        );

        if ( !Objects.equals(shareItem.getItemCreater(),itemCreater)){
            throw new IllegalStateException("限發起人刪除");

        }else{
            shareItemRepository.deleteById(itemId);
        }
    }

    @Transactional
    public void upShareItemInfo(BigInteger itemId, String itemName, String itemCreater, String itemMember,Integer itemCost){
        ShareItem shareItem = shareItemRepository.findById(itemId).orElseThrow(
                () -> new IllegalStateException("listId:" + itemId + "不存在")
        );

        if(!Objects.equals(shareItem.getItemCreater(),itemCreater)){
            throw new IllegalStateException("限發起人編輯");
        }else if (shareItem.getItemName() == null || shareItem.getItemName().length() <= 0){
            throw new IllegalStateException("品項名稱不得為空");
        }else if (shareItem.getItemCost() == null || shareItem.getItemCost() <= 0){
            throw new IllegalStateException("品項花費不得為空");
        }else if (shareItem.getItemCreater()== null || shareItem.getItemCreater().length() <= 0){
            throw new IllegalStateException("代墊人不得為空");
        }else if (shareItem.getItemMember()== null || shareItem.getItemMember().length() <= 0){
            throw new IllegalStateException("參與人不得為空");
        }else {
            shareItem.setItemName(itemName);
            shareItem.setItemMember(itemMember);
            shareItem.setItemCost(itemCost);
            //紀錄修改時間
            Long datetime = System.currentTimeMillis();
            Timestamp timestamp = new Timestamp(datetime);
            shareItem.setUpdatedTime(timestamp);
        }
    }
}
