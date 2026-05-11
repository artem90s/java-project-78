package hexlet.code.schemas;

public class StringSchema extends BaseSchema<String> {
    private int minLength;
    private String containString;

    public final StringSchema required() {
        setRequired(true);
        return this;
    }

    public final StringSchema minLength(int i) {
        minLength = i;
        return this;
    }

    public final StringSchema contains(String s) {
        containString = s;
        return this;
    }

    @Override
    protected final boolean isValidValue(String string) {
        if (isRequired() && string.isEmpty()) {
            return false;
        }
        if (minLength > 0 && string.length() < minLength) {
            return false;
        }
        if (containString != null) {
            return string.contains(containString);
        }
        return true;
    }
}
