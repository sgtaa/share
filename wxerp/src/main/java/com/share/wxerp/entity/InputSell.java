package com.share.wxerp.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Title: InputSell
 * @ProjectName wxerp
 * @Description: 销售入库
 * @Author suguotai
 * @Date 2020/3/1820:00
 */
@Data
@TableName("erp_input_sell")
public class InputSell {
    @TableId(value = "isid",type = IdType.INPUT)
    private String isid;//销售入库ID
    @TableField("itemno")
    private String itemno;//货号
    @TableField("product")
    private String product;//退回产品
    @TableField("part")
    private String part;//配件（0不齐全，1齐全）
    @TableField("colour")
    private String colour;//颜色
    @TableField("_date")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private Date intdate;//日期
    @TableField("create_date")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private Date createDate;//创建日期
    @TableId("asid")
    private String asid;//售后ID.

    public String getIsid() {
        return isid;
    }

    public void setIsid(String isid) {
        this.isid = isid;
    }

    public String getItemno() {
        return itemno;
    }

    public void setItemno(String itemno) {
        this.itemno = itemno;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getPart() {
        return part;
    }

    public void setPart(String part) {
        this.part = part;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public Date getIntdate() {
        return intdate;
    }

    public void setIntdate(Date intdate) {
        this.intdate = intdate;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getAsid() {
        return asid;
    }

    public void setAsid(String asid) {
        this.asid = asid;
    }

    @Override
    public String toString() {
        return "InputSell{" +
                "isid='" + isid + '\'' +
                ", itemno='" + itemno + '\'' +
                ", product='" + product + '\'' +
                ", part='" + part + '\'' +
                ", colour='" + colour + '\'' +
                ", intdate=" + intdate +
                ", createDate=" + createDate +
                ", asid='" + asid + '\'' +
                '}';
    }
}
