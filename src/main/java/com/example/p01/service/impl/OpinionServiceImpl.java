package com.example.p01.service.impl;

import com.example.p01.dao.ClockDateDao;
import com.example.p01.dao.HeadDao;
import com.example.p01.dao.LeaveDetailDao;
import com.example.p01.dao.OpinionDao;
import com.example.p01.entity.ClockDate;
import com.example.p01.entity.Employee;
import com.example.p01.entity.Opinion;
import com.example.p01.service.ifs.OpinionService;
import com.example.p01.vo.OpinionVo.OpinionCreateReq;
import com.example.p01.vo.OpinionVo.OpinionRes;
import com.example.p01.vo.OpinionVo.OpinionUpdateReq;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class OpinionServiceImpl implements OpinionService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    LeaveDetailDao leaveDetailDao;

    @Autowired
    ClockDateDao clockDateDao;

    @Autowired
    OpinionDao opinionDao;

    @Autowired
    HeadDao headDao;

    @Autowired
    OpenAiChatModel chatModel;

    @Transactional(rollbackOn = Exception.class)
    @Override
    public OpinionRes addOpinion(OpinionCreateReq opinionCreateReq) throws Exception {
        try {
            Employee employee = headDao.getEmployeeById(opinionCreateReq.getEmployeeId());
            OpinionRes opinionRes = validEmployee(employee);
            if(opinionRes!=null){
                return opinionRes;
            }
            opinionDao.addOpinion(
                    opinionCreateReq.getEmployeeId(),
                    opinionCreateReq.getTitle(),
                    opinionCreateReq.getMessage(),
                    opinionCreateReq.getCreatedDate()
            );
            return new OpinionRes(200,"新增意見成功");
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw e;
        }
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public OpinionRes updateOpinion(OpinionUpdateReq opinionUpdateReq) throws Exception {
        try {
            Employee employee = headDao.getEmployeeById(opinionUpdateReq.getEmployeeId());
            OpinionRes opinionRes = validEmployee(employee);
            if(opinionRes!=null){
                return opinionRes;
            }
            opinionDao.updateOpinion(
                    opinionUpdateReq.getEmployeeId(),
                    opinionUpdateReq.getTitle(),
                    opinionUpdateReq.getMessage(),
                    opinionUpdateReq.getCreatedDate()
            );
            return new OpinionRes(200,"更新意見成功");
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw e;
        }
    }


    @Transactional(rollbackOn = Exception.class)
    @Override
    public OpinionRes deleteOpinion(String employeeId, LocalDate createdDate) throws Exception {
        try {
            Employee employee = headDao.getEmployeeById(employeeId);
            OpinionRes opinionRes = validEmployee(employee);
            if(opinionRes!=null){
                return opinionRes;
            }
            opinionDao.deleteOpinion(
                    employeeId,
                    createdDate
            );
            return new OpinionRes(200,"刪除意見成功");
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw e;
        }
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public OpinionRes getOpinionById(String employeeId, LocalDate createdDate) throws Exception {
        try {
            Employee employee = headDao.getEmployeeById(employeeId);
            OpinionRes opinionRes = validEmployee(employee);
            if(opinionRes!=null){
                return opinionRes;
            }
           Opinion opinion = opinionDao.getOpinionById(
                    employeeId,
                    createdDate
            );
            OpinionRes opinionRes1 = validOpinion(opinion);
            if(opinionRes1 != null){
                return opinionRes1;
            }
            return new OpinionRes(200,"查詢意見成功",opinion);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw e;
        }
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public OpinionRes getOpinionById(String employeeId) throws Exception {
        try {
            Employee employee = headDao.getEmployeeById(employeeId);
            OpinionRes opinionRes = validEmployee(employee);
            if(opinionRes!=null){
                return opinionRes;
            }
            List<Opinion> opinionList = opinionDao.getOpinionById(employeeId);
            //取得當前日期
            LocalDate today = LocalDate.now();
            //取得該月份最後一天
            YearMonth yearMonth = YearMonth.of(today.getYear(),today.getMonthValue());
            LocalDate endDate = yearMonth.atEndOfMonth();
            LocalDate startDate = endDate.withDayOfMonth(1);
            //一個月打卡紀錄
            List<Integer> scoreList = clockDateDao.getScoreById(employeeId,startDate,endDate);
            //一個月意見分析
            List<String> anazlyOpinion = opinionDao.getOpinionById(employeeId,startDate,endDate);
            //一個月請假天數
            Integer leaveCount = leaveDetailDao.countLeaveTimeByIdandDate(employeeId,startDate,endDate);

            String result = String.format(
                    "你是一位離職風險分析助理。你的任務是從給定資料中識別潛在的風險因素\n" +
                            "若未找到訊息，請明確註明「未找到」\n" +
                            "請你根據該員工30天的打卡心情紀錄以及請假紀錄以及意見提供離職率風險\n" +
                            "如果你沒辦法分析請說無法分析\n" +
                            "這是一位員工過去 30 天的紀錄：\n" +
                            "- 每日心情分數 (1~5)：%s\n" +
                            "- 請假天數：本月 %d 天\n" +
                            "- 意見文字：%s\n" +
                            "請根據這些資料：\n" +
                            "1. 推測這位員工的離職風險程度（低 / 中 / 高）。\n" +
                            "2. 給出風險分數 (0~100)。\n" +
                            "3. 提供具體建議，幫助降低他的離職風險。",
                            scoreList.toString(),
                            leaveCount,
                            anazlyOpinion.toString());
            String cleanText = chatModel.call(result).replaceAll("\n\\d","");
            return new OpinionRes(200,"查詢意見成功",opinionList,cleanText);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw e;
        }
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public OpinionRes getAllOpinion() throws Exception {
        try {
            List<Employee> employeeList = headDao.getAllEmployee();
            if(employeeList == null || employeeList.isEmpty()){
                return new OpinionRes(404,"找不到員工列表");
            }
            for (Employee employee : employeeList) {
                OpinionRes opinionRes = validEmployee(employee);
                if (opinionRes != null) {
                    return opinionRes;
                }
            }
            List<Opinion> opinionList = opinionDao.getAllOpinion();
            if (opinionList == null || opinionList.isEmpty()) {
                return new OpinionRes(404, "找不到意見列表");
            }
            for (Opinion opinion : opinionList) {
                OpinionRes opinionRes1 = validOpinion(opinion);
                if (opinionRes1 != null) {
                    return opinionRes1;
                }
            }
            return new OpinionRes(200, "查詢意見成功", opinionList);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw e;
        }
    }


    private OpinionRes validEmployee(Employee employee){
        if(employee == null){
            return new OpinionRes(404,"找不到該員工");
        }
        if(employee.getName() == null || employee.getName().equals("")){
            return new OpinionRes(400,"員工名字為空");
        }
        if(employee.getEmail() == null || employee.getEmail().equals("")){
            return new OpinionRes(400,"員工信箱為空");
        }
        if(!employee.getEmail().matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")){
            return new OpinionRes(400,"員工信箱格式是錯誤");
        }
        if(employee.getPhone() == null || employee.getPhone().equals("")){
            return new OpinionRes(400,"員工手機為空");
        }
        if(!employee.getPhone().matches("^09\\d{8}$")){
            return new OpinionRes(400,"員工手機格式是錯誤");
        }
        if(employee.getDepartment() == null || employee.getDepartment().equals("")){
            return new OpinionRes(400,"員工部門為空");
        }
        if(employee.getTitle() == null || employee.getTitle().equals("")){
            return new OpinionRes(400,"員工職稱為空");
        }
        if(employee.getPassword() == null || employee.getPassword().equals("")){
            return new OpinionRes(400,"員工密碼為空");
        }
        return null;
    }

    private OpinionRes validOpinion(Opinion opinion){
        if(opinion == null){
            return new OpinionRes(404,"找不到意見");
        }
        if(opinion.getTitle() == null || opinion.getTitle().equals("")){
            return new OpinionRes(400,"意見標題為空");
        }
        if(opinion.getMessage() == null || opinion.getMessage().equals("")){
            return new OpinionRes(400,"意見內容為空");
        }
        return null;
    }
}
