package com.contact.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "name")
public class Name {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long id;
	
	@Column(name = "first")
	private String first;
	 
	@Column(name = "middle")
	private String middle;
	 
	@Column(name = "last")
	private String last;
	 
	
	@OneToOne(mappedBy = "name")
    private Contact contact;


	public Name() {
		
	}


	public Name(Long id, String first, String middle, String last, Contact contact) {
		this.id = id;
		this.first = first;
		this.middle = middle;
		this.last = last;
		this.contact = contact;
	}
	
	public Name(String first, String middle, String last) {
		this.first = first;
		this.middle = middle;
		this.last = last;
	}

	public String getFirst() {
		return first;
	}


	public String getMiddle() {
		return middle;
	}


	public String getLast() {
		return last;
	}


	public void setFirst(String first) {
		this.first = first;
	}


	public void setMiddle(String middle) {
		this.middle = middle;
	}


	public void setLast(String last) {
		this.last = last;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}


	@Override
	public String toString() {
		return "Name [id=" + id + ", first=" + first + ", middle=" + middle + ", last=" + last
				+ "]";
	}
	
	

	
}
