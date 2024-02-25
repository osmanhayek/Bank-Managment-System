
package validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordValidator {

    // digit + lowercase char + uppercase char + punctuation + symbol
    private static final String PASSWORD_PATTERN =
            "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$";

    private static final Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
    
    public static String getErrorMessage(final String password) {
        if (password == null || password.isEmpty()) {
            return "Password is required.";
        }

        Matcher matcher = pattern.matcher(password);

        if (!matcher.matches()) {
            return "Invalid Password format.\nSecure Password requirements\r\n"
            		+ "\r\n"
            		+ "Password must contain at least one digit [0-9].\r\n"
            		+ "Password must contain at least one lowercase Latin character [a-z].\r\n"
            		+ "Password must contain at least one uppercase Latin character [A-Z].\r\n"
            		+ "Password must contain at least one special character like ! @ # & ( ).\r\n"
            		+ "Password must contain a length of at least 8 characters and a maximum of 20 characters.";
        }

        // Additional validation conditions can be added here

        return null; // No error
    }

    public static boolean isValid(final String password) {
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

}
