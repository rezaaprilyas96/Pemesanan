package com.test.pemesanan.repository;

import com.test.pemesanan.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface UserRepository extends JpaRepository<User, Integer> {

    String insertUserQuery = "insert into tb_user(username, nama, alamat) values (:username, :nama, :alamat)";
    String selectUserQuery = "select * from tb_user where username = :username";
    String selectIdUser = "select * from tb_user where id_user = :id_user";

    @Modifying
    @Query(value = insertUserQuery, nativeQuery = true)
    @Transactional
    void insertUser(@Param("username") String username, @Param("nama") String nama, @Param("alamat") String alamat);

    @Query(value = selectUserQuery, nativeQuery = true)
    User selectUser(@Param("username") String username);

    @Query(value = selectIdUser, nativeQuery = true)
    User selectIdUser(@Param("id_user") Integer idUser);
}
