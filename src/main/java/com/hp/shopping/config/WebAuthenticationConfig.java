package com.hp.shopping.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;

@Configuration
public class WebAuthenticationConfig extends GlobalAuthenticationConfigurerAdapter {

	@Override
	public void init(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("ashish").password("{noop}test").roles("USER", "OPERATOR");
		auth.inMemoryAuthentication().withUser("ash1").password("{noop}test2").roles("ADMIN");

	}

}
