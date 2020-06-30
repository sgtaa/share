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
 * @Description 记录网盘信息基本信息
 * @Date
 * @Param
 **/
@Data
@TableName("share_skydrive")
public class Skydrive {
    private static final long serialVersionUID = 1L;
    /**
     * 信息id
     */
    @TableId(value = "skydriveid",type = IdType.INPUT)
    private String skydriveid;
    /**
     * 资源ID
     */
    @TableField("resourceid")
    private String resourceid;
    /**
     * 用户id
     */
    @TableField("userid")
    private String userid;
    /**
     * 回复码
     */
    @TableField("code")
    private String code;
    /**
     *网盘地址
     */
    @TableField("address")
    private String address;
    /**
     *上传类型:1用户 2管理员
     */
    @TableField("type")
    private String type;
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
        return "Skydrive{" +
                "skydriveid='" + skydriveid + '\'' +
                ", resourceid='" + resourceid + '\'' +
                ", userid='" + userid + '\'' +
                ", code='" + code + '\'' +
                ", address='" + address + '\'' +
                ", type='" + type + '\'' +
                ", state='" + state + '\'' +
                ", remark=" + remark +
                ", createDate=" + createDate +
                ", modiDate=" + modiDate +
                '}';
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getSkydriveid() {
        return skydriveid;
    }

    public void setSkydriveid(String skydriveid) {
        this.skydriveid = skydriveid;
    }

    public String getResourceid() {
        return resourceid;
    }

    public void setResourceid(String resourceid) {
        this.resourceid = resourceid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
