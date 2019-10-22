package com.gnnt.mybatisgenerator.model;

import java.util.List;

public class RoleExt extends Role {
    private List<Permission> permissions;

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }
}
