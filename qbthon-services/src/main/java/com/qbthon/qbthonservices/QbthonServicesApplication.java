package com.qbthon.qbthonservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication(scanBasePackages="com.qbthon.qbthonservices")
public class QbthonServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(QbthonServicesApplication.class, args);
		
		/*
		 * BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(); String
		 * password = "password"; String encodedPassword =
		 * passwordEncoder.encode(password);
		 * 
		 * System.out.println(); System.out.println("Password is         : " +
		 * password); System.out.println("Encoded Password is : " + encodedPassword);
		 * System.out.println();
		 */
		  
		  
		 
		 
	}

}
