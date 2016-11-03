package com.dataart.citybikerentalservicespring.view.controller.mainpage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by mkrasowski on 07.09.2016.
 */
@Controller
public class MainPageController {

    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public String mainPage() {
        return "/index";
    }

}
