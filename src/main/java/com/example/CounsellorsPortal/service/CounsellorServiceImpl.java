package com.example.CounsellorsPortal.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.CounsellorsPortal.dao.CounsellorRepository;
import com.example.CounsellorsPortal.dao.EnquiryRepository;
import com.example.CounsellorsPortal.dto.DashboardResponse;
import com.example.CounsellorsPortal.model.Counsellor;
import com.example.CounsellorsPortal.model.Enquiry;

@Service
public class CounsellorServiceImpl implements CounsellorService {

	private CounsellorRepository counsellorRepository;

	private EnquiryRepository enquiryRepository;

	public CounsellorServiceImpl(CounsellorRepository counsellorRepository, EnquiryRepository enquiryRepository) {

		this.counsellorRepository = counsellorRepository;
		this.enquiryRepository = enquiryRepository;
	}

	@Override
	public Counsellor findByEmail(String Email) {
		return counsellorRepository.findByEmail(Email);
	}

//	@Override
//	public boolean registerCounsellor(Counsellor counsellor) {
//		Counsellor savedCounsellor = counsellorRepository.save(counsellor);
//		if (null != savedCounsellor.getCounsellorId()) {
//			return true;
//		}
//		return false;
//	}

	@Override
	public boolean registerCounsellor(Counsellor counsellor) {
		Counsellor savedCounsellor = counsellorRepository.save(counsellor);
		return savedCounsellor.getCounsellorId() != null;
	}

	@Override
	public Counsellor login(String email, String password) {
		Counsellor counsellor = counsellorRepository.findByEmailAndPassword(email, password);
		return counsellor;
	}

//	@Override
//	public DashboardResponse getDashboardInfo(Integer counsellorId) {
//		DashboardResponse dashboardResponse = new DashboardResponse();
//		List<Enquiry> enqlist = enquiryRepository.getEnquiresByCounsellorId(counsellorId);
//		int totalEnqs = enqlist.size();
//
//		int enrolledEnqs = enqlist.stream().filter(e -> e.getEnquiryStatus().equals("Enrolled"))
//				.collect(Collectors.toList()).size();
//		int openEnqs = enqlist.stream().filter(e -> e.getEnquiryStatus().equals("Lost")).collect(Collectors.toList())
//				.size();
//
//		int lostEnqs = enqlist.stream().filter(e -> e.getEnquiryStatus().equals("Open")).collect(Collectors.toList())
//				.size();
//		dashboardResponse.setEnrolledEnqs(enrolledEnqs);
//		dashboardResponse.setLostEnqs(lostEnqs);
//		dashboardResponse.setOpenEnqs(openEnqs);
//		dashboardResponse.setTotalEnqs(totalEnqs);
//		return dashboardResponse;
//	}

	@Override
	public DashboardResponse getDashboardInfo(Integer counsellorId) {
		DashboardResponse dashboardResponse = new DashboardResponse();
		List<Enquiry> enqList = enquiryRepository.getEnquiresByCounsellorId(counsellorId);
		int totalEnqs = enqList.size();

		int enrolledEnqs = (int) enqList.stream().filter(e -> "Enrolled".equals(e.getEnquiryStatus())).count();
		int openEnqs = (int) enqList.stream().filter(e -> "Open".equals(e.getEnquiryStatus())).count();
		int lostEnqs = (int) enqList.stream().filter(e -> "Lost".equals(e.getEnquiryStatus())).count();

		dashboardResponse.setEnrolledEnqs(enrolledEnqs);
		dashboardResponse.setLostEnqs(lostEnqs);
		dashboardResponse.setOpenEnqs(openEnqs);
		dashboardResponse.setTotalEnqs(totalEnqs);

		return dashboardResponse;
	}

}
