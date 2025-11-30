package com.example.p01.vo.ClockDateVo;

public enum FixStatus {

    NO_WORK,  // 今日不上班（未申請、主管未核可）。

    MISS_TWO,  // 上下班皆未打卡

    MISS_ON,  // 未打上班卡

    MISS_OFF, // 未打下班卡

    IS_OK  // 上下班皆已打卡
}
