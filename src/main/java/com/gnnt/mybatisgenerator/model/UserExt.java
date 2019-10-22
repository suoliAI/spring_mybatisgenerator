package com.gnnt.mybatisgenerator.model;

import java.util.List;

public class UserExt extends User {
    private List<Role> roles;

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
