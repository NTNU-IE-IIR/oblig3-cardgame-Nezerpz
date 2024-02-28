package no.ntnu.idatx2003.oblig3.cardgame;

import java.util.ArrayList;
import java.util.List;

public class DeckOfCards {
  private final char[] suit = {'S','H','D','C'};
  private final int[] face = {1,2,3,4,5,6,7,8,9,10,11,12,13};
  private List<PlayingCard> deck;

  public DeckOfCards(char suit, int face) {
    this.deck = new ArrayList<>();
    for (int i = 0; i < suit; i++) {
      for (int j = 0; j < face; j++) {
        deck.add(new PlayingCard(suit[i], face[j]));
      }
    }
  }
}
