package com.it353.registration.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.it353.registration.service.CustomUserDetailsSevice;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//@Autowired
//private DataSource dataSource;

@Bean
public UserDetailsService userDetailsService() {
return new CustomUserDetailsSevice();
}

@Bean
public BCryptPasswordEncoder passwordEncoder() {
return new BCryptPasswordEncoder();
}

@Bean
public DaoAuthenticationProvider authenticationProvider() {
DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
authProvider.setUserDetailsService(userDetailsService());
authProvider.setPasswordEncoder(passwordEncoder());

return authProvider;
}

@Override
protected void configure(AuthenticationManagerBuilder auth) throws Exception {
auth.authenticationProvider(authenticationProvider());
}


@Override
protected void configure(HttpSecurity http) throws Exception {
http.authorizeRequests()
.antMatchers("/users").authenticated()
.anyRequest().permitAll()
.and()
.formLogin()
.usernameParameter("email")
.defaultSuccessUrl("/users")
.permitAll()
.and()
.logout().logoutSuccessUrl("/");
//.and()
//.sessionManagement()
//.invalidSessionUrl("/login")
//you can customize the url when the session expires
//.maximumSessions(1)
//.maxSessionsPreventsLogin(true)
//.sessionRegistry(sessionRegistry());
}

@Bean
public SessionRegistry sessionRegistry() {
    SessionRegistry sessionRegistry = new SessionRegistryImpl();
    return sessionRegistry;
}

}


