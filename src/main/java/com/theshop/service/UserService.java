package com.theshop.service;

import com.theshop.config.AdminConfig;
import com.theshop.dao.UserDao;
import com.theshop.domain.Mail;
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

    @Autowired
    private UserDao userDao;

    @Autowired
    EmailService emailService;

    @Autowired
    AdminConfig adminConfig;

    private final Logger log = LoggerFactory.getLogger(UserService.class);

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
        User createdOrder = userDao.save(user);
        emailService.send(new Mail(
                adminConfig.getAdminMail(),
                "New user",
                "Added new user with mail adress: " + createdOrder.getMailAdress()
        ));
        return userDao.save(user);
    }

    public void deleteUser(long id) {
        log.debug("Request to delete User with id : {}", id);
        userDao.deleteById(id);
    }

    public Optional<User> getUserByMail(String mail){
        return userDao.findByMailAdress(mail);
    }
}
