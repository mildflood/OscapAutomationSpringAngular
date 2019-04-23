package org.hcqis.ventech.cloud.automation.configuration;

import org.hcqis.ventech.cloud.automation.model.LoginUser;
import org.hcqis.ventech.cloud.automation.model.ScanHost;
import org.hcqis.ventech.cloud.automation.model.ScanScheduler;
import org.hcqis.ventech.cloud.automation.model.ScanSession;
import org.hcqis.ventech.cloud.automation.model.User;
import org.hcqis.ventech.cloud.automation.model.manager.AuthenticationManager;
import org.hcqis.ventech.cloud.automation.model.manager.ScanManager;
import org.hcqis.ventech.cloud.automation.model.manager.SchedManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.ModelAttribute;

@Configuration
public class OscapAutomationConfiguration {
	@Bean
	public LoginUser loginUser() {
		return new LoginUser();
	}

	@Bean
	public ScanHost scanHost() {
		return new ScanHost();
	}
	@Bean
	public ScanScheduler scanScheduler() {
		return new ScanScheduler();
	}
	
	@Bean
	public ScanSession scanSession() {
		return new ScanSession();
	}
	
	@ModelAttribute("userObj")
	public User user() {
		return new User();
	}
	
	@Bean
	public ScanManager scanManager() {
		return new ScanManager();
	}
	@Bean
	public SchedManager schedManager() {
		return new SchedManager();
	}
	
	@Bean
	public AuthenticationManager authenticationManager() {
		return new AuthenticationManager();
	}
}
