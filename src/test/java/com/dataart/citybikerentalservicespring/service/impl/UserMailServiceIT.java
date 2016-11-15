package com.dataart.citybikerentalservicespring.service.impl;

import com.dataart.citybikerentalservicespring.service.UserMailService;
import com.dataart.citybikerentalservicespring.utils.WebUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.junit.Assert.assertEquals;

/**
 * Created by mkrasowski on 12.09.2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        {
                "file:src/main/webapp/WEB-INF/spring/root-context.xml",
                "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
                "file:src/main/webapp/WEB-INF/spring/appServlet/security-config.xml"

        }
)
public class UserMailServiceIT {
    @Resource
    private UserMailService userMailService;

    @Test
    public void testEmail() {
        Map<String, Object> model = new HashMap<>();
        model.put("date", new Date().toString());
        userMailService.send("cbrs.testuser1@gmail.com","Test message", "test1.ftl", model);
    }

    @Test
    public void testRegEmail(){
        String token = UUID.randomUUID().toString();
        String email = "cbrs.testuser1@gmail.com";
        Map<String, Object> model = new HashMap<>();
        model.put("email", email);
        model.put("creationDate", new Date().toString());
        model.put("actLink", WebUtil.createActivationLink(token));
        model.put("date", new Date().toString());
        userMailService.send(email,"Registration test", "registration.ftl", model);
    }

}
