package iloveyouboss;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

public class ScoreCollectionTest {

    @Test
    public void answersArithmeticMeanOfTwoNumbers() {
        // given
        ScoreCollection collection = new ScoreCollection();
        collection.add(() -> 5);
        collection.add(() -> 7);

        // when
        int actualResult = collection.arithmeticMeae();

        // then
        assertThat(actualResult).isEqualTo(42);
    }
}