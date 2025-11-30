package com.example.p01.service.impl;

import com.example.p01.dao.NotifyDao;
import com.example.p01.entity.Notify;
import com.example.p01.service.ifs.NotifyService;
import com.example.p01.vo.NotifyVo.NotifyCreateReq;
import com.example.p01.vo.NotifyVo.NotifyRes;
import com.example.p01.vo.NotifyVo.NotifyUpdateReq;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;


@Service
public class NotifyServiceImpl implements NotifyService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    NotifyDao notifyDao;

    //新增通知
    @Transactional(rollbackOn = Exception.class)
    @Override
    public NotifyRes addNotify(NotifyCreateReq notifyCreateReq) throws Exception {
        try {
            notifyDao.addNotify(
                    notifyCreateReq.getTitle(),
                    notifyCreateReq.getMessage(),
                    notifyCreateReq.getCreatedDate(),
                    notifyCreateReq.getLinkUrl(),
                    notifyCreateReq.isPublish()
            );
            return new NotifyRes(200,"新增通知成功");
        }catch (Exception e){
            logger.error(e.getMessage());
            throw e;
        }
    }

    //更新通知
    @Transactional(rollbackOn = Exception.class)
    @Override
    public NotifyRes updateNotify(NotifyUpdateReq notifyUpdateReq) throws Exception {
        try {
            Notify notify = notifyDao.getNotifyById(notifyUpdateReq.getId());
            if(notify == null){
                return new NotifyRes(404,"找不到該通知");
            }
            notifyDao.updateNotify(
                    notifyUpdateReq.getId(),
                    notifyUpdateReq.getTitle(),
                    notifyUpdateReq.getMessage(),
                    notifyUpdateReq.getCreatedDate(),
                    notifyUpdateReq.getLinkUrl(),
                    notifyUpdateReq.isPublish()
            );
            return new NotifyRes(200,"更新通知成功");
        }catch (Exception e){
            logger.error(e.getMessage());
            throw e;
        }
    }

    //刪除通知
    @Transactional(rollbackOn = Exception.class)
    @Override
    public NotifyRes deleteNotify(int id) throws Exception {
        try {
            Notify notify = notifyDao.getNotifyById(id);
            if(notify == null) {
                return new NotifyRes(404, "找不到該通知");
            }
            notifyDao.deleteNotify(id);
            return new NotifyRes(200,"刪除通知成功");
        }catch (Exception e){
            logger.error(e.getMessage());
            throw e;
        }
    }

    //取單筆通知
    @Transactional(rollbackOn = Exception.class)
    @Override
    public NotifyRes getNotifyById(int id) throws Exception {
        try {
            Notify notify = notifyDao.getNotifyById(id);
            NotifyRes notifyRes = validNotify(notify);
            if(notifyRes != null){
                return notifyRes;
            }
            return new NotifyRes(200,"查詢成功",notify);
        }catch (Exception e){
            logger.error(e.getMessage());
            throw e;
        }
    }

    //取全部通知
    @Transactional(rollbackOn = Exception.class)
    @Override
    public NotifyRes getAllNotify() throws Exception {
        try {
            List<Notify> notifyList = notifyDao.getAllNotify();
            if(notifyList == null && notifyList.isEmpty()){
                return new NotifyRes(404,"找不到通知列表");
            }
            for(Notify notify : notifyList) {
                NotifyRes notifyRes = validNotify(notify);
                if (notifyRes != null) {
                    return notifyRes;
                }
            }
            return new NotifyRes(200,"查詢成功",notifyList);
        }catch (Exception e){
            logger.error(e.getMessage());
            throw e;
        }
    }

    //只找有推播的通知
    @Override
    public NotifyRes getTrueNotify() throws Exception {
        try {
            LocalDate past = LocalDate.of(LocalDate.now().getYear(),LocalDate.now().getMonthValue()-1,1);
            LocalDate future = LocalDate.of(LocalDate.now().getYear(),LocalDate.now().getMonthValue()+1,1);
            List<Notify> notifyList = notifyDao.getTrueNotify(past,future);
            if(notifyList == null && notifyList.isEmpty()){
                return new NotifyRes(404,"找不到通知列表");
            }
            for(Notify notify : notifyList) {
                NotifyRes notifyRes = validNotify(notify);
                if (notifyRes != null) {
                    return notifyRes;
                }
            }
            return new NotifyRes(200,"查詢成功",notifyList);
        }catch (Exception e){
            logger.error(e.getMessage());
            throw e;
        }
    }

    //驗證Notify
    public NotifyRes validNotify(Notify notify){
        if(notify == null){
            return new NotifyRes(404,"找不到該通知");
        }
        if(notify.getTitle() == null || notify.getTitle().equals("")){
            return new NotifyRes(400,"ID："+notify.getId()+"該通知標題為空");
        }
        if(notify.getMessage() == null || notify.getMessage().equals("")){
            return new NotifyRes(400,"ID："+notify.getId()+"該通知內容為空");
        }
        if(notify.getCreatedDate() == null){
            return new NotifyRes(400,"ID："+notify.getId()+"該通知時間為空");
        }
        return null;
    }
}
