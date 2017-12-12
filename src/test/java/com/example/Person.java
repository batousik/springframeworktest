package com.example;

import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import lombok.Data;

@Entity
@Data
public class Person {

	@Id
	private String name;

	@OneToOne(cascade = {
			CascadeType.DETACH,
			CascadeType.MERGE,
			CascadeType.REFRESH,
			CascadeType.PERSIST
	})
	private Person spouse;

	@ManyToMany(cascade = {
			CascadeType.DETACH,
			CascadeType.MERGE,
			CascadeType.REFRESH,
			CascadeType.PERSIST
	})
	@JoinTable(name = "tbl_parents_children",
			joinColumns = @JoinColumn(name = "parentId"),
			inverseJoinColumns = @JoinColumn(name = "childId")
	)
	private Collection<Person> children;

	@ManyToMany(cascade = {
			CascadeType.DETACH,
			CascadeType.MERGE,
			CascadeType.REFRESH,
			CascadeType.PERSIST
	})
	@JoinTable(name = "tbl_parents_children",
			joinColumns = @JoinColumn(name = "childId"),
			inverseJoinColumns = @JoinColumn(name = "parentId")
	)
	private Collection<Person> parents;
}
