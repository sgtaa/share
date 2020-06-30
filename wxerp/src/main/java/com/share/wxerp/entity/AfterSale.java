package com.share.wxerp.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Title: AfterSale
 * @ProjectName wxerp
 * @Description: 售后
 * @Author suguotai
 * @Date 2020/3/1818:23
 */
@Data
@TableName("erp_after_sale")
public class AfterSale {
    @TableId(value = "asid",type = IdType.INPUT)
    private String asid;//售后ID.
    @TableField("type")
    private String type;//机型.
    @TableField("itemno")
    private String itemno;//货号
    @TableField("product")
    private String product;//退回产品
    @TableField("sender")
    private String sender;//寄件人
    @TableField("odd")
    private String odd;//单号
    @TableField("_products")
    private String products;//商品详情(配件详情)
    @TableField("amount")
    private int amount;//数量
    @TableField("part")
    private String part;//配件（0不齐全，1齐全）
    @TableField("parts")
    private String parts;//缺少的配件
    @TableField("colour")
    private String colour;//颜色
    @TableField("problems")
    private String problems;//问题归纳（客户反馈）
    @TableField("phone_problems")
    private String phoneProblems;//手机问题（客户反馈）
    @TableField("result_problems")
    private String resultProblems;//存在的问题
    @TableField("result")
    private String result;//检测结果（0相符，1没问题，2其它问题）
    @TableField("after_date")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private Date afterDate;//日期
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
    @TableId(value = "sid")
    private String sid;//销售id

    public String getAsid() {
        return asid;
    }

    public void setAsid(String asid) {
        this.asid = asid;
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

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getOdd() {
        return odd;
    }

    public void setOdd(String odd) {
        this.odd = odd;
    }

    public String getProducts() {
        return products;
    }

    public void setProducts(String products) {
        this.products = products;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getPart() {
        return part;
    }

    public void setPart(String part) {
        this.part = part;
    }

    public String getParts() {
        return parts;
    }

    public void setParts(String parts) {
        this.parts = parts;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public String getProblems() {
        return problems;
    }

    public void setProblems(String problems) {
        this.problems = problems;
    }

    public String getPhoneProblems() {
        return phoneProblems;
    }

    public void setPhoneProblems(String phoneProblems) {
        this.phoneProblems = phoneProblems;
    }

    public String getResultProblems() {
        return resultProblems;
    }

    public void setResultProblems(String resultProblems) {
        this.resultProblems = resultProblems;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Date getAfterDate() {
        return afterDate;
    }

    public void setAfterDate(Date afterDate) {
        this.afterDate = afterDate;
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

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    @Override
    public String toString() {
        return "AfterSale{" +
                "asid='" + asid + '\'' +
                ", type='" + type + '\'' +
                ", itemno='" + itemno + '\'' +
                ", product='" + product + '\'' +
                ", sender='" + sender + '\'' +
                ", odd='" + odd + '\'' +
                ", products='" + products + '\'' +
                ", amount=" + amount +
                ", part='" + part + '\'' +
                ", parts='" + parts + '\'' +
                ", colour='" + colour + '\'' +
                ", problems='" + problems + '\'' +
                ", phoneProblems='" + phoneProblems + '\'' +
                ", resultProblems='" + resultProblems + '\'' +
                ", result='" + result + '\'' +
                ", afterDate=" + afterDate +
                ", createDate=" + createDate +
                ", username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", modiTime=" + modiTime +
                ", sid='" + sid + '\'' +
                '}';
    }
}
