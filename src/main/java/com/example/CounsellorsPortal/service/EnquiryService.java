package com.example.CounsellorsPortal.service;

import java.util.List;

import com.example.CounsellorsPortal.dto.viewEnqsFilterRequest;
import com.example.CounsellorsPortal.model.Enquiry;

public interface EnquiryService {

	public boolean addEnquiry(Enquiry enq, Integer counsellorId) throws Exception;

	public List<Enquiry> getEnquiriesByCounsellor(Integer counsellorId);

	public List<Enquiry> getEnquiresWithFilter(viewEnqsFilterRequest filterReq, Integer counsellorId);

	public Enquiry getEnquiryById(Integer enqId);

}
