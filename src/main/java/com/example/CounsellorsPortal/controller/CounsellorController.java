package com.example.CounsellorsPortal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.CounsellorsPortal.dto.DashboardResponse;
import com.example.CounsellorsPortal.model.Counsellor;
import com.example.CounsellorsPortal.service.CounsellorService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class CounsellorController {

	private final CounsellorService counsellorService;

	public CounsellorController(CounsellorService counsellorService) {
		this.counsellorService = counsellorService;
	}

	@GetMapping("/")
	public String index(Model model) {
		Counsellor couns = new Counsellor();
		model.addAttribute("counsellor", couns);
		return "index";
	}

	@PostMapping("/login")
	public String login(@ModelAttribute Counsellor counsellor, HttpServletRequest request, Model model) {
		Counsellor coun = counsellorService.login(counsellor.getEmail(), counsellor.getPassword());
		if (coun == null) {
			model.addAttribute("msg", "Invalid credentials. Please try again.");
			return "index";
		} else {
			HttpSession session = request.getSession(true);
			session.setAttribute("counsellorId", coun.getCounsellorId());

			return "redirect:/dashboard";
		}
	}

	@GetMapping("/dashboard")
	public String displayDashboard(HttpServletRequest req, Model model) {
		HttpSession session = req.getSession(false);
		if (session != null) {
			Integer counsellorId = (Integer) session.getAttribute("counsellorId");
			DashboardResponse dbresobj = counsellorService.getDashboardInfo(counsellorId);
			model.addAttribute("dashboardInfo", dbresobj);
			return "dashboard"; // Assuming a Thymeleaf template named "dashboard.html"
		}
		return "redirect:/";
	}

	@GetMapping("/register")
	public String registerPage(Model model) {
		Counsellor couns = new Counsellor();
		model.addAttribute("counsellor", couns);
		return "register";
	}

	@PostMapping("/register")
	public String handleRegister(@ModelAttribute Counsellor counsellor, Model model) {
		Counsellor byEmail = counsellorService.findByEmail(counsellor.getEmail());
		if (byEmail != null) {
			model.addAttribute("emsg", "Email already registered.");
			return "register";
		}
		boolean isRegister = counsellorService.registerCounsellor(counsellor);
		if (isRegister) {
			model.addAttribute("smsg", "Registration successful!");
		} else {
			model.addAttribute("emsg", "Registration failed. Please try again.");
		}
		return "register";
	}

	@GetMapping("/logout")
	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate();
		}
		return "redirect:/";
	}
}
