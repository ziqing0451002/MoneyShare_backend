package com.example.MoneyShare.ShareItem;

import com.example.MoneyShare.ShareMember.ShareMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;
import java.util.List;

public interface ShareItemRepository extends JpaRepository<ShareItem, BigInteger> {
    List<ShareItem> findShareItemByShareListId(BigInteger shareListId);
    ShareItem findShareItemByItemId(BigInteger shareItemId);
}
