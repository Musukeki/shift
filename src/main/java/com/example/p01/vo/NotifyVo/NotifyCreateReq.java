package com.example.p01.vo.NotifyVo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class NotifyCreateReq {

    @NotBlank(message = "通知標題為空")
    private String title;

    @NotBlank(message = "通知內容為空")
    private String message;

    @NotNull(message = "新增通知時間為空")
    private LocalDate createdDate;

    private String linkUrl;

    @NotNull(message="新增狀態為空")
    private boolean isPublish;

    public NotifyCreateReq() {}

    public NotifyCreateReq(String title, String message, LocalDate createdDate, String linkUrl, boolean isPublish) {
        this.title = title;
        this.message = message;
        this.createdDate = createdDate;
        this.linkUrl = linkUrl;
        this.isPublish = isPublish;
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
