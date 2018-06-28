package src.by.minsk.kes.exmo.main;

import src.by.minsk.kes.exmo.legacy.Exmo;
import src.by.minsk.kes.exmo.model.ExOrder;
import src.by.minsk.kes.exmo.model.KesOrder;
import src.by.minsk.kes.exmo.transform.ExOrderParser;
import src.by.minsk.kes.exmo.transform.KesOrderConverter;

import java.util.HashMap;
import java.util.List;

public class Main {

  public static void main(String[] args) {

    Exmo e = new Exmo("key", "secret");
    String result = e.Request("user_info", null);
    System.out.println(result);
    String result2 = e.Request("user_cancelled_orders", new HashMap<String, String>() {{
      put("limit", "2");
      put("offset", "0");
    }});
    System.out.println(result2);

    ExOrderParser parser = new ExOrderParser();
    final List<ExOrder> exOrderList = parser.buildFromJson(result2);
    System.out.println(exOrderList);
    KesOrderConverter converter = new KesOrderConverter();
    final List<KesOrder> kesOrderList = converter.build(exOrderList);
    System.out.println(kesOrderList);
  }
}
