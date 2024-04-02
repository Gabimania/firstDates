package com.example.firstdates.service;

import com.example.firstdates.model.FirstDate;
import com.example.firstdates.model.User;
import com.example.firstdates.repository.DateRepository;
import com.example.firstdates.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final DateRepository dateRepository;
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, DateRepository dateRepository) {
        this.userRepository = userRepository;
        this.dateRepository = dateRepository;
        passwordEncoder=new BCryptPasswordEncoder();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Usuario no encontrado con nombre de usuario: " + username);
        }
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                new ArrayList<>());
    }

    public void createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public List<FirstDate> getAvailableDates() {
        return dateRepository.findByUserReceiveDateIsNull();
    }

    public List<FirstDate> getAvailableDatesCreatedByOtherUsers(User currentUser) {
        List<FirstDate> availableDates = dateRepository.findByUserReceiveDateIsNull();
        availableDates.removeIf(date -> date.getUserCreateDate().equals(currentUser));

        return availableDates;

    }

    public List<FirstDate> getUserDates(User currentUser) {
        return dateRepository.findByUserCreateDate(currentUser);
    }

    public User getUserByUsername(String name){
        return userRepository.findByUsername(name);
    }

    public void createDate(Integer userId, LocalDate date) {
        User user = userRepository.findById(userId)
                    .orElseThrow(()-> new RuntimeException("User not found"+ userId));
        FirstDate newDate = new FirstDate();
        newDate.setUserCreateDate(user);
        newDate.setDate(date);
        dateRepository.save(newDate);

    }


    public void deleteDate(Integer idDate){
        FirstDate firstDate = dateRepository.findById(idDate)
                .orElseThrow(() -> new IllegalArgumentException("Id not found" + idDate));
                dateRepository.deleteById(firstDate.getIddate());

    }

    public void joinDate(Integer dateId, User joiningUser) {
        FirstDate dateToJoin = dateRepository.findById(dateId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid date Id:" + dateId));
        dateToJoin.setUserReceiveDate(joiningUser);
        dateRepository.save(dateToJoin);

}

    public List<FirstDate> getUserDatesWithPendingStatus(User user) {
        List<FirstDate> userDates = dateRepository.findByUserCreateDate(user);
        List<FirstDate> pendingDates = new ArrayList<>();

        for (FirstDate date : userDates) {
            // Verifica si el estado es null
            if (date.getUserReceiveDate() != null && date.isStatus() == null) {
                pendingDates.add(date);
            }
        }

        return pendingDates;
    }

    public List<FirstDate> getAcceptedDates() {
        return dateRepository.findByStatus(true);
    }


}
