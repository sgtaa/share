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
 * @Description 记录分类基本信息
 * @Date
 * @Param
 **/
@Data
@TableName("share_classify")
public class Classify {
    private static final long serialVersionUID = 1L;
    /**
     * 分类id
     */
    @TableId(value = "classid",type = IdType.INPUT)
    private String classid;
    /**
     * userid
     */
    @TableField("userid")
    private String userid;
    /**
     * 分类名称
     */
    @TableField("classname")
    private String className;
    /**
     * 分类编号
     */
    @TableField("classcode")
    private String classCode;
    /**
     * 父ID
     */
    @TableField("parent")
    private String parent;
    /**
     * 最左子类ID(或第一个子类)
     */
    @TableField("left_child")
    private Integer leftChild;
    /**
     * 右兄弟ID
     */
    @TableField("right_sibling")
    private Integer rightSibling;
    /**
     * 层级(第一级类别1，第二级类别2，第三级类别3)
     */
    @TableField("layer")
    private Integer layer;

    /**
     * 排序号
     */
    @TableField("rank")
    private Integer rank;
    /**
     * 状态
     */
    @TableField("state")
    private Integer state;
    /**
     * 创建日期
     */
    @TableField("create_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createDate;
    /**
     * 修改日期
     */
    @TableField("modi_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date modiDate;

    @Override
    public String toString() {
        return "Classify{" +
                "classid='" + classid + '\'' +
                ", userid='" + userid + '\'' +
                ", className='" + className + '\'' +
                ", classCode='" + classCode + '\'' +
                ", parent='" + parent + '\'' +
                ", leftChild=" + leftChild +
                ", rightSibling=" + rightSibling +
                ", layer=" + layer +
                ", rank=" + rank +
                ", state=" + state +
                ", createDate=" + createDate +
                ", modiDate=" + modiDate +
                '}';
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
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

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getClassCode() {
        return classCode;
    }

    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public Integer getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(Integer leftChild) {
        this.leftChild = leftChild;
    }

    public Integer getRightSibling() {
        return rightSibling;
    }

    public void setRightSibling(Integer rightSibling) {
        this.rightSibling = rightSibling;
    }

    public Integer getLayer() {
        return layer;
    }

    public void setLayer(Integer layer) {
        this.layer = layer;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
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
}
