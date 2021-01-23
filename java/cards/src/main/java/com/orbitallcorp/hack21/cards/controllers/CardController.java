package com.orbitallcorp.hack21.cards.controllers;

import com.orbitallcorp.hack21.cards.domains.Card;
import com.orbitallcorp.hack21.cards.services.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
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

        if(card.isEmpty()) {
            return new ResponseEntity("No card was found!",
                    HttpStatus.NOT_FOUND);
        } else {
            return ResponseEntity.ok(card);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Card>> findById(@PathVariable Long id) {
        Optional<Card> card = cardService.findById(id);

        if(card.isEmpty()) {
            return new ResponseEntity("Card with that id " + id + " is not found!",
                    HttpStatus.NOT_FOUND);
        } else {
            return ResponseEntity.ok(card);
        }
    }

    @PostMapping
    public ResponseEntity<Card> save(@RequestBody Card card) {
        Card savedCard = cardService.save((card));
        return new ResponseEntity(savedCard, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Card> update(@PathVariable Long id, @RequestBody Card card) {
        Optional<Card> existsCard = cardService.findById(id);

        if(existsCard.isEmpty()) {
            return new ResponseEntity("Card with that id " + id + " is not found!",
                    HttpStatus.NOT_FOUND);
        } else {
            Card updateCard = card;
            updateCard.setId(id);

            updateCard = cardService.update((card));
            return new ResponseEntity(updateCard, HttpStatus.OK);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Optional<Card> existsCard = cardService.findById(id);

        if(existsCard.isEmpty()) {
            return new ResponseEntity("Card with that id " + id + " is not found!",
                    HttpStatus.NOT_FOUND);
        } else {
            cardService.deleteById(id);
            return new ResponseEntity("Card deleted!" ,HttpStatus.OK);
        }
    }

    @GetMapping("/paginationAndSorting")
    public ResponseEntity<List<Card>> getAllCard(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy)
    {
        List<Card> list = cardService.getAllCards(pageNo, pageSize, sortBy);

        return new ResponseEntity<List<Card>>(list, new HttpHeaders(), HttpStatus.OK);
    }
}
