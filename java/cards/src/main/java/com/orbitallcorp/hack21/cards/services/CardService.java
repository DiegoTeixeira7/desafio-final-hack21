package com.orbitallcorp.hack21.cards.services;

import com.orbitallcorp.hack21.cards.domains.Card;
import com.orbitallcorp.hack21.cards.repositories.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CardService {
    @Autowired
    private CardRepository cardRepository;

    public List<Card> findAll() {
        List<Card> card = new ArrayList<>();
        cardRepository.findAll().forEach(card :: add);
        return card;
    }

    public Optional<Card> findById(Long id) {
        return cardRepository.findById(id);
    }

    public Card save(Card card) {
        return cardRepository.save((card));
    }

    public Card update(Card card) {
        return  cardRepository.save(card);
    }
    public void deleteById(Long id) {
        cardRepository.deleteById(id);
    }


}
