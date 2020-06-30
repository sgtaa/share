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
 * @Description 记录资源基本信息
 * @Date
 * @Param
 * @return
 **/
@Data
@TableName("share_resource")
public class Resource {
    private static final long serialVersionUID = 1L;
    /**
     * 资源id
     */
    @TableId(value = "resourceid",type = IdType.INPUT)
    private String resourceid;
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
     * 提交id
     */
    @TableField("submitid")
    private String submitid;
    /**
     * 资源名称
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
     *图片路径PICTURE_ADDRESS
     */
    @TableField("picture_address")
    private String pictureAddress;
    /**
     *描述
     */
    @TableField("described")
    private String described;
    /**
     *状态
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
    /**
     * 查看人数
     */
    @TableField("look_people")
    private Integer lookPeople;
    /**
     * 查看人数
     */
    @TableField("download_people")
    private Integer downloadPeople;

    @Override
    public String toString() {
        return "Resource{" +
                "resourceid='" + resourceid + '\'' +
                ", classid='" + classid + '\'' +
                ", userid='" + userid + '\'' +
                ", submitid='" + submitid + '\'' +
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
                ", lookPeople=" + lookPeople +
                ", downloadPeople=" + downloadPeople +
                '}';
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getResourceid() {
        return resourceid;
    }

    public void setResourceid(String resourceid) {
        this.resourceid = resourceid;
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

    public String getSubmitid() {
        return submitid;
    }

    public void setSubmitid(String submitid) {
        this.submitid = submitid;
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

    public Integer getLookPeople() {
        return lookPeople;
    }

    public void setLookPeople(Integer lookPeople) {
        this.lookPeople = lookPeople;
    }

    public Integer getDownloadPeople() {
        return downloadPeople;
    }

    public void setDownloadPeople(Integer downloadPeople) {
        this.downloadPeople = downloadPeople;
    }
}
