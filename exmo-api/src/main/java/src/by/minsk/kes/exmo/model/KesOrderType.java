package src.by.minsk.kes.exmo.model;

public enum KesOrderType {
  BUY, SELL;

  public static KesOrderType getTypeFromString(final String type) {
    for (final KesOrderType kesOrderType : KesOrderType.values()) {
      if (kesOrderType.toString().equalsIgnoreCase(type)) {
        return kesOrderType;
      }
    }
    return null;
  }
}
