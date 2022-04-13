package com.example.command.ws;

import com.example.command.bean.Paiement;
import com.example.command.service.CommandeService;
import com.example.command.service.PaiementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value ="stock/paiement")
public class PaiementWs {
    @GetMapping("/ref/{ref}")
    public Paiement findByRef(@PathVariable String ref) {
        return paiementService.findByRef(ref);
    }

    @GetMapping("/commande/ref/{ref}")
    public List<Paiement> findByCommandeRef(@PathVariable String ref) {
        return paiementService.findByCommandeRef(ref);
    }

    @DeleteMapping("/commande/ref/{ref}")
    public int deleteByCommandeRef(@PathVariable String ref) {
        int res1 = paiementService.deleteByCommandeRef(ref);
        int res2 = commandeService.deleteByRef(ref);
        return res1 + res2;
    }

    @GetMapping("/")
    public List<Paiement> findAll() {
        return paiementService.findAll();
    }

    @PostMapping("/")
    public String save(@RequestBody Paiement paiement) {
        return paiementService.save(paiement);
    }

    @Autowired
    private PaiementService paiementService;
    @Autowired
    private CommandeService commandeService;
}