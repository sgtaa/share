package com.share.wxerp.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Title: ReturnRepair
 * @ProjectName wxerp
 * @Description: 返修
 * @Author suguotai
 * @Date 2020/3/1820:43
 */
@Data
@TableName("erp_return_repair")
public class ReturnRepair {
    @TableId(value = "rrid",type = IdType.INPUT)
    private String rrid;//返修ID
    @TableField("type")
    private String type;//机型
    @TableField("itemno")
    private String itemno;//货号
    @TableField("amount")
    private int amount;//数量
    @TableField("addressee")
    private String addressee;//收件人
    @TableField("packard")
    private String packard;//打包人
    @TableField("express")
    private String express;//快递公司
    @TableField("number")
    private String number;//快递编号
    @TableField("return_case")
    private String returnCase;//寄回情况（0待寄回,1已寄回,2寄回失败）
    @TableField("return_type")
    private String returnType;//返修类型（0入库返修、1销售返修）
    @TableField("status")
    private String status;//返修状态（0待返修、1已返修）
    @TableField("return_date")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private Date returnDate;//返修日期（2020/3/3）
    @TableField("create_date")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private Date createDate;//创建日期
    @TableField("username")
    private String username;//修改人的账号
    @TableField("name")
    private String name;//修改人的名称
    @TableField("modi_date")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private Date modiDate;//修改的日期
    @TableId("recid")
    private String recid;//进货ID.
    @TableId("srid")
    private String srid;//售后ID.

    public String getRrid() {
        return rrid;
    }

    public void setRrid(String rrid) {
        this.rrid = rrid;
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

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getAddressee() {
        return addressee;
    }

    public void setAddressee(String addressee) {
        this.addressee = addressee;
    }

    public String getPackard() {
        return packard;
    }

    public void setPackard(String packard) {
        this.packard = packard;
    }

    public String getExpress() {
        return express;
    }

    public void setExpress(String express) {
        this.express = express;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getReturnCase() {
        return returnCase;
    }

    public void setReturnCase(String returnCase) {
        this.returnCase = returnCase;
    }

    public String getReturnType() {
        return returnType;
    }

    public void setReturnType(String returnType) {
        this.returnType = returnType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
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

    public Date getModiDate() {
        return modiDate;
    }

    public void setModiDate(Date modiDate) {
        this.modiDate = modiDate;
    }

    public String getRecid() {
        return recid;
    }

    public void setRecid(String recid) {
        this.recid = recid;
    }

    public String getSrid() {
        return srid;
    }

    public void setSrid(String srid) {
        this.srid = srid;
    }

    @Override
    public String toString() {
        return "ReturnRepair{" +
                "rrid='" + rrid + '\'' +
                ", type='" + type + '\'' +
                ", itemno='" + itemno + '\'' +
                ", amount=" + amount +
                ", addressee='" + addressee + '\'' +
                ", packard='" + packard + '\'' +
                ", express='" + express + '\'' +
                ", number='" + number + '\'' +
                ", returnCase='" + returnCase + '\'' +
                ", returnType='" + returnType + '\'' +
                ", status='" + status + '\'' +
                ", returnDate=" + returnDate +
                ", createDate=" + createDate +
                ", username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", modiDate=" + modiDate +
                ", recid='" + recid + '\'' +
                ", srid='" + srid + '\'' +
                '}';
    }
}
