# 📌 API 目錄

## 👤 Employee
- **創建職員個人資料** (`employeeCreate`)  
  **POST** `http://localhost:8080/head/update`
  postBody:
  ```json
  {
    "id": "AAVP0001",
    "name": "國恒",
    "title": "副社長",
    "employmentStatus": "在職中",
    "phone": "0916888888",
    "email": "AAVP@gmail.com",
    "department": "万十店"
  }

- **透過 ID 取得職員資訊** (`employeeSearch`)  
  **GET** `http://localhost:8080/head/search?id=AACTO0001`

- **取得所有職員資訊** (`employeesSearch`)  
  **GET** `http://localhost:8080/head/searchAll`

- **更新職員個人資料** (`employeeUpdate`)  
  **POST** `http://localhost:8080/head/update`  
  postBody:
  ```json
  {
    "id": "AAVP0001",
    "name": "國恒",
    "title": "副社長",
    "employmentStatus": "在職中",
    "phone": "0916888888",
    "email": "AAVP@gmail.com",
    "department": "万十店"
  }


- **修改密碼** ／ (`changePassword`)
**POST**`http://localhost:8080/head/changePassword`
postBody:
      ```json
  {
  "id": "AAPT0017",
  "newPassword":"68955959"
  
  }

- **登入** ／ (`login`)  
**POST**`http://localhost:8080/head/login`
postBody:
      ```json
  {
  "id": "AAPT0017",
  "password":"68955959"
  
  }
  
  

---

## Clock Date
- **上班打卡**  
  **POST** `http://localhost:8080/clock/on`
  
  PostBody:
  ```json
  {
    "employeeId": "AACEO0002",
    "clockOn": "07:13:00",
    "workDate": "2025-10-16"
  }
  ```
  Response(成功):
  ```json
  {
    "code": 200,
    "message": "上班打卡成功！",
    "clockDateInfo": {
        "employeeId": "AACEO0002",
        "workDate": "2025-10-16",
        "clockOn": "07:13:00",
        "hasDouble": false,
        "score": 0
    }
  }
  ```

- **下班打卡**  
  **POST** `http://localhost:8080/clock/off`
  
  PostBody:
  ```json
  {
    "employeeId": "AACEO0002",
    "workDate": "2025-10-16",
    "clockOff": "15:09:00",
    "score": 3
  }
  ```
  Response(成功):
  ```json
  // 有打上班卡
  {
    "code": 200,
    "message": "下班打卡成功",
    "clockDateInfo": {
        "employeeId": "AACEO0002",
        "workDate": "2025-10-16",
        "clockOn": "07:13:00",
        "clockOff": "15:09:00",
        "hasDouble": false,
        "totalHour": 7.5,
        "score": 3
    }
  }
  ```
  ```json
   // 未打上班卡(上班下班時間相同、總時數為0，等待補打卡)
   {
     "code": 200,
     "message": "下班打卡成功（已自動建立臨時上班卡，待補上班時間）",
     "clockDateInfo": {
         "employeeId": "AACEO0002",
         "workDate": "2025-10-16",
         "clockOn": "15:09:00",
         "clockOff": "15:09:00",
         "hasDouble": false,
         "totalHour": 0.0,
         "score": 3
     }
  }
   ```
  - **補打卡(上下班通用)**  
  **POST** `http://localhost:8080/clock/fix`
  
  PostBody:
  ```json
  {
    "employeeId": "AACEO0002",
    "workDate": "2025-10-16",
    "clockOn": "07:22:00",   //可傳可不傳，有傳就更新，沒傳就照舊
    "clockOff": "15:51:00",  //可傳可不傳，有傳就更新，沒傳就照舊
    "score": 3  //可傳可不傳，有傳就更新，沒傳就照舊
  }

  ```
  Response(成功):
  ```json
  {
    "code": 200,
    "message": "補卡成功",
    "clockDateInfo": {
        "employeeId": "AACEO0002",
        "workDate": "2025-10-16",
        "clockOn": "07:22:00",
        "clockOff": "15:09:00",
        "hasDouble": false,
        "totalHour": 7.5,
        "score": 3
    }
  }
  ```

  - **查詢職員指定時段打卡狀態**  
  **POST** `http://localhost:8080/clock/fix/check`
  
  PostBody:
  ```json
  {
    "employeeId": "AACEO0002",
    "workDate": "2025-10-20"
  }
  
  ```
  Response(上班未打卡):
  ```json
  {
     "code": 200,
     "message": "未打上班卡",
     "status": "MISS_ON",
     "canUseFix": true,
     "clockDateInfo": {
         "employeeId": "AACEO0002",
         "workDate": "2025-10-20",
         "clockOn": "17:13:00",
         "clockOff": "17:13:00",
         "hasDouble": false,
         "totalHour": 0.0,
         "score": 5
     }
  }
  ```
  
    Response(下班未打卡):
  ```json
  {
    "code": 200,
    "message": "未打下班卡",
    "status": "MISS_OFF",
    "canUseFix": true,
    "clockDateInfo": {
        "employeeId": "AACEO0002",
        "workDate": "2025-10-20",
        "clockOn": "07:21:00",
        "hasDouble": false,
        "score": 0
    }
  }
  ```

      Response(上下班未打卡):
  ```json
  {
    "code": 200,
    "message": "上下班皆未打卡",
    "status": "MISS_TWO",
    "canUseFix": false
  }
  ```

  - **取得個人歷史打卡紀錄**  
  **POST** `http://localhost:8080/clock/get_one?employee_id=此處帶入職員編號`
    
  Response(成功):
  ```json
  {
    "code": 200,
    "message": "已取得職員歷史打卡資訊",
    "clockDateInfoResList": [
        {
            "employeeId": "AACEO0002",
            "workDate": "2025-10-12",
            "clockOn": "07:04:00",
            "clockOff": "15:25:00",
            "hasDouble": false,
            "totalHour": 8.0,
            "score": 5
        },
        {
            "employeeId": "AACEO0002",
            "workDate": "2025-10-10",
            "clockOn": "07:05:00",
            "clockOff": "15:11:00",
            "hasDouble": true,
            "totalHour": 8.0,
            "score": 3
        }
    ]
  }
  ```
   - **取得所有職員歷史打卡紀錄**  
  **POST** `http://localhost:8080/clock/get_all`

  Response(成功):
  ```json
  {
    "code": 200,
    "message": "已取得所有職員歷史打卡資訊",
    "clockDateInfoResList": [
        {
            "employeeId": "AACEO0002",
            "workDate": "2025-10-12",
            "clockOn": "07:04:00",
            "clockOff": "15:25:00",
            "hasDouble": false,
            "totalHour": 8.0,
            "score": 5
        },
        {
            "employeeId": "AACEO0002",
            "workDate": "2025-10-10",
            "clockOn": "07:05:00",
            "clockOff": "15:11:00",
            "hasDouble": true,
            "totalHour": 8.0,
            "score": 3
        },
        {
            "employeeId": "AACTO0001",
            "workDate": "2025-10-20",
            "clockOn": "07:21:00",
            "clockOff": "17:13:00",
            "hasDouble": false,
            "totalHour": 9.5,
            "score": 5
        }
    ]
  }
```

