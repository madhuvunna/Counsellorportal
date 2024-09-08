package com.example.CounsellorsPortal.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.CounsellorsPortal.model.Enquiry;

@Repository
public interface EnquiryRepository extends JpaRepository<Enquiry, Integer> {

	@Query(value = " select * from enquiries_tbl where counsellor_id=:CounsellorId", nativeQuery = true)
	public List<Enquiry> getEnquiresByCounsellorId(@Param("CounsellorId") Integer counsellorId);
}
