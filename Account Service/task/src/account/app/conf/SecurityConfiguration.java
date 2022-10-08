package account.app.conf;

import account.app.exception.CustomAccessDeniedHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;

@Configuration
//@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private RestAuthenticationEntryPoint restAuthenticationEntryPoint;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return new CustomAccessDeniedHandler();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic()
                .authenticationEntryPoint(restAuthenticationEntryPoint) // Handles auth error
                .and()
                .csrf().disable().headers().frameOptions().disable() // for Postman, the H2 console
                .and()
                .authorizeRequests() // manage access
                .antMatchers(HttpMethod.POST,"/api/auth/signup").permitAll()
                .antMatchers("/h2/**").permitAll()
                .antMatchers("/actuator/shutdown").permitAll()
                .antMatchers(HttpMethod.POST, "/api/auth/changepass").hasAnyAuthority("ROLE_USER","ROLE_ACCOUNTANT", "ROLE_ADMINISTRATOR")
                .antMatchers(HttpMethod.GET, "/api/empl/payment").hasAnyAuthority("ROLE_USER","ROLE_ACCOUNTANT")
                .antMatchers(HttpMethod.POST, "/api/acct/payments").hasAuthority("ROLE_ACCOUNTANT")
                .antMatchers(HttpMethod.PUT, "/api/acct/payments").hasAuthority("ROLE_ACCOUNTANT")
                .antMatchers(HttpMethod.GET, "/api/admin/user/**").hasAuthority("ROLE_ADMINISTRATOR")
                .antMatchers(HttpMethod.DELETE, "/api/admin/user/**").hasAuthority("ROLE_ADMINISTRATOR")
                .antMatchers(HttpMethod.PUT,"/api/admin/user/access/**").hasAuthority("ROLE_ADMINISTRATOR")
                .antMatchers(HttpMethod.PUT, "/api/admin/user/role/**").hasRole("ADMINISTRATOR")
                .antMatchers("/api/security/events").hasAuthority("ROLE_AUDITOR")
                .and()
                .exceptionHandling()
//                .accessDeniedHandler((request, response, accessDeniedException) -> response.sendError(403, "Access Denied!"))
                .accessDeniedHandler(accessDeniedHandler())
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS); // no session
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(userDetailsService);
        return provider;
    }


}
