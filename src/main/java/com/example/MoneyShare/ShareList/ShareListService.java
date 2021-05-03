package com.example.MoneyShare.ShareList;

import com.example.MoneyShare.UserInfo.UserInfo;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.sql.SQLOutput;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class ShareListService {

    private ShareListRepository shareListRepository;
    private int count = 0; //要想方法讓他不因為重是重開而定值



    public ShareListService(ShareListRepository shareListRepository) {
        this.shareListRepository = shareListRepository;
    }


    public List<ShareList> getShareList(){
        return shareListRepository.findAll();
    }

    public boolean addShareList(ShareList shareList){
        // ID 前八碼為日期流水號
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String dateFormatToString = dateFormat.format(date).toString();
        // ID 後五碼為編號流水號
        StringBuilder id = new StringBuilder("0");
        count = count + 1 ;
        id.append("0".repeat(Math.max(0, (5 - getDigitsNumber(count)) - 1)));
        String countTiString = id.toString() + count;
        String result = dateFormatToString + countTiString;
        BigInteger resultToBigint = new BigInteger(result );
        shareList.setListId(resultToBigint);
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
            shareListRepository.save(shareList);
            return true;
        }
    }

    public static int getDigitsNumber(final double d) {
        return (int) Math.log10(d) + 1;
    }


}
