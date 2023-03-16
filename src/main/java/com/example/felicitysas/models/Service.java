package com.example.felicitysas.models;

import jakarta.persistence.*;

@Entity
@Table(
        name = "services",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "name")
        }
)
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private Long price;

    @Column(name = "available")
    private Boolean available;

    public Service(String name, String description, Long price, Boolean available) {
        super();
        this.name = name;
        this.description = description;
        this.price = price;
        this.available = available;
    }

    public Service(Long id) {
        this.id = id;
    }

    public Service() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean _available) {
        available = _available;
    }
}
