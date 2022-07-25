package com.contact.api;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.contact.api.controller.ContactController;
import com.contact.api.model.Address;
import com.contact.api.model.Contact;
import com.contact.api.model.Name;
import com.contact.api.model.Phone;
import com.contact.api.service.ContactService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(ContactController.class)
public class ContactControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

    @MockBean
	private ContactService contactService;
    

	@Test
	void shouldCreateContact() throws Exception {
		Contact contact = new Contact();
		contact.setName(new Name("First-1", "Middle-1", "Last-1"));
		contact.setAddress(new Address("street-1", "city-1", "state-1", "1"));
		contact.setPhone(Set.of(new Phone("123456789", "home"), new Phone("987654321", "mobile")));
		contact.setEmail("test@gmail.com");

		mockMvc.perform(post("/api/v1/contacts").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(contact))).andExpect(status().isCreated()).andDo(print());
	}

	@Test
	void shouldReturnContact() throws Exception {
		long id = 2L;
		Contact contact = new Contact();
		contact.setId(id);
		contact.setName(new Name("First-1", "Middle-1", "Last-1"));
		contact.setAddress(new Address("street-1", "city-1", "state-1", "1"));
		contact.setPhone(Set.of(new Phone("123456789", "home"), new Phone("987654321", "mobile")));

		when(contactService.getContactById(id)).thenReturn(Optional.of(contact));
		mockMvc.perform(get("/api/v1/contacts/{id}", id)).andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value(id)).andDo(print());
	}

	@Test
	void shouldReturnNotFoundContact() throws Exception {
		long id = 1L;

		when(contactService.getContactById(id)).thenReturn(Optional.empty());
		mockMvc.perform(get("/api/v1/contacts/{id}", id)).andExpect(status().isNotFound()).andDo(print());
	}
	
	

}
