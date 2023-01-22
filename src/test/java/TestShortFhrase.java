import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertTrue;


class TestSortFhrase {

    @ParameterizedTest
    @ValueSource(strings = {"1234567890123456",  "1234567890123456789", "123456"})
    public void testPhrase(String phrase) {
        assertTrue(phrase.length() > 15, phrase + "  error > 15");
    }
}