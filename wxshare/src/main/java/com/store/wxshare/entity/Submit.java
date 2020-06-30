package com.store.wxshare.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Author suguotai
 * @Description 记录资源提交基本信息
 * @Date
 * @Param
 **/
@Data
@TableName("share_submit")
public class Submit {
    private static final long serialVersionUID = 1L;
    /**
     * 提交资源id
     */
    @TableId(value = "submitid",type = IdType.INPUT)
    private String submitid;
    /**
     * 用户id
     */
    @TableField("userid")
    private String userid;
    /**
     * 资源名称
     */
    @TableField("ename")
    private String ename;
    /**
     *网盘地址
     */
    @TableField("address")
    private String address;
    /**
     *内容简介
     */
    @TableField("summary")
    private String summary;
    /**
     *图片路径
     */
    @TableField("picture_address")
    private String pictureAddress;
    /**
     *描述
     */
    @TableField("described")
    private String described;
    /**
     *审核 0待审核1已通过2未通过
     */
    @TableField("state")
    private String state;
    /**
     * 备注
     */
    @TableField("remark")
    private String remark;
    /**
     * 创建时间
     */
    @TableField("create_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createDate;
    /**
     * 修改时间
     */
    @TableField("modi_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date modiDate;

    @Override
    public String toString() {
        return "Submit{" +
                "submitid='" + submitid + '\'' +
                ", userid='" + userid + '\'' +
                ", ename='" + ename + '\'' +
                ", address='" + address + '\'' +
                ", summary='" + summary + '\'' +
                ", pictureAddress='" + pictureAddress + '\'' +
                ", described='" + described + '\'' +
                ", state='" + state + '\'' +
                ", remark=" + remark +
                ", createDate=" + createDate +
                ", modiDate=" + modiDate +
                '}';
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getSubmitid() {
        return submitid;
    }

    public void setSubmitid(String submitid) {
        this.submitid = submitid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getPictureAddress() {
        return pictureAddress;
    }

    public void setPictureAddress(String pictureAddress) {
        this.pictureAddress = pictureAddress;
    }

    public String getDescribed() {
        return described;
    }

    public void setDescribed(String described) {
        this.described = described;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String  getRemark() {
        return remark;
    }

    public void setRemark(String  remark) {
        this.remark = remark;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getModiDate() {
        return modiDate;
    }

    public void setModiDate(Date modiDate) {
        this.modiDate = modiDate;
    }
}
