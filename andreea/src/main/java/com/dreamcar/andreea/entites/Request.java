package com.dreamcar.andreea.entites;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "requests")
public class Request {

    public Request() { }

    public Request(
        Component component,
     int amount,
     int price,
     Date date2,
     String timeoutTime, 
     Boolean status) {
        this.component = component;
        this.amount = amount;
        this.price = price;
        this.timeout = new Date(date2.getTime());
        this.status = status;   

        this.time = Time.valueOf(timeoutTime + ":00");
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false, cascade = CascadeType.MERGE)
    @JoinColumn(name = "provider_id", nullable = false)
    private Provider provider;

    @OneToOne(cascade = CascadeType.ALL)
    public Component component;

    @OneToMany(mappedBy = "request", fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private List<Deal> deals;

    @Column(name = "amount")
    private int amount;

    @Column(name = "price")
    private int price;
    
    @Column(name = "timeout")
    private Date timeout;

    @Column(name = "date")
    private Date date;

    @Column(name = "time")
    private Time time;

    @Column(name = "status")
    private Boolean status;

    @Column(name = "modifiedBy")
    private String modifiedBy;

    public String getModifiedBy() {
        return this.modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Time getTime() {
        return this.time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    public List<Deal> getDeals() {
        return deals;
    }

    public void setDeals(List<Deal> deals) {
        this.deals = deals;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Date getTimeout() {
        return timeout;
    }

    public void setTimeout(Date timeout) {
        this.timeout = timeout;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Boolean isStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
    
    public Component getComponent() {
        return this.component;
    }

    public void setComponent(Component component) {
        this.component = component;
    }

    public Boolean getStatus() {
        return this.status;
    }
}
