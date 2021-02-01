package com.test.pemesanan.services;

import com.test.pemesanan.model.Status;
import com.test.pemesanan.model.User;
import com.test.pemesanan.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServices {

    @Autowired
    UserRepository ur;

    private Status status;

    public Status Insert(User user) {
        if (user.getUserName().isEmpty()) {
            return status = new Status("401", "username tidak boleh kosong", null);
        } else if (user.getNama().isEmpty()) {
            return status = new Status("401", "nama tidak boleh kosong", null);
        } else if (user.getAlamat().isEmpty()) {
            return status = new Status("401", "alamat tidak boleh kosong", null);
        }

        if (ur.selectUser(user.getUserName()) == null) {
            ur.insertUser(user.getUserName(), user.getNama(), user.getAlamat());
            status = new Status("400", "succses", ur.selectUser(user.getUserName()));
        } else {
            status = new Status("401", "user sudah ada", ur.selectUser(user.getUserName()));
        }
        return status;
    }
}
