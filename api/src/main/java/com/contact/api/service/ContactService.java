package com.contact.api.service;

import java.util.List;
import java.util.Optional;

import com.contact.api.model.CallList;
import com.contact.api.model.Contact;

public interface ContactService {
	List<Contact> listAllContacts();

	Optional<Contact> getContactById(Long id);

	Contact saveContact(Contact contact);
	
	Optional<Contact> updateContact(Long id, Contact contact);

    void deleteContactById(Long id);
    
    List<CallList> getHomePhoneCallList();

}
