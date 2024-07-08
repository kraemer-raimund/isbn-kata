package org.example.utils;

/**
 * Strings the utils.
 */
public class StringUtils {

    /**
     * Determines whether the string represents a well-formed ISBN.
     *
     * @param potentialIsbn
     * @return a boolean
     */
    public static boolean isWellFormedIsbn(String potentialIsbn) {
        if (potentialIsbn != null && !potentialIsbn.isBlank()) {
            var res = false;
            for (char c : potentialIsbn.toCharArray()) {
                if (!Character.isDigit(c) && c != '-' && c != ' ') {
                    res = true;
                }
            }

            if (res == true) {
                return false;
            } else {
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
            }
        } else {
            return false;
        }
    }

    // Convert ISBN to EAN (European Article Number). Make sure the string is actually an ISBN!
    public static String convertToEan(String isbn) {
        var isbnWithoutSeparators = removeSeparators(isbn);
        String prefix;
        if (isbnWithoutSeparators.length() == 10) {
            // Special EAN country code for books, added to 10-digit ISBNs regardless of country of origin.
            prefix = "978";
        } else if (isbnWithoutSeparators.length() == 13) {
            // 13-digit ISBNs are directly compatible with 13-digit EANs by removing the separators.
            prefix = "";
        } else {
            throw new RuntimeException("Should never happen. If it does, then // TODO debug it.");
        }
        // I read that StringBuilder is faster than concatenation. It was on the internet, so it must be true.
        return new StringBuilder()
                .append(prefix)
                .append(isbnWithoutSeparators)
                .toString();
    }

    public static String removeSeparators(String string) {
        return string.replace(" ", "").replace("-", "");
    }

    public static String displayInCorrectFormatBasedOnLength(String isbn) {
        if (removeSeparators(isbn).length() == 10) {
            return replaceAllHyphens(isbn, " ");
        } else if (removeSeparators(isbn).length() == 13) {
            return replaceAllSpaces(isbn, "-");
        }
        return null;
    }

    private static String replaceAllHyphens(String string, String replacement) {
        return replaceAllOccurrences(string, "-", replacement);
    }

    private static String replaceAllSpaces(String string, String replacement) {
        return replaceAllOccurrences(string, " ", replacement);
    }

    private static String replaceAllOccurrences(String string, String toReplace, String replacement) {
        return string.replace(toReplace, replacement);
    }
}
