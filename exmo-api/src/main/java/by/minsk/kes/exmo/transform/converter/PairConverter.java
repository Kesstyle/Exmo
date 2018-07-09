package by.minsk.kes.exmo.transform.converter;

import by.minsk.kes.exmo.model.domain.Pair;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PairConverter {

  private static final String PAIRS_SEPARATOR = ",";

  public Pair getFromString(final String pairString) {
    if (StringUtils.isBlank(pairString)) {
      return null;
    }
    final String[] currencies = pairString.split(Pair.PAIR_SEPARATOR);
    if (currencies == null || currencies.length != 2) {
      return null;
    }
    final Pair pair = new Pair();
    pair.setFirstCurrency(currencies[0]);
    pair.setSecondCurrency(currencies[1]);
    return pair;
  }

  public List<Pair> getListFromString(final String pairStrings) {
    if (pairStrings == null) {
      return null;
    }
    final String[] strPairs = pairStrings.split(PAIRS_SEPARATOR);
    if (strPairs == null) {
      return null;
    }
    final List<Pair> pairs = new ArrayList<>();
    for (final String strPair : strPairs) {
      pairs.add(getFromString(strPair));
    }
    return pairs;
  }

  public Map<String, Set<String>> getMapFromString(final String pairStrings) {
    final List<Pair> pairs = getListFromString(pairStrings);
    if (pairs == null) {
      return null;
    }
    final Map<String, Set<String>> pairsMap = new HashMap<>();
    for (final Pair pair: pairs) {
      final String first = pair.getFirstCurrency();
      final String second = pair.getSecondCurrency();
      if (pairsMap.containsKey(first)) {
        final Set<String> secondCurrencies = pairsMap.get(first);
        secondCurrencies.add(second);
      } else {
        final Set<String> secondCurrencies = new HashSet<>();
        secondCurrencies.add(second);
        pairsMap.put(first, secondCurrencies);
      }
    }
    return pairsMap;
  }

  public String getStringFromList(final Collection<Pair> pairs) {
    if (pairs == null) {
      return null;
    }
    if (pairs.isEmpty()) {
      return StringUtils.EMPTY;
    }
    StringBuilder strBuilder = new StringBuilder();
    for (final Pair pair : pairs) {
      strBuilder.append(pair.toString());
    }
    return strBuilder.toString();
  }
}
