package com.example.MoneyShare.ShareList;


import javax.persistence.*;

import java.math.BigInteger;
import java.sql.Timestamp;

// 宣告為實體(@Entity)
@Entity
// 對應資料庫表名稱
@Table(name = "share_list")
public class ShareList {
    @Id
    private BigInteger listId;
    private String listName;
    private String listCreater;
    private String listMember;
    private Timestamp createdTime;
    private Timestamp updatedTime;

    public ShareList() {

    }

    public ShareList(BigInteger listId, String listName, String listCreater, String listMember, Timestamp createdTime, Timestamp updatedTime) {
        this.listId = listId;
        this.listName = listName;
        this.listCreater = listCreater;
        this.listMember = listMember;
        this.createdTime = createdTime;
        this.updatedTime = updatedTime;
    }


    public BigInteger getListId() {
        return listId;
    }

    public void setListId(BigInteger listId) {
        this.listId = listId;
    }

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    public String getListCreater() {
        return listCreater;
    }

    public void setListCreater(String listCreater) {
        this.listCreater = listCreater;
    }

    public String getListMember() {
        return listMember;
    }

    public void setListMember(String listNumber) {
        this.listMember = listNumber;
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
