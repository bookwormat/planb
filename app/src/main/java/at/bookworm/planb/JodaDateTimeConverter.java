package at.bookworm.planb;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.parceler.ParcelConverter;

/**
 *
 */
public class JodaDateTimeConverter implements ParcelConverter<DateTime> {

    @Override
    public void toParcel(DateTime time, android.os.Parcel parcel) {
        parcel.writeLong(time.getMillis());
    }

    @Override
    public DateTime fromParcel(android.os.Parcel parcel) {
        return new DateTime(parcel.readLong(), DateTimeZone.forID("UTC"));
    }
}
