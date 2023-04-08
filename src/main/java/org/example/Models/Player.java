package org.example.Models;

import java.util.List;

public class Player {

    String Name;

    List<Card> cards;

    public Player(String name, List<Card> cards) {
        Name = name;
        this.cards = cards;
    }

    public String getName() {
        return Name;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void insertCard(Card newCard){
        cards.add(newCard);
    }

    public void makeMove(Card card){
        cards.remove(card);
    }

    public boolean winner(){
        return cards.isEmpty();
    }

}
