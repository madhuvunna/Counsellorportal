package com.example.CounsellorsPortal.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.CounsellorsPortal.model.Counsellor;

@Repository
public interface CounsellorRepository extends JpaRepository<Counsellor, Integer> {
	
	public Counsellor findByEmail(String email);
	
	public Counsellor findByEmailAndPassword(String email, String password);

}
