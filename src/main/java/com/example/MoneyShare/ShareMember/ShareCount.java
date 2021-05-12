package com.example.MoneyShare.ShareMember;

import java.math.BigInteger;

public class ShareCount {
    private String memberName;
    private String countCreater;
    private BigInteger shareListId;
    private Integer payTotal;
    private Integer shareTotal;
    private Integer resultTotal;

    public ShareCount() {
        this.memberName = memberName;
        this.countCreater = countCreater;
        this.shareListId = shareListId;
        this.payTotal = payTotal;
        this.shareTotal = shareTotal;
        this.resultTotal = resultTotal;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getCountCreater() {
        return countCreater;
    }

    public void setCountCreater(String countCreater) {
        this.countCreater = countCreater;
    }

    public BigInteger getShareListId() {
        return shareListId;
    }

    public void setShareListId(BigInteger shareListId) {
        this.shareListId = shareListId;
    }

    public Integer getPayTotal() {
        return payTotal;
    }

    public void setPayTotal(Integer payTotal) {
        this.payTotal = payTotal;
    }

    public Integer getShareTotal() {
        return shareTotal;
    }

    public void setShareTotal(Integer shareTotal) {
        this.shareTotal = shareTotal;
    }

    public Integer getResultTotal() {
        return resultTotal;
    }

    public void setResultTotal(Integer resultTotal) {
        this.resultTotal = resultTotal;
    }
}
