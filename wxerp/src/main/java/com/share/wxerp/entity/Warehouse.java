package com.share.wxerp.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Title: Warehouse
 * @ProjectName wxerp
 * @Description: TODO
 * @Author suguotai
 * @Date 2020/3/1821:53
 */
@Data
@TableName("erp_warehouse")
public class Warehouse {
    @TableId(value = "whid",type = IdType.INPUT)
    private String whid;//库存编号
    @TableField("type")
    private String type;//机型
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
    @TableField("warehouse_type")
    private String warehouseType;//库存类型（0进货库存、1销售库存）
    @TableField("_date")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private Date whDate;//日期（2020/3/3）
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
    @TableId("iwhid")
    private String iwhid;//入库编号.

    public String getWhid() {
        return whid;
    }

    public void setWhid(String whid) {
        this.whid = whid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getWarehouseType() {
        return warehouseType;
    }

    public void setWarehouseType(String warehouseType) {
        this.warehouseType = warehouseType;
    }

    public Date getWhDate() {
        return whDate;
    }

    public void setWhDate(Date whDate) {
        this.whDate = whDate;
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

    public String getIwhid() {
        return iwhid;
    }

    public void setIwhid(String iwhid) {
        this.iwhid = iwhid;
    }

    @Override
    public String toString() {
        return "Warehouse{" +
                "whid='" + whid + '\'' +
                ", type='" + type + '\'' +
                ", itemno='" + itemno + '\'' +
                ", source='" + source + '\'' +
                ", amount=" + amount +
                ", goodAmount=" + goodAmount +
                ", badAmount=" + badAmount +
                ", warehouseType='" + warehouseType + '\'' +
                ", whDate=" + whDate +
                ", createDate=" + createDate +
                ", username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", modiTime=" + modiTime +
                ", iwhid='" + iwhid + '\'' +
                '}';
    }
}
