package com.dreamcar.andreea.dtos;

import com.dreamcar.andreea.entites.Request;

public class DealDto {

    public DealDto(Request request, boolean isOwner, String email) {
        this.request = request;
        this.isOwner = isOwner;
        this.email = email;
    }

    public Request request;

    public boolean isOwner;

    public String email;

    public boolean isIsOwner() {
        return this.isOwner;
    }

    public boolean getIsOwner() {
        return this.isOwner;
    }

    public void setIsOwner(boolean isOwner) {
        this.isOwner = isOwner;
    }

    public Request getRequest() {
        return this.request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
