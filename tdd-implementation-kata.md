# ISBN Kata - TDD Implementation

branch: `tdd-implementation-starting-point`

In this kata, an existing code base needs to be adapted to new requirements.

## Design Constraints

- No static utils.

## Requirements

*Consider a parallel universe where ISBN rules are much simpler than in real life.*

- If the user input is not a well-formed ISBN, an error message is shown; no search is performed in that case. An ISBN consists of either 10 digits and 1 separator (e.g. "12345678-90"), or 13 digits and 4 separators (e.g. "123-4-56-789012-3"). Separators can be at any position except the beginning and end, and no two separators can be adjacent to each other. Some books may have a 10-digit ISBN, others a 13-digit ISBN. The two formats are considered equivalent, but there is no way to convert between the two formats.
- Both hyphens and spaces are supported as separators, but they may not be mixed within the same ISBN.
- In this application, 13-digit ISBNs are *always* displayed using hyphens as separators, whereas 10-digit ISBNs are *always* displayed using spaces. This applies only to how they are displayed to the user, hyphens and spaces are otherwise considered equivalent, so "12345-67890" and "12345 67890" are considered equal, but only the latter will ever be shown to the user.
- An ISBN can be converted to an EAN (European Article Number), and the EAN should always be displayed in the search result in addition to the ISBN. The rules for converting an ISBN to an EAN are as follows:
  - A 13-digit ISBN is converted to an EAN by removing all separators.
  - A 10-digit ISBN is converted to an EAN by removing all separators and adding "978" as prefix.

___

© 2023 Raimund Krämer - Use with attribution.

Links to third party sites are included for convenience only and I am not responsible for their contents.
