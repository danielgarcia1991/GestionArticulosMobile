package com.example.lenovo.gestionarticulosm;

import com.example.lenovo.gestionarticulosm.models.Category;

import java.util.ArrayList;

public class LoginResponse {

    private String users;
    private boolean status;


    public String getUsers() {
        return users;
    }

    public void setUsers(String users) {
        this.users = users;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
