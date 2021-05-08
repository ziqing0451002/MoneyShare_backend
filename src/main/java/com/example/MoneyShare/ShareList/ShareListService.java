package com.example.MoneyShare.ShareList;

import org.springframework.stereotype.Service;
import com.example.MoneyShare.CommentModel.SerialNumberMaker;
import com.example.MoneyShare.ShareMember.ShareMemberService;

import javax.transaction.Transactional;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

@Service
public class ShareListService {

    private ShareListRepository shareListRepository;
    private SerialNumberMaker serialNumberMaker;
    private ShareMemberService shareMemberService;


    public ShareListService(ShareListRepository shareListRepository, SerialNumberMaker serialNumberMaker, ShareMemberService shareMemberService) {
        this.shareListRepository = shareListRepository;
        this.serialNumberMaker = serialNumberMaker;
        this.shareMemberService = shareMemberService;
    }


    public List<ShareList> getShareList(){
        return shareListRepository.findAll();
    }

    public boolean addShareList(ShareList shareList){
        int count = 0;
        BigInteger idInit = serialNumberMaker.IdCount(count);
        shareList.setListId(idInit);

        while (shareListRepository.existsById(shareList.getListId())){
            count = count + 1;
            BigInteger id = serialNumberMaker.IdCount(count);
            shareList.setListId(id);
        }

        boolean exists = shareListRepository.existsById(shareList.getListId());
        if (exists){
            throw new IllegalStateException("listId:" + shareList.getListId() + "已被使用");
        }else if (shareList.getListName() == null || shareList.getListName().length() <= 0){
            throw new IllegalStateException("清單名稱不得為空");
        }else if (shareList.getListCreater() == null || shareList.getListCreater().length() <= 0){
            throw new IllegalStateException("發起人不得為空");
        }else if (shareList.getListMember()== null || shareList.getListMember().length() <= 0){
            throw new IllegalStateException("參與人不得為空");
        }else{
            //紀錄創建時間以及初始化最終修改時間
            Long datetime = System.currentTimeMillis();
            Timestamp timestamp = new Timestamp(datetime);
            shareList.setCreatedTime(timestamp);
            shareList.setUpdatedTime(timestamp);
            //將分帳參與人紀錄至ShareMember
            shareMemberService.addreShareMemberLoop(shareList.getListMember(),shareList.getListId());
            shareListRepository.save(shareList);

            return true;
        }
    }

    public void deletShareList(BigInteger listId,String listCreater){
        ShareList shareList = shareListRepository.findById(listId).orElseThrow(
                () -> new IllegalStateException("listId:" + listId + "不存在")
        );

        if ( !Objects.equals(shareList.getListCreater(),listCreater)){
            throw new IllegalStateException("限發起人刪除");

        }else{
            shareListRepository.deleteById(listId);
        }
    }

    @Transactional
    public void upShareListInfo(BigInteger listId, String listName, String listCreater, String listMember){
        ShareList shareList = shareListRepository.findById(listId).orElseThrow(
                () -> new IllegalStateException("listId:" + listId + "不存在")
        );

        if(!Objects.equals(shareList.getListCreater(),listCreater)){
            throw new IllegalStateException("限發起人編輯");
        }else if(listName == null || listName.length() <= 0){
            throw new IllegalStateException("名稱不得為空");
        }else if(listMember == null || listMember.length() <= 0){
            throw new IllegalStateException("參與人不得為空");
        }else {
            shareList.setListName(listName);
            shareList.setListMember(listMember);
            //紀錄修改時間
            Long datetime = System.currentTimeMillis();
            Timestamp timestamp = new Timestamp(datetime);
            shareList.setUpdatedTime(timestamp);
        }
    }


}
