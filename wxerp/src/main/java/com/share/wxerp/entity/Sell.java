package com.share.wxerp.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Title: Sell
 * @ProjectName wxerp
 * @Description: TODO
 * @Author suguotai
 * @Date 2020/3/1821:30
 */
@Data
@TableName("erp_sell")
public class Sell {
    @TableId(value = "sid",type = IdType.INPUT)
    private String sid;//销售编号
    @TableField("type")
    private String type;//机型
    @TableField("colour")
    private String colour;//颜色
    @TableField("itemno")
    private String itemno;//货号
    @TableField("product")
    private String product;//产品
    @TableField("amount")
    private int amount;//数量
    @TableField("sell_price")
    private int sellPrice;//售价
    @TableField("wholesale")
    private int wholesale;//批发价
    @TableField("profit")
    private int profit;//毛利
    @TableField("addressee")
    private String addressee;//收件人
    @TableField("freight")
    private int freight;//运费
    @TableField("express")
    private String express;//快递公司
    @TableField("details")
    private String details;//寄送详情（包含收件人电话和地址）
    @TableField("_products")
    private String products;//商品详情(配件详情)
    @TableField("parts")
    private int parts;//配件数
    @TableField("platform")
    private String platform;//销售平台
    @TableField("saler")
    private String saler;//销售员
    @TableField("account")
    private String account;//销
    @TableField("send_date")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private Date sendDate;//发货日期（2020/3/3）
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
    @TableId("recid")
    private String recid;//进货ID.
    @TableId("whid")
    private String whid;//库存编号.

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
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

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(int sellPrice) {
        this.sellPrice = sellPrice;
    }

    public int getWholesale() {
        return wholesale;
    }

    public void setWholesale(int wholesale) {
        this.wholesale = wholesale;
    }

    public int getProfit() {
        return profit;
    }

    public void setProfit(int profit) {
        this.profit = profit;
    }

    public String getAddressee() {
        return addressee;
    }

    public void setAddressee(String addressee) {
        this.addressee = addressee;
    }

    public int getFreight() {
        return freight;
    }

    public void setFreight(int freight) {
        this.freight = freight;
    }

    public String getExpress() {
        return express;
    }

    public void setExpress(String express) {
        this.express = express;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getProducts() {
        return products;
    }

    public void setProducts(String products) {
        this.products = products;
    }

    public int getParts() {
        return parts;
    }

    public void setParts(int parts) {
        this.parts = parts;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getSaler() {
        return saler;
    }

    public void setSaler(String saler) {
        this.saler = saler;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public Date getSendDate() {
        return sendDate;
    }

    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
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

    public String getRecid() {
        return recid;
    }

    public void setRecid(String recid) {
        this.recid = recid;
    }

    public String getWhid() {
        return whid;
    }

    public void setWhid(String whid) {
        this.whid = whid;
    }

    @Override
    public String toString() {
        return "Sell{" +
                "sid='" + sid + '\'' +
                ", type='" + type + '\'' +
                ", colour='" + colour + '\'' +
                ", itemno='" + itemno + '\'' +
                ", product='" + product + '\'' +
                ", amount=" + amount +
                ", sellPrice=" + sellPrice +
                ", wholesale=" + wholesale +
                ", profit=" + profit +
                ", addressee='" + addressee + '\'' +
                ", freight=" + freight +
                ", express='" + express + '\'' +
                ", details='" + details + '\'' +
                ", products='" + products + '\'' +
                ", parts=" + parts +
                ", platform='" + platform + '\'' +
                ", saler='" + saler + '\'' +
                ", account='" + account + '\'' +
                ", sendDate=" + sendDate +
                ", createDate=" + createDate +
                ", username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", modiTime=" + modiTime +
                ", recid='" + recid + '\'' +
                ", whid='" + whid + '\'' +
                '}';
    }
}
