package com.example.command.ws;

import com.example.command.bean.Paiement;
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
        return paiementService.deleteByCommandeRef(ref);

    }

    @GetMapping("/")
    public List<Paiement> findAll() {
        return paiementService.findAll();
    }

    @PostMapping("/")
    public int save(@RequestBody Paiement paiement) {
        return paiementService.save(paiement);
    }

    @Autowired
    private PaiementService paiementService;
}