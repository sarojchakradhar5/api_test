package com.contact.api.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.contact.api.model.CallList;
import com.contact.api.model.Contact;
import com.contact.api.service.ContactService;

@RestController
@RequestMapping("/api/v1")
public class ContactController {
	
	@Autowired
	private ContactService contactService;

	@GetMapping("/contacts")
	public ResponseEntity<List<Contact>> getAllContacts() {
		var allContacts = contactService.listAllContacts();
		return new ResponseEntity<List<Contact>>(allContacts, HttpStatus.OK);
	}
	
	@PostMapping("/contacts")
	public ResponseEntity<Contact> createContact(@Valid @RequestBody Contact contactRequest) {

		var contactResponse = contactService.saveContact(contactRequest);

		return new ResponseEntity<Contact>(contactResponse, HttpStatus.CREATED);
	}
	

	@GetMapping("/contacts/{id}")
	public ResponseEntity<Contact> getContactById(@PathVariable(value = "id") Long contactId) throws Exception {
		Optional<Contact> savedContact = contactService.getContactById(contactId);
		if(savedContact.isPresent()) {
			return new ResponseEntity<Contact>(savedContact.get(), HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}


	@PutMapping("/contacts/{id}")
	public ResponseEntity<Contact> updateContact(@PathVariable("id") long id, @RequestBody Contact contact) {
		
		var updatedContact = contactService.updateContact(id, contact);
		
		if(updatedContact.isPresent()) {
			return new ResponseEntity<Contact>(updatedContact.get(), HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		
	}

	@DeleteMapping("/contacts/{id}")
	public ResponseEntity<HttpStatus> deleteEmployee(@PathVariable(value = "id") Long id) throws Exception {
		try {
			contactService.deleteContactById(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	

	@GetMapping("/contacts/call-list")
	public ResponseEntity<List<CallList>> getCallList() {
		var callList = contactService.getHomePhoneCallList();
		if(callList.isEmpty()) {
			return new ResponseEntity<>( HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<List<CallList>>(callList, HttpStatus.OK);
		}
		
	}

}
