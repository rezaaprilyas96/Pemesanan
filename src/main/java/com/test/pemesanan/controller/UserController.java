package com.test.pemesanan.controller;

import com.test.pemesanan.model.Status;
import com.test.pemesanan.model.User;
import com.test.pemesanan.repository.UserRepository;
import com.test.pemesanan.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/")
public class UserController {

    @Autowired
    UserRepository ur;

    @Autowired
    UserServices us;

    @PostMapping("/registeruser")
    public Status status(@RequestBody User user) {
        return us.Insert(user);
    }

    @GetMapping("/listusers")
    public List<User> users() {
        return ur.findAll();
    }
}
