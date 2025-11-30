package com.example.p01.controller;

import com.example.p01.service.ifs.NotifyService;
import com.example.p01.vo.NotifyVo.NotifyCreateReq;
import com.example.p01.vo.NotifyVo.NotifyRes;
import com.example.p01.vo.NotifyVo.NotifyUpdateReq;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://localhost:4200")
@RestController
@Validated
public class NotifyController {
    @Autowired
    NotifyService notifyService;

    //查詢單筆通知
    @GetMapping("/notify/search")
    public NotifyRes getNotifyById(@Min(value = 1,message = "通知ID不能小於1")@RequestParam("id") int id) throws Exception{
        return notifyService.getNotifyById(id);
    }
    //查詢全部通知
    @GetMapping("/notify/searchAll")
    public NotifyRes getAllNotify() throws Exception{
        return notifyService.getAllNotify();
    }
    //查詢全部已推播通知
    @GetMapping("/notify/searchTrueAll")
    public NotifyRes getTrueNotify() throws Exception{
        return notifyService.getTrueNotify();
    }
    //新增通知
    @PostMapping("/notify/create")
    public NotifyRes addNotify(@Valid @RequestBody NotifyCreateReq notifyCreateReq) throws Exception{
        return notifyService.addNotify(notifyCreateReq);
    }
    //更新通知
    @PutMapping("/notify/update")
    public NotifyRes updateNotify(@Valid @RequestBody NotifyUpdateReq notifyUpdateReq) throws Exception{
        return notifyService.updateNotify(notifyUpdateReq);
    }
    //刪除通知
    @DeleteMapping("notify/delete")
    public NotifyRes deleteNotify(@Min(value = 1,message = "通知ID不能小於1")@RequestParam("id") int id) throws Exception{
        return notifyService.deleteNotify(id);
    }

}
