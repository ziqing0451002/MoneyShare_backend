package com.example.MoneyShare.ShareMember;

import com.example.MoneyShare.ShareItem.ShareItem;
import com.example.MoneyShare.ShareList.ShareList;
import com.example.MoneyShare.ShareResult.ShareResultService;
import com.example.MoneyShare.ShareList.ShareListRepository;
import com.example.MoneyShare.ShareItem.ShareItemRepository;
import com.example.MoneyShare.CommentModel.SerialNumberMaker;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

@Service
public class ShareMemberService {

    private ShareMemberRepository shareMemberRepository;
    private SerialNumberMaker serialNumberMaker;
    private ShareResultService shareResultService;
    private ShareListRepository shareListRepository;
    private ShareItemRepository shareItemRepository;


    public ShareMemberService(ShareMemberRepository shareMemberRepository, SerialNumberMaker serialNumberMaker, ShareResultService shareResultService, ShareListRepository shareListRepository, ShareItemRepository shareItemRepository) {
        this.shareMemberRepository = shareMemberRepository;
        this.serialNumberMaker = serialNumberMaker;
        this.shareResultService = shareResultService;
        this.shareListRepository = shareListRepository;
        this.shareItemRepository = shareItemRepository;
    }

    public List<ShareMember> getShareMember(){
        return shareMemberRepository.findAll();
    }

    public List<ShareMember> getShareMemberById(BigInteger id){
        return shareMemberRepository.findShareMemberByShareListId(id);
    }

    public boolean addShareMemberLoop(String memberList,BigInteger shareListId,String listCreater){
        String[] memberSplit = memberList.split(",");
        System.out.println(memberSplit);
        for (String s : memberSplit) {
            ShareMember shareMember = new ShareMember();
            System.out.println(s);
            shareMember.setMemberName(s);
            System.out.println(shareMember);
            shareMember.setShareListId(shareListId);
            shareMember.setListCreater(listCreater);
            System.out.println(s);
            shareMember.setShareItemId(BigInteger.valueOf(0));
            shareMember.setShareItemName("init");
            shareMember.setShareMoney((0));
            addShareMember(shareMember);
        }
        return true;
    }

    public boolean addShareMemberLoop(String memberList,BigInteger shareListId,BigInteger shareItemId,String shareItemName,Integer itemCost,String personPayBefore,String listCreater){
        String[] memberSplit = memberList.split(",");
        System.out.println(memberSplit);
        for (String s : memberSplit) {
            ShareMember shareMember = new ShareMember();
            shareMember.setMemberName(s);
            System.out.println("變數S:"+ s);
            System.out.println("變數personPayBefore:"+ personPayBefore);

            shareMember.setShareListId(shareListId);
            shareMember.setListCreater(listCreater);
//            System.out.println(s);
            shareMember.setShareItemId(shareItemId);
            shareMember.setShareItemName(shareItemName);
            shareMember.setShareMoney(itemCost/memberSplit.length);
            if (Objects.equals(s,personPayBefore)){
                shareMember.setSharePayBefore(itemCost);
                System.out.println("SUCCSEE");

            }else{
                shareMember.setSharePayBefore(0);
            }
            addShareMember(shareMember);
        }
        return true;
    }

    public boolean addShareMember(ShareMember shareMember ) {
        int count = 0;
        BigInteger idInit = serialNumberMaker.IdCount(count);
        System.out.println(idInit);
        shareMember.setMemberId(idInit);

        while (shareMemberRepository.existsById(shareMember.getMemberId())) {
            count = count + 1;
            System.out.println(count);
            BigInteger id = serialNumberMaker.IdCount(count);
            System.out.println(id);
            shareMember.setMemberId(id);
        }
        boolean exists = shareMemberRepository.existsById(shareMember.getMemberId());
        if (exists) {
            throw new IllegalStateException("MemberId:" + shareMember.getMemberId() + "已被使用");
        } else if (shareMember.getMemberName() == null || shareMember.getMemberName().length() <= 0) {
            throw new IllegalStateException("參與人名稱不得為空");
        } else if (shareMember.getShareListId() == null) {
            throw new IllegalStateException("所屬shareList不得為空");
        } else {
            //紀錄創建時間以及初始化最終修改時間
            Long datetime = System.currentTimeMillis();
            Timestamp timestamp = new Timestamp(datetime);
            shareMember.setCreatedTime(timestamp);
            shareMember.setUpdatedTime(timestamp);
            shareMemberRepository.save(shareMember);
            return true;
        }
    }

    public void deletShareMember(BigInteger memberId,BigInteger shareListId){
        ShareMember shareMember  = shareMemberRepository.findById(memberId).orElseThrow(
                () -> new IllegalStateException("listId:" + memberId + "不存在")
        );

        if ( !Objects.equals(shareMember.getShareListId(),shareListId)){
            throw new IllegalStateException("限發起人刪除");
        }else{
            shareMemberRepository.deleteById(memberId);
        }
    }

