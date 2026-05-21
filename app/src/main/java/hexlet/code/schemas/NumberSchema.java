package hexlet.code.schemas;

import java.util.Objects;

public class NumberSchema extends BaseSchema<Integer> {

    public final NumberSchema required() {
        required = true;
        addCheck("required", Objects::nonNull);
        return this;
    }

    public final NumberSchema positive() {
        addCheck("positive", i -> i > 0);
        return this;
    }

    public final NumberSchema range(int one, int two) {
        addCheck("range", i -> i >= one && i <= two);
        return this;
    }
}
