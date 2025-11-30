package com.example.p01.vo.NotifyVo;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class NotifyUpdateReq {

    @Min(value = 1,message = "通知ID不能小於1")
    @NotNull(message = "通知ID為空")
    private int id;

    private String title;

    private String message;

    private LocalDate createdDate;

    private String linkUrl;

    private boolean isPublish;

    public NotifyUpdateReq() {
    }

    public NotifyUpdateReq(int id, String title, String message, LocalDate createdDate, String linkUrl, boolean isPublish) {
        this.id = id;
        this.title = title;
        this.message = message;
        this.createdDate = createdDate;
        this.linkUrl = linkUrl;
        this.isPublish = isPublish;
    }

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
