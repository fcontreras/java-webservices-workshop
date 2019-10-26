package com.njug.academic.config;

import com.sun.tools.corba.se.idl.Noop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Configuration
    @EnableResourceServer
    static class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

        @Autowired
        private DataSource dataSource;

        @Bean
        public TokenStore tokenStore() {
            return new JdbcTokenStore(dataSource);
        }

        @Bean
        public PasswordEncoder passwordEncoder() {
            return new PasswordEncoder() {

                @Override
                public String encode(CharSequence rawPassword) {
                    return rawPassword.toString();
                }

                @Override
                public boolean matches(CharSequence rawPassword, String encodedPassword) {
                    return rawPassword.equals(encodedPassword);
                }
            };
        }

        public void configure(HttpSecurity http) throws Exception {
            http.authorizeRequests()
                    .antMatchers("/students/**")
                    .access("hasRole('ROLE_CLIENT')");
        }

    }

    @Configuration
    @EnableAuthorizationServer
    static  class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

        @Autowired
        private DataSource dataSource;

        @Autowired
        private AuthenticationManager authenticationManager;

        @Bean
        public TokenStore tokenStore() {
            return new JdbcTokenStore(dataSource);
        }

        @Override
        public void configure(AuthorizationServerEndpointsConfigurer enpoints) throws Exception {
            enpoints.tokenStore(tokenStore())
                    .authenticationManager(authenticationManager);
        }

        @Override
        public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
            clients.jdbc(dataSource);
        }

        @Override
        public void configure(AuthorizationServerSecurityConfigurer security) throws  Exception {
            security.allowFormAuthenticationForClients();
        }
    }

    @Configuration
    static class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

        @Bean
        @Override
        public AuthenticationManager authenticationManagerBean() throws Exception {
            return super.authenticationManagerBean();
        }
    }

}
