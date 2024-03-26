package com.example.firstdates.controller;

import com.example.firstdates.model.User;
import com.example.firstdates.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Controller
public class loginController {
    private UserService userService;

    @Value("${upload.path}")
    private String uploadPath;
    @Autowired
    public loginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login(){
        return  "login";
    }

    @GetMapping("/register")
    public String register(){
        return "register";
    }

    @PostMapping("/register")
    public String postRegister(@ModelAttribute User user, @RequestParam("image") MultipartFile imageFile) {
        if (!imageFile.isEmpty()) {
            try {
                String uniqueFileName = UUID.randomUUID().toString() + "_" + imageFile.getOriginalFilename();

                Files.copy(imageFile.getInputStream(), Paths.get(uploadPath, uniqueFileName), StandardCopyOption.REPLACE_EXISTING);
                user.setImg(uniqueFileName);


            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        userService.createUser(user);
        System.out.println(user);

        return "register";
    }
}
