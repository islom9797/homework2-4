package org.islom.dars241.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@Entity
public class Card {
    @Id
    private Long id;

    private Long userId;
    private int number;
    private double balance;
    private String expiryDate;
    private boolean active;

    @OneToMany(mappedBy = "sender")
    private List<OutCome> outgoingTransfers;

    @OneToMany(mappedBy = "receiver")
    private List<Income> incomingTransfers;
    public Card( ) {
    }

    public Card(Long id, Long userId, int number, int balance, String expiryDate, boolean active) {
        this.id = id;
        this.userId = userId;
        this.number = number;
        this.balance = balance;
        this.expiryDate = expiryDate;
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUsername() {
        return userId;
    }

    public void setUsername(Long userId) {
        this.userId = userId;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
