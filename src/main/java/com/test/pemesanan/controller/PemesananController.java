package com.test.pemesanan.controller;

import com.test.pemesanan.model.Barang;
import com.test.pemesanan.model.Pemesanan;
import com.test.pemesanan.model.Status;
import com.test.pemesanan.model.User;
import com.test.pemesanan.repository.BarangRepository;
import com.test.pemesanan.repository.PemesananRepository;
import com.test.pemesanan.repository.UserRepository;
import com.test.pemesanan.services.PemesananServicec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/")
public class PemesananController {

    @Autowired
    PemesananRepository pr;

    @Autowired
    PemesananServicec pemesananServicec;

    @GetMapping("/listpemesanan")
    private List<Pemesanan> pemesanans() {
        return pr.findAll();
    }

    @PostMapping("/insertpemesanan")
    private Status status(@RequestBody Pemesanan pemesanan) {
        return pemesananServicec.InsertPemesanan(pemesanan);
    }

    @PostMapping("/historypemesanan")
    private Status statusHistoryPemesanan(@RequestBody Pemesanan pemesanan) {
        return pemesananServicec.statusHistoryPemesanan(pemesanan);
    }
}