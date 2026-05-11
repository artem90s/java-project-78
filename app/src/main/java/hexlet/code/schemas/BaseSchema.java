package hexlet.code.schemas;

public abstract class BaseSchema<T> {
    private boolean required;

    public final boolean isRequired() {
        return required;
    }

    final void setRequired(boolean bool) {
        required = bool;
    }

    /**
     * Устанавливает флаг, что значение обязательно для заполнения.
     * Если значение равно null и required = true, валидация не пройдена.
     *
     * @return this (текущий экземпляр для цепочечных вызовов)
     */
    public BaseSchema<T> required() {
        required = true;
        return this;
    }

    public final boolean isValid(T t) {
        if (required && t == null) {
            return false;
        }
        return isValidValue(t);
    }

    protected abstract boolean isValidValue(T t);
}
