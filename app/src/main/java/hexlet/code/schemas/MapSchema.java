package hexlet.code.schemas;

import java.util.Map;

public class MapSchema extends BaseSchema<Map> {
    private Integer size;
    private Map<String, BaseSchema<String>> schemas;

    public final MapSchema sizeof(int i) {
        size = i;
        return this;
    }

    @Override
    protected final boolean isValidValue(Map map) {
        if (isRequired() && map.containsValue(null)) {
            return false;
        }
        if (size != null) {
            if (!(map.keySet().size() == size)) {
                return false;
            }
        }
        if (schemas != null) {
            for (Map.Entry<String, BaseSchema<String>> entry : schemas.entrySet()) {
                String key = entry.getKey();

                if (!entry.getValue().isValid((String) map.get(key))) {
                    return false;
                }
            }
        }
        return true;
    }

    public final MapSchema shape(Map<String, BaseSchema<String>> input) {
        this.schemas = input;
        return this;
    }
}
