package imedevo.service;

import imedevo.model.AppUser;
import imedevo.model.Role;
import imedevo.model.UserRole;
import imedevo.repository.UserRepository;
import imedevo.repository.UserRoleRepository;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import javax.swing.SwingWorker.StateValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CustomUserDetailService implements UserDetailsService {

  @Autowired
  private UserRoleRepository userRoleRepository;

  private UserRepository userRepository;

  @Autowired
  public CustomUserDetailService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    AppUser appUser = Optional.ofNullable(
        userRepository.findByUsername(username))
        .orElseThrow(() -> new UsernameNotFoundException("User not found"));

    List<UserRole> userRoles = userRoleRepository.findByUserId(appUser.getId());

    List<GrantedAuthority> authorityList = AuthorityUtils.createAuthorityList();

    if ( userRoles != null ) {
      for (UserRole userRole : userRoles) {
        String roleName = userRole.getRoleName();
        authorityList.add(new SimpleGrantedAuthority("ROLE_" + roleName));
      }
    }
//    List<GrantedAuthority> authorityListDoctor = AuthorityUtils.createAuthorityList("USER", "DOCTOR");
//    List<GrantedAuthority> authorityListSuperAdmin = AuthorityUtils.createAuthorityList("SUPER_ADMIN");

    return new User(appUser.getUsername(), appUser.getPassword(), authorityList);
  }
}