package za.co.bmw.vehicestock.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 *
 * @author cliff
 */
  @Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private static final String[] WHITELIST = {
            "/v2/api-docs",
            "/v3/api-docs",
            "/**/v3/api-docs",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui.html",
            "**/swagger-ui.html",
            "/**/swagger-ui.html**",
            "/swagger-ui.html**",
            "/webjars/**"
    };
    
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(HttpMethod.PUT, WHITELIST).permitAll();
        http.csrf().disable();
    } 
}    
