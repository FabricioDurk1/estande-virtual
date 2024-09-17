package com.ufc.pi.webservice.services;

import org.springframework.stereotype.Service;

import com.ufc.pi.webservice.data.structures.DoublyLinkedList;
import com.ufc.pi.webservice.dtos.input.CreateCreditCardDTO;
import com.ufc.pi.webservice.dtos.input.UpdateCreditCardDTO;
import com.ufc.pi.webservice.models.CreditCard;
import com.ufc.pi.webservice.models.User;
import com.ufc.pi.webservice.repositories.CreditCardRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CreditCardService {
    public final CreditCardRepository creditCardRepository;
    public final AuthService authService;

    public void createCreditCard(CreateCreditCardDTO createCreditCardDTO) throws Exception {

        DoublyLinkedList<CreditCard> creditCards = creditCardRepository.getByCardNumber(createCreditCardDTO.number());
        
        if (creditCards.getHead() != null) {
            throw new Exception("Cartão de crédito já existe");
        }

        User user = authService.getSessionUser();

        var creditCard = new CreditCard();
        creditCard.setName(createCreditCardDTO.name());
        creditCard.setFlag(createCreditCardDTO.flag());
        creditCard.setNumber(createCreditCardDTO.number());
        creditCard.setExpirationDate(createCreditCardDTO.expirationDate());
        creditCard.setSecurityCode(createCreditCardDTO.securityCode());
        creditCard.setCreditLimit(createCreditCardDTO.creditLimit());
        
        creditCard.setUserId(user.getId());
        creditCardRepository.create(creditCard);
    }

    public DoublyLinkedList<CreditCard> getAllCreditCards() {
        User user = authService.getSessionUser();
        DoublyLinkedList<CreditCard> creditCards = creditCardRepository.getAllByUserId(user.getId());
        return creditCards;
    }

    public DoublyLinkedList<CreditCard> getCreditCardById(Long id) throws Exception {
        DoublyLinkedList<CreditCard> creditCards = creditCardRepository.findById(id);

        if (creditCards.getHead() == null) {
            throw new Exception("Cartão de crédito não encontrado");
        }

        return creditCards;
    }

    public void updateCreditCard(Long id, UpdateCreditCardDTO updateCreditCardDTO) throws Exception {
        DoublyLinkedList<CreditCard> creditCards = creditCardRepository.getByCardNumberAndNotId(updateCreditCardDTO.number(), id);
        
        if (creditCards.getHead() != null) {
            throw new Exception("Cartão de crédito já existe");
        }

        DoublyLinkedList<CreditCard> creditCardList = getCreditCardById(id);
        
        if(creditCardList.getHead() == null) {
            throw new Exception("Cartão de crédito não encontrado");
        }

        var creditCard = creditCardList.getHead().data;
        
        creditCard.setName(updateCreditCardDTO.name());
        creditCard.setFlag(updateCreditCardDTO.flag());
        creditCard.setNumber(updateCreditCardDTO.number());
        creditCard.setExpirationDate(updateCreditCardDTO.expirationDate());
        creditCard.setSecurityCode(updateCreditCardDTO.securityCode());
        creditCard.setCreditLimit(updateCreditCardDTO.creditLimit());
        
        creditCardRepository.update(creditCard);
    }
}
