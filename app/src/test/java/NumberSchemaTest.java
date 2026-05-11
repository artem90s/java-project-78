import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

class NumberSchemaTest {
    public static final int NUMBER = 5;
    public static final int NUMBER_1 = 10;
    public static final int NUMBER_3 = 0;

    @Test
    void testNumberSchema() {
        var v = new Validator();
        var schema = v.number();

        assertTrue(schema.isValid(-NUMBER));
        assertTrue(schema.isValid(null));
    }

    @Test
    void testRequired() {
        var validator = new Validator();
        var schema = validator.number();

        schema.required();

        assertFalse(schema.isValid(null));
        assertTrue(schema.isValid(NUMBER_1));
    }

    @Test
    void testPositive() {
        var validator = new Validator();
        var schema = validator.number();

        assertTrue(schema.positive().isValid(null));

        schema.required();

        assertTrue(schema.isValid(NUMBER_1));
        assertFalse(schema.isValid(-NUMBER_1));
        assertFalse(schema.isValid(NUMBER_3));
    }

    @Test
    void testRange() {
        var validator = new Validator();
        var schema = validator.number();

        schema.range(NUMBER, NUMBER_1);
        assertTrue(schema.isValid(NUMBER));
        assertTrue(schema.isValid(NUMBER_1));
    }

}
