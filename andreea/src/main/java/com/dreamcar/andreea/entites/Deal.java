package com.dreamcar.andreea.entites;

import javax.persistence.*;

@Entity
@Table(name = "deals")
public class Deal {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name="request_id")
    private Request requests;

    @ManyToOne
    @JoinColumn(name="provider_id")
    private Provider provider;

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
     * @return Request return the requests
     */
    public Request getRequests() {
        return requests;
    }

    /**
     * @param requests the requests to set
     */
    public void setRequests(Request requests) {
        this.requests = requests;
    }

    /**
     * @return Provider return the provider
     */
    public Provider getProvider() {
        return provider;
    }

    /**
     * @param provider the provider to set
     */
    public void setProvider(Provider provider) {
        this.provider = provider;
    }

}