    public void deletShareMemberByShareListId(BigInteger shareListId){
        List<ShareMember> shareMembers = shareMemberRepository.findShareMemberByShareListId(shareListId);
        for (ShareMember shareMember : shareMembers) {
            shareMemberRepository.deleteById(shareMember.getMemberId());
        }
    }


    @Transactional
    public void upShareMemberInfo(BigInteger memberId, String memberName, BigInteger shareListId){
        ShareMember shareMember  = shareMemberRepository.findById(memberId).orElseThrow(
                () -> new IllegalStateException("listId:" + memberId + "不存在")
        );

        if ( !Objects.equals(shareMember.getShareListId(),shareListId)){
            throw new IllegalStateException("限發起人刪除");
        }else if(memberName == null || memberName.length() <= 0){
            throw new IllegalStateException("名稱不得為空");
        }else {
            shareMember.setMemberName(memberName);
            //紀錄修改時間
            Long datetime = System.currentTimeMillis();
            Timestamp timestamp = new Timestamp(datetime);
            shareMember.setUpdatedTime(timestamp);
        }
    }

    //想辦法產出計算後的結果，set進去ShareResult
    public boolean resultCalculate(BigInteger shareListId){
        //如果重新計算結果，就先將原有的sgareMember和ShareResult刪除
        deletShareMemberByShareListId(shareListId);
        shareResultService.deletShareResultByShareListId(shareListId);

        //送出shareList表單，產生shareMember
        ShareList shareList =  shareListRepository.findShareListByListId(shareListId);
        addShareMemberLoop(shareList.getListMember(),shareList.getListId(),shareList.getListCreater());

        //送出shareItem表單，產生shareMember
        List<ShareItem> shareItems =  shareItemRepository.findShareItemByShareListId(shareListId);
        for(ShareItem shareItem:  shareItems){
            addShareMemberLoop(shareItem.getItemMember(),shareItem.getShareListId(),shareItem.getItemId(),shareItem.getItemName(),shareItem.getItemCost(),shareItem.getItemCreater(),shareItem.getItemCreater());
        }

        //開始計算
        List<ShareMember> payDetails = shareMemberRepository.findShareMemberByShareListId(shareListId);
        List<ShareMember> shareMembers = shareMemberRepository.findShareMemberByShareListIdAndShareItemId(shareListId, BigInteger.valueOf(0));
        System.out.println(shareMembers);
        boolean calculateResult = false;
        for (int i = 0; i < shareMembers.size(); i++) {
            ShareCount shareCount = new ShareCount();
            ShareMember shareMember = shareMembers.get(i);
            shareCount.setShareListId(shareMember.getShareListId());
            shareCount.setMemberName(shareMember.getMemberName());
            shareCount.setCountCreater(shareMember.getListCreater());
            shareCount.setPayTotal(0);
            shareCount.setShareTotal(0);
            shareCount.setResultTotal(0);
//            System.out.println(shareCount.getMemberName());
//            System.out.println(shareCount.getShareListId());
//            System.out.println(shareCount.getPayTotal());
//            System.out.println(shareCount.getShareTotal());
//            System.out.println(shareCount.getResultTotal());
            for (ShareMember payDetail : payDetails) {
//                if (!payDetail.getShareItemId().equals(BigInteger.valueOf(0))){
//                    System.out.println("getMemberName:" + payDetail.getMemberName());
//                    System.out.println("getShareListId:" + payDetail.getShareListId());
//                    System.out.println("getSharePayBefore:" + payDetail.getSharePayBefore());
//                    System.out.println("getShareMoney:" + payDetail.getShareMoney());
//                }
                if (shareCount.getShareListId().equals(payDetail.getShareListId()) && shareCount.getMemberName().equals(payDetail.getMemberName()) && !payDetail.getShareItemId().equals(BigInteger.valueOf(0))) {
//                    System.out.println("getMemberName:" + payDetail.getMemberName());
//                    System.out.println("getShareListId:" + payDetail.getShareListId());
//                    System.out.println("getSharePayBefore:" + payDetail.getSharePayBefore());
//                    System.out.println("getShareMoney:" + payDetail.getShareMoney());
                    shareCount.setPayTotal(shareCount.getPayTotal() + payDetail.getSharePayBefore());
                    shareCount.setShareTotal(shareCount.getShareTotal() + payDetail.getShareMoney());
                    shareCount.setResultTotal(shareCount.getPayTotal() - shareCount.getShareTotal());
//                }
                }
            }
            System.out.println("getMemberName:" + shareCount.getMemberName());
            System.out.println("getResultTotal:" + shareCount.getResultTotal());
            calculateResult = shareResultService.addShareResultFromCalculate(shareCount);


        }
        return calculateResult;
    }


}
