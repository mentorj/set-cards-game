package katas.set_cards_game;


import io.vavr.Function1;
import io.vavr.Predicates;
import io.vavr.collection.List;

import java.util.function.Predicate;

public class SetCardsEngine {
    public boolean deckIsSet(List<Card> deck){
        return allTheSameOrAllDifferentWithColor(deck);
    }

    private boolean allTheSameOrAllDifferentWithColor(List<Card> deck) {
        var numberOfColorsInTheDeck = deck.groupBy(card -> card.color()).size();
        return (numberOfColorsInTheDeck==3||numberOfColorsInTheDeck==1);
    }


    private boolean allTheSameOrAllDifferentByCriteria(List<Card> deck, Function1<Card,Distinguishable> criteriaFunc){
        return deck.groupBy(criteriaFunc).size()!=2;
    }
    public boolean allTheSameOrAllDifferent(List<Card> deck){

        return allTheSameOrAllDifferentByCriteria(deck,Card::color) && allTheSameOrAllDifferentByCriteria(deck,Card::numberShapes)
        &&  allTheSameOrAllDifferentByCriteria(deck,Card::shading) && allTheSameOrAllDifferentByCriteria(deck,Card::shape);
    }

}
