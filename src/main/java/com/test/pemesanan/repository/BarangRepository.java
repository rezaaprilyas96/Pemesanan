package com.test.pemesanan.repository;

import com.test.pemesanan.model.Barang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface BarangRepository extends JpaRepository<Barang, Integer> {

    String insertBarangQuery = "insert into tb_barang(foto_barang, nama_barang, stok_barang) values (:foto_barang, :nama_barang, :stok_barang)";
    String selectBarangQuery = "select * from tb_barang where id_barang = :id_barang";
    String updateStokBarang = "update tb_barang set stok_barang = :stok_barang where id_barang = :id_barang";
    String banyakDataQuery = "select count(*) from tb_barang";

    @Query(value = banyakDataQuery, nativeQuery = true)
    Integer totalDataBarang();

    @Modifying
    @Query(value = insertBarangQuery, nativeQuery = true)
    @Transactional
    void insertBarang(@Param("nama_barang") String namaBarang, @Param("foto_barang") String fotoBarang, @Param("stok_barang") String StokBarang);

    @Query(value = selectBarangQuery, nativeQuery = true)
    Barang selectIdBarang(@Param("id_barang") Integer idBarang);

    @Modifying
    @Query(value = updateStokBarang, nativeQuery = true)
    @Transactional
    void updateStokBarang(@Param("id_barang") Integer idBarang, @Param("stok_barang") String stokBarang);
}
