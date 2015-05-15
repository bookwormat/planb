package at.bookworm.planb;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.lang.reflect.Type;
import java.util.Date;

/**
 *
 */
public class DateTimeTypeConverter
        implements JsonSerializer<DateTime>, JsonDeserializer<DateTime> {

    private static final String FORMAT_DATETIME = "yyyy-MM-dd'T'HH:mm:ss'Z'";
    public static final String TIMEZONE = "UTC";

    @Override
    public JsonElement serialize(DateTime src, Type srcType, JsonSerializationContext context) {
        DateTimeFormatter fmt = DateTimeFormat.forPattern(FORMAT_DATETIME);
        fmt.withZone(DateTimeZone.forID(TIMEZONE));
        String str = fmt.print(src);
        return new JsonPrimitive(str);
    }

    @Override
    public DateTime deserialize(JsonElement json, Type type, JsonDeserializationContext context)
            throws JsonParseException {
        try {
            try {
                long millis = json.getAsLong();
                return new DateTime(DateTimeZone.forID(TIMEZONE)).withMillis(millis);
            } catch (NumberFormatException nfe) {
                return new DateTime(json.getAsString(), DateTimeZone.forID(TIMEZONE));
            }
        } catch (IllegalArgumentException e) {
            // May be it came in formatted as a java.util.Date, so try that
            Date date = context.deserialize(json, Date.class);
            return new DateTime(date);
        }
    }

}

