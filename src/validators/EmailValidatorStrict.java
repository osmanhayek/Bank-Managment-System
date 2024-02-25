package validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidatorStrict {

    private static final String EMAIL_PATTERN =
            "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
            + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";

    private static final Pattern pattern = Pattern.compile(EMAIL_PATTERN);

    public static boolean isValid(final String email) {
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static String getErrorMessage(final String email) {
        if (email == null || email.isEmpty()) {
            return "Email is required.";
        }

        Matcher matcher = pattern.matcher(email);

        if (!matcher.matches()) {
            return "Invalid email format.";
        }

        return null;
    }
}