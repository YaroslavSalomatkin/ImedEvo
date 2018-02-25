//package imedevo;
//
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import imedevo.controller.UserController;
//import imedevo.model.AppUser;
//import imedevo.repository.UserRepository;
//import imedevo.service.UserService;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//
//@RunWith(SpringRunner.class)
//@WebMvcTest(value = UserController.class, secure = false)
//public class UserControllerTest {
//
//  @Autowired
//  private MockMvc mockMvc;

//  @MockBean
//  private UserService userService;
//  private UserRepository userRepository;
//  private AppUser appUser = new AppUser();
//  private List<AppUser> users = new ArrayList<>();
//
//  public String objectToJson(Object object) throws Exception {
//    ObjectMapper mapper = new ObjectMapper();
//    return mapper.writeValueAsString(object);
//  }
//
//  @Test
//  public void getAll() throws Exception {
//    when(userService.getAll()).thenReturn(Arrays.asList(appUser));
//
//    mockMvc.perform(get("/users/getall"))
//        .andDo(print())
//        .andExpect(content().string(objectToJson(Arrays.asList(appUser))));
//  }
//}
