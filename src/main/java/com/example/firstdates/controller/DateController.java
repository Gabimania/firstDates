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

    @GetMapping("/createDateForm")
    public String showCreateDateForm() {
        return "formdate"; // Nombre de la vista del formulario de creación de citas
    }

    @PostMapping("/formdate")
    public String createDate(@RequestParam("date") String dateString) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.getUserByUsername(authentication.getName());
        LocalDate date = LocalDate.parse(dateString);
        userService.createDate(user.getIduser(),date);

        return "availableDates";
    }
}

//- Metodo para eliminar una cita creada por un usuario.
//-Método para que un usuario se meta en una cita creada por otro usuario
//-Método para que el usuario cambie el status de la cita.
//-Método para ver todas las citas disponibles para un usuario
//- Método para ver todas las citas donde el status sea 1.