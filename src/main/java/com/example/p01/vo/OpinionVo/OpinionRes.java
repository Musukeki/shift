package com.example.p01.vo.OpinionVo;

import com.example.p01.entity.Opinion;

import java.util.List;
import java.util.Map;

public class OpinionRes {

    private int code;
    private String message;
    private Opinion opinion;
    private List<Opinion> opinionList;
    private String analyze;

    public OpinionRes(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public OpinionRes(int code, String message, Opinion opinion) {
        this.code = code;
        this.message = message;
        this.opinion = opinion;
    }

    public OpinionRes(int code, String message, List<Opinion> opinionList) {
        this.code = code;
        this.message = message;
        this.opinionList = opinionList;
    }

    public OpinionRes(int code, String message, List<Opinion> opinionList, String analyze) {
        this.code = code;
        this.message = message;
        this.opinionList = opinionList;
        this.analyze = analyze;
    }

    public String getAnalyze() {
        return analyze;
    }

    public void setAnalyze(String analyze) {
        this.analyze = analyze;
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

    public Opinion getOpinion() {
        return opinion;
    }

    public void setOpinion(Opinion opinion) {
        this.opinion = opinion;
    }

    public List<Opinion> getOpinionList() {
        return opinionList;
    }

    public void setOpinionList(List<Opinion> opinionList) {
        this.opinionList = opinionList;
    }
}
