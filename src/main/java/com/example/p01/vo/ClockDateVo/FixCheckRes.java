package com.example.p01.vo.ClockDateVo;


import com.example.p01.vo.headVo.BasicRes;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class FixCheckRes extends BasicRes {

    private FixStatus status; // enum

    private Boolean canUseFix;

    private ClockDateInfoRes clockDateInfo;

    public FixCheckRes() {
    }

    public FixCheckRes(int code, String message, FixStatus status, Boolean canUseFix) {
        super(code, message);
        this.status = status;
        this.canUseFix = canUseFix;
    }

    public FixCheckRes(int code, String message, FixStatus status, Boolean canUseFix, ClockDateInfoRes info) {
        this(code, message, status, canUseFix);
        this.clockDateInfo = info;
    }

    public FixStatus getStatus() {
        return status;
    }

    public void setStatus(FixStatus status) {
        this.status = status;
    }

    public Boolean getCanUseFix() {
        return canUseFix;
    }

    public void setCanUseFix(Boolean canUseFix) {
        this.canUseFix = canUseFix;
    }

    public ClockDateInfoRes getClockDateInfo() {
        return clockDateInfo;
    }

    public void setClockDateInfo(ClockDateInfoRes clockDateInfo) {
        this.clockDateInfo = clockDateInfo;
    }
}
