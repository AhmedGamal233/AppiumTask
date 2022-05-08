Feature: Purchase
  Scenario: add product into cart
    Given As user after navigate to home screen and dismiss tooltip
    When scroll to product and tap on it
    When product details screen displayed and tap on buy now from pop up
    Then Cart screen should be displayed with selected product
