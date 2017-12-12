package com.example;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import lombok.Data;

@Entity
@Data
public class Person {

	@Id
	private String name;

	@OneToOne
	@Cascade(CascadeType.PERSIST)
	private Person spouse;

	@OneToMany
	@Cascade(CascadeType.PERSIST)
	private List<Person> parents;

	@OneToMany
	@Cascade(CascadeType.PERSIST)
	private List<Person> children;
}
