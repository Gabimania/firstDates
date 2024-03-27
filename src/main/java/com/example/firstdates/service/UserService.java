package com.example.firstdates.service;

import com.example.firstdates.model.FirstDate;
import com.example.firstdates.model.User;
import com.example.firstdates.repository.DateRepository;
import com.example.firstdates.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    public List<FirstDate> getUserDates(Integer iduser) {
        return dateRepository.findByUserCreateDateIduser(iduser);
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
}
