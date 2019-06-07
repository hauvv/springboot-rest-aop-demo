package com.ascend.example.demospringboot.service;

import com.ascend.example.demospringboot.aspect.LogExecutionTime;
import com.ascend.example.demospringboot.exception.UserNotFoundException;
import com.ascend.example.demospringboot.model.User;
import com.ascend.example.demospringboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @LogExecutionTime
    @Override
    public User add(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteById(Long id) {
        Optional<User> opUser = userRepository.findById(id);
        if(opUser.isPresent())
            userRepository.deleteById(id);
        else
            throw new UserNotFoundException(id);

    }

    @LogExecutionTime
    @Override
    public List getList(){
        return userRepository.findAll();
    }

    @LogExecutionTime
    @Override
    public Optional<User> findById(Long id) {

        Optional<User> opUser = userRepository.findById(id);
        if(opUser.isPresent())
            return userRepository.findById(id);
        else
            throw new UserNotFoundException(id);
    }

    @Override
    public User getUser(Long id) {
        Optional<User> opUser = userRepository.findById(id);
        if(opUser.isPresent())
        return userRepository.findById(id).get();
        else
            throw new UserNotFoundException(id);
    }

    @LogExecutionTime
    @Override
    public User save(User user) {
        return userRepository.save(user);
    }
}
