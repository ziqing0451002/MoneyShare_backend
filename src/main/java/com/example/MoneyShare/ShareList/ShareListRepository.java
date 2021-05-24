package com.example.MoneyShare.ShareList;

import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface ShareListRepository extends JpaRepository<ShareList,BigInteger> {
    ShareList findShareListByListId(BigInteger shareListId);

}
