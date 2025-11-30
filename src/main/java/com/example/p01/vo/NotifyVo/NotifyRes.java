package com.example.p01.vo.NotifyVo;

import com.example.p01.entity.Notify;

import java.util.List;

public class NotifyRes {

    private int code;
    private String message;
    private Notify notify;
    private List<Notify> notifyList;

    public NotifyRes(int code, String message, Notify notify) {
        this.code = code;
        this.message = message;
        this.notify = notify;
    }

    public NotifyRes(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public NotifyRes(int code, String message, List<Notify> notifyList) {
        this.code = code;
        this.message = message;
        this.notifyList = notifyList;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Notify getNotify() {
        return notify;
    }

    public void setNotify(Notify notify) {
        this.notify = notify;
    }

    public List<Notify> getNotifyList() {
        return notifyList;
    }

    public void setNotifyList(List<Notify> notifyList) {
        this.notifyList = notifyList;
    }
}
