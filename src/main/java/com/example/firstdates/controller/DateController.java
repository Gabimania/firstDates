package com.example.firstdates.controller;

import com.example.firstdates.model.FirstDate;
import com.example.firstdates.model.User;
import com.example.firstdates.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Controller
public class DateController {
    private final UserService userService;

    @Autowired
    public DateController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/availabledates")
    public String showAvailableDates(Model model){
      List<FirstDate> availableDate = userService.getAvailableDates();
       model.addAttribute("availableDates", availableDate);
        return "availableDates";
    }

    @GetMapping("userdates")
    public String showDatesCreatedByUser(Model model, @RequestParam("iduser") Integer iduser){
        List<FirstDate> userdates = userService.getUserDates(iduser);
        model.addAttribute("userDates", userdates);
        return "userDates";
    }

    @PostMapping("/formdate")
    public String createDate(@RequestParam("date") String dateString) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        LocalDate date = LocalDate.parse(dateString);
        userService.createDate(user.getIduser(),date);

        return "";
    }
}
