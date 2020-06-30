package com.share.wxerp.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Title: Product
 * @ProjectName wxerp
 * @Description: TODO
 * @Author suguotai
 * @Date 2020/3/1820:26
 */
@Data
@TableName("erp_product")
public class Product {
    @TableId(value = "prodid",type = IdType.INPUT)
    private String prodid;//商品ID
    @TableField("type")
    private String type;//机型
    @TableField("itemno")
    private String itemno;//货号
    @TableField("source")
    private String source;//货源情况
    @TableField("amount")
    private int amount;//数量
    @TableField("cost")
    private int cost;//成本价
    @TableField("sales")
    private int sales;//分销价
    @TableField("retail")
    private int retail;//零售价
    @TableField("wholesale")
    private int wholesale;//批发价
    @TableField("create_date")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private Date createDate;//创建日期
    @TableId("recid")
    private String recid;//进货ID

    public String getProdid() {
        return prodid;
    }

    public void setProdid(String prodid) {
        this.prodid = prodid;
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

    @Override
    public String toString() {
        return "Product{" +
                "prodid='" + prodid + '\'' +
                ", type='" + type + '\'' +
                ", itemno='" + itemno + '\'' +
                ", source='" + source + '\'' +
                ", amount=" + amount +
                ", cost=" + cost +
                ", sales=" + sales +
                ", retail=" + retail +
                ", wholesale=" + wholesale +
                ", createDate=" + createDate +
                ", recid='" + recid + '\'' +
                '}';
    }
}
