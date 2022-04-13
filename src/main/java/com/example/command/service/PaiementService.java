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
        int res1 = paiementDao.deleteByCommandeRef(ref);
        int res2 = commandeService.deleteByRef(ref);
        return res1 + res2;
    }

    public List<Paiement> findAll() {
        return paiementDao.findAll();
    }


    public String save(Paiement paiement) {
        if(findByRef(paiement.getRef()) != null){
            return "ref deja existe";
        }
        Commande commande = commandeService.findByRef(paiement.getCommande().getRef());
        paiement.setCommande(commande);
        if(commande == null){
            return "pas de commande";
        }else if(commande.getTotalPaye()+paiement.getMontant() > commande.getTotal()){
            return "total paye depasse total de la commande";
        }else{
            Double nvTotalPaye = commande.getTotalPaye()+paiement.getMontant();
            commande.setTotalPaye(nvTotalPaye);
            commandeService.update(commande);
            paiementDao.save(paiement);
            return "merci";
        }
    }



    @Lazy
    @Autowired
    private CommandeService commandeService;
    @Lazy
    @Autowired
    private PaiementDao paiementDao;
}
