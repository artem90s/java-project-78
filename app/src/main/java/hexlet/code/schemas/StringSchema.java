package hexlet.code.schemas;

public class StringSchema extends BaseSchema<String> {
    public final StringSchema required() {
        required = true;
        addCheck("required", e -> !e.isEmpty());
        return this;
    }

    public final StringSchema minLength(int i) {
        addCheck("minLength", e -> i > 0 && e.length() > i);
        return this;
    }

    public final StringSchema contains(String s) {
        addCheck("containString", e -> s != null && e.contains(s));
        return this;
    }
}
