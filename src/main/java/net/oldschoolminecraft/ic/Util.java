package net.oldschoolminecraft.ic;

import java.security.SecureRandom;

public class Util
{
    private static final String UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
    private static final String DIGITS = "0123456789";
    private static final String SPECIAL_CHARACTERS = "!@#$%^&*()-_+=<>?";

    private static final String ALL_CHARACTERS = UPPERCASE + LOWERCASE + DIGITS;

    private static final SecureRandom RANDOM = new SecureRandom();

    public static String generateSecurePassword(int length)
    {
        if (length < 8)
            throw new IllegalArgumentException("Password length should be at least 8 characters."); // Ensure minimum length for security

        StringBuilder password = new StringBuilder(length);

        // Ensure the password contains at least one character from each category
        password.append(UPPERCASE.charAt(RANDOM.nextInt(UPPERCASE.length())));
        password.append(LOWERCASE.charAt(RANDOM.nextInt(LOWERCASE.length())));
        password.append(DIGITS.charAt(RANDOM.nextInt(DIGITS.length())));
//        password.append(SPECIAL_CHARACTERS.charAt(RANDOM.nextInt(SPECIAL_CHARACTERS.length())));

        // Fill the remaining characters randomly
        for (int i = 4; i < length; i++)
            password.append(ALL_CHARACTERS.charAt(RANDOM.nextInt(ALL_CHARACTERS.length())));

        // Shuffle the characters to remove predictable pattern
        return shuffleString(password.toString());
    }

    private static String shuffleString(String input)
    {
        char[] characters = input.toCharArray();
        for (int i = characters.length - 1; i > 0; i--)
        {
            int index = RANDOM.nextInt(i + 1);
            // Simple swap
            char temp = characters[index];
            characters[index] = characters[i];
            characters[i] = temp;
        }
        return new String(characters);
    }
}
