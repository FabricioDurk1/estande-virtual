package com.ufc.pi.webservice.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ufc.pi.webservice.data.structures.DoublyLinkedList;
import com.ufc.pi.webservice.dtos.input.CreateCreditCardDTO;
import com.ufc.pi.webservice.dtos.input.UpdateCreditCardDTO;
import com.ufc.pi.webservice.models.CreditCard;
import com.ufc.pi.webservice.services.CreditCardService;

import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("api/v1/credit-cards")
@RequiredArgsConstructor
public class CreditCardController {
    private final CreditCardService creditCardService;

    @PostMapping
    public ResponseEntity<?> createCreditCard(@RequestBody CreateCreditCardDTO createCreditCardDTO) {
        try {
            creditCardService.createCreditCard(createCreditCardDTO);
            return ResponseEntity.ok("Cartão de crédito cadastrado com sucesso");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllCreditCards() {
        List<CreditCard> creditCardResponse = new ArrayList<>();

        DoublyLinkedList<CreditCard> creditCards = creditCardService.getAllCreditCards();
        DoublyLinkedList<CreditCard>.Node current = creditCards.getHead();

        while (current != null) {
            creditCardResponse.add(current.data);
            current = current.next;
        }

        return ResponseEntity.ok(creditCardResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCreditCardById(@PathVariable("id") Long id) {
        try {
            var publisher = creditCardService.getCreditCardById(id);

            return ResponseEntity.ok(publisher.getHead().data);    
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> updateCreditCard(@PathVariable("id") Long id, @RequestBody UpdateCreditCardDTO updateCreditCardDTO) {
        try {
            creditCardService.updateCreditCard(id, updateCreditCardDTO);
            return ResponseEntity.ok("Cartão de crédito atualizado com sucesso");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
