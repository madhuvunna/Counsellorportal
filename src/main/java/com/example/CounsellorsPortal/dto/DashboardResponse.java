package com.example.CounsellorsPortal.dto;

public class DashboardResponse {
	
	private int totalEnqs;
	
	private int openEnqs;
	
	private int enrolledEnqs;
	
	private int lostEnqs;

	public int getTotalEnqs() {
		return totalEnqs;
	}

	public void setTotalEnqs(int totalEnqs) {
		this.totalEnqs = totalEnqs;
	}

	public int getOpenEnqs() {
		return openEnqs;
	}

	public void setOpenEnqs(int openEnqs) {
		this.openEnqs = openEnqs;
	}

	public int getEnrolledEnqs() {
		return enrolledEnqs;
	}

	public void setEnrolledEnqs(int enrolledEnqs) {
		this.enrolledEnqs = enrolledEnqs;
	}

	public int getLostEnqs() {
		return lostEnqs;
	}

	public void setLostEnqs(int lostEnqs) {
		this.lostEnqs = lostEnqs;
	}

		
}
