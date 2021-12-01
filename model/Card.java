package org.acme.model;
class Card {
    public String idCard;
    public String balance;
    public String pin;

    public Card(String idCard, String balance, String pin) {
        this.idCard = idCard;
        this.balance = balance;
        this.pin = pin;
    }

    public Card() { }

    @Override
    public String toString() {
        return "Card{" +
                "idCard='" + idCard + '\'' +
                ", balance='" + balance + '\'' +
                ", pin='" + pin + '\'' +
                '}';
    }
}