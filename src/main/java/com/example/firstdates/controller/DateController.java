package com.example.firstdates.controller;

import com.example.firstdates.model.FirstDate;
import com.example.firstdates.model.User;
import com.example.firstdates.repository.DateRepository;
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

    private final DateRepository dateRepository;

    @Autowired
    public DateController(UserService userService, DateRepository dateRepository) {
        this.userService = userService;
        this.dateRepository = dateRepository;
    }

    @GetMapping("/availabledates")
    public String showAvailableDates(Model model) {
        List<FirstDate> availableDate = userService.getAvailableDates();
        model.addAttribute("availableDates", availableDate);
        return "availableDates";
    }

    @GetMapping("userdates")
    public String showDatesCreatedByUser(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = userService.getUserByUsername(authentication.getName());
        List<FirstDate> userdates = userService.getUserDates(currentUser);
        model.addAttribute("userDates", userdates);
        return "userDates";
    }

    @GetMapping("/createDateForm")
    public String showCreateDateForm() {
        return "formdate";
    }

    @PostMapping("/formdate")
    public String createDate(@RequestParam("date") String dateString, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.getUserByUsername(authentication.getName());
        LocalDate date = LocalDate.parse(dateString);
        userService.createDate(user.getIduser(), date);

        List<FirstDate> avaibleDates = userService.getAvailableDatesCreatedByOtherUsers(user);

        model.addAttribute("availableDates", avaibleDates);

        return "availableDates";
    }
    //- Metodo para eliminar una cita creada por un usuario.
    @PostMapping("/deleteDate")
    public String deleteDate(@RequestParam("dateID") Integer iddate, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = userService.getUserByUsername(authentication.getName());

        FirstDate dateToDelete = dateRepository.findById(iddate)
                .orElseThrow(() -> new IllegalArgumentException("Invalidad date id" + iddate));
        if (!dateToDelete.getUserCreateDate().equals(currentUser)) {
            throw new IllegalArgumentException("You are not authorized to delete this date.");
        }
        userService.deleteDate(dateToDelete.getIddate());
        List<FirstDate> userDates = userService.getUserDates(currentUser);
        model.addAttribute("userDates", userDates);
        return "redirect:/userdates";
    }

    @PostMapping("/joinDate")
    public String joinDate(@RequestParam("dateId") Integer dateId, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User joiningUser = userService.getUserByUsername(authentication.getName());
        userService.joinDate(dateId, joiningUser);
        return "redirect:/availabledates";
    }
    @GetMapping("/userDatesWithPendingStatus")
    public String showUserDatesWithPendingStatus(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = userService.getUserByUsername(authentication.getName());
        List<FirstDate> userDatesWithPendingStatus = userService.getUserDatesWithPendingStatus(currentUser);
        model.addAttribute("pendingInvitations", userDatesWithPendingStatus);
        return "pendingInvitations";
    }

    @GetMapping("/acceptDate")
    public String acceptDate(@RequestParam("dateId") Integer dateId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = userService.getUserByUsername(authentication.getName());
        List<FirstDate> pendingDates = userService.getUserDatesWithPendingStatus(currentUser);

        for (FirstDate date : pendingDates) {
            if (date.getIddate().equals(dateId)) {
                date.setStatus(true);
                dateRepository.save(date);
                break;
            }
        }

        return "redirect:/userDatesWithPendingStatus";
    }

    @GetMapping("/rejectDate")
    public String rejectDate(@RequestParam("dateId") Integer dateId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = userService.getUserByUsername(authentication.getName());
        List<FirstDate> pendingDates = userService.getUserDatesWithPendingStatus(currentUser);

        for (FirstDate date : pendingDates) {
            if (date.getIddate().equals(dateId)) {
                date.setStatus(false);
                dateRepository.save(date);
                break;
            }
        }

        return "redirect:/userDatesWithPendingStatus";
    }

    @GetMapping("/acceptedDates")
    public String showAcceptedDates(Model model) {
        List<FirstDate> acceptedDates = userService.getAcceptedDates();
        model.addAttribute("acceptedDates", acceptedDates);
        return "acceptedDates";
    }
}

