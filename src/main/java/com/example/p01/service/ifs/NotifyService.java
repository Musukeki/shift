package com.example.p01.service.ifs;

import com.example.p01.vo.NotifyVo.NotifyCreateReq;
import com.example.p01.vo.NotifyVo.NotifyRes;
import com.example.p01.vo.NotifyVo.NotifyUpdateReq;

public interface NotifyService {

    public NotifyRes addNotify(NotifyCreateReq notifyCreateReq) throws  Exception;

    public NotifyRes updateNotify(NotifyUpdateReq notifyUpdateReq) throws Exception;

    public NotifyRes deleteNotify(int id) throws Exception;

    public NotifyRes getNotifyById(int id) throws Exception;

    public NotifyRes getAllNotify() throws Exception;

    public NotifyRes getTrueNotify() throws Exception;
}
