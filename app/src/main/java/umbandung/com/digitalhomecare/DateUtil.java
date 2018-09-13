package umbandung.com.digitalhomecare;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * Created by github/fiyyanputra on 9/13/2018.
 */

public class DateUtil {
    public static String dateFormatting(String s) {
        String date = null;
        try {
            String pattern = "dd MMM yyyy HH:mm:ss";
            SimpleDateFormat simpleDateFormat =
                    new SimpleDateFormat(pattern, new Locale("id", "ID"));
            date = simpleDateFormat.format(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").parse(s));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Log.d("dateFormatting: ", date);
        return date;
    }
}
