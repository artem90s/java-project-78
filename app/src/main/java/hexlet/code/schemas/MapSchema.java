package hexlet.code.schemas;

import java.util.Map;

public class MapSchema extends BaseSchema<Map> {
    private Integer size;
    private Map<String, BaseSchema<String>> schemas;

    public final MapSchema required() {
        required = true;
        addCheck("required", map -> map != null && !map.containsValue(null));
        return this;
    }

    public final MapSchema sizeof(int i) {
        size = i;
        addCheck("sizeof", map -> map.keySet().size() == size);
        return this;
    }

    public final MapSchema shape(Map<String, BaseSchema<String>> input) {
        this.schemas = input;
        addCheck("shape", map -> schemas.entrySet().stream()
                .allMatch(e -> e.getValue().isValid((String) map.get(e.getKey()))));
        return this;
    }
}
