package com.api.parkingcontroljava;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class ParkingControlJavaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ParkingControlJavaApplication.class, args);
		System.out.println(new BCryptPasswordEncoder().encode("senha123"));
	}
}