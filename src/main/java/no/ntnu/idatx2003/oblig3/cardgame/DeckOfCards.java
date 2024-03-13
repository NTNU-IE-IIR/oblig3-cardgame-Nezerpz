package no.ntnu.idatx2003.oblig3.cardgame;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;


public class DeckOfCards{
  private final char[] suit = {'S', 'H', 'D', 'C'};
  private final int[] face = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};
  private Random random;
  private ArrayList<PlayingCard> deck;

  public DeckOfCards() {
    this.deck = new ArrayList<>();
    this.random = new Random();
    for (char c : suit) {
      for (int i : face) {
        this.deck.add(new PlayingCard(c, i));
      }
    }
  }
  public Collection<PlayingCard> dealHand(int n){
    if (n < 1 || n > 52) {
      throw new IllegalArgumentException("Invalid number of cards: " + n);
    }
    ArrayList<PlayingCard> hand = new ArrayList<>();
    for (int i = 0; i < n; i++) {
      hand.add(deck.remove(random.nextInt(deck.size())));
    }
    return hand;
  }

  //TODO: remove after tests
  public void getDeck() {
    for (PlayingCard card : deck) {
      System.out.println(card.getAsString());
    }
  }


}



