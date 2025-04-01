package agus.ramdan.cdt.event.utils;

public class LongUtils {
    public static boolean canConvertToLong(Object obj) {
        if (obj == null) return false;

        try {
            Long.parseLong(obj.toString());
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
