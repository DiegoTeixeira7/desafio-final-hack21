package com.orbitallcorp.hack21.cards.controllers;

import com.orbitallcorp.hack21.cards.domains.Card;
import com.orbitallcorp.hack21.cards.services.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/cards")
public class CardController {
    @Autowired
    private CardService cardService;

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

}
