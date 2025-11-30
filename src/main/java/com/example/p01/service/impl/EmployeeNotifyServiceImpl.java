package com.example.p01.service.impl;

import com.example.p01.dao.EmployeeNotifyDao;
import com.example.p01.entity.EmployeeNotify;
import com.example.p01.service.ifs.EmployeeNotifyService;
import com.example.p01.vo.EmployeeNotifyVo.AddEmployeeNotifyReq;
import com.example.p01.vo.EmployeeNotifyVo.EmployeeNotifyRes;
import com.example.p01.vo.EmployeeNotifyVo.UpdateEmployeeNotifyReq;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeNotifyServiceImpl implements EmployeeNotifyService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    EmployeeNotifyDao employeeNotifyDao;

    @Override
    public EmployeeNotifyRes addEmployeeNotify(AddEmployeeNotifyReq addEmployeeNotifyReq) throws Exception {
        try {
            employeeNotifyDao.addEmployeeNotify(
                    addEmployeeNotifyReq.getEmployeeId(),
                    addEmployeeNotifyReq.getTitle(),
                    addEmployeeNotifyReq.getMessage(),
                    addEmployeeNotifyReq.getLinkUrl(),
                    addEmployeeNotifyReq.getCreatedDate()
            );
            return new EmployeeNotifyRes(200,"新增成功");
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw e;
        }
    }

    @Override
    public EmployeeNotifyRes updateEmployeeNotify(UpdateEmployeeNotifyReq updateEmployeeNotifyReq) throws Exception {
        try {
            EmployeeNotify employeeNotify = employeeNotifyDao.getEmployeeNotifyById(updateEmployeeNotifyReq.getId());
            if(employeeNotify == null){
                return new EmployeeNotifyRes(404,"找不到員工通知");
            }
            employeeNotifyDao.updateEmployeeNotify(
                    updateEmployeeNotifyReq.getId(),
                    updateEmployeeNotifyReq.getEmployeeId(),
                    updateEmployeeNotifyReq.getTitle(),
                    updateEmployeeNotifyReq.getMessage(),
                    updateEmployeeNotifyReq.getLinkUrl(),
                    updateEmployeeNotifyReq.getCreatedDate()
            );
            return new EmployeeNotifyRes(200,"更新成功");
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw e;
        }
    }

    @Override
    public EmployeeNotifyRes getEmployeeNotifyById(String employeeId) throws Exception {
        try {
            List<EmployeeNotify> employeeNotifyList =  employeeNotifyDao.getEmployeeNotifyByEmployeeId(employeeId);
            if(employeeNotifyList == null || employeeNotifyList.isEmpty()){
                return new EmployeeNotifyRes(404,"找不到員工通知列表");
            }
            return new EmployeeNotifyRes(200,"查詢成功",employeeNotifyList);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw e;
        }
    }

    @Override
    public EmployeeNotifyRes getEmployeeNotifyById(int id) throws Exception {
        try {
            EmployeeNotify employeeNotify =  employeeNotifyDao.getEmployeeNotifyById(id);
            if(employeeNotify == null){
                return new EmployeeNotifyRes(404,"找不到員工通知");
            }
            return new EmployeeNotifyRes(200,"查詢成功",employeeNotify);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw e;
        }
    }

    @Override
    public EmployeeNotifyRes getAllEmployeeNotify() throws Exception {
        try {
            List<EmployeeNotify> employeeNotifyList =  employeeNotifyDao.getAllEmployeeNotify();
            if(employeeNotifyList == null || employeeNotifyList.isEmpty()){
                return new EmployeeNotifyRes(404,"找不到員工通知列表");
            }
            return new EmployeeNotifyRes(200,"查詢成功",employeeNotifyList);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw e;
        }
    }
}
