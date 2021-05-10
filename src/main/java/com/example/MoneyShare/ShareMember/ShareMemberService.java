package com.example.MoneyShare.ShareMember;

import com.example.MoneyShare.CommentModel.SerialNumberMaker;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ShareMemberService {

    private ShareMemberRepository shareMemberRepository;
    private SerialNumberMaker serialNumberMaker;

    public ShareMemberService(ShareMemberRepository shareMemberRepository, SerialNumberMaker serialNumberMaker) {
        this.shareMemberRepository = shareMemberRepository;
        this.serialNumberMaker = serialNumberMaker;
    }

    public List<ShareMember> getShareMember(){
        return shareMemberRepository.findAll();
    }

    public List<ShareMember> getShareMemberById(BigInteger id){
        return shareMemberRepository.findShareMemberByShareListId(id);
    }

    public boolean addreShareMemberLoop(String memberList,BigInteger shareListId){
        String[] memberSplit = memberList.split(",");
        System.out.println(memberSplit);
        for (String s : memberSplit) {
            ShareMember shareMember = new ShareMember();
            System.out.println(s);
            shareMember.setMemberName(s);
            System.out.println(shareMember);
            shareMember.setShareListId(shareListId);
            System.out.println(s);
            shareMember.setShareItemId(BigInteger.valueOf(0));
            shareMember.setShareItemName("init");
            shareMember.setShareMoney((0));
            addShareMember(shareMember);
        }
        return true;
    }

    public boolean addreShareMemberLoop(String memberList,BigInteger shareListId,BigInteger shareItemId,String shareItemName,Integer itemCost,String personPayBefore){
        String[] memberSplit = memberList.split(",");
        System.out.println(memberSplit);
        for (String s : memberSplit) {
            ShareMember shareMember = new ShareMember();
            shareMember.setMemberName(s);
            System.out.println("變數S:"+ s);
            System.out.println("變數personPayBefore:"+ personPayBefore);

            shareMember.setShareListId(shareListId);
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


}
