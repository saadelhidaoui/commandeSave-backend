package com.example.command.service;

import com.example.command.bean.Commande;
import com.example.command.bean.Paiement;
import com.example.command.dao.PaiementDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaiementService {
    public Paiement findByRef(String ref) {
        return paiementDao.findByRef(ref);
    }

    public List<Paiement> findByCommandeRef(String ref) {
        return paiementDao.findByCommandeRef(ref);
    }

    public int deleteByCommandeRef(String ref) {
        return paiementDao.deleteByCommandeRef(ref);
    }

    public List<Paiement> findAll() {
        return paiementDao.findAll();
    }


    public int save(Paiement paiement) {
        if(findByRef(paiement.getRef()) != null){
            return -1;
        }
        Commande commande = commandeService.findByRef(paiement.getCommande().getRef());
        paiement.setCommande(commande);
        if(commande == null){
            return -2;
        }else if(commande.getTotalPaye()+paiement.getMontant() > commande.getTotal()){
            return -3;
        }else{
            Double nvTotalPaye = commande.getTotalPaye()+paiement.getMontant();
            commande.setTotalPaye(nvTotalPaye);
            commandeService.update(commande);
            paiementDao.save(paiement);
            return 1;
        }
    }



    @Lazy
    @Autowired
    private CommandeService commandeService;
    @Lazy
    @Autowired
    private PaiementDao paiementDao;
}
