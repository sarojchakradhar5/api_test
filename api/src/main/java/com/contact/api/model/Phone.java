package com.contact.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "phone")
public class Phone {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private Long id;

	@Column(name = "number")
	private String number;

	@Column(name = "type")
	private String type;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "contact_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private Contact contact;

	public Phone() {

	}

	public Phone(Long id, String number, String type, Contact contact) {
		this.id = id;
		this.number = number;
		this.type = type;
		this.contact = contact;
	}
	
	public Phone( String number, String type) {
		this.number = number;
		this.type = type;
	}
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Contact getContact() {
		return contact;
	}

	public String getNumber() {
		return number;
	}

	public String getType() {
		return type;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}

	@Override
	public String toString() {
		return "Phone [id=" + id + ", number=" + number + ", type=" + type  + "]";
	}


}
