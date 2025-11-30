package com.example.p01.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "notify")
public class Notify {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name="title")
    private String title;

    @Column(name = "message")
    private String message;

    @Column(name = "created_date")
    private LocalDate createdDate;

    @Column(name = "link_url")
    private String linkUrl;

    @Column(name = "is_publish")
    private boolean isPublish;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

    public boolean isPublish() {
        return isPublish;
    }

    public void setPublish(boolean publish) {
        isPublish = publish;
    }
}
