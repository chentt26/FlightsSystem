package com.example.flightsSystem.security;

import com.example.flightsSystem.daos.Connections;
import com.example.flightsSystem.daos.UsersDAO;
import com.example.flightsSystem.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;
import java.security.Security;
import java.sql.Statement;

@EnableWebSecurity
public class MySec extends WebSecurityConfigurerAdapter {
@Autowired
    private DataSource dataSource;
@Autowired
private UsersDAO dao;

private JwtUtil jwtUtil;

    Statement statement = Connections.getStatement();
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeHttpRequests()
                .antMatchers("/admin/**").hasRole("Administrator")
                .antMatchers("/Customer/**").hasRole("Customer")
                .antMatchers("/Airline/**").hasRole("Airline Company")
                .antMatchers("/ContactUs/**","/Anonymous/**").permitAll() //allow any user to connect to the page
                .and().formLogin();


    }
    @Bean
    public PasswordEncoder passwordEncoderEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource).
                    usersByUsernameQuery("Select \"username\",\"password\",true from users  WHERE \"username\" = ?")
                    .authoritiesByUsernameQuery("select users.\"username\",user_roles.\"role_name\" from \"user_roles\"\n" +
                            "JOIN \"users\" on user_roles.\"id\"=users.\"user_role\"\n" +
                            "WHERE users.\"username\" = ?").rolePrefix("ROLE_");


    }


}