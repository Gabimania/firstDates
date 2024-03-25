package com.example.firstdates.service;

import com.example.firstdates.model.Date;
import com.example.firstdates.model.User;
import com.example.firstdates.repository.DateRepository;
import com.example.firstdates.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final DateRepository dateRepository;


    public UserService(UserRepository userRepository, DateRepository dateRepository) {
        this.userRepository = userRepository;
        this.dateRepository = dateRepository;
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
        userRepository.save(user);
    }

    public List<Date> getAvailableDates() {
        return dateRepository.findByUserReceiveDateIsNull();
    }

    public List<Date> getUserDates(Integer iduser) {
        return dateRepository.findByUserCreateDateIduser(iduser);
    }
}
