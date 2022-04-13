package com.example.command.dao;

import com.example.command.bean.Commande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommandeDao extends JpaRepository<Commande, Long> {

    Commande findByRef(String ref);
    int deleteByRef(String ref);
    List<Commande> findByRefLikeAndTotalGreaterThan(String ref, Double total);

}
