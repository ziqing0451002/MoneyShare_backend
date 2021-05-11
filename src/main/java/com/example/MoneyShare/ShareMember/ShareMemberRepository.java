package com.example.MoneyShare.ShareMember;

import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

public interface ShareMemberRepository extends JpaRepository<ShareMember, BigInteger> {

    List<ShareMember> findShareMemberByShareListId(BigInteger shareListId);
    List<ShareMember> findShareMemberByShareListIdAndShareItemId(BigInteger shareListId,BigInteger shareItemId);

}