package imedevo.model;

import org.springframework.stereotype.Component;

@Component
public class ChangePassword {

  private String email;
  private String password;

  public ChangePassword() {

  }

  public ChangePassword(String email, String password) {
    this.email = email;
    this.password = password;
  }

  public String getEmail() {
    return email;
  }

  public String getPassword() {
    return password;
  }
}
