package com.ascend.example.demospringboot.service;

import java.util.List;
import java.util.Optional;

import com.ascend.example.demospringboot.aspect.LogExecutionTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ascend.example.demospringboot.model.User;
import com.ascend.example.demospringboot.repository.UserRepository;

@Service
public interface UserService {

    @LogExecutionTime
    public User add(User user);

    public void deleteById(Long id);

    @LogExecutionTime
    public List getList();

    @LogExecutionTime
    public Optional<User> findById(Long id);

    public User getUser(Long id);

    @LogExecutionTime
    public User save(User user);
}
