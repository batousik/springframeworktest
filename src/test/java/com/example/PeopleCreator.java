package com.example;

import java.util.ArrayList;

import lombok.Data;

@Data
public class PeopleCreator {

	private ArrayList<Person> people;

	public PeopleCreator() {
		init();
	}

	private void init() {
		ArrayList<Person> people = new ArrayList<>();

		Person childTom = new Person();
		childTom.setName( "Tom" );

		Person childLouis = new Person();
		childLouis.setName( "Louis" );

		Person childAlex = new Person();
		childAlex.setName( "Alex" );

		Person mary = new Person();
		mary.setName( "Mary" );

		mary.setSpouse( childTom );
		childTom.setSpouse( mary );

		ArrayList<Person> children = new ArrayList<>();
		children.add( childLouis );
		children.add( childTom );
		children.add( childAlex );

		Person husband = new Person();
		husband.setName( "Nick" );

		Person wife = new Person();
		wife.setName( "Kate" );

		husband.setSpouse( wife );
		wife.setSpouse( husband );

		husband.setChildren( children );
		wife.setChildren( children );

		ArrayList<Person> parents = new ArrayList<>();
		parents.add( husband );
		parents.add( wife );

		for ( Person child : children ) {
			child.setParents( parents );
		}

		people.add( husband );
		people.add( wife );
		people.add( childAlex );
		people.add( childLouis );
		people.add( childTom );
		people.add( mary );

		this.people = people;
	}
}
