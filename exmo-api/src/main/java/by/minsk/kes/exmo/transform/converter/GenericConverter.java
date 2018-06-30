package by.minsk.kes.exmo.transform.converter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class GenericConverter<T, S, X> {

    public abstract T convert(final S source);

    public List<T> convert(final Collection<S> source) {
        if (source == null) {
            return null;
        }
        final List<T> resultList = new ArrayList<>();
        for (final S s : source) {
            resultList.add(convert(s));
        }
        return resultList;
    }

    public Map<X, List<T>> convertMapCollection(final Map<X, List<S>> source) {
        if (source == null) {
            return null;
        }
        final Map<X, List<T>> result = new HashMap<>();
        for (final Map.Entry<X, List<S>> entry : source.entrySet()) {
            result.put(entry.getKey(), convert(entry.getValue()));
        }
        return result;
    }

    public Map<X, T> convertMap(final Map<X, S> source) {
        if (source == null) {
            return null;
        }
        final Map<X, T> result = new HashMap<>();
        for (final Map.Entry<X, S> entry : source.entrySet()) {
            result.put(entry.getKey(), convert(entry.getValue()));
        }
        return result;
    }

    protected Date getDateFromUnix(final Long unixDate, final boolean alignOffset) {
        if (unixDate == null) {
            return null;
        }
        final Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(unixDate * 1000L);
        if (alignOffset) {
            calendar.add(Calendar.HOUR_OF_DAY, new Date().getTimezoneOffset() / 60);
        }
        return calendar.getTime();
    }
}
