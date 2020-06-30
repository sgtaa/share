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
 * @Description 记录用户积分基本信息
 * @Date
 * @Param
 **/
@Data
@TableName("share_integral")
public class Integral {
    private static final long serialVersionUID = 1L;
    /**
     * 积分idINTEGERID integerid
     */
    @TableId(value = "integerid",type = IdType.INPUT)
    private String integrid;
    /**
     * 用户id
     */
    @TableField("userid")
    private String userid;
    /**
     * 积分
     */
    @TableField("grade")
    private Integer grade;
    /**
     *状态 0:正常  1:未启用
     */
    @TableField("state")
    private String state;
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
     * 签到日期
     */
    @TableField("register_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date registerDate;

    @Override
    public String toString() {
        return "Integral{" +
                "integrid='" + integrid + '\'' +
                ", userid='" + userid + '\'' +
                ", grade=" + grade +
                ", state='" + state + '\'' +
                ", createDate=" + createDate +
                ", modiDate=" + modiDate +
                ", registerDate=" + registerDate +
                '}';
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getIntegrid() {
        return integrid;
    }

    public void setIntegrid(String integrid) {
        this.integrid = integrid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
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

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }
}
