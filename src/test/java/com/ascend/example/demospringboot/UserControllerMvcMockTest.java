package com.ascend.example.demospringboot;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import com.ascend.example.demospringboot.model.User;
import com.ascend.example.demospringboot.service.UserService;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class UserControllerMvcMockTest {

  @MockBean
  UserService userService;

  @Autowired
  private MockMvc mockMvc;

  @Before
  public void setUp() {
    // given
    User user = new User(50l, "hauvv", "hauvv@gmail.com", "hai duong");
    List<User> users = new ArrayList<User>();
    users.add(user);
    Optional<User> opUser = Optional.of(user);
    // get list
    when(userService.getList()).thenReturn(users);
    // find by id
    when(userService.findById(Mockito.anyLong())).thenReturn(opUser);
    // create
    when(userService.save(user)).thenReturn(user);
  }

  @Test
  public void testList() throws Exception {
    this.mockMvc.perform(get("/api/v1/users").characterEncoding("UTF-8")).andExpect(status().isOk())
        // .andDo(print())
        .andExpect(jsonPath("$[0].name", is("hauvv")));
  }

  @Test
  public void testGetUserById() throws Exception {
    this.mockMvc.perform(get("/api/v1/users/{id}", 42L).characterEncoding("UTF-8"))
        // .andDo(print())
        .andExpect(status().isOk()).andExpect(jsonPath("$.id", is(50)));
  }

  @Test
  public void testAdd() throws Exception {
    this.mockMvc.perform(post("/api/v1/users").characterEncoding("UTF-8")
        .contentType(MediaType.APPLICATION_JSON)
        .content(
            "{\"id\" : 50, \"name\" : \"hauvv\",\"email\":\"hauvv@gmail.com\",\"address\":\"hai duong\" }")
        .accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andDo(print());
  }

  @Test
  public void testUpdate() throws Exception {
    this.mockMvc.perform(put("/api/v1/users/1").characterEncoding("UTF-8")
        .contentType(MediaType.APPLICATION_JSON)
        .content(
            "{\"id\" : 50, \"name\" : \"hauvv-edit\",\"email\":\"hauvv@gmail.com\",\"address\":\"hai duong\" }")
        .accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
        .andExpect(jsonPath("$.name", is("hauvv-edit"))).andDo(print());
  }

  @Test
  public void testDelete() throws Exception {
    this.mockMvc.perform(delete("/api/v1/users/1").characterEncoding("UTF-8")
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
        .andDo(print());
  }
}
