package org.example;


import org.example.Game.Game;
import org.example.Models.Card;
import org.example.Models.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Main {

    public static void displayChance(Player player, Game game){
        System.out.println();
        System.out.println("Top Card:" + game.getTopCard().toString());
        // Available cards
        System.out.println("Available Cards: ");
        List<Card> availableCards = player.getCards();
        for(Card card:availableCards){
            System.out.println(card.toString());
        }
        // Available Moves
        System.out.println("Available Moves: ");
        List<Card> availableMoves = game.availableMoves(availableCards);
        int key = 1;
        for(Card card:availableMoves){
            String message = "";
            if(Objects.equals(card.getRank(), "A")) message = " (Skip the next player in turn)";
            if(Objects.equals(card.getRank(), "Q")) message = " (Next player draw 2 cards)";
            if(Objects.equals(card.getRank(), "K")) message = " (Reverse the sequence of who plays next )";
            if(Objects.equals(card.getRank(), "J")) message = " (Next player draw 4 cards)";
            System.out.println(key + ". " + card.toString() + message);
            key++;
        }
        System.out.println();
    }
    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);
        System.out.println("Welcome to Cards Game!");
        System.out.println("RULES:");
        System.out.println("1:Each player starts with a hand of 5 cards.");
        System.out.println("2:The game starts with a deck of 52 cards ( a standard deck of playing cards).");
        System.out.println("3:Players take turns playing cards from their hand, following a set of rules that define what cards can be played when.");
        System.out.println("4:A player can only play a card if it matches either the suit or the rank of the top card on the discard pile.");
        System.out.println("5:If a player cannot play a card, they must draw a card from the draw pile. If the draw pile is empty, the game ends in a draw and no player is declared a winner.");
        System.out.println("6:The game ends when one player runs out of cards who is declared the winner.");
        System.out.println("7:BONUS: Aces, Kings, Queens and Jack are action cards. When one of these is played the following actions occur:");
        System.out.println("Ace(A): Skip the next player in turn");
        System.out.println("Kings(K): Reverse the sequence of who plays next ");
        System.out.println("Queens(Q): +2");
        System.out.println("Jacks(J): +4");

        System.out.println();
        System.out.println("Enter Number of players(2-4):");
        int numberOfPlayers = Integer.parseInt(sc.nextLine());
        while(numberOfPlayers < 2 || numberOfPlayers > 4){
            System.out.println("Enter Number of players(2-4):");
            numberOfPlayers = Integer.parseInt(sc.nextLine());
        }
        System.out.println();

        Game game  = new Game();
        boolean clockWise = true;

        List<Player> players = new ArrayList<>();


        //get player names
        for(int i=0;i<numberOfPlayers;++i){
            System.out.print("Enter Player Name: ");
            String name = sc.nextLine();
            Player player = new Player(name,game.get5Cards());
            players.add(player);
            System.out.println();
        }
        int chance = 0;
        Card lastMove = null;

        while(true){
            if(players.get(chance).winner()){
                System.out.println("Winner: " + players.get(chance).getName());
                break;
            }
            if(game.gameDraw()) {
                System.out.println("Game Draw!!!!!");
                break;
            }

            if(lastMove != null){
                if(Objects.equals(lastMove.getRank(), "J")){
                    System.out.println("Picked 4 cards!");
                    for(int i=0;i<4;++i) {
                        players.get(chance).insertCard(game.getCard());
                        if (game.gameDraw()) {
                            System.out.println("Game Draw!!!!!");
                            break;
                        }
                    }
                }
            }

            if(lastMove != null){
                if(Objects.equals(lastMove.getRank(), "Q")){
                    System.out.println("Picked 2 cards!");
                    for(int i=0;i<2;++i) {
                        players.get(chance).insertCard(game.getCard());
                        if (game.gameDraw()) {
                            System.out.println("Game Draw!!!!!");
                            break;
                        }
                    }
                }
            }

            List<Card> availableCards = players.get(chance).getCards();
            List<Card> availableMoves = game.availableMoves(availableCards);

            System.out.println("Chance:" + players.get(chance).getName());

            if(availableMoves.isEmpty()){
                System.out.println();
                System.out.println("No Available move so picking up a card!");
                Card insertedCard = game.getCard();
                players.get(chance).insertCard(insertedCard);
                if(!game.isValidMove(insertedCard)) {
                    if(clockWise) chance++;
                    else chance--;
                    if(chance < 0) chance = numberOfPlayers-1;
                    if(chance >= numberOfPlayers) chance = 0;
                    lastMove=null;
                    continue;
                }
                else availableMoves.add(insertedCard);
                System.out.println();
            }
            displayChance(players.get(chance),game);
            System.out.println("Move: ");
            int move = Integer.parseInt(sc.nextLine());
            while (move <= 0 || move > availableMoves.size()){
                System.out.println("Move: ");
                move = Integer.parseInt(sc.nextLine());
            }

            players.get(chance).makeMove(availableMoves.get(move-1));
            game.move(availableMoves.get(move-1));
            lastMove = availableMoves.get(move-1);

            if(Objects.equals(availableMoves.get(move - 1).getRank(), "A")){
                lastMove = null;
                if(clockWise) chance++;
                else chance--;
                if(chance < 0) chance = numberOfPlayers-1;
                if(chance >= numberOfPlayers) chance = 0;
            }

            if(Objects.equals(availableMoves.get(move - 1).getRank(), "K")){
                clockWise=!clockWise;
            }

            if(clockWise) chance++;
            else chance--;
            if(chance < 0) chance = numberOfPlayers-1;
            if(chance >= numberOfPlayers) chance = 0;
            // break in case of draw
            if(game.gameDraw()) {
                System.out.println("Game Draw!!!!!");
                break;
            }
            if(players.get(chance).winner()){
                System.out.println("Winner: " + players.get(chance).getName());
                break;
            }
        }
    }
}