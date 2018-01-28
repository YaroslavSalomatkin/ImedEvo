package imedevo.configuration;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Spring security configuration.
 */

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  private DataSource dataSource;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
        .csrf().disable()
        .authorizeRequests()
        .antMatchers("/", "/home").permitAll()
        .antMatchers("/users/login", "users/registration", "/doctors/getall", "/doctors/getdoctor/*",
            "/clinics/getall", "/clinics/getclinic/*", "/forgot/**", "/clients/**")
        .permitAll()
        .antMatchers("/users/getuser/*", "/users/updateuser").hasAuthority("USER")
        .antMatchers("/doctors/updatedoctor").hasAuthority("DOCTOR")
        .antMatchers("/doctors/createdoctor", "/doctors/updatedoctor",
            "doctors/deletedoctor/*", "clinics/createclinic", "clinics/updateclinic",
            "clinics/deleteclinic/*").hasAuthority("CLINIC_ADMIN")
        .antMatchers("/users/getall", "/users/getuser/*", "/users/createuser", "/users/updateuser",
            "/users/deleteuser/*", "/users/createdoctor", "/doctors/updatedoctor",
            "doctors/deletedoctor/*", "clinics/createclinic", "clinics/updateclinic",
            "clinics/deleteclinic/*").hasAuthority("SUPER_ADMIN")
//        .antMatchers("/**").hasAuthority("SYSTEM")
        .and()
        .logout().logoutUrl("/users/logout")
        .clearAuthentication(true).logoutSuccessUrl("/users/login")
        .and()
        .httpBasic()
        .and();
  }

  @Autowired
  public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    auth
        .jdbcAuthentication()
        .dataSource(dataSource)
        .usersByUsernameQuery(
            "select u.email as username, u.password, true as enabled from users u "
                + "where u.email=?")
        .authoritiesByUsernameQuery(
            "select u.email as username, r.role from users u "
                + "join user_roles ur "
                + "join roles r "
                + "on r.id=ur.role_id and u.id=ur.user_id "
                + "where u.email=?");
  }
}
