package com.dreamcar.andreea.dtos;

import com.dreamcar.andreea.entites.Provider;

import com.dreamcar.andreea.entites.Request;

public class DealDetailsDto {

    public DealDetailsDto(Long id, Request request, Provider provider, int price, boolean isYou) {
        this.id = id;
        this.request = request;
        this.provider = provider;
        this.price = price;
        this.isYou = isYou;
    }

    private Long id;

    private Request request;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Request getRequest() {
        return this.request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public Provider getProvider() {
        return this.provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    public int getPrice() {
        return this.price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isIsYou() {
        return this.isYou;
    }

    public boolean getIsYou() {
        return this.isYou;
    }

    public void setIsYou(boolean isYou) {
        this.isYou = isYou;
    }

    private Provider provider;

    private int price;

    private boolean isYou; 
}
