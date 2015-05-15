package at.bookworm.planb.model;

import android.os.Parcel;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.parceler.ParcelConverter;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class JodaDateTimeListConverter implements ParcelConverter<List<DateTime>> {

    @Override
    public void toParcel(final List<DateTime> days, final Parcel parcel) {
        long[] timestamps = new long[days.size()];
        int i = 0;
        for (DateTime day : days) {
            timestamps[i] = day.getMillis();
            i++;
        }
        parcel.writeInt(days.size());
        parcel.writeLongArray(timestamps);
    }

    @Override
    public List<DateTime> fromParcel(android.os.Parcel parcel) {
        int size = parcel.readInt();
        long[] timestamps = new long[size];
        parcel.readLongArray(timestamps);

        List<DateTime> days = new ArrayList<>();

        for (long t : timestamps) {
            days.add(new DateTime(t, DateTimeZone.forID("UTC")));
        }

        return days;
    }

}
