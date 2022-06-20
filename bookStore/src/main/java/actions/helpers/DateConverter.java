package actions.helpers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateConverter {
    public static Date StringToDate(String s) throws ParseException {
        Date date = new SimpleDateFormat("yyyy-MM-dd").parse(s);
        return date;
    }
}
