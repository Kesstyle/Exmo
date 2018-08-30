package by.minsk.kes.exmo.transform.converter;

import java.util.*;
import java.util.stream.Collectors;

public abstract class GenericConverter<T, S, X> {

    public abstract T convert(final S source);

    public List<T> convert(final Collection<S> source) {
        if (source == null) {
            return null;
        }
        return source.stream().map(this::convert).collect(Collectors.toList());
    }

    public Map<X, List<T>> convertMapCollection(final Map<X, List<S>> source) {
        if (source == null) {
            return null;
        }
        return source.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, entry -> convert(entry.getValue()), (v1, v2) -> v1, HashMap::new));
    }

    public Map<X, T> convertMap(final Map<X, S> source) {
        if (source == null) {
            return null;
        }
        return source.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, entry -> convert(entry.getValue()), (v1, v2) -> v1, HashMap::new));
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
