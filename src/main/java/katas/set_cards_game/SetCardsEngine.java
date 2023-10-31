package katas.set_cards_game;


import io.vavr.Function1;
import io.vavr.Function2;
import io.vavr.Predicates;
import io.vavr.Tuple;
import io.vavr.collection.List;

import java.util.function.Function;
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

        //Function2<List<Card>,Function1<Card,Distinguishable>,Boolean> checkCriteria = this::allTheSameOrAllDifferentByCriteria;
        var function2 = Function2.of(this::allTheSameOrAllDifferentByCriteria);

        var function1= function2.apply(deck);
        var predicate = asPredicate(function1);
        boolean result=true;
        List<Function1<Card,Distinguishable>> allCriterias = List.of(Card::color,Card::numberShapes,Card::shape,Card::shading);
        
        for(Function1<Card,Distinguishable> function : allCriterias){
            result=result && allTheSameOrAllDifferentByCriteria(deck,function);
        }
        return result;
        //return allTheSameOrAllDifferentByCriteria(deck,Card::color) && allTheSameOrAllDifferentByCriteria(deck,Card::numberShapes)
        //&&  allTheSameOrAllDifferentByCriteria(deck,Card::shading) && allTheSameOrAllDifferentByCriteria(deck,Card::shape);
    }

}
