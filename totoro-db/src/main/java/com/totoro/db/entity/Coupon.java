package com.totoro.db.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 *
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table coupon
 */
public class Coupon implements Serializable {
    /**
     * Database Column Remarks:
     *   id

     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column coupon.id
     *
     * @mbg.generated
     */
    private Long id;

    /**
     * Database Column Remarks:
     *   优惠券名称
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column coupon.name
     *
     * @mbg.generated
     */
    private String name;

    /**
     * Database Column Remarks:
     *   优惠券面值
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column coupon.value
     *
     * @mbg.generated
     */
    private Integer value;

    /**
     * Database Column Remarks:
     *   使用限制：购买金额需大于该值
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column coupon.condition
     *
     * @mbg.generated
     */
    private Integer condition;

    /**
     * Database Column Remarks:
     *   优惠券图片
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column coupon.img
     *
     * @mbg.generated
     */
    private String img;

    /**
     * Database Column Remarks:
     *   优惠券是否可用【1：可用，2：禁用】
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column coupon.status
     *
     * @mbg.generated
     */
    private Byte status;

    /**
     * Database Column Remarks:
     *   优惠券生效日期
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column coupon.effective_time
     *
     * @mbg.generated
     */
    private LocalDate effectiveTime;

    /**
     * Database Column Remarks:
     *   优惠券失效日期
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column coupon.expire_time
     *
     * @mbg.generated
     */
    private LocalDate expireTime;

    /**
     * Database Column Remarks:
     *   创建时间
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column coupon.create_time
     *
     * @mbg.generated
     */
    private LocalDateTime createTime;

    /**
     * Database Column Remarks:
     *   修改时间
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column coupon.update_time
     *
     * @mbg.generated
     */
    private LocalDateTime updateTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table coupon
     *
     * @mbg.generated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column coupon.id
     *
     * @return the value of coupon.id
     *
     * @mbg.generated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column coupon.id
     *
     * @param id the value for coupon.id
     *
     * @mbg.generated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column coupon.name
     *
     * @return the value of coupon.name
     *
     * @mbg.generated
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column coupon.name
     *
     * @param name the value for coupon.name
     *
     * @mbg.generated
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column coupon.value
     *
     * @return the value of coupon.value
     *
     * @mbg.generated
     */
    public Integer getValue() {
        return value;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column coupon.value
     *
     * @param value the value for coupon.value
     *
     * @mbg.generated
     */
    public void setValue(Integer value) {
        this.value = value;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column coupon.condition
     *
     * @return the value of coupon.condition
     *
     * @mbg.generated
     */
    public Integer getCondition() {
        return condition;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column coupon.condition
     *
     * @param condition the value for coupon.condition
     *
     * @mbg.generated
     */
    public void setCondition(Integer condition) {
        this.condition = condition;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column coupon.img
     *
     * @return the value of coupon.img
     *
     * @mbg.generated
     */
    public String getImg() {
        return img;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column coupon.img
     *
     * @param img the value for coupon.img
     *
     * @mbg.generated
     */
    public void setImg(String img) {
        this.img = img == null ? null : img.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column coupon.status
     *
     * @return the value of coupon.status
     *
     * @mbg.generated
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column coupon.status
     *
     * @param status the value for coupon.status
     *
     * @mbg.generated
     */
    public void setStatus(Byte status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column coupon.effective_time
     *
     * @return the value of coupon.effective_time
     *
     * @mbg.generated
     */
    public LocalDate getEffectiveTime() {
        return effectiveTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column coupon.effective_time
     *
     * @param effectiveTime the value for coupon.effective_time
     *
     * @mbg.generated
     */
    public void setEffectiveTime(LocalDate effectiveTime) {
        this.effectiveTime = effectiveTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column coupon.expire_time
     *
     * @return the value of coupon.expire_time
     *
     * @mbg.generated
     */
    public LocalDate getExpireTime() {
        return expireTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column coupon.expire_time
     *
     * @param expireTime the value for coupon.expire_time
     *
     * @mbg.generated
     */
    public void setExpireTime(LocalDate expireTime) {
        this.expireTime = expireTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column coupon.create_time
     *
     * @return the value of coupon.create_time
     *
     * @mbg.generated
     */
    public LocalDateTime getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column coupon.create_time
     *
     * @param createTime the value for coupon.create_time
     *
     * @mbg.generated
     */
    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column coupon.update_time
     *
     * @return the value of coupon.update_time
     *
     * @mbg.generated
     */
    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column coupon.update_time
     *
     * @param updateTime the value for coupon.update_time
     *
     * @mbg.generated
     */
    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coupon
     *
     * @mbg.generated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", value=").append(value);
        sb.append(", condition=").append(condition);
        sb.append(", img=").append(img);
        sb.append(", status=").append(status);
        sb.append(", effectiveTime=").append(effectiveTime);
        sb.append(", expireTime=").append(expireTime);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append("]");
        return sb.toString();
    }
}