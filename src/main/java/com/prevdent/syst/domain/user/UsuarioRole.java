package com.prevdent.syst.domain.user;

public enum UsuarioRole {

    Admin("admin"),

    USER_ROLE("user");

    private final String role;

    UsuarioRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
