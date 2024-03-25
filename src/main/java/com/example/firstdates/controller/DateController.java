package com.example.firstdates.controller;

import com.example.firstdates.model.Date;
import com.example.firstdates.repository.DateRepository;
import com.example.firstdates.repository.UserRepository;
import com.example.firstdates.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class DateController {
    private final UserService userService;

    @Autowired
    public DateController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/available-dates")
    public String showAvailableDates(Model model){
        List<Date> availableDate = userService.getAvailableDates();
        model.addAttribute("availableDates", availableDate);
        return "availableDates";
    }

    @GetMapping("user-dates")
    public String showDatesCreatedByUser(Model model, @RequestParam("iduser") Integer iduser){
        List<Date> userdates = userService.getUserDates(iduser);
        model.addAttribute("userDates", userdates);
        return "userDates";
    }
}
