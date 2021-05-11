package com.example.MoneyShare.ShareResult;

import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;
import java.util.List;

public interface ShareResultRepository  extends JpaRepository<ShareResult, BigInteger> {
    List<ShareResult> findShareResultByShareListId(BigInteger shareListId);

}
