package com.eazybytes.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ProjectSecurityConfig {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
            .authorizeHttpRequests()
            .requestMatchers("/myAccount", "/myBalance", "/myLoan", "myCard").authenticated()
            .requestMatchers("/notice", "/contact", "/auth").permitAll()
            .and().formLogin()
            .and().httpBasic();

        // Configuration to deny all requests
        // http.authorizeHttpRequests()
            // .anyRequest().denyAll()
            // .and().formLogin()
            // .and().httpBasic();

        // Configuration to permit all requests
        // http.authorizeHttpRequests()
            // .and().formLogin()
            // .and().httpBasic();

        return http.build();
    }

    /**
     * NoOpPasswordEncoder is not recommended for production usage.
     * Use only for non-prod.
     *
     * @return PasswordEncoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    // @Bean
    // public UserDetailsService userDetailsService(DataSource dataSource) {
        // return new JdbcUserDetailsManager(dataSource);
    // }

    // @Bean
    // public InMemoryUserDetailsManager userDetailsService() {
        // Approach 1 where we use withDefaultPasswordEncoder() method
        // while creating the user details
        /*
        UserDetails admin = User.withDefaultPasswordEncoder()
            .username("admin")
            .password("12345")
            .authorities("admin") // admin permissions
            .build();

        UserDetails user = User.withDefaultPasswordEncoder()
            .username("user")
            .password("12345")
            .authorities("read") // read permissions
            .build();

        return new InMemoryUserDetailsManager(admin, user);
        */

        // Approach 2 where we use NoOpPasswordEncoder Bean while
        // creating the user details
        /*
        UserDetails admin = User.withUsername("admin")
            .password("12345")
            .authorities("admin")
            .build();

        UserDetails user = User.withUsername("user")
            .password("12345")
            .authorities("read")
            .build();

        return new InMemoryUserDetailsManager(admin, user);
         */
    // }
}
