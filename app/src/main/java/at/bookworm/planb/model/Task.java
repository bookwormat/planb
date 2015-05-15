package at.bookworm.planb.model;

import org.joda.time.DateTime;
import org.parceler.Parcel;
import org.parceler.ParcelPropertyConverter;

import java.util.List;

import at.bookworm.planb.JodaDateTimeConverter;
import lombok.Data;

/**
 *
 */
@Data
@Parcel
public class Task {
    String name;
    String description;

    @ParcelPropertyConverter(JodaDateTimeConverter.class)
    DateTime createdAt;

    @ParcelPropertyConverter(JodaDateTimeListConverter.class)
    List<DateTime> days;

    int currentStreak;

    int longestStreak;

    public Task() {
    }

}
