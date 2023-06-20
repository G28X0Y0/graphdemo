package com.graphdemo.graphdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {
		SecurityAutoConfiguration.class,
		HibernateJpaAutoConfiguration.class,
		DataSourceAutoConfiguration.class
})
public class GraphDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(GraphDemoApplication.class, args);
	}

}