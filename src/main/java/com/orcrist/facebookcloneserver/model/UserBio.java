package com.orcrist.facebookcloneserver.model;

import com.orcrist.facebookcloneserver.util.RelationShip;
import jakarta.persistence.*;

@Entity
@Table(name = "user_bio")
public class UserBio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String bio;
    private String job;
    private String workplace;
    private String highSchool;
    private String college;
    private String currentCity;
    private String homeTown;

    @Enumerated(EnumType.STRING)
    private RelationShip relationhip;
    private String instagram;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", unique = true)
    private User user;
}
