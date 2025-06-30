package ynu.com.orders.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CommonUtil {
    public static String getCurrentDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(new Date());
    }
}
