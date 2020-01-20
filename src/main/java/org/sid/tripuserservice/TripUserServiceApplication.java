package org.sid.tripuserservice;

import org.sid.tripuserservice.entities.AppRole;
import org.sid.tripuserservice.service.AccountService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.stream.Stream;

@SpringBootApplication
public class TripUserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TripUserServiceApplication.class, args);
	}
	@Bean
	CommandLineRunner start(AccountService accountService) {
		return args -> {
			accountService.save(new AppRole(null, "USER"));
			accountService.save(new AppRole(null, "ADMIN"));
			Stream.of("user1","user2","adminH", "adminA").forEach(u -> {
				accountService.saveUser(u, "1234", "Male", 639736838,"1234",
                        "qsdvfvfdbqdbd", "Ghalem", "Haitam", "Morocco", "pro.haitamghalem@gmail.com"," ");
			});
			accountService.addRoleToUser("adminH", "ADMIN");
            accountService.addRoleToUser("adminA", "ADMIN");
		};
	}

	@Bean
	BCryptPasswordEncoder getBCPE() {
		return new BCryptPasswordEncoder();
	}

}
