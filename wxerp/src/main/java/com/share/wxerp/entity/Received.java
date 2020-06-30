package com.share.wxerp.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Title: Received
 * @ProjectName wxerp
 * @Description: 进货
 * @Author suguotai
 * @Date 2020/3/1820:34
 */
@Data
@TableName("erp_received")
public class Received {
    @TableId(value = "recid",type = IdType.INPUT)
    private String recid;//进货编号
    @TableField("type")
    private String type;//机型
    @TableField("colour")
    private String colour;//颜色
    @TableField("itemno")
    private String itemno;//货号
    @TableField("source")
    private String source;//货源情况
    @TableField("amount")
    private int amount;//数量
    @TableField("good_amount")
    private int goodAmount;//好机数量
    @TableField("bad_amount")
    private int badAmount;//坏机数量
    @TableField("principle")
    private String principle;//清点员
    @TableField("inspector")
    private String inspector;//验货员
    @TableField("cost")
    private int cost;//成本价
    @TableField("sales")
    private int sales;//分销价
    @TableField("retail")
    private int retail;//零售价
    @TableField("wholesale")
    private int wholesale;//批发价
    @TableField("received_date")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private Date receivedDate;//进货日期（2020/3/3）
    @TableField("create_date")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private Date createDate;//创建日期
    @TableField("username")
    private String username;//修改人的账号
    @TableField("name")
    private String name;//修改人的名称
    @TableField("modi_date")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private Date modiTime;//修改的日期

    public String getRecid() {
        return recid;
    }

    public void setRecid(String recid) {
        this.recid = recid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public String getItemno() {
        return itemno;
    }

    public void setItemno(String itemno) {
        this.itemno = itemno;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getGoodAmount() {
        return goodAmount;
    }

    public void setGoodAmount(int goodAmount) {
        this.goodAmount = goodAmount;
    }

    public int getBadAmount() {
        return badAmount;
    }

    public void setBadAmount(int badAmount) {
        this.badAmount = badAmount;
    }

    public String getPrinciple() {
        return principle;
    }

    public void setPrinciple(String principle) {
        this.principle = principle;
    }

    public String getInspector() {
        return inspector;
    }

    public void setInspector(String inspector) {
        this.inspector = inspector;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getSales() {
        return sales;
    }

    public void setSales(int sales) {
        this.sales = sales;
    }

    public int getRetail() {
        return retail;
    }

    public void setRetail(int retail) {
        this.retail = retail;
    }

    public int getWholesale() {
        return wholesale;
    }

    public void setWholesale(int wholesale) {
        this.wholesale = wholesale;
    }

    public Date getReceivedDate() {
        return receivedDate;
    }

    public void setReceivedDate(Date receivedDate) {
        this.receivedDate = receivedDate;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getModiTime() {
        return modiTime;
    }

    public void setModiTime(Date modiTime) {
        this.modiTime = modiTime;
    }

    @Override
    public String toString() {
        return "Received{" +
                "recid='" + recid + '\'' +
                ", type='" + type + '\'' +
                ", colour='" + colour + '\'' +
                ", itemno='" + itemno + '\'' +
                ", source='" + source + '\'' +
                ", amount=" + amount +
                ", goodAmount=" + goodAmount +
                ", badAmount=" + badAmount +
                ", principle='" + principle + '\'' +
                ", inspector='" + inspector + '\'' +
                ", cost=" + cost +
                ", sales=" + sales +
                ", retail=" + retail +
                ", wholesale=" + wholesale +
                ", receivedDate=" + receivedDate +
                ", createDate=" + createDate +
                ", username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", modiTime=" + modiTime +
                '}';
    }
}
