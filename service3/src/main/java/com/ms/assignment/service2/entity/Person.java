package com.ms.assignment.service2.entity;

import javax.validation.constraints.NotEmpty;

public class Person {
	@NotEmpty(message = "Please provide a name")
	private String name;
	@NotEmpty(message = "Please provide a surname")
	private String surname;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	@Override
	public String toString() {
		return "Person [name=" + name + ", surname=" + surname + "]";
	}
}
