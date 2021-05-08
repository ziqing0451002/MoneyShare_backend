package com.example.MoneyShare.ShareItem;

import com.example.MoneyShare.ShareMember.ShareMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface ShareItemRepository extends JpaRepository<ShareItem, BigInteger> {
}
