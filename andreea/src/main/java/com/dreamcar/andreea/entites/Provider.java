package com.dreamcar.andreea.entites;

import java.util.List;

import javax.persistence.*;

import com.dreamcar.andreea.entites.base.Deal;

@Entity
@Table(name = "providers")
public class Provider {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    
    @OneToOne(mappedBy = "users")
    public User user;

    @OneToMany(mappedBy = "requests")
    private List<Request> requests;

    @OneToMany(mappedBy = "deals")
    private List<Deal> deals;

    @Column(name = "phoneNumber", nullable = false, length = 20)
	private String phoneNumber;

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
     * @return String return the phoneNumber
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * @param phoneNumber the phoneNumber to set
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    /**
     * @return List<Request> return the requests
     */
    public List<Request> getRequests() {
        return requests;
    }

    /**
     * @param requests the requests to set
     */
    public void setRequests(List<Request> requests) {
        this.requests = requests;
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

}
