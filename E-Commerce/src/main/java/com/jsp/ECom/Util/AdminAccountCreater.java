package com.jsp.ECom.Util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.jsp.ECom.Entity.User;
import com.jsp.ECom.Enum.UserRole;
import com.jsp.ECom.Repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@RequiredArgsConstructor
public class AdminAccountCreater implements CommandLineRunner {
 
	
	private final UserRepository Ur;
	private final PasswordEncoder Pe;
	
	@Value("${admin.email}")
	private String adminEmail;
	@Value("${admin.password}")
	private String adminPassword;
	@Value("${admin.mobile}")
	private long adminMobile;
	@Value("${admin.userName}")
	private String adminUserName;
	
	@Override
	public void run(String... args) throws Exception {
		log.info("Admin Account Creation Started");
		if(Ur.existsByEmail(adminEmail)) {
			log.info("Admin Account is alredy Exist");
		}
		else {
			User u1 = new User(null, adminUserName, adminEmail, adminMobile, Pe.encode(adminPassword),
					UserRole.ADMIN, true);
			Ur.save(u1);
			log.info("Admin Account is created Succesfully");
		}
		
		
	}

}
