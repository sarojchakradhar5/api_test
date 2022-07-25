package com.contact.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.contact.api.model.Phone;

public interface PhoneRepository extends JpaRepository<Phone, Long> {

	
	@Query(value = "SELECT * from Phone WHERE type='home'", nativeQuery = true)
	List<Phone> findAllHomePhone();

}
