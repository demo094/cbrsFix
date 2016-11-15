package com.dataart.citybikerentalservicespring.service;

import com.dataart.citybikerentalservicespring.utils.WebUtil;
import freemarker.template.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by mkrasowski on 12.09.2016.
 */

@Service
public class UserMailService {
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private Configuration freeMarkerConfig;


    public void send(String toAddress, String subject, String templatePath, Map<String, Object> model) {
        MimeMessagePreparator mimeMessagePreparator = mimeMessage -> {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
            mimeMessageHelper.setTo(toAddress);
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setText(FreeMarkerTemplateUtils.
                    processTemplateIntoString(freeMarkerConfig.getTemplate(templatePath, "UTF-8"), model), true);
        };
        mailSender.send(mimeMessagePreparator);
    }

    public Map<String, Object> prepareRegistrationEmail(String email, String token) {
        Map<String, Object> model = new HashMap<>();
        model.put("email", email);
        model.put("creationDate", new Date().toString());
        model.put("actLink", WebUtil.createActivationLink(token));
        return model;
    }

    public Map<String, Object> prepareResendTokenEmail(String email, String token) {
        Map<String, Object> model = new HashMap<>();
        model.put("email", email);
        model.put("actLink", WebUtil.createActivationLink(token));
        return model;
    }

    public Map<String, Object> prepareResetPasswordEmail(String email, String tokenContents) {
        Map<String, Object> model = new HashMap<>();
        model.put("email", email);
        model.put("resetLink", WebUtil.createPasswordResetLink(tokenContents));
        return model;
    }
}
