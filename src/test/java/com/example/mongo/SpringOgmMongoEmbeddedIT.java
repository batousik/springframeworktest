package com.example.mongo;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.transaction.TransactionManager;
import javax.transaction.UserTransaction;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.example.PeopleCreator;
import com.example.Person;
import com.example.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.jta.JtaTransactionManager;

@RunWith(SpringRunner.class)
@EnableJpaRepositories(basePackages = { "com.example" })
public class SpringOgmMongoEmbeddedIT extends AbstractMongoDBTest {

	@PersistenceContext
	EntityManager em;

	@Autowired
	PersonRepository personRepository;

	@TestConfiguration
	static class TestConfig {

		@Bean
		LocalContainerEntityManagerFactoryBean entityManagerFactory() {
			LocalContainerEntityManagerFactoryBean bean = new LocalContainerEntityManagerFactoryBean();
			bean.setPersistenceUnitName( "mongodb-embedded" );
			return bean;
		}

		@Bean
		public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
			UserTransaction userTransaction = com.arjuna.ats.jta.UserTransaction.userTransaction();
			TransactionManager transactionManager = com.arjuna.ats.jta.TransactionManager.transactionManager();
			return new JtaTransactionManager( userTransaction, transactionManager );
		}
	}

	@Test
	@Transactional
	public void testSaveAndRetrieveEntity() {
		String personName = "Anonymous Person X";
		Person person = new Person();
		person.setName( personName );

		personRepository.saveAndFlush( person );

		Person person2 = personRepository.findOne( personName );

		assertEquals( personName, person2.getName() );
	}

	@Test
	@Transactional
	@Commit
	public void testSavePeopleAndRetrieveSpecificPerson() {
		Person husband = new PeopleCreator().getHusband();
		personRepository.save( husband );

		Person mary = personRepository.findOne( "Mary" );
		Person maybeHusband = null;
		for ( Person p : mary.getSpouse().getParents() ) {
			if ( p.getName().equals( "Kate" ) ) {
				maybeHusband = p.getSpouse();
			}
		}
		assertEquals( husband, maybeHusband );
	}
}
