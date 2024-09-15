package com.ufc.pi.webservice.repositories;

import java.time.LocalDate;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import com.ufc.pi.webservice.data.structures.DoublyLinkedList;
import com.ufc.pi.webservice.models.CreditCard;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class CreditCardRepositoryImpl implements CreditCardRepository {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public DoublyLinkedList<CreditCard> getAllByUserId(long userId) {
        String sql = "SELECT * FROM credit_cards WHERE user_id = ?";

        PreparedStatementSetter queryDynamicParamsSetter = ps -> {
            ps.setLong(1, userId);
        };

        DoublyLinkedList<CreditCard> creditCards = new DoublyLinkedList<>();

        jdbcTemplate.query(sql, queryDynamicParamsSetter, (rs, rowNum) -> {
            CreditCard creditCard = mapRowToCreditCard(rs, rowNum);
            creditCards.addNode(creditCard);
            return creditCard;
        });

        return creditCards;
    }

    @Override
    public void create(CreditCard creditCard) {
        String sql = "INSERT INTO credit_cards (name, flag, number, expiration_date, security_code, credit_limit, user_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, creditCard.getName(), creditCard.getFlag(), creditCard.getNumber(), creditCard.getExpirationDate(), creditCard.getSecurityCode(), creditCard.getCreditLimit(), creditCard.getUserId());
    }

    @Override
    public DoublyLinkedList<CreditCard> getByCardNumber(String cardNumber){
        String sql = "SELECT * FROM credit_cards WHERE number = ?";

        PreparedStatementSetter queryDynamicParamsSetter = ps -> {
            ps.setString(1, cardNumber);
        };

        DoublyLinkedList<CreditCard> creditCards = new DoublyLinkedList<>();

        jdbcTemplate.query(sql, queryDynamicParamsSetter, (rs, rowNum) -> {
            CreditCard creditCard = mapRowToCreditCard(rs, rowNum);
            creditCards.addNode(creditCard);
            return creditCard;
        });

        return creditCards;
    }

    @Override
    public DoublyLinkedList<CreditCard> findById(long id) {
        String sql = "SELECT * FROM credit_cards WHERE id = ?";

        PreparedStatementSetter queryDynamicParamsSetter = ps -> {  
            ps.setLong(1, id);
        };

        DoublyLinkedList<CreditCard> creditCards = new DoublyLinkedList<>();

        jdbcTemplate.query(sql, queryDynamicParamsSetter, (rs, rowNum) -> {
            CreditCard creditCard = mapRowToCreditCard(rs, rowNum);
            creditCards.addNode(creditCard);
            return creditCard;
        });

        return creditCards;
    }

    @Override
    public void update(CreditCard creditCard) {
        String sql = "UPDATE credit_cards SET name = ?, flag = ?, number = ?, expiration_date = ?, security_code = ?, credit_limit = ? WHERE id = ?";

        jdbcTemplate.update(sql, 
            creditCard.getName(), 
            creditCard.getFlag(), 
            creditCard.getNumber(), 
            creditCard.getExpirationDate(), 
            creditCard.getSecurityCode(), 
            creditCard.getCreditLimit(), 
            creditCard.getId()
        );
    }
    
    public CreditCard mapRowToCreditCard(java.sql.ResultSet rs, int rowNum) throws java.sql.SQLException {
        CreditCard entity = new CreditCard();

        entity.setId(rs.getInt("id"));
        entity.setName(rs.getString("name"));
        entity.setFlag(rs.getString("flag"));
        entity.setNumber(rs.getString("number"));

        String expirationDate = rs.getString("expiration_date");
        LocalDate localDate = LocalDate.parse(expirationDate);
        entity.setExpirationDate(localDate);

        entity.setSecurityCode(rs.getString("security_code"));
        entity.setCreditLimit(rs.getDouble("credit_limit"));
        entity.setUserId(rs.getLong("user_id"));
        
        return entity;
    }

    @Override
    public DoublyLinkedList<CreditCard> getByCardNumberAndNotId(String cardNumber, long id) {
        String sql = "SELECT * FROM credit_cards WHERE number = ? AND id != ?";

        PreparedStatementSetter queryDynamicParamsSetter = ps -> {
            ps.setString(1, cardNumber);
            ps.setLong(2, id);
        };

        DoublyLinkedList<CreditCard> creditCards = new DoublyLinkedList<>();

        jdbcTemplate.query(sql, queryDynamicParamsSetter, (rs, rowNum) -> {
            CreditCard creditCard = mapRowToCreditCard(rs, rowNum);
            creditCards.addNode(creditCard);
            return creditCard;
        });

        return creditCards;
    }
}
