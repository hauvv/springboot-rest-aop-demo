package com.ascend.example.demospringboot;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.List;
import java.util.Optional;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import com.ascend.example.demospringboot.model.User;
import com.ascend.example.demospringboot.repository.UserRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class UserRepositoryTest {

  @Autowired
  private TestEntityManager testEntityManager;

  @Autowired
  private UserRepository userRespository;

  @Before
  public void setup() {
    User entity = new User(11L, "hauvv", "hauvv@gmail.com", "hai duong");
    testEntityManager.merge(entity);
  }

  @Test
  public void whenFindAll_thenReturnUserList() {
    List<User> users = userRespository.findAll();
    // then
    assertThat(users).hasSize(10);
  }

  @Test
  public void whenFindOne_thenReturnUser() {
    // when
    User user = userRespository.findById(1L).get();
    // then
    assertThat(user.getName()).isEqualTo("name- 1");
  }

  @Test
  public void whenCreate_thenReturnUser() {
    User entity = new User(11L, "hauvv", "hauvv@gmail.com", "hai duong");
    User newUser = userRespository.save(entity);
    assertThat(newUser.getName()).isEqualTo("hauvv");
  }
  @Test
  public void whenDelete_thenReturnObj() {
    when(userRespository.findById(0l)).thenReturn(Optional.of(new User()));
    userRespository.deleteById(0l);
    //verify(userRespository,times(1)).delete(0l);
    verify(userRespository, times(1)).deleteById(0L);

//    List<User> users = userRespository.findAll();
//    
//    userRespository.deleteById(1l);
//    // then
//    assertThat(users).hasSize(10);
  }
}
