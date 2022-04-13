package com.example.command.ws;

import com.example.command.bean.Commande;
import com.example.command.service.CommandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value ="stock/commande")
@CrossOrigin("http://localhost:4200")
public class CommandeWs {
    @Autowired
    private CommandeService commandeService;

    @GetMapping("/ref/{ref}")
    public Commande findByRef(@PathVariable String ref) {
        return commandeService.findByRef(ref);
    }

    @DeleteMapping("/ref/{ref}")
    public int deleteByRef(@PathVariable String ref) {
        return commandeService.deleteByRef(ref);
    }

    @PutMapping("/")
    public void update(@RequestBody Commande commande) {
        commandeService.update(commande);
    }

    @GetMapping("/ref/{ref}/total/{total}")
    public List<Commande> findByRefLikeAndTotalGreaterThan(@PathVariable String ref, @PathVariable Double total) {
        return commandeService.findByRefLikeAndTotalGreaterThan(ref, total);
    }

    @GetMapping("/")
    public List<Commande> findAll() {
        return commandeService.findAll();
    }

    @PostMapping("/")
    public int save(@RequestBody Commande commande) {
        return commandeService.save(commande);
    }

    @PutMapping("/payer/ref/{ref}/montant/{montant}")
    public int payer(@PathVariable String ref, @PathVariable Double montant) {
        return commandeService.payer(ref, montant);
    }
}
