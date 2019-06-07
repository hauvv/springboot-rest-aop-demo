package com.ascend.example.demospringboot.controller;

import java.util.List;
import java.util.Optional;

import com.ascend.example.demospringboot.dto.UserDto;
import com.ascend.example.demospringboot.exception.UserNotFoundException;
import com.ascend.example.demospringboot.service.UserServiceImpl;
import lombok.extern.java.Log;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ascend.example.demospringboot.model.User;

/**
 * User's REST-FULL api
 * 
 * @author hau.vuvan
 *
 */
@Log
@RestController
@RequestMapping("/api/v1/users")
public class UserController {
  @Autowired
  UserServiceImpl service;

  @GetMapping
  public List findAll() throws Exception{
    return service.getList();
  }

  /**
   * Get user by id
   * 
   * @param id
   * @return User object
   */
//  @GetMapping(path = {"/{id}"})
//  public ResponseEntity<User> findById(@PathVariable Long id) {
//    return service.findById(id).map(record -> ResponseEntity.ok().body(record))
//        .orElse(ResponseEntity.notFound().build());
//  }
  
//  @GetMapping(path = {"/{id}"})
//  public User getUser(@PathVariable Long id) {
//    Optional<User> u = service.findById(id);
//    User user = u.get();
//    return user;
//  }

  /**
   * Get user dto by user id
   * @param id User's id
   * @return UserDTO
   */
  @GetMapping(path = {"/{id}"})
  public UserDto getUserDto(@PathVariable Long id) throws UserNotFoundException {
    Optional<User> u = service.findById(id);
    User user = u.get();
    //log.info("user model :"+ user);
    ModelMapper mapper = new ModelMapper();
    UserDto userDto = mapper.map(user,UserDto.class);
    //log.info("userDto: "+ userDto);
    return userDto;
  }
  /**
   * Create user
   *
   * @param userDto
   * {
   *     "id": 3,
   *     "user_name": "name- 3",
   *     "user_email": "email3@gmail.com",
   *     "user_adress": "address-3"
   * }
   * @return User object
   */
//  @PostMapping
//  public User create(@RequestBody User user) {
//    return service.save(user);
//  }

  @PostMapping
  public UserDto createUser(@RequestBody UserDto userDto) {
    ModelMapper mapper = new ModelMapper();
    User create = service.save(mapper.map(userDto, User.class));
    userDto.setId(create.getId());
    return userDto;
  }

  /**
   * Update user
   * 
   * @param id
   * @param user
   * @return
   */
  @PutMapping(value = "/{id}")
  public ResponseEntity<User> update(@PathVariable Long id, @RequestBody User user) {
    return service.findById(id).map(record -> {
      record.setName(user.getName());
      record.setEmail(user.getEmail());
      record.setAddress(user.getAddress());
      User updated = service.save(user);
      return ResponseEntity.ok().body(updated);
    }).orElse(ResponseEntity.notFound().build());
  }

  /**
   * Delete user
   * 
   * @param id
   * @return
   */
  @DeleteMapping(value = "/{id}")
  public ResponseEntity<?> delete(@PathVariable Long id) {
    return service.findById(id).map(record -> {
      service.deleteById(id);
      return ResponseEntity.ok().build();
    }).orElse(ResponseEntity.notFound().build());
  }
}
