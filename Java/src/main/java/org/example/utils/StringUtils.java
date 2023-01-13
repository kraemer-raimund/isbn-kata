package org.example.utils;

public class StringUtils {

    public static boolean isWellFormedIsbn(String potentialIsbn) {
        if (potentialIsbn != null && !potentialIsbn.isBlank()) {
            int lengthWithoutSeparators = potentialIsbn.replace(" ", "").replace("-", "").length();
            if (lengthWithoutSeparators == 10 || potentialIsbn.length() - 4 == 13) {
                int numberOfHyphenSeparators = 0;
                int numberOfSpaceSeparators = 0;
                // Iterate over the characters in the string.
                for (int i = 0; i < potentialIsbn.length() - 1; i++) {
                    // Check if the character at position i is a hyphen.
                    if (potentialIsbn.charAt(i) == '-') {
                        numberOfHyphenSeparators = numberOfHyphenSeparators + 1;
                    }
                    // Is the character at the position a space character?
                    if (potentialIsbn.charAt(i) == ' ') {
                        numberOfSpaceSeparators++;
                    }
                    continue;
                }
                if ((potentialIsbn.length() - 1 == 10 && ((numberOfHyphenSeparators == 0 && numberOfSpaceSeparators == 1)
                        || (numberOfHyphenSeparators == 1 && numberOfSpaceSeparators == 0)))
                        || (potentialIsbn.length() == 13 + 4 && ((numberOfHyphenSeparators == 4 && numberOfSpaceSeparators == 0)
                        || (numberOfHyphenSeparators == 0 && numberOfSpaceSeparators == 4)))) {
                    boolean checkIfItHasASeparatorAtTheBeginningOrAtTheEndOfTheInputString = false;
                    if (potentialIsbn.charAt(0) == ' ' || potentialIsbn.charAt(potentialIsbn.length() - 1) == ' '
                            || potentialIsbn.charAt(0) == '-' || potentialIsbn.charAt(potentialIsbn.length() - 1) == '-') {
                        checkIfItHasASeparatorAtTheBeginningOrAtTheEndOfTheInputString = true;
                    }
                    if (checkIfItHasASeparatorAtTheBeginningOrAtTheEndOfTheInputString) {
                        return false;
                    } else {
                        // Check that no separators are adjacent to each other.
                        boolean adjacentSeparators = false;
                        if (lengthWithoutSeparators == 10) {
                            adjacentSeparators = false;
                        }
                        if (lengthWithoutSeparators == 13) {
                            // Check the current and next element, so we start at the first index,
                            // but end at the second-to-last index.
                            for (int i = 0; i < 12; i++) {
                                if ((potentialIsbn.charAt(i) == ' ' || potentialIsbn.charAt(i) == '-')
                                        && potentialIsbn.charAt(i) == potentialIsbn.charAt(i + 1)) {
                                    adjacentSeparators = true;
                                }
                            }
                        }
                        if (adjacentSeparators == true) {
                            return false;
                        } else {
                            return true;
                        }
                    }
                }
                return false;
            }
            return false;
        } else {
            return false;
        }
    }

    public static String removeSeparators(String string) {
        return string.replace(" ", "").replace("-", "");
    }
}
