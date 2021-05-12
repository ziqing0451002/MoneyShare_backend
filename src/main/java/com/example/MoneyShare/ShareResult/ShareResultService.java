package com.example.MoneyShare.ShareResult;

import com.example.MoneyShare.CommentModel.SerialNumberMaker;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

@Service
public class ShareResultService {

    private ShareResultRepository shareResultRepository;
    private SerialNumberMaker serialNumberMaker;


    public ShareResultService(ShareResultRepository shareResultRepository, SerialNumberMaker serialNumberMaker) {
        this.shareResultRepository = shareResultRepository;
        this.serialNumberMaker = serialNumberMaker;
    }

    public List<ShareResult> getShareResult(){
        return shareResultRepository.findAll();
    }

//    public boolean addShareResultFromCalculate(){
//
//    }

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
            throw new IllegalStateException("resultId:" + shareResult.getResultId() + "已被使用");
        }else if (shareResult.getShareListId()== null){
            throw new IllegalStateException("所屬shareList不得為空");
        }else if (shareResult.getMemberName()== null || shareResult.getMemberName().length() <= 0){
            throw new IllegalStateException("名稱不得為空");
        }else if (shareResult.getResultCreater()== null || shareResult.getResultCreater().length() <= 0){
            throw new IllegalStateException("創建人不得為空");
        }else if (shareResult.getPayTotal()== null){
            throw new IllegalStateException("已付金額不得為空");
        }else if (shareResult.getShareTotal()== null){
            throw new IllegalStateException("未付金額不得為空");
        }else if (shareResult.getResultTotal()== null){
            throw new IllegalStateException("分帳金額不得為空");
        }else{
            //紀錄創建時間以及初始化最終修改時間
            Long datetime = System.currentTimeMillis();
            Timestamp timestamp = new Timestamp(datetime);
            shareResult.setCreatedTime(timestamp);
            shareResult.setUpdatedTime(timestamp);

            shareResultRepository.save(shareResult);

            return true;
        }
    }

    public void deletShareResult(BigInteger shareListId, String resultCreater){
        ShareResult shareResult = shareResultRepository.findById(shareListId).orElseThrow(
                () -> new IllegalStateException("resultId:" + shareListId + "不存在")
        );

        if ( !Objects.equals(shareResult.getResultCreater(),resultCreater)){
            throw new IllegalStateException("限發起人刪除");
        }else{
            shareResultRepository.deleteById(shareListId);
        }
    }
}
