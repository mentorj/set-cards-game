package katas.set_cards_game;


import io.vavr.Function1;
import io.vavr.Function2;
import io.vavr.collection.List;

import java.util.Map;
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

    private static <T> Predicate<T> asPredicate(Function1<T, Boolean> function) {
        return function::apply;
    }

    public boolean allTheSameOrAllDifferent(List<Card> deck){
        boolean result=true;
        List<Function1<Card,Distinguishable>> allCriterias = List.of(Card::color,Card::numberShapes,Card::shape,Card::shading);
        var allResults = allCriterias.map(fn -> deck.groupBy(fn).size()!=2);
        return allResults.foldLeft(true, (aBoolean, aBoolean2) -> aBoolean.booleanValue() && aBoolean2.booleanValue());
    }
}
