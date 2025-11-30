package com.example.p01.vo.OpenAiVo;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class openAiGenerateReq {

    @NotEmpty(message="心情評分列表為空")
    private List<Integer> moodScore;

    @NotNull(message="請假次數為空")
    private int leaveCount;

    private List<String> opinion;


    public openAiGenerateReq() {}

    public openAiGenerateReq(List<Integer> moodScore, int leaveCount, List<String> opinion) {
        this.moodScore = moodScore;
        this.leaveCount = leaveCount;
        this.opinion = opinion;
    }

    public int getLeaveCount() {
        return leaveCount;
    }

    public void setLeaveCount(int leaveCount) {
        this.leaveCount = leaveCount;
    }

    public List<Integer> getMoodScore() {
        return moodScore;
    }

    public void setMoodScore(List<Integer> moodScore) {
        this.moodScore = moodScore;
    }

    public List<String> getOpinion() {
        return opinion;
    }

    public void setOpinion(List<String> opinion) {
        this.opinion = opinion;
    }
}