package org.teomant.controller;


import hello.wsdl.GetCountryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.teomant.soap.CountryClient;


@Controller
@RequestMapping("/")
public class PagesController {

    @Autowired
    CountryClient client;

    @GetMapping(value = "/")
    public String getRegistration(Model model) {

        GetCountryResponse response = client.getCountry("Spain");
        model.addAttribute("currency", response.getCountry().getCurrency());
        return "index";

    }

}
