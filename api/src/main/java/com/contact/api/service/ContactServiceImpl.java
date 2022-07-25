package com.contact.api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.contact.api.model.CallList;
import com.contact.api.model.Contact;
import com.contact.api.repository.ContactRepository;
import com.contact.api.repository.PhoneRepository;

@Service
public class ContactServiceImpl implements ContactService {
	
	@Autowired
	private ContactRepository contactRepository;
	
	@Autowired
	private PhoneRepository phoneRepository;

	@Override
	public List<Contact> listAllContacts() {
		return contactRepository.findAll();
	}

	@Override
	public Optional<Contact> getContactById(Long id) {
		return contactRepository.findById(id);
	}

	@Override
	public Contact saveContact(Contact contact) {
		return contactRepository.save(contact);
	}

	@Override
	public Optional<Contact> updateContact(Long id, Contact contact) {
		Optional<Contact> contactSaved = getContactById(id);
		if (contactSaved.isPresent()) {
			Contact updatedContact = contactSaved.get();
			updatedContact.setName(contact.getName());

			updatedContact.setAddress(contact.getAddress());
			
			updatedContact.getPhone().clear();
			updatedContact.setPhone(contact.getPhone());

			updatedContact.setEmail(contact.getEmail());
			
			return Optional.of(contactRepository.save(updatedContact));
		} else {
			return Optional.ofNullable(null);
		}
	}

	@Override
	public void deleteContactById(Long id) {
		contactRepository.deleteById(id);
	}
	
	@Override
	public List<CallList> getHomePhoneCallList() {
		var allHomePhone = phoneRepository.findAllHomePhone();
		List<CallList> callLists = new ArrayList<>();
		allHomePhone.forEach(phone -> {
			var name = phone.getContact().getName();
			callLists.add(new CallList(name, phone.getNumber()));
		});
		
		return callLists;
		
	}
	

}
