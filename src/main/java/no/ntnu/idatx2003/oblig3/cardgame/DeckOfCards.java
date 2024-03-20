package no.ntnu.idatx2003.oblig3.cardgame;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

/**
 * Represents a deck of cards. A deck of cards contains 52 playing cards.
 * The deck can be shuffled and cards can be dealt from the deck.
 * The deck is created and shuffled when the deck is created.
 *
 * @author Henrik Aamot
 * @version 2024-03-20
 */
public class DeckOfCards {
  private final char[] suit = {'S', 'H', 'D', 'C'};
  private final int[] face = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};
  private Random random;
  private ArrayList<PlayingCard> deck;

  /**
   * Creates a new deck of cards and shuffles it.
   */
  public DeckOfCards() {
    shuffleDeck();
  }

  /**
   * Deals a hand of n cards from the deck.
   * The hand that is dealt is removed from the deck.
   *
   * @param n the number of cards to deal
   * @return a hand of n cards
   * @throws IllegalArgumentException if n is less than 1 or greater than 52
   */
  public Collection<PlayingCard> dealHand(int n) {
    if (n < 1 || n > 52) {
      throw new IllegalArgumentException("Invalid number of cards: " + n);
    }
    ArrayList<PlayingCard> hand = new ArrayList<>();
    for (int i = 0; i < n; i++) {
      hand.add(deck.remove(random.nextInt(deck.size())));
    }
    return hand;
  }

  /**
   * Returns the deck of cards.
   *
   * @return the deck of cards
   */
  public ArrayList<PlayingCard> getDeck() {
    return deck;
  }

  /**
   * Shuffles the deck of cards.
   */
  public void shuffleDeck() {
    this.deck = new ArrayList<>();
    this.random = new Random();
    for (char c : suit) {
      for (int i : face) {
        try {
          deck.add(new PlayingCard(c, i));
        } catch (IllegalArgumentException e) {
          System.out.println(e.getMessage());
        }
      }
    }
  }


}



