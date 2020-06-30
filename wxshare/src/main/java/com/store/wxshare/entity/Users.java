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
 * @Description 记录用户基本信息
 * @Date 15:14$ 2019-12-2$
 * @Param $
 **/
@Data
@TableName("share_user")
public class Users {
    private static final long serialVersionUID = 1L;
    /**
     * userid
     */
    @TableId(value = "userid",type = IdType.INPUT)
    private String userId;
    /**
     * open_id
     */
    @TableField("openId")
    private String openId;
    /**
     * 微信skey
     */
    @TableField("skey")
    private String skey;
    /**
     * 微信名称
     */
    @TableField("nick_name")
    private String nickName;
    /**
     * 用户名称
     */
    @TableField("ename")
    private String ename;
    /**
     * 用户密码
     */
    @TableField("password")
    private String password;
    /**
     * 性别
     */
    @TableField("gender")
    private Integer gender;
    /**
     * 年龄
     */
    @TableField("age")
    private Integer age;
    /**
     * 手机号
     */
    @TableField("mobile")
    private String mobile;
    /**
     * 邮箱
     */
    @TableField("email")
    private String email;
    /**
     * 状态
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
    /**
     * 最后登录时间
     */
    @TableField("last_visit_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date lastVisitTime;
    /**
     * session_key
     */
    @TableField("session_key")
    private String sessionKey;
    /**
     * 头像
     */
    @TableField("avatar_url")
    private String avatarUrl;
    /**
     * 地址
     */
    @TableField("address")
    private String address;
    /**
     * 详细地址
     */
    @TableField("detailed_address")
    private String detailedAddress;
    /**
     * 用户级别
     */
    @TableField("userlvl")
    private String userlvl;

    @TableField(exist = false)
    private Integral integral;

    public Integral getIntegral() {
        return integral;
    }

    public void setIntegral(Integral integral) {
        this.integral = integral;
    }
    @Override
    public String toString() {
        return "Users{" +
                "userId='" + userId + '\'' +
                ", openId='" + openId + '\'' +
                ", skey='" + skey + '\'' +
                ", nickName='" + nickName + '\'' +
                ", ename='" + ename + '\'' +
                ", password='" + password + '\'' +
                ", gender=" + gender +
                ", age=" + age +
                ", mobile=" + mobile +
                ", email=" + email +
                ", state=" + state +
                ", remark=" + remark +
                ", createDate=" + createDate +
                ", modiDate=" + modiDate +
                ", lastVisitTime=" + lastVisitTime +
                ", sessionKey='" + sessionKey + '\'' +
                ", avatarUrl='" + avatarUrl + '\'' +
                ", address='" + address + '\'' +
                ", detailedAddress='" + detailedAddress + '\'' +
                ", userlvl='" + userlvl + '\'' +
                '}';
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getSkey() {
        return skey;
    }

    public void setSkey(String skey) {
        this.skey = skey;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
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

    public Date getLastVisitTime() {
        return lastVisitTime;
    }

    public void setLastVisitTime(Date lastVisitTime) {
        this.lastVisitTime = lastVisitTime;
    }

    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDetailedAddress() {
        return detailedAddress;
    }

    public void setDetailedAddress(String detailedAddress) {
        this.detailedAddress = detailedAddress;
    }

    public String getUserlvl() {
        return userlvl;
    }

    public void setUserlvl(String userlvl) {
        this.userlvl = userlvl;
    }
}
