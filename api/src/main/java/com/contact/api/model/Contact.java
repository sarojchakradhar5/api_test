package com.contact.api.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "contact")
public class Contact {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "name_id", referencedColumnName = "id")
	private Name name;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "address_id", referencedColumnName = "id")
	private Address address;

	@OneToMany(mappedBy = "contact", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Phone> phone = new HashSet<>();

	@Column(name = "email")
	private String email;

	public Contact() {

	}

	public Contact(Long id, Name name, Address address, Set<Phone> phone, String email) {
		this.id = id;
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.email = email;
	}

	public Contact(Name name, Address address, Set<Phone> phone, String email) {
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.email = email;
	}

	public Long getId() {
		return id;
	}

	public Name getName() {
		return name;
	}

	public Address getAddress() {
		return address;
	}

	public Set<Phone> getPhone() {
		return phone;
	}

	public String getEmail() {
		return email;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(Name name) {
		this.name = name;
		name.setContact(this);
	}

	public void setAddress(Address address) {
		this.address = address;
		address.setContact(this);
	}

	public void setPhone(Set<Phone> phones) {

		this.phone.clear();
		if (phones != null) {
			this.phone.addAll(phones);
			for (Phone phone : phones) {
				phone.setContact(this);
			}
		}

	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Contact [id=" + id + ", name=" + name + ", address=" + address + ", phones=" + phone + ", email="
				+ email + "]";
	}

}
