package com.example.p01.controller;

import com.example.p01.service.ifs.OpinionService;
import com.example.p01.vo.OpinionVo.OpinionCreateReq;
import com.example.p01.vo.OpinionVo.OpinionRes;
import com.example.p01.vo.OpinionVo.OpinionUpdateReq;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@CrossOrigin("http://localhost:4200")
@RestController
@Validated
public class OpinionController {

    @Autowired
    OpinionService opinionService;

    //查詢該員工單筆意見
    @GetMapping("/opinion/searchByDate")
    public OpinionRes getOpinionById(@NotBlank(message = "員工ID為空")@RequestParam("id")String employeeId,
                                     @NotNull(message = "意見日期為空")@RequestParam("date")LocalDate createdDate) throws Exception{
        return opinionService.getOpinionById(employeeId,createdDate);
    }
    //查詢該員工全部意見
    @GetMapping("/opinion/searchById")
    public OpinionRes getOpinionById(@NotBlank(message = "員工ID為空")@RequestParam("id")String employeeId) throws Exception{
        return opinionService.getOpinionById(employeeId);
    }
    //查詢全部員工意見
    @GetMapping("/opinion/search")
    public OpinionRes getAllOpinion() throws Exception{
        return opinionService.getAllOpinion();
    }
    //新增意見
    @PostMapping("/opinion/create")
    public OpinionRes addOpinion(@Valid @RequestBody OpinionCreateReq opinionCreateReq) throws Exception{
        return opinionService.addOpinion(opinionCreateReq);
    }
    //更新意見
    @PostMapping("/opinion/update")
    public OpinionRes updateOpinion(@Valid @RequestBody OpinionUpdateReq opinionUpdateReq) throws Exception{
        return opinionService.updateOpinion(opinionUpdateReq);
    }
    //刪除意見
    @DeleteMapping("/opinion/delete")
    public OpinionRes deleteOpinion(@NotBlank(message = "員工ID為空")@RequestParam("id")String employeeId,
                                    @NotNull(message = "意見日期為空")@RequestParam("date")LocalDate createdDate) throws Exception{
        return opinionService.deleteOpinion(employeeId,createdDate);
    }

}
