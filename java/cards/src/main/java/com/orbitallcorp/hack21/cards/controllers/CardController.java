package com.orbitallcorp.hack21.cards.controllers;

import com.orbitallcorp.hack21.cards.domains.Card;
import com.orbitallcorp.hack21.cards.services.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cards")
public class CardController {
    @Autowired
    private CardService cardService;

    @GetMapping
    public ResponseEntity<List<Card>> findAll() {
        List<Card> card = cardService.findAll();
        return ResponseEntity.ok(card);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Card>> findById(@PathVariable Long id) {
        Optional<Card> card = cardService.findById(id);
        return ResponseEntity.ok(card);
    }

    @PostMapping
    public ResponseEntity<Card> save(@RequestBody Card customer) {
        Card savedCustomer = cardService.save((customer));
        return new ResponseEntity(savedCustomer, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Card> update(@RequestBody Card card) {
        Optional<Card> existsCard = cardService.findById(card.getId());
        if(existsCard.isEmpty()) {
            return new ResponseEntity("User with that id " + card.getId() + " is not found!",
                    HttpStatus.NOT_FOUND);
        } else {
            Card updateCard = cardService.update((card));
            return new ResponseEntity(updateCard, HttpStatus.OK);
        }
    }

}
