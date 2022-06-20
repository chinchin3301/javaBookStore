package actions.helpers;

import org.apache.commons.codec.digest.DigestUtils;

public class UChecker {
    public static boolean isCheckPassword(String s) {
        String passwordRegex = "(?=^.{8,}$)((?=.*\\d)|(?=.*\\W+))(?![.\\n])(?=.*[A-Z])(?=.*[a-z]).*$";
        return  s.matches(passwordRegex);
    }
    public static boolean isCheckPhone(String s) {
        String allCountryRegex = "^(\\+\\d{1,3}( )?)?((\\(\\d{1,3}\\))|\\d{1,3})[- .]?\\d{3,4}[- .]?\\d{4}$";
        return s.matches(allCountryRegex);
    }
    public static boolean isCheckTitle(String s) {
        //String regex = "[^A-Za-z0-9]+";
        String regex = "^[A-Za-z0-9_-]*$";
        s = s.replaceAll("\\s+","");
        return s.matches(regex);
        //return true;
    }

}
