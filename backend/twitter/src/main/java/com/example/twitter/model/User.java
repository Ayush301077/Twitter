package com.example.twitter.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String fullName;
    private String location;
    private String website;
    private String birthDate;
    private String email;
    private String password;
    private String mobile;
    private String image;
    private String backgroundImage;
    private String bio;
    private boolean req_user;
    private boolean login_with_google;

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Twit> twit = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Like> likes = new ArrayList<>();

    @Embedded
    private Varification verification;

    @JsonIgnore
    @ManyToMany
    private List<User> followers = new ArrayList<>();

    @JsonIgnore
    @ManyToMany
    private List<User> followings = new ArrayList<>();


    public List<Like> getLikes() {
        return likes;
    }

    public void setLikes(List<Like> likes) {
        this.likes = likes;
    }

    public Varification getVerification() {
        return verification;
    }

    public void setVerification(Varification verification) {
        this.verification = verification;
    }

    public List<User> getFollowers() {
        return followers;
    }

    public void setFollowers(List<User> followers) {
        this.followers = followers;
    }

    public List<User> getFollowings() {
        return followings;
    }

    public void setFollowings(List<User> followings) {
        this.followings = followings;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getBackgroundImage() {
        return backgroundImage;
    }

    public void setBackgroundImage(String backgroundImage) {
        this.backgroundImage = backgroundImage;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public boolean isReq_user() {
        return req_user;
    }

    public void setReq_user(boolean req_user) {
        this.req_user = req_user;
    }

    public boolean isLogin_with_google() {
        return login_with_google;
    }

    public void setLogin_with_google(boolean login_with_google) {
        this.login_with_google = login_with_google;
    }

    public List<Twit> getTwit() {
        return twit;
    }

    public void setTwit(List<Twit> twit) {
        this.twit = twit;
    }
}
