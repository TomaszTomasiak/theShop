package com.theshop.service;

import com.theshop.dao.UserDao;
import com.theshop.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class UserService {

    private final Logger log = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserDao userDao;

    public List<User> getUsers() {
        log.debug("Request to get all Users");
        return userDao.findAll();
    }

    public Optional<User> getUser(long id) {
        log.debug("Request to get User with id: {}", id);
        return userDao.findById(id);
    }

    public User saveUser(User user) {
        log.debug("Request to create User : {}", user);
        return userDao.save(user);
    }

    public void deleteUser(long id) {
        log.debug("Request to delete User with id : {}", id);
        userDao.deleteById(id);
    }
}

