package com.dreamcar.andreea.dtos;

import java.sql.Date;
import java.sql.Time;

import com.dreamcar.andreea.entites.Component;
import com.dreamcar.andreea.entites.Provider;

public class RequestDto {
    private Long id;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Provider getProvider() {
        return this.provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
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

    public Date getDate() {
        return this.date;
    }

    public RequestDto(){}

    public RequestDto(
        Long id, Provider provider, Component component, int amount, int price, Date timeout, Date date, Boolean status, boolean isYou, Time time) {
        this.id = id;
        this.provider = provider;
        this.component = component;
        this.amount = amount;
        this.price = price;
        this.timeout = timeout;
        this.date = date;
        this.status = status;
        this.isYou = isYou;
        this.time = time;
    }

    public void setDate(Date date) {
        this.date = date;
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

    public Boolean isIsYou() {
        return this.isYou;
    }

    public Boolean getIsYou() {
        return this.isYou;
    }

    public void setIsYou(Boolean isYou) {
        this.isYou = isYou;
    }

    private Provider provider;

    public Component component;

    private int amount;

    private int price;
    
    private Date timeout;

    private Date date;

    private Boolean status;

    private Boolean isYou;

    private Time time;

    public Time getTime() {
        return this.time;
    }

    public void setTime(Time time) {
        this.time = time;
    }
}
