package com.example.CounsellorsPortal.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.CounsellorsPortal.dto.viewEnqsFilterRequest;
import com.example.CounsellorsPortal.model.Enquiry;
import com.example.CounsellorsPortal.service.EnquiryService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class EnquiryController {

	private EnquiryService enquiryService;

	public EnquiryController(EnquiryService enquiryService) {
		this.enquiryService = enquiryService;
	}

//	@PostMapping("/filter-enqs")
//	public String filterEnquiries(viewEnqsFilterRequest viewEnqsFilterRequest, HttpServletRequest req, Model model) {
//		HttpSession session = req.getSession(false);
//		Integer counsellorId = (Integer) session.getAttribute("counsellorId");
//		List<Enquiry> enqsList = enquiryService.getEnquiresWithFilter(viewEnqsFilterRequest, counsellorId);
//		model.addAttribute("enquiries", enqsList);
//		return "viewEnqsPage";
//	}

	@PostMapping("/filter-enqs")
	public String filterEnquiries(viewEnqsFilterRequest viewEnqsFilterRequest, HttpServletRequest req, Model model) {
		HttpSession session = req.getSession(false);
		if (session == null || session.getAttribute("counsellorId") == null) {
			return "redirect:/login"; // Redirect to login if session is not valid
		}
		Integer counsellorId = (Integer) session.getAttribute("counsellorId");
		List<Enquiry> enqsList = enquiryService.getEnquiresWithFilter(viewEnqsFilterRequest, counsellorId);
		model.addAttribute("enquiries", enqsList);
		model.addAttribute("viewEnqFilter", viewEnqsFilterRequest); // Add this line to prevent errors on re-rendering

		return "viewEnqsPage";
	}

//	@GetMapping("/view-enquiries")
//	public String getEnquiries(HttpServletRequest request, Model model) {
//		HttpSession session = request.getSession(false);
//		Integer counsellorId = (Integer) session.getAttribute("counsellorId");
//		List<Enquiry> enqList = enquiryService.getEnquiriesByCounsellor(counsellorId);
//		model.addAttribute("enquiries", enqList);
//
//		viewEnqsFilterRequest filterreq = new viewEnqsFilterRequest();
//		model.addAttribute("viewEnqFilter", filterreq);
//
//		return "viewEnqsPage";
//	}

	@GetMapping("/view-enquiries")
	public String getEnquiries(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("counsellorId") == null) {
			return "redirect:/login"; // Redirect to login if session is not valid
		}

		Integer counsellorId = (Integer) session.getAttribute("counsellorId");
		List<Enquiry> enqList = enquiryService.getEnquiriesByCounsellor(counsellorId);
		model.addAttribute("enquiries", enqList);

		// Add the filter object to the model
		viewEnqsFilterRequest filterreq = new viewEnqsFilterRequest();
		model.addAttribute("viewEnqFilter", filterreq);

		return "viewEnqsPage";
	}

	@GetMapping("/enquiry")
	public String addEnquiryPage(Model model) {
		Enquiry enquiry = new Enquiry();
		model.addAttribute("enquiry", enquiry);
		return "enquiryForm";
	}

	@GetMapping("/editEnq")
	public String editEnquiry(@RequestParam("enqId") Integer enquiryId, Model model) {
		Enquiry enquiry = enquiryService.getEnquiryById(enquiryId);
		model.addAttribute("enquiry", enquiry);
		return "enquiryForm";
	}

	@PostMapping("/addEnq")
	public String handleAddEnquiry(Enquiry enquiry, HttpServletRequest req, Model model) throws Exception {
		HttpSession session = req.getSession(false);
		Integer counsellorId = (Integer) session.getAttribute("counsellorId");

		boolean isSaved = enquiryService.addEnquiry(enquiry, counsellorId);

		if (isSaved) {
			model.addAttribute("smsg", "Enquiry Added Successfully");
		} else {
			model.addAttribute("emsg", "Failed to Add Enquiry");
		}

		enquiry = new Enquiry();
		model.addAttribute("enquiry", enquiry);
		return "enquiryForm";
	}
}
