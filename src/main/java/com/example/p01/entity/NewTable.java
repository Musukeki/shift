package com.example.p01.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "new_table")
public class NewTable {
	
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name="employee_Id")
	private String employeeId;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @Column(name="selected_date")
    private LocalDateTime selectedDate;
    
    @Column(name="user_message", columnDefinition = "TEXT")
    private String userMessage;
    
    @Lob
    @Column(name="assistant_reply", columnDefinition = "MEDIUMTEXT")
    private String assistantReply;
    
    @Column(name="created_at")
    private LocalDateTime createdAt;
    

    // Getters & Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
    
    public LocalDateTime getSelectedDate() { return selectedDate; }
    public void setSelectedDate(LocalDateTime selectedDate) { this.selectedDate = selectedDate; }

    public String getUserMessage() { return userMessage; }
    public void setUserMessage(String userMessage) { this.userMessage = userMessage; }

    public String getAssistantReply() { return assistantReply; }
    public void setAssistantReply(String assistantReply) { this.assistantReply = assistantReply; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
