package hexlet.code.schemas;

public class StringSchema extends BaseSchema<String> {
    private int minLength;
    private String containString;

    public final StringSchema required() {
        required = true;
        addCheck("required", e -> !e.isEmpty());
        return this;
    }

    public final StringSchema minLength(int i) {
        minLength = i;
        addCheck("minLength", e -> minLength > 0 && e.length() > minLength);
        return this;
    }

    public final StringSchema contains(String s) {
        containString = s;
        addCheck("containString", e -> containString != null && e.contains(containString));
        return this;
    }
}
