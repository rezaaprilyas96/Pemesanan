package com.test.pemesanan.repository;

import com.test.pemesanan.model.Pemesanan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

public interface PemesananRepository extends JpaRepository<Pemesanan, Integer> {

//    @Query(value = "select p.id_pemesanan, u.nama, b.nama_barang, p.jumlah_barang_pesanan, u.alamat, p.status_pemesanan, p.tgl_pemesanan, p.keterangan" +
//            "from tb_pemesanan p inner join tb_user u on p.user_id = u.id_user inner join tb_barang b on p.barang_id = b.id_barang" +
//            "where p.status_pemesanan = 1",nativeQuery = true)

    String insertPemesananQuery = "insert into tb_pemesanan" +
            "(barang_id, jumlah_barang_pesanan, keterangan, nomor_register, status_pemesanan, tgl_pemesanan, user_id)" +
            "values (:barang_id, :jumlah_barang_pesanan, :keterangan, :nomor_register, :status_pemesanan, :tgl_pemesanan, :user_id)";

    String banyakDataQuery = "select count(*) from tb_pemesanan";
    String listHistoryQuery = "select * from tb_pemesanan where user_id = :id_user";
    String updatePesananQuery = "update tb_pemesanan set jumlah_barang_pesanan = :jumlah_barang_pesanan where nomor_register = :nomor_register";
    String selectNoRegisterQuery = "select * from tb_pemesanan where nomor_register = :nomor_register";

    //untuk total pemesanan untuk menjadi list id_pemesanan hanya untuk json bukan untuk disimpan
    @Query(value = banyakDataQuery, nativeQuery = true)
    Long totalDataPemesanan();


    @Query(value = listHistoryQuery, nativeQuery = true)
    List<Pemesanan> selectIdUser(@Param("id_user") Integer idUser);

    @Modifying
    @Query(value = insertPemesananQuery, nativeQuery = true)
    @Transactional
    void insertPemesanan(@Param("barang_id") Integer barangId, @Param("jumlah_barang_pesanan") String jumlahBarangPesanan,
                         @Param("keterangan") String keterangan, @Param("nomor_register") Long nomorRegister,
                         @Param("status_pemesanan") Boolean statusPemesanan, @Param("tgl_pemesanan") Date tgPemesanan,
                         @Param("user_id") Integer userId);

    @Modifying
    @Query(value = updatePesananQuery, nativeQuery = true)
    @Transactional
    void updatePesanan(@Param("nomor_register") Long nomorRegister, @Param("jumlah_barang_pesanan") String jumlahBarangPesanan);

    @Query(value = selectNoRegisterQuery, nativeQuery = true)
    Pemesanan selectNoRegister(@Param("nomor_register") Long nomorRegister);
}
