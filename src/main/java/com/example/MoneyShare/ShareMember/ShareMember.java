package com.example.MoneyShare.ShareMember;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigInteger;
import java.sql.Timestamp;

// 宣告為實體(@Entity)
@Entity
// 對應資料庫表名稱
@Table(name = "share_member")
public class ShareMember {

    @Id
    private BigInteger memberId;
    private String memberName;
    private BigInteger shareListId;
    private Timestamp createdTime;
    private Timestamp updatedTime;

    public ShareMember(BigInteger memberId, String memberName, BigInteger shareListId, Timestamp createdTime, Timestamp updatedTime) {
        this.memberId = memberId;
        this.memberName = memberName;
        this.shareListId = shareListId;
        this.createdTime = createdTime;
        this.updatedTime = updatedTime;
    }

    public ShareMember() {

    }

    public BigInteger getMemberId() {
        return memberId;
    }

    public void setMemberId(BigInteger memberId) {
        this.memberId = memberId;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public BigInteger getShareListId() {
        return shareListId;
    }

    public void setShareListId(BigInteger shareListId) {
        this.shareListId = shareListId;
    }

    public Timestamp getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Timestamp createdTime) {
        this.createdTime = createdTime;
    }

    public Timestamp getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Timestamp updatedTime) {
        this.updatedTime = updatedTime;
    }
}
