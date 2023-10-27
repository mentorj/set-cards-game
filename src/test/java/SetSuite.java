import io.vavr.collection.List;
import katas.set_cards_game.*;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.Condition;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class SetSuite {
    private  static List<Card> deckWith3DifferentCardsRegardingAllCriteria;
    private static List<Card> deckWithSameShading;
    private static List<Card> deckInvalidSetBecauseRedondantShading;
    private static SetCardsEngine engine;

    @BeforeAll
    static void setUp(){
        engine = new SetCardsEngine();
        deckWith3DifferentCardsRegardingAllCriteria = List.of(
                new Card(Shape.DIAMOND, Color.GREEN, NumberOfShapes.ONE, Shading.SOLID),
                new Card(Shape.OVAL, Color.PURPLE, NumberOfShapes.TWO, Shading.OPEN),
                new Card(Shape.SQUIGGLE, Color.RED, NumberOfShapes.THREE, Shading.STRIP)
                );
        deckWithSameShading =List.of(
                new Card(Shape.DIAMOND, Color.GREEN, NumberOfShapes.ONE, Shading.SOLID),
                new Card(Shape.OVAL, Color.PURPLE, NumberOfShapes.TWO, Shading.SOLID),
                new Card(Shape.SQUIGGLE, Color.RED, NumberOfShapes.THREE, Shading.SOLID)
        );

        deckInvalidSetBecauseRedondantShading=List.of(
                new Card(Shape.DIAMOND, Color.GREEN, NumberOfShapes.ONE, Shading.SOLID),
                new Card(Shape.OVAL, Color.PURPLE, NumberOfShapes.TWO, Shading.SOLID),
                new Card(Shape.SQUIGGLE, Color.RED, NumberOfShapes.THREE, Shading.OPEN)
        );
    }
   @Test
    void aDeckWithDifferentCardsRegardingAllPropertiesIsASet(){
       Assertions.assertThat(engine.deckIsSet(deckWith3DifferentCardsRegardingAllCriteria)).isEqualTo(true);
   }

    @Test
    void aDeckWithSameCardsRegardingAllPropertiesIsASet(){
        Assertions.assertThat(engine.deckIsSet(deckWith3DifferentCardsRegardingAllCriteria)).isEqualTo(true);
    }

    @Test
    void givenDeck_WithDifferentCardsRegardingAllProperties_shouldBeDetectedAsaSet(){
        Assertions.assertThat(engine.allTheSameOrAllDifferent(deckWith3DifferentCardsRegardingAllCriteria)).isTrue();
    }

    @Test
    void givenDeck_withSameShading_shouldBeDetectedAsaSet(){
        Assertions.assertThat(engine.allTheSameOrAllDifferent(deckWithSameShading)).isTrue();
    }

    @Test
    void givenDeckWithRedondantShadingShouldNotBeDetectedAsaSet(){
        Assertions.assertThat(engine.allTheSameOrAllDifferent(deckInvalidSetBecauseRedondantShading)).isFalse();
    }


}
