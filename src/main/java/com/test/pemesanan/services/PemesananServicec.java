package com.test.pemesanan.services;

import com.test.pemesanan.model.Barang;
import com.test.pemesanan.model.Pemesanan;
import com.test.pemesanan.model.Status;
import com.test.pemesanan.model.User;
import com.test.pemesanan.repository.BarangRepository;
import com.test.pemesanan.repository.PemesananRepository;
import com.test.pemesanan.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Service
public class PemesananServicec {

    @Autowired
    PemesananRepository pr;

    @Autowired
    UserRepository ur;

    @Autowired
    BarangRepository br;

    private Status status;

    private DateTimeFormatter myFormatObj;
    private String formattedDate;

    public Status InsertPemesanan(Pemesanan pemesanan){
        //jika data user tidak ada
        if (ur.selectIdUser(pemesanan.getUser().getIdUser()) == null) {
            status = new Status("401", "tidak bisa insert pesanan karena user tidak ada");
        }
        //jika databarang tidak ada
        else if (br.selectIdBarang(pemesanan.getBarang().getIdBarang()) == null) {
            status = new Status("401", "tidak bisa insert pesanan karena barang tidak ada");
        }
        else if (pemesanan.getJumlahBarangPesan().isEmpty()){
            status = new Status("401", "tidak bisa insert pesanan karena jumlah barang pesanan kosong");
        }
        //jika nomor register kosong maka lakukan input atau insert
        else if (pr.selectNoRegister(pemesanan.getNomorRegister()) == null) {
            //pemesanan
            Integer pemesananIdBarang = pemesanan.getBarang().getIdBarang();
            String pemesananJumlahBarang = pemesanan.getJumlahBarangPesan();
            String pemesananKeterangan = pemesanan.getKeterangan();
            Boolean pemesananStatus = pemesanan.getStatusPemesanan();

            //user
            Integer idUser = pemesanan.getUser().getIdUser();
            String userName = ur.selectIdUser(pemesanan.getUser().getIdUser()).getUserName();
            String nama = ur.selectIdUser(pemesanan.getUser().getIdUser()).getNama();
            String alamat = ur.selectIdUser(pemesanan.getUser().getIdUser()).getAlamat();

            //barang
            Integer idBarang = pemesanan.getBarang().getIdBarang();
            String namaBarang = br.selectIdBarang(pemesanan.getBarang().getIdBarang()).getNamaBarang();
            String stokBarang = br.selectIdBarang(pemesanan.getBarang().getIdBarang()).getStokBarang();
            String fotoBarang = br.selectIdBarang(pemesanan.getBarang().getIdBarang()).getFotoBarang();

            //jika stok barang kosong
            if (Integer.valueOf(stokBarang) <= 0) {
                status = new Status("401", "stok habis " + stokBarang);
            }
            //jika stok barang lebih kecil dari pesanan
            else if (Integer.valueOf(stokBarang) < Integer.valueOf(pemesananJumlahBarang)) {
                status = new Status("401", "stok kurang tersisa " + stokBarang);
            } else {
                //
                Long banyakData = pr.totalDataPemesanan() + 1;

                //format date
                LocalDateTime myDateObj = LocalDateTime.now();

                //format date untuk registerNomor
                myFormatObj = DateTimeFormatter.ofPattern("ddMMyyyy");
                formattedDate = myDateObj.format(myFormatObj);

                //banyakdata+userid+barangid+formatedata
                String textRegisterNomor = banyakData.toString() + pemesanan.getUser().getIdUser().toString() + pemesanan.getBarang().getIdBarang().toString() + formattedDate;

                //for insert to tb_pemesanan
                pr.insertPemesanan(pemesananIdBarang, pemesananJumlahBarang, pemesananKeterangan, Long.parseLong(textRegisterNomor), pemesananStatus, new Date(), pemesanan.getUser().getIdUser());

                //for making obyek json user
                User obUserJson = new User(idUser, userName, nama, alamat);

                //procces stok barang minus jumlah pesanan
                Integer minusStokBarang = Integer.valueOf(stokBarang) - Integer.valueOf(pemesananJumlahBarang);

                //for making obyek json barang
                Barang obBarangJson = new Barang(idBarang, namaBarang, minusStokBarang.toString(), fotoBarang);

                //update for data stok barang
                br.updateStokBarang(idBarang, minusStokBarang.toString());

                //idpemesan just for print not for save
                Long idPemesanan = pr.totalDataPemesanan();

                //obyek pemesan for making all json
                Pemesanan obPemesanan = new Pemesanan(idPemesanan.intValue(), Long.parseLong(textRegisterNomor), pemesanan.getJumlahBarangPesan(), new Date(), pemesanan.getStatusPemesanan(), pemesanan.getKeterangan(), obUserJson, obBarangJson);

                status = new Status("400", "pesanan telah di terima dengan nomor register " + textRegisterNomor, obPemesanan);
            }
        }
        //lakukan update jika nomor register ada
        else {

            //40 10
            // rumus sisa stok barang
            // pesanan barang sekarang - pesanan barang baru + stok barang
            //10 - 10 = - 0 + 40 = 40
            //
            //10 - 5 = 5 + 40 = 45
            //
            //10 - 100 = -90 + 40 = -50

            String stokBarang = br.selectIdBarang(pemesanan.getBarang().getIdBarang()).getStokBarang();
            String pesananBarang = pr.selectNoRegister(pemesanan.getNomorRegister()).getJumlahBarangPesan();
            String pesananBarangBaru = pemesanan.getJumlahBarangPesan();

            // pesanan barang sekarang - pesanan barang baru + stok barang
            Integer sisaStokBarang = Integer.valueOf(pesananBarang) - Integer.valueOf(pesananBarangBaru) + Integer.valueOf(stokBarang);

            if (sisaStokBarang < 0) {
                status = new Status("401", "stok barang tidak cukup stok sisa " + stokBarang);
            } else {
                pr.updatePesanan(pemesanan.getNomorRegister(), pesananBarangBaru);
                br.updateStokBarang(pemesanan.getBarang().getIdBarang(), sisaStokBarang.toString());
                status = new Status("401", "sukses update jumlah barang " + pesananBarangBaru);
            }
        }
        return  status;
    }

    public Status statusHistoryPemesanan(Pemesanan pemesanan) {
        if (ur.selectIdUser(pemesanan.getUser().getIdUser()) == null) {
            status = new Status("401", "id user tidak ada");
        } else {
            List<Pemesanan> p1 = pr.selectIdUser(pemesanan.getUser().getIdUser());
            status = new Status("400", "succses", p1);
        }
        return status;
    }
}
