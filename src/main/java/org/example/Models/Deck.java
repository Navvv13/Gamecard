package org.example.Models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {

    List<Card> cards;

    public Deck() {
        cards = new ArrayList<>();
        //append clubs
        for(int i=0;i<13;++i){
            Card tempCard = new Card();
            tempCard.setSuit("Club");
            if(i==0){tempCard.setRank("A");}
            else if(i==10){tempCard.setRank("J");}
            else if(i==11){tempCard.setRank("Q");}
            else if(i==12){tempCard.setRank("K");}
            else{tempCard.setRank(String.valueOf(i+1));}
            cards.add(tempCard);
        }

        //append diamonds
        for(int i=0;i<13;++i){
            Card tempCard = new Card();
            tempCard.setSuit("Diamond");
            if(i==0){tempCard.setRank("A");}
            else if(i==10){tempCard.setRank("J");}
            else if(i==11){tempCard.setRank("Q");}
            else if(i==12){tempCard.setRank("K");}
            else{tempCard.setRank(String.valueOf(i+1));}
            cards.add(tempCard);
        }

        //append hearts
        for(int i=0;i<13;++i){
            Card tempCard = new Card();
            tempCard.setSuit("Heart");
            if(i==0){tempCard.setRank("A");}
            else if(i==10){tempCard.setRank("J");}
            else if(i==11){tempCard.setRank("Q");}
            else if(i==12){tempCard.setRank("K");}
            else{tempCard.setRank(String.valueOf(i+1));}
            cards.add(tempCard);
        }

        //append spades
        for(int i=0;i<13;++i){
            Card tempCard = new Card();
            tempCard.setSuit("Spade");
            if(i==0){tempCard.setRank("A");}
            else if(i==10){tempCard.setRank("J");}
            else if(i==11){tempCard.setRank("Q");}
            else if(i==12){tempCard.setRank("K");}
            else{tempCard.setRank(String.valueOf(i+1));}
            cards.add(tempCard);
        }
    }

    public List<Card> getShuffledDeck(){
        Collections.shuffle(cards);
        return cards;
    }
}
