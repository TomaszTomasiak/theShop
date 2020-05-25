package com.theshop.scheduler;

import com.theshop.config.AdminConfig;
import com.theshop.dao.OrderDao;
import com.theshop.dao.UserDao;
import com.theshop.domain.Mail;
import com.theshop.domain.Order;
import com.theshop.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class EmailScheduler {

    @Autowired
    private EmailService emailService;

    @Autowired
    private AdminConfig adminConfig;

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private UserDao userDao;

    private long numberOfUsers = 0;
    private long lastDayOrdersNumber = 0;
    private long numberOfNewUsers = 0;


    @Scheduled(cron = "0 0 8 * * *")
    public void dailyMailToAdmin() {
        //long ordersNumber = orderDao.count();
        List<Order> lastDayOrders = orderDao.findAll().stream()
                .filter(o -> o.getOrdered().equals(LocalDate.now().minusDays(1L)))
                .collect(Collectors.toList());
        lastDayOrdersNumber = lastDayOrders.size();
        BigDecimal valueLastDayValue = lastDayOrders.stream()
                .map(o -> o.getTotalValue())
                .reduce(BigDecimal.ZERO, (sum, current) -> sum = sum.add(current)).setScale(2, BigDecimal.ROUND_HALF_EVEN);
        long usersNumber = userDao.count();
        numberOfNewUsers = usersNumber - numberOfUsers;

        emailService.send(new Mail(adminConfig.getAdminMail(),
                "Daily information about users and orders",
                "Currently in database you have got: " + usersNumber + " user" + addS() + ", and noticed: " + numberOfNewUsers + " user" + addS() + " from yesterday.\n" +
                        "Number of orders from previous day is: " + lastDayOrdersNumber + " with value: " + valueLastDayValue + " PLN"));

        numberOfUsers = usersNumber;
    }

    public String addS() {
        if (this.lastDayOrdersNumber == 1 || this.numberOfNewUsers == 1 || this.numberOfUsers == 1) {
            return "";
        }
        return "s";
    }
}
