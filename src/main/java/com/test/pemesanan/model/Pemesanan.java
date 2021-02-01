package com.test.pemesanan.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tb_pemesanan")
public class Pemesanan {

//    @GeneratedValue(generator = "uuid")
//    @GenericGenerator(name = "uuid", strategy = "uuid2")

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPemesanan")
    private Integer idPemesanan;

    @Column(name = "nomorRegister")
    private Long nomorRegister;

    @Column(name = "jumlahBarangPesanan")
    private String jumlahBarangPesan;

    @Column(name = "tglPemesanan")
    private Date tglPemesanan;

    @Column(name = "statusPemesanan")
    private Boolean statusPemesanan;

    @Column(name = "Keterangan")
    private String keterangan;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "UserId", referencedColumnName = "idUser", insertable = false, updatable = false)
    private User user;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "BarangId", referencedColumnName = "idBarang", insertable = false, updatable = false)
    private Barang barang;

    public Pemesanan() {
    }

    public Pemesanan(Integer idPemesanan, Long nomorRegister, String jumlahBarangPesan, Date tglPemesanan, Boolean statusPemesanan, String keterangan, User user, Barang barang) {
        this.idPemesanan = idPemesanan;
        this.nomorRegister = nomorRegister;
        this.jumlahBarangPesan = jumlahBarangPesan;
        this.tglPemesanan = tglPemesanan;
        this.statusPemesanan = statusPemesanan;
        this.keterangan = keterangan;
        this.user = user;
        this.barang = barang;
    }

    public Integer getIdPemesanan() {
        return idPemesanan;
    }

    public void setIdPemesanan(Integer idPemesanan) {
        this.idPemesanan = idPemesanan;
    }

    public Long getNomorRegister() {
        return nomorRegister;
    }

    public void setNomorRegister(Long nomorRegister) {
        this.nomorRegister = nomorRegister;
    }

    public String getJumlahBarangPesan() {
        return jumlahBarangPesan;
    }

    public void setJumlahBarangPesan(String jumlahBarangPesan) {
        this.jumlahBarangPesan = jumlahBarangPesan;
    }

    public Date getTglPemesanan() {
        return tglPemesanan;
    }

    public void setTglPemesanan(Date tglPemesanan) {
        this.tglPemesanan = tglPemesanan;
    }

    public Boolean getStatusPemesanan() {
        return statusPemesanan;
    }

    public void setStatusPemesanan(Boolean statusPemesanan) {
        this.statusPemesanan = statusPemesanan;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Barang getBarang() {
        return barang;
    }

    public void setBarang(Barang barang) {
        this.barang = barang;
    }
}
