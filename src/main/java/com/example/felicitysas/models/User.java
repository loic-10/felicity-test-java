package com.example.felicitysas.models;

import jakarta.persistence.*;

@Entity
@Table(
        name = "users",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "username")
        }
)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "password")
    private String password;

    public User(Long id) {
        this.id = id;
    }

    @Enumerated(EnumType.STRING)
    @Column(length = 20, name = "role")
    private ERoleUser role;

    public User(String username, String full_name, String password, ERoleUser role) {
        super();
        this.username = username;
        this.fullName = full_name;
        this.password = password;
        this.role = role;
    }

    public User(String username, String password) {
        super();
        this.username = username;
        this.password = password;
    }


    public User() {
        super();

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ERoleUser getRole() {
        return role;
    }

    public void setRole(ERoleUser role) {
        this.role = role;
    }
}
