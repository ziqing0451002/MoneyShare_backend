package com.example.MoneyShare.ShareMember;

import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface ShareMemberRepository extends JpaRepository<ShareMember, BigInteger> {
}
