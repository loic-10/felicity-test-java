package com.example.felicitysas.models;


import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "subscriptions")
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date")
    private Date date;

    @Column(name = "period")
    private Integer period;

    @Column(name = "total_price")
    private Long total_price;

    @ManyToOne
    private User user;

    @ManyToOne
    private Service service;

    public Subscription() {
        super();
    }

    public Subscription(Date date, Integer period, Long total_price, User user, Service service) {
        this.date = date;
        this.period = period;
        this.total_price = total_price;
        this.user = user;
        this.service = service;
    }

    public Subscription(Date date, Integer period, Long total_price) {
        super();
        this.date = date;
        this.period = period;
        this.total_price = total_price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getPeriod() {
        return period;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }

    public Long getTotalPrice() {
        return total_price;
    }

    public void setTotalPrice(Long totalPrice) {
        this.total_price = totalPrice;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }
}
