package com.contact.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.contact.api.model.Contact;

public interface ContactRepository extends JpaRepository<Contact, Long> {

}
