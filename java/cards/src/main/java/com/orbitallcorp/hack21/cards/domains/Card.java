package com.orbitallcorp.hack21.cards.domains;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="TBL_CARDS")
@Getter
@Setter
public class Card implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="card_number")
    private Long cardNumber;

    @Column(name="emboss_name")
    private String embossName;

    @Column(name="customer_name")
    private String customerName;

    @Column(name="document_number")
    private Long documentNumber;

    @Column(name="mother_name")
    private String motherName;

    @Column(name="address_name")
    private String address;

    @Column(name="city_name")
    private String city;
}
