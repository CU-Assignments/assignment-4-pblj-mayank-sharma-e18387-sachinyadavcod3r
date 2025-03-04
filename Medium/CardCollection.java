import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
class Card {
    private String symbol;
    private String value;
    public Card(String symbol, String value) {
        this.symbol = symbol;
        this.value = value;
    }
    public String getSymbol() {
        return symbol;
    }
    public String getValue() {
        return value;
    }
    public String toString() {
        return value + " of " + symbol;
    }
}
public class CardCollection {
    public static void main(String[] args) {
        Map<String, List<Card>> cardCollection = new HashMap<>();
        Scanner scanner = new Scanner(System.in);
        addCard(cardCollection, new Card("Hearts", "Ace"));
        addCard(cardCollection, new Card("Hearts", "King"));
        addCard(cardCollection, new Card("Spades", "Queen"));
        addCard(cardCollection, new Card("Spades", "Jack"));
        addCard(cardCollection, new Card("Diamonds", "10"));
        addCard(cardCollection, new Card("Clubs", "2"));
        addCard(cardCollection, new Card("Hearts", "10"));
        while (true) {
            System.out.println("\nEnter the symbol to find cards (or 'exit'): ");
            String symbol = scanner.nextLine();
            if (symbol.equalsIgnoreCase("exit")) {
                break;
            }
            if (cardCollection.containsKey(symbol)) {
                List<Card> cards = cardCollection.get(symbol);
                System.out.println("Cards of " + symbol + ":");
                for (Card card : cards) {
                    System.out.println(card);
                }
            } else {
                System.out.println("Symbol not found.");
            }
        }
        scanner.close();
    }
    private static void addCard(Map<String, List<Card>> cardCollection, Card card) {
        String symbol = card.getSymbol();
        if (!cardCollection.containsKey(symbol)) {
            cardCollection.put(symbol, new ArrayList<>());
        }
        cardCollection.get(symbol).add(card);
    }
}
