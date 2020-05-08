package com.theshop.service;

import com.theshop.dao.UserDao;
import com.theshop.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public List<User> getUsers() {
        return userDao.findAll();
    }

    public User getUser(final long id) {
        return userDao.findById(id).orElse(null);
    }

    public User save(final User user) {
        return userDao.save(user);
    }

    public void delete(final long id) {
        userDao.deleteById(id);
    }
}

