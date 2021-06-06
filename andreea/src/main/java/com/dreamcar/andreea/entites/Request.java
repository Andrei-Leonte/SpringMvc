package com.dreamcar.andreea.entites;

import java.sql.Date;
import java.util.List;

import javax.persistence.*;

import com.dreamcar.andreea.entites.base.Deal;

@Entity
@Table(name = "requests")
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @OneToOne(mappedBy = "components")
    public Component component;

    @OneToMany(mappedBy = "deals")
    private List<Deal> deals;

    @Column(name = "amount")
    private int amount;

    @Column(name = "price")
    private int price;
    
    @Column(name = "timeout")
    private Date timeout;

    @Column(name = "date")
    private Date date;

    @Column(name = "status")
    private Boolean status;

    /**
     * @return Long return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return User return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * @return List<Deal> return the deals
     */
    public List<Deal> getDeals() {
        return deals;
    }

    /**
     * @param deals the deals to set
     */
    public void setDeals(List<Deal> deals) {
        this.deals = deals;
    }

    /**
     * @return int return the amount
     */
    public int getAmount() {
        return amount;
    }

    /**
     * @param amount the amount to set
     */
    public void setAmount(int amount) {
        this.amount = amount;
    }

    /**
     * @return int return the price
     */
    public int getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(int price) {
        this.price = price;
    }

    /**
     * @return Date return the timeout
     */
    public Date getTimeout() {
        return timeout;
    }

    /**
     * @param timeout the timeout to set
     */
    public void setTimeout(Date timeout) {
        this.timeout = timeout;
    }

    /**
     * @return Date return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * @return Boolean return the status
     */
    public Boolean isStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(Boolean status) {
        this.status = status;
    }

}
