package com.gnnt.mybatisgenerator.model;

import java.io.Serializable;
import java.util.Date;

public class Person implements Serializable {
    private Integer id;

    private String cardId;

    private String nativePlace;

    private Integer married;

    private String education;

    private Date birthday;

    private Integer employeeId;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId == null ? null : cardId.trim();
    }

    public String getNativePlace() {
        return nativePlace;
    }

    public void setNativePlace(String nativePlace) {
        this.nativePlace = nativePlace == null ? null : nativePlace.trim();
    }

    public Integer getMarried() {
        return married;
    }

    public void setMarried(Integer married) {
        this.married = married;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education == null ? null : education.trim();
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", cardId=").append(cardId);
        sb.append(", nativePlace=").append(nativePlace);
        sb.append(", married=").append(married);
        sb.append(", education=").append(education);
        sb.append(", birthday=").append(birthday);
        sb.append(", employeeId=").append(employeeId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}