package org.example;

import org.example.utils.StringUtils;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class StringUtilsTest {

    @ParameterizedTest
    @CsvSource({
            ", is null",
            "'', is empty string",
            "123-4-56-78012-3, too short (13-digit ISBN with only 12 digits)",
            "123-4-56-789012-34, too long (13-digit ISBN with 14 digits)",
            "1234-5678, too short (10-digit ISBN with only 8 digits)",
            "1234-56789012, too long (10-digit ISBN with 12 digits)",
            "123-4-56-7$9012-3, contains non-digit non-separator",
            "123-4-56-7a9b12-c, contains letters",
            "123-4 56-789012 3, mixes separators",
            "123 4 56 789 012 3, contains too many separators (13-digit ISBN must have 4)",
            "123-456789-012-3, contains too few separators (13-digit ISBN must have 4)",
            "123-4567-890, contains too many separators (10-digit ISBN must have 1)",
            "1234567890, contains too few separators (10-digit ISBN must have 1)",
            "-123-4-56-7890123, 13-digit ISBN with separator at the beginning",
            "'123 4 56 7890123 ', 13-digit ISBN with separator at the end",
            "' 123 456 7890123 ', 13-digit ISBN with separator at the beginning and end",
            "' 64632879 42', 10-digit ISBN with separator at the beginning",
            "'6463287942-', 10-digit ISBN with separator at the end",
    })
    void classifiesAsNotWellFormedIsbn(String notWellFormedIsbn, String description) {
        assertThat(StringUtils.isWellFormedIsbn(notWellFormedIsbn))
                .describedAs(description)
                .isFalse();
    }

    @ParameterizedTest
    @CsvSource({
            "123-4-56-789012-3, 13-digit ISBN with hyphens",
            "4-44156-7890-1-23, 13-digit ISBN with hyphens",
            "123 4 56 789012 3, 13-digit ISBN with spaces",
            "5 4 3 2 109871337, 13-digit ISBN with spaces",
            "12345678-90, 10-digit ISBN with hyphen",
            "123-4567890, 10-digit ISBN with hyphen",
            "123 4567890, 10-digit ISBN with space",
            "64632879 42, 10-digit ISBN with space",
    })
    void classifiesAsWellFormedIsbn(String wellFormedIsbn, String description) {
        assertThat(StringUtils.isWellFormedIsbn(wellFormedIsbn))
                .describedAs(description)
                .isTrue();
    }
}
