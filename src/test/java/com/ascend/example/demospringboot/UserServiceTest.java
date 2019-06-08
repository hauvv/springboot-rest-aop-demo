package com.ascend.example.demospringboot;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import com.ascend.example.demospringboot.model.User;
import com.ascend.example.demospringboot.repository.UserRepository;
import com.ascend.example.demospringboot.service.UserService;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

  @Mock
  UserRepository userRepository;
  @InjectMocks
  UserService userService;

  @Test
  public void whenFindAll_thenReturnUserList() {
    // give
    User entity = new User(11L, "hauvv", "hauvv@gmail.com", "hai duong");
    List<User> expectUsers = new ArrayList<User>();
    expectUsers.add(entity);

    doReturn(expectUsers).when(userRepository).findAll();

    // when
    List<User> actualUser = userService.getList();
    // then
    assertThat(actualUser).isEqualTo(expectUsers);
  }

  @Test
  public void whenFindOne_thenReturnUser() {
    Optional<User> entity = Optional.of(new User(11L, "hauvv", "hauvv@gmail.com", "hai duong"));
    doReturn(entity).when(userRepository).findById(11L);
    // when
    Optional<User> actualUser = userService.findById(11L);
    // then
    assertThat(actualUser).isEqualTo(entity);
  }

  @Test
  public void whenAdd_thenReturnUser() {
    User entity = new User(11L, "hauvv", "hauvv@gmail.com", "hai duong");
    doReturn(entity).when(userRepository).save(entity);

    User u = userService.add(entity);
    assertThat(entity).isEqualTo(u);
  }
  
//  @Test
//  public void whenDelete_thenReturnUser() {
//    //User entity = userService.findById(1l).get();
//    User entity = new User(1l, "hauvv", "hauvv@gmail.com", "hai duong");
//    Optional<User> opU= Optional.of(entity);
//    doReturn(opU).when(userRepository).findById(1L);
//    
//    Mockito.when(userService.findById(1l)).thenReturn(opU).thenReturn(null);
//    
////    doReturn(entity).when(userRepository).findById(1l).get();
////    
////    User u = userService.save(entity);
////    assertThat(entity)
//  }
}
