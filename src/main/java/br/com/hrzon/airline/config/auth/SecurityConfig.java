package br.com.hrzon.airline.config.auth;

import br.com.hrzon.airline.domain.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  private final UserService userService;
  private final JwtAuthorizationFilter jwtAuthorizationFilter;

  public SecurityConfig(UserService userService, JwtAuthorizationFilter jwtAuthorizationFilter) {
    this.userService = userService;
    this.jwtAuthorizationFilter = jwtAuthorizationFilter;
  }

  @Bean
  public AuthenticationManager authenticationManager(HttpSecurity http, PasswordEncoder passwordEncoder)
          throws Exception {
    AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
    authenticationManagerBuilder.userDetailsService(userService).passwordEncoder(passwordEncoder);
    return authenticationManagerBuilder.build();
  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.csrf().disable()
            .authorizeRequests()
            .requestMatchers("/v3/api-docs/**", "/swagger-ui.html","/swagger-ui/**", "/api/**","/api/doc.html","/auth/**", "/consumers/**", "/passengers/**").permitAll()
            .requestMatchers(HttpMethod.GET, "/flights/**").permitAll()
            .requestMatchers(HttpMethod.GET, "/tickets/**").permitAll()
            .requestMatchers(HttpMethod.POST, "/tickets/**").permitAll()
            .anyRequest().authenticated()
            .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and().addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class);

    return http.build();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

}
