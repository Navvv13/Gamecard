package org.example.Game;

import org.example.Models.Card;
import org.example.Models.Deck;

import java.util.*;

public class Game {

    Queue<Card> deck;

    Card topCard;

    public Game() {
        deck = new LinkedList<>();
        Deck shuffledDeck = new Deck();
        List<Card> cards = shuffledDeck.getShuffledDeck();
        // Assigning cards in game
        deck.addAll(cards);
        topCard = deck.remove();
    }

    public Card getTopCard() {
        return topCard;
    }

    public List<Card> get5Cards(){
        List<Card> fiveCards = new ArrayList<>();
        for(int i=0;i<5;++i){
            fiveCards.add(deck.remove());
        }
        return fiveCards;
    }

    public Card getCard(){
        return deck.remove();
    }

    public boolean gameDraw(){
        return deck.isEmpty();
    }

    public List<Card> availableMoves(List<Card> availableCards){
        List<Card> availableMoves = new ArrayList<>();
        for(Card card:availableCards){
            if(isValidMove(card)){
                availableMoves.add(card);
            }
        }
        return availableMoves;
    }

    public Boolean isValidMove(Card card){
        if(Objects.equals(card.getRank(), topCard.getRank()) || Objects.equals(card.getSuit(), topCard.getSuit())){
            return !Objects.equals(topCard.getRank(), "J") && !Objects.equals(topCard.getRank(), "Q");
        }
        return false;
    }

    public void move(Card card){
        topCard = card;
    }



}
