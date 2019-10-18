package com.gnnt.mybatisgenerator.model;

import java.io.Serializable;

public class Employee implements Serializable {
    private Integer id;

    private String code;

    private String lastName;

    private Integer age;

    private Double salary;

    private Integer gender;

    private String address;

    private String position;

    private Integer depCode;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName == null ? null : lastName.trim();
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position == null ? null : position.trim();
    }

    public Integer getDepCode() {
        return depCode;
    }

    public void setDepCode(Integer depCode) {
        this.depCode = depCode;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", code=").append(code);
        sb.append(", lastName=").append(lastName);
        sb.append(", age=").append(age);
        sb.append(", salary=").append(salary);
        sb.append(", gender=").append(gender);
        sb.append(", address=").append(address);
        sb.append(", position=").append(position);
        sb.append(", depCode=").append(depCode);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}