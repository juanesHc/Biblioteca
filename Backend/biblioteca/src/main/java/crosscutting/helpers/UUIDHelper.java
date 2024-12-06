package crosscutting.helpers;

import java.util.UUID;

public final class UUIDHelper {
    private static final String DEFAULT_UUID_STRING = "00000000-0000-0000-0000-000000000000";

    private UUIDHelper() {
        super();
    }

    public static UUID convertToUUID(final String uuidAsString) {
        return UUID.fromString(uuidAsString);
    }

    public static UUID getDefault(final UUID value, final UUID defaultValue) {
        return ObjectHelper.getDefault(value,defaultValue);
    }

    public static String getDefaultAssString() {
        return DEFAULT_UUID_STRING;
    }

    public static UUID getDefault() {
        return convertToUUID(DEFAULT_UUID_STRING);
    }

    public static UUID generate() {
        return UUID.randomUUID();
    }

    public static boolean isDefault(final UUID value) {
        return isEqual(value, getDefault());
    }

    public static boolean isDefault(final String uuidAsString) {
        return isDefault(convertToUUID(uuidAsString));
    }

    public static boolean isEqual(final UUID valueOne, final UUID valueTwo) {
        return getDefault(valueOne, getDefault()).compareTo(getDefault(valueTwo, getDefault())) == 0;
    }

    public static UUID convertToUUID(final Object uuidAsObject) {
        return convertToUUID(ObjectHelper.getDefault(uuidAsObject, TextHelper.EMPTY).toString());
    }
}
