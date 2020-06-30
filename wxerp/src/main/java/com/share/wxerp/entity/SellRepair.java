package com.share.wxerp.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Title: SellRepair
 * @ProjectName wxerp
 * @Description: TODO
 * @Author suguotai
 * @Date 2020/3/1821:47
 */
@Data
@TableName("erp_sell_repair")
public class SellRepair {
    @TableId(value = "srid",type = IdType.INPUT)
    private String srid;//售后返修ID
    @TableField("itemno")
    private String itemno;//货号
    @TableField("product")
    private String product;//产品
    @TableField("result_problems")
    private String resultProblems;//存在问题
    @TableField("_date")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private Date srDate;//日期（2020/3/3）
    @TableField("create_date")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private Date createDate;//创建日期
    @TableId("asid")
    private String asid;//售后ID

    public String getSrid() {
        return srid;
    }

    public void setSrid(String srid) {
        this.srid = srid;
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

    public String getResultProblems() {
        return resultProblems;
    }

    public void setResultProblems(String resultProblems) {
        this.resultProblems = resultProblems;
    }

    public Date getSrDate() {
        return srDate;
    }

    public void setSrDate(Date srDate) {
        this.srDate = srDate;
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
        return "SellRepair{" +
                "srid='" + srid + '\'' +
                ", itemno='" + itemno + '\'' +
                ", product='" + product + '\'' +
                ", resultProblems='" + resultProblems + '\'' +
                ", srDate=" + srDate +
                ", createDate=" + createDate +
                ", asid='" + asid + '\'' +
                '}';
    }
}
