package imedevo.security;

import static imedevo.security.SecurityConstants.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import imedevo.model.AppUser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.io.IOException;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configurable
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

  private AuthenticationManager authenticationManager;

  public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
    this.authenticationManager = authenticationManager;
  }

  @Override
  public Authentication attemptAuthentication(HttpServletRequest request,
      HttpServletResponse response) throws AuthenticationException {
    try {
      AppUser appUser = new ObjectMapper().readValue(request.getInputStream(), AppUser.class);
      return authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(appUser.getUsername(), appUser.getPassword()));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
      FilterChain chain, Authentication authResult) throws IOException, ServletException {

    ZonedDateTime expirationTimeUTC = ZonedDateTime.now(ZoneOffset.UTC).plus(EXPIRATION_TIME,
        ChronoUnit.MILLIS);
    String username = ((org.springframework.security.core.userdetails.User) authResult.getPrincipal())
        .getUsername();
    String token = Jwts.builder()
        .setSubject(username)
        .setExpiration(Date.from(expirationTimeUTC.toInstant()))
        .signWith(SignatureAlgorithm.HS256, SECRET)
        .compact();
    response.getWriter().write(token);
    response.addHeader(HEADER_STRING, TOKEN_PREFIX + token);
  }
}