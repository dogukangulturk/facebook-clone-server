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

    public Long getId() {
        return id;
    }

    public String getBio() {
        return bio;
    }

    public String getJob() {
        return job;
    }

    public String getWorkplace() {
        return workplace;
    }

    public String getHighSchool() {
        return highSchool;
    }

    public String getCollege() {
        return college;
    }

    public String getCurrentCity() {
        return currentCity;
    }

    public String getHomeTown() {
        return homeTown;
    }

    public RelationShip getRelationhip() {
        return relationhip;
    }

    public String getInstagram() {
        return instagram;
    }

    public User getUser() {
        return user;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public void setWorkplace(String workplace) {
        this.workplace = workplace;
    }

    public void setHighSchool(String highSchool) {
        this.highSchool = highSchool;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public void setCurrentCity(String currentCity) {
        this.currentCity = currentCity;
    }

    public void setHomeTown(String homeTown) {
        this.homeTown = homeTown;
    }

    public void setRelationhip(RelationShip relationhip) {
        this.relationhip = relationhip;
    }

    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
