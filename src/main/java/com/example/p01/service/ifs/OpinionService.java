package com.example.p01.service.ifs;

import com.example.p01.vo.OpinionVo.OpinionCreateReq;
import com.example.p01.vo.OpinionVo.OpinionRes;
import com.example.p01.vo.OpinionVo.OpinionUpdateReq;

import java.time.LocalDate;

public interface OpinionService {

    public OpinionRes addOpinion(OpinionCreateReq opinionCreateReq) throws Exception;

    public OpinionRes updateOpinion(OpinionUpdateReq opinionUpdateReq) throws Exception;

    public OpinionRes deleteOpinion(String employeeId, LocalDate createdDate) throws Exception;

    public OpinionRes getOpinionById(String employeeId, LocalDate createdDate) throws Exception;

    public OpinionRes getOpinionById(String employeeId) throws Exception;

    public OpinionRes getAllOpinion() throws Exception;
}
