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
 * @Description 记录用户资源兑换基本信息
 * @Date
 * @Param
 **/
@Data
@TableName("share_convert")
public class Convert {
    private static final long serialVersionUID = 1L;
    /**
     * 兑换资源id
     */
    @TableId(value = "convertid",type = IdType.INPUT)
    private String convertid;
    /**
     * 分类id
     */
    @TableField("classid")
    private String classid;
    /**
     * 用户id
     */
    @TableField("userid")
    private String userid;

    /**
     * 用户id
     */
    @TableField("resourceid")
    private String resourceid;
    /**
     * 资源名称resourceid
     */
    @TableField("ename")
    private String ename;
    /**
     * 积分
     */
    @TableField("integral")
    private Integer integral;
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
     *状态 0:正常  1:未启用
     */
    @TableField("state")
    private String state;
    /**
     * 备注
     */
    @TableField("remark")
    private Integer remark;
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
        return "Convert{" +
                "convertid='" + convertid + '\'' +
                ", classid='" + classid + '\'' +
                ", userid='" + userid + '\'' +
                ", resourceid='" + resourceid + '\'' +
                ", ename='" + ename + '\'' +
                ", integral=" + integral +
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

    public String getResourceid() {
        return resourceid;
    }

    public void setResourceid(String resourceid) {
        this.resourceid = resourceid;
    }
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getConvertid() {
        return convertid;
    }

    public void setConvertid(String convertid) {
        this.convertid = convertid;
    }

    public String getClassid() {
        return classid;
    }

    public void setClassid(String classid) {
        this.classid = classid;
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

    public Integer getIntegral() {
        return integral;
    }

    public void setIntegral(Integer integral) {
        this.integral = integral;
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

    public Integer getRemark() {
        return remark;
    }

    public void setRemark(Integer remark) {
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
