package com.example.MoneyShare.ShareItem;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigInteger;
import java.sql.Timestamp;


// 宣告為實體(@Entity)
@Entity
// 對應資料庫表名稱
@Table(name = "share_Item")

public class ShareItem {
    @Id
    private BigInteger itemId;
    private String itemName;
    private String itemCreater;
    private String itemMember;
    private Integer itemCost;
    private BigInteger shareListId;
    private Timestamp createdTime;
    private Timestamp updatedTime;

    public ShareItem(BigInteger itemId, String itemName, String itemCreater, String itemMember, Integer itemCost, BigInteger shareListId, Timestamp createdTime, Timestamp updatedTime) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.itemCreater = itemCreater;
        this.itemMember = itemMember;
        this.itemCost = itemCost;
        this.shareListId = shareListId;
        this.createdTime = createdTime;
        this.updatedTime = updatedTime;
    }

    public ShareItem() {

    }

    public BigInteger getItemId() {
        return itemId;
    }

    public void setItemId(BigInteger itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemCreater() {
        return itemCreater;
    }

    public void setItemCreater(String itemCreater) {
        this.itemCreater = itemCreater;
    }

    public String getItemMember() {
        return itemMember;
    }

    public void setItemMember(String itemMember) {
        this.itemMember = itemMember;
    }

    public Integer getItemCost() {
        return itemCost;
    }

    public void setItemCost(Integer itemCost) {
        this.itemCost = itemCost;
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
