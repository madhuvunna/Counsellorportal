package com.example.CounsellorsPortal.model;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "enquiries_tbl")
public class Enquiry {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "enquirie_id")
	private Integer enquiryId;

	@Column(name = "student_name")
	private String studentName;

	@Column(name = "student_phnnumber")
	private String studentPhoneNumber;

	@Column(name = "course_name")
	private String courseName;

	@Column(name = "class_mode")
	private String classMode;

	@Column(name = "enquirie_status")
	private String enquiryStatus;

	@CreationTimestamp
	private LocalDate createdDate;

	@UpdateTimestamp
	private LocalDate updatedDate;

	@ManyToOne
	@JoinColumn(name = "counsellor_id")
	private Counsellor counsellor;

	public Enquiry() {
		super();
	}

	public Integer getEnquiryId() {
		return enquiryId;
	}

	public void setEnquiryId(Integer enquiryId) {
		this.enquiryId = enquiryId;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getStudentPhoneNumber() {
		return studentPhoneNumber;
	}

	public void setStudentPhoneNumber(String studentPhoneNumber) {
		this.studentPhoneNumber = studentPhoneNumber;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getClassMode() {
		return classMode;
	}

	public void setClassMode(String classMode) {
		this.classMode = classMode;
	}

	public String getEnquiryStatus() {
		return enquiryStatus;
	}

	public void setEnquiryStatus(String enquiryStatus) {
		this.enquiryStatus = enquiryStatus;
	}

	public LocalDate getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDate createdDate) {
		this.createdDate = createdDate;
	}

	public LocalDate getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(LocalDate updatedDate) {
		this.updatedDate = updatedDate;
	}

	public Counsellor getCounsellor() {
		return counsellor;
	}

	public void setCounsellor(Counsellor counsellor) {
		this.counsellor = counsellor;
	}

	@Override
	public String toString() {
		return "Enquiry [enquiryId=" + enquiryId + ", studentName=" + studentName + ", studentPhoneNumber="
				+ studentPhoneNumber + ", courseName=" + courseName + ", classMode=" + classMode + ", enquiryStatus="
				+ enquiryStatus + ", createdDate=" + createdDate + ", updatedDate=" + updatedDate + ", counsellor="
				+ counsellor + "]";
	}

	
}
