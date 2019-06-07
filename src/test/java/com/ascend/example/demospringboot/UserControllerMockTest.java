package com.ascend.example.demospringboot;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.ArrayList;
import java.util.List;
import org.assertj.core.util.Arrays;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.context.WebApplicationContext;
import com.ascend.example.demospringboot.controller.UserController;
import com.ascend.example.demospringboot.model.User;
import com.ascend.example.demospringboot.service.UserService;


@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerMockTest {

  @MockBean
  UserService userService;

  @Autowired
  private MockMvc mockMvc;

  @Test
  public void testList() throws Exception {
    // given
    User user = new User(50l, "hauvv", "hauvv@gmail.com", "hai duong");
    List<Object> lst = Arrays.asList(user);
    given(userService.getList()).willReturn(lst);

    this.mockMvc.perform(get("/api/v1/users")).andExpect(status().isOk()).andExpect(content()
        .json("[{'id': 50,'name': 'hauvv','email': 'hauvv@gmail.com','address':'hai duong'}]"));
  }

  @Test
  public void giveUser_whenGetUsers_thenReturnJsonArray() throws Exception {
    User user = new User(50l, "hauvv", "hauvv@gmail.com", "hai duong");
    List<User> users = new ArrayList<User>();
    users.add(user);
    given(userService.getList()).willReturn(users);

    ResultActions rs =
        this.mockMvc.perform(get("/api/v1/users").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(1)))
            .andExpect(jsonPath("$[0].name", is(user.getName())));
    System.err.println("-- " + rs.andReturn().getResponse().getContentAsString());
  }

  @Test
  public void testGetUserById() throws Exception {
    User user = new User(50l, "hauvv", "hauvv@gmail.com", "hai duong");
    given(userService.getUser(0l)).willReturn(user);
    this.mockMvc.perform(get("/api/v1/users/{id}", 50l)).andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
        .andExpect(jsonPath("$.id", is(50l)))
        .andExpect(jsonPath("$.name", is("hauvv")));
  }

  @Test
  public void testAdd() throws Exception {
    // given
    User user = new User(50l, "hauvv", "hauvv@gmail.com", "hai duong");

    given(userService.save(user)).willReturn(user);
    // when
    this.mockMvc.perform(post("/api/v1/users").contentType(MediaType.APPLICATION_JSON)
        .characterEncoding("UTF-8")
        .content(
            "{\"id\" : 50, \"name\" : \"hauvv\",\"email\":\"hauvv@gmail.com\",\"address\":\"hai duong\" }")
        .accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
    // .andExpect(jsonPath("$.id").exists()).andExpect(jsonPath("$.name").exists())
    // .andExpect(jsonPath("$.email").exists()).andExpect(jsonPath("$.address").exists())
    // .andReturn().getResponse().getContentAsString()
    ;
  }
}
