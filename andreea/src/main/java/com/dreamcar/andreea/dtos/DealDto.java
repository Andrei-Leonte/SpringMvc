package com.dreamcar.andreea.dtos;

import java.util.List;

import com.dreamcar.andreea.entites.Request;

public class DealDto {
    public DealDto(boolean isOwner, Request request) {
        this.isOwner = isOwner;
        this.request = request;
    }

    public boolean isOwner;

    public Request request;

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

}
