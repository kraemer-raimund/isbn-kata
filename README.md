# ISBN Kata

A coding kata that focuses on **design** and **TDD**.

## Application

The program is a book library administration app. You can consider the provided code to be a small subset of the full application.

If you open the `BookSearchView` you find the `main` method. In order to avoid depending on a full UI framework, we simulate a simple UI. Each time the main method runs and calls `onSearchSubmitted`, it is the equivalent of the user pressing "Enter" in their search bar. The search input is read from the command line arguments (or can alternatively be hardcoded in the `onSearchSubmitted` method).

## Variants

This kata can be practiced in different variants, depending on the skill to focus on:

- ### [TDD Implementation](tdd-implementation-kata.md)
  branch: `tdd-implementation-starting-point`
- ### ~~[Refactoring Legacy Code](refactoring-legacy-code-kata.md)~~ *(work in progress, please try the other variant for now!)*
  branch: `refactoring-legacy-code-starting-point`
  

___

© 2023 Raimund Krämer - Use with attribution.

Links to third party sites are included for convenience only and I am not responsible for their contents.
