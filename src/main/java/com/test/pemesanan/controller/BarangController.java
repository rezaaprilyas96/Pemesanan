package com.test.pemesanan.controller;

import com.test.pemesanan.model.Barang;
import com.test.pemesanan.model.Status;
import com.test.pemesanan.repository.BarangRepository;
import com.test.pemesanan.services.BarangServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/")
public class BarangController {

    @Autowired
    BarangRepository br;

    @Autowired
    BarangServices barangServices;

    @PostMapping("/insertbarang")
    public Status InsertBarang(@RequestBody Barang barang) {
        return barangServices.InsertBarang(barang);
    }

    @GetMapping("/listbarang")
    public List<Barang> barangs() {
        return br.findAll();
    }
}
