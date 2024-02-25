package generators;

import java.util.Random;
import java.util.UUID;

public class Generator {
	public static String generateRandomId() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString();
	}

    public static String generateCreditCardNo() {
        // The first digit of a credit card number is the Major Industry Identifier (MII).
        // For simplicity, let's assume it is 4, which is the MII for Visa.
        StringBuilder creditCardNumber = new StringBuilder("4");

        // Generate the next 15 digits randomly.
        Random random = new Random();
        for (int i = 1; i <= 14; i++) {
            int digit = random.nextInt(10);
            creditCardNumber.append(digit);
        }

        // Add the check digit using the Luhn algorithm.
        int checkDigit = calculateLuhnCheckDigit(creditCardNumber.toString());
        creditCardNumber.append(checkDigit);

        return creditCardNumber.toString();
    }

    private static int calculateLuhnCheckDigit(String number) {
        int sum = 0;
        boolean alternate = false;

        for (int i = number.length() - 1; i >= 0; i--) {
            int digit = Character.getNumericValue(number.charAt(i));

            if (alternate) {
                digit *= 2;
                if (digit > 9) {
                    digit -= 9;
                }
            }

            sum += digit;
            alternate = !alternate;
        }

        int remainder = sum % 10;
        return (remainder == 0) ? 0 : (10 - remainder);
    }
    
    public static String generatePin() {
        // Generate a random 4-digit PIN
        Random random = new Random();
        int pinValue = 1000 + random.nextInt(9000); // Generates a random number between 1000 and 9999

        return String.valueOf(pinValue);
    }
}
