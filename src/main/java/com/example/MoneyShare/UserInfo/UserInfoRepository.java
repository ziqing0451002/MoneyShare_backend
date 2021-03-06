package com.example.MoneyShare.UserInfo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, String> {
    UserInfo findUserInfoByUserAccount(String userAccount);
    boolean existsByUserName(String userName);
}