---

## 📝 Leave Record
**請假申請** 
**post** `http://localhost:8080/leave/create`
req範例:
{
  "employeeId": "AACTO0001",
  "leaveType": "事假",
  "leaveDescription": "家裡有事",
  "leaveDetails": [
    {
      "leaveDate": "2025-09-20",
      "startTime": "09:00:00",
      "endTime": "12:00:00",
      "leaveHours": 3
    },
    {
      "leaveDate": "2025-09-21",
      "startTime": "13:00:00",
      "endTime": "17:00:00",
      "leaveHours": 4
    }
  ]
}

**請假同意**
**post** `http://localhost:8080/leave/leaveApproved`
req範例:
{
    "leaveId": "393",
    "approved": "true"
}

**取得所有請假資料**
**get** `http://localhost:8080/leave/getAllLeave`
不用參數
res範例:
[
    {
        "employeeId": "AACTO0001",
        "name": "自由",
        "leaveId": 393,
        "applyDate": "2025-09-20",
        "leaveType": "事假",
        "startTime": "09:00:00",
        "endTime": "12:00:00",
        "leaveHours": 3.0
    },
    {
        "employeeId": "AACTO0001",
        "name": "自由",
        "leaveId": 393,
        "applyDate": "2025-09-21",
        "leaveType": "事假",
        "startTime": "13:00:00",
        "endTime": "17:00:00",
        "leaveHours": 4.0
    }
]

## 🏷 Leave Type
(待定)

---

## 📅 Pre Schedule
- **修改**  
  **POST** `http://localhost:8080/PreSchedule/update`  
  postBody:
    ```json
  {
    "employeeId": "AACTO0001",
    "preSchduleUpdateVo": [
      {
        "applyDate": "2025-10-11",
        "isWorking": true,
        "shiftWorkId": "3",
        "isAccept": true
      },
      {
        "applyDate": "2025-10-12",
        "isWorking": false,
        "shiftWorkId": "4",
        "isAccept": false
      }
    ]
  }

- **新增**  
  **POST** `http://localhost:8080/PreSchedule/create`  
  postBody:
    ```json
  {
    "employeeId": "AACTO0001",
    "preScheduleVo": [
      {
        "applyDate": "2025-10-11",
        "isWorking": true,
        "shiftWorkId": "2"
      },
      {
        "applyDate": "2025-10-12",
        "isWorking": true,
        "shiftWorkId": "2"
      }
    ]
  }

**新增所有員工一個月的預設排班**
**post** `http://localhost:8080/PreSchedule/createAllPreSchedule`
req 範例:
{
    "year":2025,
    "month":9
}

**取得全員全部班表(同意不同意都有)**
**get** `http://localhost:8080/PreSchedule/getAllSchedule`
不用帶參數

**用員工ID取得該員工還沒同意的班表**
**get** `http://localhost:8080/PreSchedule/getScheduleByEmployeeId?employeeId=AACTO0001`
key:employeeId
value:員工ID

**用員工ID取得該員工同意後的班表**
**get** `http://localhost:8080/PreSchedule/getAcceptScheduleByEmployeeId?employeeId=AACTO0001`
key:employeeId
value:員工ID

**取得當天所有員工的班表**
**get** `http://localhost:8080/PreSchedule/getThisDaySchedule?thisDay=2025-09-20`
key:thisDay
value:日期 




---

## 💰 Salary
(待定)

## 📆 Schedule
(待定)

## 🔄 Shift Work
(待定)
# shift
