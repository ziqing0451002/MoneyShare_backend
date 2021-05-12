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
    private BigInteger shareItemId;
    private String shareItemName;
    private String listCreater;
    private Integer shareMoney;
    private Integer sharePayBefore;
    private Timestamp createdTime;
    private Timestamp updatedTime;

    public ShareMember(BigInteger memberId, String memberName, BigInteger shareListId, BigInteger shareItemId, String shareItemName, String listCreater, Integer shareMoney, Integer sharePayBefore, Timestamp createdTime, Timestamp updatedTime) {
        this.memberId = memberId;
        this.memberName = memberName;
        this.shareListId = shareListId;
        this.shareItemId = shareItemId;
        this.shareItemName = shareItemName;
        this.listCreater = listCreater;
        this.shareMoney = shareMoney;
        this.sharePayBefore = sharePayBefore;
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

    public BigInteger getShareItemId() {
        return shareItemId;
    }

    public void setShareItemId(BigInteger shareItemId) {
        this.shareItemId = shareItemId;
    }

    public String getShareItemName() {
        return shareItemName;
    }

    public void setShareItemName(String shareItemName) {
        this.shareItemName = shareItemName;
    }

    public Integer getShareMoney() {
        return shareMoney;
    }

    public void setShareMoney(Integer shareMoney) {
        this.shareMoney = shareMoney;
    }

    public Integer getSharePayBefore() {
        return sharePayBefore;
    }

    public void setSharePayBefore(Integer sharePayBefore) {
        this.sharePayBefore = sharePayBefore;
    }

    public String getListCreater() {
        return listCreater;
    }

    public void setListCreater(String listCreater) {
        this.listCreater = listCreater;
    }
}
