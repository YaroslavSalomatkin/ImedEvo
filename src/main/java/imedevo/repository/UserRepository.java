package imedevo.repository;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import imedevo.httpStatuses.NoSuchUserException;
import imedevo.httpStatuses.UserAlreadyExistsException;
import imedevo.model.User;

@Repository
public class UserRepository {

  private List<User> users = new ArrayList();

  public UserRepository() {
    User user = new User();
    user.setId(1);
    user.setName("Olya");
    user.setEmail("olya@test.com");
    user.setPhone("0931111111");
    user.setBirthDate(1986);

    users.add(user);

    user = new User();
    user.setId(2);
    user.setName("Petya");
    user.setEmail("petya@test.com");
    user.setPhone("0982222222");
    user.setBirthDate(1973);

    users.add(user);

    user = new User();
    user.setId(3);
    user.setName("Masha");
    user.setEmail("masha@test.com");
    user.setPhone("0673333333");
    user.setBirthDate(1993);

    users.add(user);

    user = new User();
    user.setId(4);
    user.setName("Sasha");
    user.setEmail("sasha@test.com");
    user.setPhone("0944444444");
    user.setBirthDate(2000);

    users.add(user);

    user = new User();
    user.setId(5);
    user.setName("Klavdiya Olegovna");
    user.setEmail("KlavdiyaOlegovna@test.com");
    user.setPhone("0482654321");
    user.setBirthDate(1941);

    users.add(user);
  }

  public List<User> findAll() {
    return users;
  }

  public User findById(long id) {
    for (User user : users) {
      if (user.getId() == id) {
        return user;
      }
    }
    throw new NoSuchUserException();
  }

  public User save(User newUser) {
    for (User user : users) {
      if (user.getEmail().equals(newUser.getEmail())) {
        throw new UserAlreadyExistsException();
      }
    }
    users.add(newUser);
    return newUser;
  }

  public User updateUser(User updatedUser) {
    for (User user : users) {
      if (user.getId() == updatedUser.getId()) {
        users.remove(user);
        users.add(updatedUser);
        return updatedUser;
      }
    }
    throw new NoSuchUserException();
  }

  public void delete(long id) {
    if (users.contains(findById(id))) {
      users.remove(findById(id));
    } else {
      throw new NoSuchUserException();
    }
  }

  public User getUserByEmail(User user) {
    Iterator<User> iterator = users.iterator();
    while (iterator.hasNext()) {
      User u1 = iterator.next();
      if (u1.getEmail().equals(user.getEmail())) {
        return u1;
      }
    }
    return null;
  }
}