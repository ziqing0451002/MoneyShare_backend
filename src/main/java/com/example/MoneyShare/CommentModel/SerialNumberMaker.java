package com.example.MoneyShare.CommentModel;

import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class SerialNumberMaker {

    public BigInteger IdCount(int count){
        // ID 前八碼為日期流水號
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String dateFormatToString = dateFormat.format(date).toString();
        // ID 後五碼為編號流水號
        count = count + 1 ;
//        System.out.println(count);
        StringBuilder id = new StringBuilder("0");
        id.append("0".repeat(Math.max(0, (5 - getDigitsNumber(count)) - 1)));
        String countToString = id.toString() + count;
        String result = dateFormatToString + countToString;
        BigInteger resultToBigint = new BigInteger(result );
//        System.out.println(resultToBigint);
        return resultToBigint;
    }

    public static int getDigitsNumber(final double d) {
        return (int) Math.log10(d) + 1;
    }
}
