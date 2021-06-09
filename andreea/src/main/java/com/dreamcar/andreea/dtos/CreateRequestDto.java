package com.dreamcar.andreea.dtos;

import java.sql.*;

import com.dreamcar.andreea.entites.Component;

public class CreateRequestDto {
    public Component component;

    private int amount;

    private int price;
    
    private Date timeout;

    private String timeoutTime;

    private Boolean status = true;

    public String getTimeoutTime() {
        return this.timeoutTime;
    }

    public void setTimeoutTime(String timeoutTime) {
        this.timeoutTime = timeoutTime;
    }

    public Component getComponent() {
        return this.component;
    }

    public void setComponent(Component component) {
        this.component = component;
    }

    public int getAmount() {
        return this.amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getPrice() {
        return this.price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Date getTimeout() {
        return this.timeout;
    }

    public void setTimeout(Date timeout) {
        this.timeout = timeout;
    }

    public Boolean isStatus() {
        return this.status;
    }

    public Boolean getStatus() {
        return this.status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
