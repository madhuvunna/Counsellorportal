package com.example.CounsellorsPortal.service;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.example.CounsellorsPortal.dao.CounsellorRepository;
import com.example.CounsellorsPortal.dao.EnquiryRepository;
import com.example.CounsellorsPortal.dto.viewEnqsFilterRequest;
import com.example.CounsellorsPortal.model.Counsellor;
import com.example.CounsellorsPortal.model.Enquiry;

import io.micrometer.common.util.StringUtils;

@Service
public class EnquiryServiceImpl implements EnquiryService {

	private CounsellorRepository counsellorRepository;

	private EnquiryRepository enquiryRepository;

	public EnquiryServiceImpl(CounsellorRepository counsellorRepository, EnquiryRepository enquiryRepository) {

		this.counsellorRepository = counsellorRepository;
		this.enquiryRepository = enquiryRepository;
	}

//	@Override
//	public boolean addEnquiry(Enquiry enq, Integer counsellorId) throws Exception {
//		Counsellor counsellor = counsellorRepository.findById(counsellorId).orElse(null);
//		if (counsellor == null) {
//			throw new Exception("No Counsellor Found");
//		}
//		enq.setCounsellor(counsellor);
//		Enquiry save = enquiryRepository.save(enq);
//
//		if (save.getEnquiryId() != null) {
//			return true;
//		}
//		return false;
//	}

	@Override
	public boolean addEnquiry(Enquiry enq, Integer counsellorId) throws Exception {
		Counsellor counsellor = counsellorRepository.findById(counsellorId)
				                                    .orElseThrow(() -> new Exception("No Counsellor Found"));

		enq.setCounsellor(counsellor);
		Enquiry savedEnquiry = enquiryRepository.save(enq);

		return savedEnquiry.getEnquiryId() != null;
	}

	@Override
	public List<Enquiry> getEnquiriesByCounsellor(Integer counsellorId) {
		return enquiryRepository.getEnquiresByCounsellorId(counsellorId);
	}

	@Override
	public Enquiry getEnquiryById(Integer enqId) {
		return enquiryRepository.findById(enqId).orElse(null);
	}

	@Override
	public List<Enquiry> getEnquiresWithFilter(viewEnqsFilterRequest filterReq, Integer counsellorId) {

		Enquiry enq = new Enquiry();
		if (StringUtils.isNotEmpty(filterReq.getCourseName())) {
			enq.setCourseName(filterReq.getCourseName());
		}

		if (StringUtils.isNotEmpty(filterReq.getEnquiryStatus())) {
			enq.setEnquiryStatus(filterReq.getEnquiryStatus());
		}

		Counsellor counsellor = counsellorRepository.findById(counsellorId).orElse(null);

		enq.setCounsellor(counsellor);

		Example<Enquiry> off = Example.of(enq);

		List<Enquiry> enqliSt = enquiryRepository.findAll(off);

		return enqliSt;
	}

}
