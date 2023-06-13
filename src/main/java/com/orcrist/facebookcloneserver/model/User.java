package com.orcrist.facebookcloneserver.model;

import com.orcrist.facebookcloneserver.util.GenderEnum;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String lastname;

    @Column(unique = true)
    private String username;

    @Column(unique = true)
    private String email;

    private String password;
    private String picture;
    private String cover;

    @Enumerated(EnumType.STRING)
    private GenderEnum gender;

    private LocalDate birthday;
    private Boolean isVerified;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    //@ToString.Exclude
    private Set<Friend> friends = new HashSet<>();

    //private Set<Following> followings = new HashSet<>();
    //private Set<Follower> followers = new HashSet<>();
    //private Set<Request> requests = new HashSet<>();

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public void setName(String name) {
        this.name = name;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
