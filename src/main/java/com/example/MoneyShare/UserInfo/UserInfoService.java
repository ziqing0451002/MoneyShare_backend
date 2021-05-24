package com.example.MoneyShare.UserInfo;

import com.example.MoneyShare.CommentModel.Encoder_MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.sql.Timestamp;

@Service
public class UserInfoService {

    private final UserInfoRepository userInfoRepository;
    private final Encoder_MD5 encoder_md5;


    @Autowired
    public UserInfoService(UserInfoRepository userInfoRepository, Encoder_MD5 encoder_md5) {
        this.userInfoRepository = userInfoRepository;
        this.encoder_md5 = encoder_md5;
    }

    public List<UserInfo> getUserInfo(){
        return userInfoRepository.findAll();
    }

    public UserInfo getUserInfoByAccount(String userAccount){
        return userInfoRepository.findUserInfoByUserAccount(userAccount);
    }

    public boolean userLogin(String  userAccount, String  userPassword){
        UserInfo userInfo = userInfoRepository.findById(userAccount).orElseThrow(
                () -> new IllegalStateException("userAccount:" + userAccount + "不存在")
        );
        if (  !Objects.equals(userInfo.getUserPassword(),encoder_md5.encodeMD5(userPassword))){
            throw new IllegalStateException("密碼錯誤");
        }else{
            return true;
        }
    }

    public void addUser(UserInfo userInfo){
        boolean existsAccount = userInfoRepository.existsById(userInfo.getUserAccount());
        boolean existsName = userInfoRepository.existsByUserName(userInfo.getUserName());
        if (existsAccount){
            throw new IllegalStateException("userAccount:" + userInfo.getUserAccount() + "已被使用");
        }else if (existsName){
            throw new IllegalStateException("userName:" + userInfo.getUserName() + "已被使用");
        }else if (userInfo.getUserName() == null || userInfo.getUserName().length() <= 0){
            throw new IllegalStateException("暱稱不得為空");
        } else if (userInfo.getUserPhoneNumber() == null || userInfo.getUserPhoneNumber().length() <= 0) {
            throw new IllegalStateException("手機不得為空");
        }else{
            //紀錄創建時間以及初始化最終修改時間
            Long datetime = System.currentTimeMillis();
            Timestamp timestamp = new Timestamp(datetime);
            userInfo.setCreatedTime(timestamp);
            userInfo.setUpdatedTime(timestamp);
            //MD5加密
            userInfo.setUserPassword(encoder_md5.encodeMD5(userInfo.getUserPassword()));
            userInfoRepository.save(userInfo);
        }

    }

    public void deletUser(String  userAccount,String userPassword){
        UserInfo userInfo = userInfoRepository.findById(userAccount).orElseThrow(
                () -> new IllegalStateException("userAccount:" + userAccount + "不存在")
        );

        if ( !Objects.equals(userInfo.getUserPassword(),encoder_md5.encodeMD5(userPassword))){
            throw new IllegalStateException("密碼錯誤");

        }else{
            userInfoRepository.deleteById(userAccount);
        }
    }

    @Transactional
    public void updatePassword(String userAccount,String originalUserPassword,String newUserPassword){
        UserInfo userInfo = userInfoRepository.findById(userAccount).orElseThrow(
                () -> new IllegalStateException("userAccount:" + userAccount + "不存在")
        );

        //新密碼不為空，不與先前密碼相同
        if ( !Objects.equals(userInfo.getUserPassword(),encoder_md5.encodeMD5(originalUserPassword))){
            throw new IllegalStateException("密碼錯誤");
        }else if (newUserPassword == null || newUserPassword.length() <= 0){
            throw new IllegalStateException("新密碼不得為空");
        }else if (Objects.equals(userInfo.getUserPassword(),newUserPassword)){
            throw new IllegalStateException("新密碼不得與舊密碼相同");
        }else {
            userInfo.setUserPassword(encoder_md5.encodeMD5(newUserPassword));
            //紀錄修改時間
            Long datetime = System.currentTimeMillis();
            Timestamp timestamp = new Timestamp(datetime);
            userInfo.setUpdatedTime(timestamp);
        }

    }
    @Transactional
    public void updateInfo(String userAccount, String userPassword,String userPhoneNumber){
        UserInfo userInfo = userInfoRepository.findById(userAccount).orElseThrow(
                () -> new IllegalStateException("userAccount:" + userAccount + "不存在")
        );

        if (userPhoneNumber == null || userPhoneNumber.length() <= 0) {
            throw new IllegalStateException("手機不得為空");
        }else if( !Objects.equals(userInfo.getUserPassword(),encoder_md5.encodeMD5(userPassword))){
            throw new IllegalStateException("密碼錯誤");
        }else{
            userInfo.setUserPhoneNumber(userPhoneNumber);
            //紀錄修改時間
            Long datetime = System.currentTimeMillis();
            Timestamp timestamp = new Timestamp(datetime);
            userInfo.setUpdatedTime(timestamp);
        }
    }



}
