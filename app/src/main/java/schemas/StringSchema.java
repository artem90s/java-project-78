package schemas;

public class StringSchema {
    private boolean required;
    private int minLength;
    private String containString;

    public final void required() {
        required = true;
    }

    public final StringSchema minLength(int i) {
        minLength = i;
        return this;
    }

    public final StringSchema contains(String s) {
        containString = s;
        return this;
    }

    public final boolean isValid(String string) {
        if (required && (string == null || string.isEmpty())) {
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
