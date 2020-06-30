package com.share.wxerp.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Title: OutputWarehouse
 * @ProjectName wxerp
 * @Description: TODO
 * @Author suguotai
 * @Date 2020/3/1820:18
 */
@Data
@TableName("erp_output_warehouse")
public class OutputWarehouse {
    @TableId(value = "opid",type = IdType.INPUT)
    private String iwid;//出库ID
    @TableField("type")
    private String type;//机型
    @TableField("colour")
    private String colour;//颜色
    @TableField("itemno")
    private String itemno;//货号
    @TableField("amount")
    private int amount;//数量
    @TableField("output_type")
    private String outputType;//出库类型（0返修出库、1销售出库）
    @TableField("output_date")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private Date outputDate;//出库日期（2020/3/3）
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
    @TableId("whid")
    private String whid;//库存ID.

    public String getIwid() {
        return iwid;
    }

    public void setIwid(String iwid) {
        this.iwid = iwid;
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

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getOutputType() {
        return outputType;
    }

    public void setOutputType(String outputType) {
        this.outputType = outputType;
    }

    public Date getOutputDate() {
        return outputDate;
    }

    public void setOutputDate(Date outputDate) {
        this.outputDate = outputDate;
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

    public String getWhid() {
        return whid;
    }

    public void setWhid(String whid) {
        this.whid = whid;
    }

    @Override
    public String toString() {
        return "OutputWarehouse{" +
                "iwid='" + iwid + '\'' +
                ", type='" + type + '\'' +
                ", colour='" + colour + '\'' +
                ", itemno='" + itemno + '\'' +
                ", amount=" + amount +
                ", outputType='" + outputType + '\'' +
                ", outputDate=" + outputDate +
                ", createDate=" + createDate +
                ", username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", modiTime=" + modiTime +
                ", whid='" + whid + '\'' +
                '}';
    }
}
