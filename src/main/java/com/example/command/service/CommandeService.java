package com.example.command.service;

import com.example.command.bean.Commande;
import com.example.command.dao.CommandeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CommandeService {

    public Commande findByRef(String ref) {
        return commandeDao.findByRef(ref);
    }

    @Transactional
    public int deleteByRef(String ref) {
        int resultPaiement = paiementService.deleteByCommandeRef(ref);
        int resultCommande = commandeDao.deleteByRef(ref);
        return resultCommande+resultPaiement;
    }

    public List<Commande> findByRefLikeAndTotalGreaterThan(String ref, Double total) {
        return commandeDao.findByRefLikeAndTotalGreaterThan(ref, total);
    }

    public List<Commande> findAll() {
        return commandeDao.findAll();
    }


    public void update(Commande commande){
        commandeDao.save(commande);
    }
    public int save(Commande commande) {
        if(findByRef(commande.getRef()) != null){
            return -1;
        }else if(commande.getTotalPaye() > commande.getTotal()){
            return -2;
        }else{
            commandeDao.save(commande);
            return 1;
        }
    }
    public int payer(String ref, Double montant) {
        Commande commande = findByRef(ref);
        if(commande == null){
            return -1;
        }else if(commande.getTotalPaye()+montant > commande.getTotal()){
            return -2;
        }else{
            Double nvTotalPaye = commande.getTotalPaye()+montant;
            commande.setTotalPaye(nvTotalPaye);
            commandeDao.save(commande);
            return 1;
        }
    }


    @Lazy
    @Autowired
    private CommandeDao commandeDao;
    @Lazy
    @Autowired
    private PaiementService paiementService;


}
