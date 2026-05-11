package hexlet.code.schemas;

import java.util.Objects;

public class NumberSchema extends BaseSchema<Integer> {
    private boolean positive;
    private Integer from;
    private Integer to;

    public final NumberSchema required() {
        required = true;
        addCheck("required", Objects::nonNull);
        return this;
    }

    public final NumberSchema positive() {
        positive = true;
        addCheck("positive", i -> positive && i > 0);
        return this;
    }

    public final NumberSchema range(int one, int two) {
        this.from = one;
        this.to = two;
        addCheck("range", i -> i >= from && i <= to);
        return this;
    }
}
