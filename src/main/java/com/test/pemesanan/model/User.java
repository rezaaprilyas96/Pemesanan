package com.test.pemesanan.model;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
@Table(name = "tb_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUser;

    //untuk nama databasenya
    @Column(name = "Username")
    @NotNull
    //untuk nama json
    private String userName;

    //untuk nama databsenya
    @Column(name = "nama")
    @NotNull
    //untuk nama json
    private String nama;

    //untuk nama databasenya
    @Column(name = "Alamat")
    @NotNull
    //untuk nama json
    private String alamat;

//    @OneToMany(cascade = CascadeType.ALL)
//    @JoinColumn(name = "userId", referencedColumnName = "idUser")

//    @OneToMany(mappedBy = "user")
//    private List<Pemesanan> pemesanans = new ArrayList<>();


    public User() {
    }

    public User(String userName, String nama, String alamat) {
        this.userName = userName;
        this.nama = nama;
        this.alamat = alamat;
    }

    public User(Integer idUser, String userName, String nama, String alamat) {
        this.idUser = idUser;
        this.userName = userName;
        this.nama = nama;
        this.alamat = alamat;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }
}
