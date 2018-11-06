package com.zlh.demo_springboot_ssm.domain;

public class Account {
    private String accounId;

    private Integer money;

    private String personId;

    public String getAccounId() {
        return accounId;
    }

    public void setAccounId(String accounId) {
        this.accounId = accounId == null ? null : accounId.trim();
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId == null ? null : personId.trim();
    }
}