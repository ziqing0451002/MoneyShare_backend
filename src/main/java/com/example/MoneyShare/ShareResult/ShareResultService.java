package com.example.MoneyShare.ShareResult;

import com.example.MoneyShare.CommentModel.SerialNumberMaker;
import com.example.MoneyShare.ShareMember.ShareCount;
import com.example.MoneyShare.ShareList.ShareListService;
import com.example.MoneyShare.ShareMember.ShareMember;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

@Service
public class ShareResultService {

    private ShareResultRepository shareResultRepository;
    private SerialNumberMaker serialNumberMaker;
//    private ShareListService shareListService;


    public ShareResultService(ShareResultRepository shareResultRepository, SerialNumberMaker serialNumberMaker) {
        this.shareResultRepository = shareResultRepository;
        this.serialNumberMaker = serialNumberMaker;
//        this.shareListService = shareListService;
    }

    public List<ShareResult> getShareResult(){
        return shareResultRepository.findAll();
    }

    public List<ShareResult> getShareResultByShareListId(BigInteger shareListId){
        return shareResultRepository.findShareResultByShareListId(shareListId);
    }

    public boolean addShareResultFromCalculate(ShareCount shareCount){
        ShareResult shareResult = new ShareResult();
        shareResult.setShareListId(shareCount.getShareListId());
        shareResult.setResultCreater(shareCount.getCountCreater());
        shareResult.setMemberName(shareCount.getMemberName());
        shareResult.setPayTotal(shareCount.getPayTotal());
        shareResult.setShareTotal(shareCount.getShareTotal());
        shareResult.setResultTotal(shareCount.getResultTotal());
        System.out.println("setResultId:"+ shareResult.getShareListId());
        System.out.println("getResultCreater:"+ shareResult.getResultCreater());
        System.out.println("getMemberName:"+ shareResult.getMemberName());
        System.out.println("getPayTotal:"+ shareResult.getPayTotal());
        System.out.println("getShareTotal:"+ shareResult.getShareTotal());
        System.out.println("getResultTotal:"+ shareResult.getResultTotal());
        boolean addResult = addShareResult(shareResult);
        return addResult;
    }

    public boolean addShareResult(ShareResult shareResult ){
        int count = 0;
        BigInteger idInit = serialNumberMaker.IdCount(count);
        shareResult.setResultId(idInit);

        while (shareResultRepository.existsById(shareResult.getResultId())){
            count = count + 1;
            BigInteger id = serialNumberMaker.IdCount(count);
            shareResult.setResultId(id);
        }

        boolean exists = shareResultRepository.existsById(shareResult.getResultId());
        if (exists){
            throw new IllegalStateException("resultId:" + shareResult.getResultId() + "????????????");
        }else if (shareResult.getShareListId()== null){
            throw new IllegalStateException("??????shareList????????????");
        }else if (shareResult.getMemberName()== null || shareResult.getMemberName().length() <= 0){
            throw new IllegalStateException("??????????????????");
        }else if (shareResult.getResultCreater()== null || shareResult.getResultCreater().length() <= 0){
            throw new IllegalStateException("?????????????????????");
        }else if (shareResult.getPayTotal()== null){
            throw new IllegalStateException("????????????????????????");
        }else if (shareResult.getShareTotal()== null){
            throw new IllegalStateException("????????????????????????");
        }else if (shareResult.getResultTotal()== null){
            throw new IllegalStateException("????????????????????????");
        }else{
            //???????????????????????????????????????????????????
            Long datetime = System.currentTimeMillis();
            Timestamp timestamp = new Timestamp(datetime);
            shareResult.setCreatedTime(timestamp);
            shareResult.setUpdatedTime(timestamp);
//            shareListService.setShareResultId(shareResult.getShareListId(),shareResult.getResultId());
            shareResultRepository.save(shareResult);

            return true;
        }
    }

    public void deletShareResult(BigInteger shareListId, String resultCreater){
        ShareResult shareResult = shareResultRepository.findById(shareListId).orElseThrow(
                () -> new IllegalStateException("resultId:" + shareListId + "?????????")
        );

        if ( !Objects.equals(shareResult.getResultCreater(),resultCreater)){
            throw new IllegalStateException("??????????????????");
        }else{
            shareResultRepository.deleteById(shareListId);
        }
    }

    public void deletShareResultByShareListId(BigInteger shareListId){
        List<ShareResult> shareResults = shareResultRepository.findShareResultByShareListId(shareListId);
        for (ShareResult shareResult : shareResults) {
            shareResultRepository.deleteById(shareResult.getResultId());
        }
    }
}
