package imedevo.configuration;

import imedevo.security.JWTAuthenticationFilter;
import imedevo.security.JWTAuthorizationFilter;
import imedevo.service.CustomUserDetailService;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Spring security configuration.
 */

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  private BCryptPasswordEncoder bCryptPasswordEncoder;

  @Autowired
  private DataSource dataSource;

  private final CustomUserDetailService customUserDetailService;

  @Autowired
  public WebSecurityConfig(CustomUserDetailService customUserDetailService) {
    this.customUserDetailService = customUserDetailService;
  }

//
//  @Override
//  public void configure(WebSecurity web) throws Exception {
//    web.ignoring()
//        .antMatchers("/assets/**", "/index.html", "/**", "/bootstrap/**", "/css/**");
//  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {

    http
        .cors().and().csrf().disable().authorizeRequests()
        .antMatchers(HttpMethod.POST, "/**").permitAll()
        .antMatchers(HttpMethod.POST, "/users/login").permitAll()
        .antMatchers(HttpMethod.POST, "/users/registration").permitAll()
        .antMatchers(HttpMethod.GET, "/doctors/getall").permitAll()
        .antMatchers(HttpMethod.GET, "/doctors/*").permitAll()
        .antMatchers(HttpMethod.GET, "/clinics/getall").permitAll()
        .antMatchers(HttpMethod.GET, "/clinics/*").permitAll()
        .antMatchers(HttpMethod.GET, "/laboratories/getall").permitAll()
        .antMatchers(HttpMethod.GET, "/laboratories/*").permitAll()
        .antMatchers(HttpMethod.GET, "/diagnostics/getall").permitAll()
        .antMatchers(HttpMethod.GET, "/diagnostics/*").permitAll()
        .antMatchers(HttpMethod.POST, "/forgot/reset").permitAll()
        .antMatchers(HttpMethod.POST, "/forgot/newpassword").permitAll()
        .antMatchers(HttpMethod.GET, "/search/byanyparams").permitAll()
        .anyRequest().authenticated()
//        .antMatchers("/users/**")
//        .hasAnyRole("USER", "SUPER_ADMIN", "DOCTOR", "CLINIC_ADMIN")
//        .antMatchers("/users/updateuser")
//        .hasAnyRole("USER", "SUPER_ADMIN", "DOCTOR", "CLINIC_ADMIN")
//        .antMatchers("/doctors/updatedoctor")
//        .hasAnyRole("DOCTOR", "CLINIC_ADMIN", "SUPER_ADMIN")
//        .antMatchers("/users/createdoctor", "/doctors/deletedoctor/*",
//            "/clinics/createclinic", "/clinics/updateclinic", "/clinics/deleteclinic/*")
//        .hasAnyRole("CLINIC_ADMIN", "SUPER_ADMIN")
//        .antMatchers("/users/getall")
//        .hasRole("SUPER_ADMIN")

        .and()
        .addFilter(new JWTAuthenticationFilter(authenticationManager()))
        .addFilter(new JWTAuthorizationFilter(authenticationManager(), customUserDetailService));
//        .and()
//        .logout().logoutUrl("/users/logout").deleteCookies("JSESSIONID")
//        .clearAuthentication(true).logoutSuccessUrl("/users/login");
//        .csrf()
//        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
  }

  @Autowired
  public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(customUserDetailService).passwordEncoder(bCryptPasswordEncoder);
//    auth
//        .jdbcAuthentication()
//        .dataSource(dataSource)
//        .usersByUsernameQuery(
//            "select u.email as username, u.password, true as enabled from users u "
//                + "where u.email=?")
//        .authoritiesByUsernameQuery(
//            "select u.email as username, r.role from users u "
//                + "join user_roles ur "
//                + "join roles r "
//                + "on r.id=ur.role_id and u.id=ur.user_id "
//                + "where u.email=?");
  }
}
