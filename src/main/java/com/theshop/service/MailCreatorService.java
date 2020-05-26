package com.theshop.service;

import com.theshop.config.AdminConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class MailCreatorService {

    @Autowired
    AdminConfig adminConfig;

    @Autowired
    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;

    private Context createContext() {
        Context context = new Context();

        context.setVariable("theShop_url", "http://localhost:4601");
        context.setVariable("admin_name", adminConfig.getAdminName());
        context.setVariable("company_name", adminConfig.getCompanyName());
        context.setVariable("company_mail", adminConfig.getCompanyMail());
        context.setVariable("company_website", adminConfig.getCompanyWebsite());

        return context;
    }

    public String buildNewEmail(String message) {
        Context context = new Context();

        context.setVariable("theShop_url", "http://localhost:4601");
        context.setVariable("admin_name", adminConfig.getAdminName());
        context.setVariable("company_name", adminConfig.getCompanyName());
        context.setVariable("company_mail", adminConfig.getCompanyMail());
        context.setVariable("company_website", adminConfig.getCompanyWebsite());
        context.setVariable("message", message);
        context.setVariable("button", "Sign in");
        context.setVariable("goodbay_message", "Have a nice day");

        return templateEngine.process("mail/mail-template", context);
    }
}
