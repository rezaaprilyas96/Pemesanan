package com.test.pemesanan.services;

import com.test.pemesanan.model.Barang;
import com.test.pemesanan.model.Status;
import com.test.pemesanan.repository.BarangRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class BarangServices {
    @Autowired
    BarangRepository br;

    private Status status;

    public Status InsertBarang(Barang barang) {
        if (barang.getNamaBarang().isEmpty()) {
            status = new Status("401", "nama barang tidak boleh kosong");
        }
        else if (barang.getStokBarang().isEmpty()) {
            status = new Status("401", "stok barang tidak boleh kosong");
        }
        else if (barang.getFotoBarang().isEmpty()) {
            status = new Status("401", "foto barang tidak boleh kosong");
        }
        else {
            Integer idBarang = br.totalDataBarang() + 1;
            Barang obJsonBarang = new Barang(idBarang, barang.getNamaBarang(), barang.getStokBarang(), barang.getFotoBarang());
            br.insertBarang(barang.getNamaBarang(), barang.getFotoBarang(), barang.getStokBarang());
            status = new Status("200", "succses", obJsonBarang);
        }
        return status;
    }
}
