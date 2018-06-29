package src.by.minsk.kes.exmo.transform.converter;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import src.by.minsk.kes.exmo.model.Pair;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
