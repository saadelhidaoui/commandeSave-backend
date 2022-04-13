package com.example.command.bean;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Paiement {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String ref;
    private Double montant;
    private Date datePaiement;
    private String code;

    @ManyToOne
    private Commande commande;



    public void setId(Long id) {
        this.id = id;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public void setMontant(Double montant) {
        this.montant = montant;
    }

    public void setDatePayement(Date datePayement) {
        this.datePaiement = datePayement;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setCommande(Commande commande) {
        this.commande = commande;
    }

    public Long getId() {
        return id;
    }

    public String getRef() {
        return ref;
    }

    public Double getMontant() {
        return montant;
    }

    public Date getDatePayement() {
        return datePaiement;
    }

    public String getCode() {
        return code;
    }

    public Commande getCommande() {
        return commande;
    }
}
