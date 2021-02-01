package com.test.pemesanan.model;

import javax.persistence.*;

@Entity
@Table(name = "tb_barang")
public class Barang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idBarang;

    @Column(name = "NamaBarang")
    private String namaBarang;

    @Column(name = "StokBarang")
    private String stokBarang;

    @Column(name = "FotoBarang")
    private String fotoBarang;

    public Barang() {
    }

    public Barang(Integer idBarang, String namaBarang, String stokBarang, String fotoBarang) {
        this.idBarang = idBarang;
        this.namaBarang = namaBarang;
        this.stokBarang = stokBarang;
        this.fotoBarang = fotoBarang;
    }

    public Barang(String namaBarang, String stokBarang, String fotoBarang) {
        this.namaBarang = namaBarang;
        this.stokBarang = stokBarang;
        this.fotoBarang = fotoBarang;
    }

    public Integer getIdBarang() {
        return idBarang;
    }

    public void setIdBarang(Integer idBarang) {
        this.idBarang = idBarang;
    }

    public String getNamaBarang() {
        return namaBarang;
    }

    public void setNamaBarang(String namaBarang) {
        this.namaBarang = namaBarang;
    }

    public String getStokBarang() {
        return stokBarang;
    }

    public void setStokBarang(String stokBarang) {
        this.stokBarang = stokBarang;
    }

    public String getFotoBarang() {
        return fotoBarang;
    }

    public void setFotoBarang(String fotoBarang) {
        this.fotoBarang = fotoBarang;
    }
}
