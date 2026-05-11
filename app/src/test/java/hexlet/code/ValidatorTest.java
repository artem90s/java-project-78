package hexlet.code;

import hexlet.code.schemas.BaseSchema;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ValidatorTest {
    public static final int MIN_LENGTH = 10;
    public static final int MIN_LENGTH_1 = 4;
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

    @Test
    void returnSuccess() {
        var v = new Validator();
        var schema = v.string();
        assertTrue(schema.isValid(""));
        assertTrue(schema.isValid(null));

        schema.required();

        assertFalse(schema.isValid(null));
        assertFalse(schema.isValid(""));

        assertTrue(schema.isValid("what does the fox say"));
        assertTrue(schema.isValid("hexlet"));


        assertTrue(schema.contains("wh").isValid("what does the fox say"));
        assertTrue(schema.contains("what").isValid("what does the fox say"));
        assertFalse(schema.contains("whatthe").isValid("what does the fox say"));

        assertFalse(schema.isValid("what does the fox say"));

        var schema1 = v.string();
        assertTrue(schema1.minLength(MIN_LENGTH).minLength(MIN_LENGTH_1).isValid("Hexlet"));
    }

    @Test
    void mapSchemaSuccessTest() {
        var v = new Validator();
        var schema = v.map();

        assertTrue(schema.isValid(null));

        schema.required();

        assertFalse(schema.isValid(null));
        assertTrue(schema.isValid(new HashMap<>()));
        var data = new HashMap<String, String>();
        data.put("key1", "value1");
        assertTrue(schema.isValid(data));

        schema.sizeof(2);

        assertFalse(schema.isValid(data));
        data.put("key2", "value2");
        assertTrue(schema.isValid(data));
    }

    @Test
    void mapWithNullValueTest() {
        var v = new Validator();
        var schema = v.map();

        var data = new HashMap<String, String>();
        data.put("key", null);

        assertTrue(schema.isValid(data));

        schema.required();

        assertFalse(schema.isValid(data));
    }

    @Test
    void success() {
        var v = new Validator();
        var schema = v.map();
        Map<String, BaseSchema<String>> schemas = new HashMap<>();
        schemas.put("firstName", v.string().required());
        schemas.put("lastName", v.string().required().minLength(2));

        schema.shape(schemas);

        Map<String, String> human1 = new HashMap<>();
        human1.put("firstName", "John");
        human1.put("lastName", "Smith");
        assertTrue(schema.isValid(human1));

        Map<String, String> human2 = new HashMap<>();
        human2.put("firstName", "John");
        human2.put("lastName", null);
        assertFalse(schema.isValid(human2));

        Map<String, String> human3 = new HashMap<>();
        human3.put("firstName", "Anna");
        human3.put("lastName", "B");
        assertFalse(schema.isValid(human3));
    }
}
