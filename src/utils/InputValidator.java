package utils;

public class InputValidator {

    private static InputValidator instance;

    // Private constructor to prevent instantiation, its empty  to prevent instance
    private InputValidator() {}

    // Static method to get the singleton instance
    public static InputValidator getInstance() {
        if (instance == null) {
            instance = new InputValidator();
        }
        return instance;
    }



    //  regex phone 10digits
    public boolean validatePhoneNumber(String phoneNumber) {
        String phoneRegex = "^[0-9]{10}$";
        return phoneNumber != null && phoneNumber.matches(phoneRegex);
    }
}
