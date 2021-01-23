package com.orbitallcorp.hack21.cards.services;

import com.orbitallcorp.hack21.cards.domains.Card;
import com.orbitallcorp.hack21.cards.repositories.CardRepository;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
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

    public List<Card> getAllCards(Integer pageNo, Integer pageSize, String sortBy) {
        PageRequest paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

        Page<Card> pagedResult = cardRepository.findAll(paging);

        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<Card>();
        }
    }
}
