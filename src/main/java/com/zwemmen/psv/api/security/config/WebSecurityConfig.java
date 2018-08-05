package com.zwemmen.psv.api.security.config;

import com.zwemmen.psv.api.security.authentication.AuthenticationFailureHandler;
import com.zwemmen.psv.api.security.authentication.AuthenticationSuccessHandler;
import com.zwemmen.psv.api.security.authentication.coach.CoachUserService;
import com.zwemmen.psv.api.security.authentication.swimmer.SwimmerUserService;
import com.zwemmen.psv.api.security.authorization.CustomAccessDeniedHandler;
import com.zwemmen.psv.api.security.token.TokenAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;

/**
 * Security configuration.
 *
 * @author afernandez
 */
@Configuration
public class WebSecurityConfig {

    @Configuration
    @Order(1)
    public static class SwimmerSecurityConfig extends WebSecurityConfigurerAdapter {
        @Autowired
        private SwimmerUserService swimmerUserService;
        @Autowired
        private UnauthorizedEntryPoint unauthorizedEntryPoint;
        @Autowired
        private CustomAccessDeniedHandler customAccessDeniedHandler;
        @Autowired
        private AuthenticationSuccessHandler authenticationSuccessHandler;
        @Autowired
        private AuthenticationFailureHandler authenticationFailureHandler;
        @Autowired
        private TokenAuthenticationService tokenAuthenticationService;

        @Bean
        public PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(swimmerUserService).passwordEncoder(passwordEncoder());
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                .antMatcher("/user/**")
                .addFilterAfter(new StatelessUserAuthenticationFilter(tokenAuthenticationService), UsernamePasswordAuthenticationFilter.class)
                .csrf().disable()
                .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .and()
                .authorizeRequests()
                    .anyRequest().hasRole("SWIMMER")
                    .anyRequest().authenticated()
                    .and()
                .exceptionHandling()
                    .authenticationEntryPoint(unauthorizedEntryPoint)
                    .accessDeniedHandler(customAccessDeniedHandler)
                    .and()
                .formLogin()
                    .loginProcessingUrl("/user/user_login")
                    .successHandler(authenticationSuccessHandler)
                    .failureHandler(authenticationFailureHandler)
                    .usernameParameter("username")
                    .passwordParameter("password")
                    .permitAll()
                    .and()
                .logout()
                    .logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler())
                    .permitAll();
        }
    }

    @Configuration
    @Order(2)
    public static class CoachSecurityConfig extends WebSecurityConfigurerAdapter {
        @Autowired
        private CoachUserService coachUserService;
        @Autowired
        private UnauthorizedEntryPoint unauthorizedEntryPoint;
        @Autowired
        private CustomAccessDeniedHandler customAccessDeniedHandler;
        @Autowired
        private AuthenticationSuccessHandler authenticationSuccessHandler;
        @Autowired
        private AuthenticationFailureHandler authenticationFailureHandler;
        @Autowired
        private TokenAuthenticationService tokenAuthenticationService;

        @Bean
        public PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(coachUserService).passwordEncoder(passwordEncoder());
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                .antMatcher("/management/**")
                .addFilterAfter(new StatelessCoachAuthenticationFilter(tokenAuthenticationService), UsernamePasswordAuthenticationFilter.class)
                .csrf().disable()
                .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .and()
                .authorizeRequests()
                    .anyRequest().hasRole("COACH")
                    .anyRequest().authenticated()
                    .and()
                .exceptionHandling()
                    .authenticationEntryPoint(unauthorizedEntryPoint)
                    .accessDeniedHandler(customAccessDeniedHandler)
                    .and()
                .formLogin()
                    .loginProcessingUrl("/management/coach_login")
                    .successHandler(authenticationSuccessHandler)
                    .failureHandler(authenticationFailureHandler)
                    .usernameParameter("username")
                    .passwordParameter("password")
                    .permitAll()
                    .and()
                .logout()
                    .logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler())
                    .permitAll();
        }
    }
}