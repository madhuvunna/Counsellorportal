package com.example.CounsellorsPortal.service;

import com.example.CounsellorsPortal.dto.DashboardResponse;
import com.example.CounsellorsPortal.model.Counsellor;

public interface CounsellorService {

	public Counsellor findByEmail(String Email);

	public boolean registerCounsellor(Counsellor counsellor);

	public Counsellor login(String email, String password);

	public DashboardResponse getDashboardInfo(Integer counsellorId);

}
