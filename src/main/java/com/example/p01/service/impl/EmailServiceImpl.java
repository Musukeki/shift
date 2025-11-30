package com.example.p01.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.example.p01.service.ifs.EmailService;


@Service
public class EmailServiceImpl implements EmailService {

	
	@Autowired
    private JavaMailSender mailSender;
	
	//固定寄郵件的方法
	public void sendMail(String to, String subject, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);            // 收件人，這裡就是 emp.getEmail()
        message.setSubject(subject);  // 主題
        message.setText(content);     // 內容
        message.setFrom("onceTechnology1111@gmail.com"); // 寄件者（固定）
        mailSender.send(message);
    }
	
	
	
	
	
	
	
}
