package com.share.wxerp.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Title: InputWarehouse
 * @ProjectName wxerp
 * @Description: 进货入库
 * @Author suguotai
 * @Date 2020/3/1820:08
 */
@Data
@TableName("erp_input_warehouse")
public class InputWarehouse {
    @TableId(value = "iwhid",type = IdType.INPUT)
    private String iwhid;//进货入库ID
    @TableField("type")
    private String type;//机型
    @TableField("colour")
    private String colour;//颜色
    @TableField("itemno")
    private String itemno;//货号
    @TableField("amount")
    private int amount;//数量
    @TableField("input_type")
    private String inputType;//入库类型（0进货入库、1销售入库）
    @TableField("input_date")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private Date inputDate;//日期
    @TableField("create_date")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private Date createDate;//创建日期
    @TableId("recid")
    private String recid;//进货ID
    @TableId("asid")
    private String asid;//售后ID.

    public String getIwhid() {
        return iwhid;
    }

    public void setIwhid(String iwhid) {
        this.iwhid = iwhid;
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

    public String getInputType() {
        return inputType;
    }

    public void setInputType(String inputType) {
        this.inputType = inputType;
    }

    public Date getInputDate() {
        return inputDate;
    }

    public void setInputDate(Date inputDate) {
        this.inputDate = inputDate;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getRecid() {
        return recid;
    }

    public void setRecid(String recid) {
        this.recid = recid;
    }

    public String getAsid() {
        return asid;
    }

    public void setAsid(String asid) {
        this.asid = asid;
    }

    @Override
    public String toString() {
        return "InputWarehouse{" +
                "iwhid='" + iwhid + '\'' +
                ", type='" + type + '\'' +
                ", colour='" + colour + '\'' +
                ", itemno='" + itemno + '\'' +
                ", amount=" + amount +
                ", inputType='" + inputType + '\'' +
                ", inputDate=" + inputDate +
                ", createDate=" + createDate +
                ", recid='" + recid + '\'' +
                ", asid='" + asid + '\'' +
                '}';
    }
}
