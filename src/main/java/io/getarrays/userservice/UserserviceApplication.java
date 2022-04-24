package io.getarrays.userservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import io.getarrays.userservice.domain.Role;
import io.getarrays.userservice.domain.User;
import io.getarrays.userservice.service.UserService;

@SpringBootApplication
public class UserserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserserviceApplication.class, args);
	}

	@Bean
	PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}

	// @Bean
	// CommandLineRunner run(UserService userService){
	// 	return args->{

	// 		userService.saveRole(new Role(1, "ROLE_USER"));
	// 		userService.saveRole(new Role(2,  "ROLE_MANAGER"));
	// 		userService.saveRole(new Role(3,  "ROLE_ADMIN"));
	// 		userService.saveRole(new Role(4,  "ROLE_SUPER_ADMIN"));

			
	// 		User user  = new User();
	// 		user.setName("John Travolta");
	// 		user.setUsername("john");
	// 		user.setPassword("1234");
	// 		userService.saveUser(user);

	// 		User user1 = new User();
	// 		user1.setName("Will Smith");
	// 		user1.setUsername("will");
	// 		user1.setPassword("1234");
	// 		userService.saveUser(user1);

	// 		User user2 = new User();
	// 		user2.setName("Jim Carry");
	// 		user2.setUsername("jim");
	// 		user2.setPassword("1234");
	// 		userService.saveUser(user2);
			
	// 		User user3 = new User();
	// 		user3.setName("Arnold Schwarzenegger");
	// 		user3.setUsername("arnold");
	// 		user3.setPassword("1234");
	// 		userService.saveUser(user3);

			
	// 		userService.addRoleToUser("john", "ROLE_USER");
	// 		userService.addRoleToUser("will", "ROLE_MANAGER");
	// 		userService.addRoleToUser("jim", "ROLE_ADMIN");
	// 		userService.addRoleToUser("arnold", "ROLE_SUPER_ADMIN");
	// 		userService.addRoleToUser("arnold", "ROLE_ADMIN");
	// 		userService.addRoleToUser("arnold", "ROLE_USER");
			
					
			
	// 	};
	//}

}
