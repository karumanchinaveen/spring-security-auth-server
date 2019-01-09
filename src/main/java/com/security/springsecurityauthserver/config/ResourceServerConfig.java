package com.security.springsecurityauthserver.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@EnableResourceServer
@Configuration
public class ResourceServerConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

    	
    	 /*this security is for rest end points*/
        http.requestMatchers()
        /*here i have 2 rest end points and i am telling spring security to match only these two rest end points*/
                .antMatchers("/login", "/oauth/authorize")
                .and()
                
                /*and rest of the end points authorize them and get them authenticated for sure*/
                .authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                
                /*enable the login page*/
                .formLogin()
                /*everybody can use the form login*/
                .permitAll();
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.parentAuthenticationManager(authenticationManager)
        		
        /*inMemoryAuthentication instaed of using external databases like h2*/
        
                .inMemoryAuthentication()
                .withUser("Naveen")
                .password("naveen")
                .roles("USER");
    }
}
