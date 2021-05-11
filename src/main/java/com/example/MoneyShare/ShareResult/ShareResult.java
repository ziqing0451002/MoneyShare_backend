package com.example.MoneyShare.ShareResult;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigInteger;
import java.sql.Timestamp;

// 宣告為實體(@Entity)
@Entity
// 對應資料庫表名稱
@Table(name = "share_result")
public class ShareResult {
    @Id
    private BigInteger resultId;
    private String memberName;
    private String resultCreater;
    private BigInteger shareListId;
    private Integer shareMoney;
    private Timestamp createdTime;
    private Timestamp updatedTime;

    public ShareResult(BigInteger resultId, String resultCreater, String resultMember, Integer resultTotalCost, BigInteger resultContentId, String memberName, String resultCreater1, BigInteger shareListId, Integer shareMoney, Timestamp createdTime, Timestamp updatedTime) {
        this.resultId = resultId;
        this.memberName = memberName;
        this.resultCreater = resultCreater1;
        this.shareListId = shareListId;
        this.shareMoney = shareMoney;
        this.createdTime = createdTime;
        this.updatedTime = updatedTime;
    }

    public ShareResult() {

    }

    public BigInteger getResultId() {
        return resultId;
    }

    public void setResultId(BigInteger resultId) {
        this.resultId = resultId;
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

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public Integer getShareMoney() {
        return shareMoney;
    }

    public void setShareMoney(Integer shareMoney) {
        this.shareMoney = shareMoney;
    }

    public String getResultCreater() {
        return resultCreater;
    }

    public void setResultCreater(String resultCreater) {
        this.resultCreater = resultCreater;
    }
}
