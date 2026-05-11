package hexlet.code.schemas;

public class NumberSchema extends BaseSchema<Integer> {
    private boolean positive;
    private Integer from;
    private Integer to;

    public final NumberSchema positive() {
        positive = true;
        return this;
    }

    public final NumberSchema range(int one, int two) {
        this.from = one;
        this.to = two;
        return this;
    }

    @Override
    protected final boolean isValidValue(Integer i) {
        if (positive && !isRequired() && i == null) {
            return true;
        }
        if (positive && i <= 0) {
            return false;
        }
        if (from != null && to != null) {
            return i >= from && i <= to;
        }
        return true;
    }
}
