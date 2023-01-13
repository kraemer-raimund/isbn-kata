# ISBN Kata - Refactoring Legacy Code

branch: `refactoring-legacy-code-starting-point`

In this kata, the code base already fulfills all of the requirements of the [TDD implementation variant](tdd-implementation-kata.md), but the code smells.

## Refactoring Goal

- No static utils.

## Constraints

- No changing of existing code or adding of new code without writing a test first.
- No unit tests for static methods at any point.
  <details><summary>Why not?</summary>
  While the existing code is not clean, it is considered trusted and correct in its current state. We want to add tests to improve the existing code and refactor it safely, but we do not want to add any tests that lock in the existing code smells.

___

© 2023 Raimund Krämer - Use with attribution.

Links to third party sites are included for convenience only and I am not responsible for their contents.
