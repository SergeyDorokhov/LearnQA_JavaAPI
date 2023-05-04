import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class LengthString {
    @ParameterizedTest
    @ValueSource(strings = {"", "12345678901234", "123456789012345", "1234567890123456"})
    public void testLengthString(String testString) {
        if (testString.length() > 15) {
            assertTrue(true);
        } else {
            fail();
        }
    }
}
