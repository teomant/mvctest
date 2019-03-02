package org.teomant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.teomant.server.GetDateTimeResponse;
import org.teomant.soap.DateTimeClient;


@Controller
@RequestMapping("/")
public class PagesController {

    @Autowired
    DateTimeClient client;

    @GetMapping(value = "/")
    public String getRegistration(Model model) {

        GetDateTimeResponse response = client.getDateTime();
        model.addAttribute("dateTime", response.getDate().toString());
        return "index";

    }

}
