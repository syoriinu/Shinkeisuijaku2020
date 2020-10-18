import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private List<Card> deck;


    public Deck(){
        deck = new ArrayList<>();
        for(int i = 1;i <= 13;i++){
            deck.add(new Card("card_spade_",i));
            deck.add(new Card("card_club_",i));
            deck.add(new Card("card_diamond_",i));
            deck.add(new Card("card_heart_",i));
        }
        deck.add(new Card("card_joker_",100));
        deck.add(new Card("card_joker_",100));
        Collections.shuffle(deck);
        sort();
    }

    public void sort(){
        List<Card> sortDeck = new ArrayList<>();
        for(int i = 0;i < deck.size();i++){
            Card pair1 = deck.get(i);
            sortDeck.add(pair1);
            deck.remove(pair1);
            sortDeck.add(searchRankPair(pair1));
        }
        deck = sortDeck;
    }

    public Card searchRankPair(Card card){
        Card pair2 = null;
        for(Card pair : deck){
            if(card.getRANK() == pair.getRANK()){
                pair2 = pair;
                deck.remove(pair2);
                break;
            }
        }
        return pair2;
    }

    public List<Card> pickCards(int total){
        List<Card> gameDeck = new ArrayList<>();
        for(int i = 0;i < total;i++){
            gameDeck.add(deck.get(i));
        }
        Collections.shuffle(gameDeck);
        return gameDeck;
    }

}
