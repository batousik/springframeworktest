package com.example;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.transaction.TransactionManager;
import javax.transaction.UserTransaction;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.jta.JtaTransactionManager;

@RunWith(SpringRunner.class)
@EnableJpaRepositories
public class MainTest {

	@PersistenceContext
	EntityManager em;

	@Autowired
	PersonRepository personRepository;

	@TestConfiguration
	static class TestConfig {

		@Bean
		LocalContainerEntityManagerFactoryBean entityManagerFactory() {
			LocalContainerEntityManagerFactoryBean bean = new LocalContainerEntityManagerFactoryBean();
			bean.setPersistenceUnitName( "mongodb-local" );
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
		public void hi() {
			List<Person> people = new PeopleCreator().getPeople();
			personRepository.save( people );

			System.out.println( "hi" );
		}
}

