package com.ufc.pi.webservice.repositories;

import com.ufc.pi.webservice.data.structures.DoublyLinkedList;
import com.ufc.pi.webservice.models.CreditCard;

public interface CreditCardRepository {
    DoublyLinkedList<CreditCard> getAllByUserId(long userId);
    void create(CreditCard creditCard);
    DoublyLinkedList<CreditCard> getByCardNumber(String cardNumber);
    DoublyLinkedList<CreditCard> getByCardNumberAndNotId(String cardNumber, long id);
    DoublyLinkedList<CreditCard> findById(long id);  
    void update(CreditCard creditCard); 
}
