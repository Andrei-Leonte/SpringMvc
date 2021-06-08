package com.dreamcar.andreea.dtos;

public class CreateDealDto {
    public Long getRequestId() {
        return this.requestId;
    }

    public void setRequestId(Long requestId) {
        this.requestId = requestId;
    }

    public int getPrice() {
        return this.price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
    
    private Long requestId;

    private int price;
}
