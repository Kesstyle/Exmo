package src.by.minsk.kes.exmo.main;

import src.by.minsk.kes.exmo.legacy.Exmo;
import src.by.minsk.kes.exmo.model.ExCancelledOrder;
import src.by.minsk.kes.exmo.model.ExTrade;
import src.by.minsk.kes.exmo.model.KesOrder;
import src.by.minsk.kes.exmo.transform.converter.KesCancelledOrderConverter;
import src.by.minsk.kes.exmo.transform.converter.KesTradeConverter;
import src.by.minsk.kes.exmo.transform.parser.ExOrderParser;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

  public static void main(String[] args) {

    Exmo e = new Exmo("key", "secret");
    userInfo(e);
    cancelledOrders(e);
    trades(e);
  }

  private static void trades(final Exmo e) {
    String result = e.get();
    ExOrderParser parser = new ExOrderParser();
    final Map<String, List<ExTrade>> exTrades = parser.buildTradeFromJson(result);
    System.out.println(exTrades);
    KesTradeConverter converter = new KesTradeConverter();
    final Map<String, List<KesOrder>> kesOrders = converter.convert(exTrades);
    System.out.println(kesOrders);
  }

  private static void userInfo(final Exmo e) {
    String result = e.post("user_info", null);
    System.out.println(result);
  }

  private static void cancelledOrders(final Exmo e) {
    String result = e.post("user_cancelled_orders", new HashMap<String, String>() {{
      put("limit", "2");
      put("offset", "0");
    }});
    System.out.println(result);
    ExOrderParser parser = new ExOrderParser();
    final List<ExCancelledOrder> exCancelledOrderList = parser.buildCancelledOrderFromJson(result);
    System.out.println(exCancelledOrderList);
    KesCancelledOrderConverter converter = new KesCancelledOrderConverter();
    final List<KesOrder> kesOrderList = converter.convert(exCancelledOrderList);
    System.out.println(kesOrderList);
  }
}
