package hexlet.code.schemas;

import java.util.Map;

public class MapSchema extends BaseSchema<Map> {
    public final MapSchema required() {
        required = true;
        addCheck("required", map -> map != null && !map.containsValue(null));
        return this;
    }

    public final MapSchema sizeof(int i) {
        addCheck("sizeof", map -> map.keySet().size() == i);
        return this;
    }

    public final MapSchema shape(Map<String, BaseSchema<String>> input) {
        addCheck("shape", map -> input.entrySet().stream()
                .allMatch(e -> e.getValue().isValid((String) map.get(e.getKey()))));
        return this;
    }
}
