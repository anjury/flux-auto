Feature: Using the TradeMe Sandbox web site write automation code which does the following:

  Scenario: Return how many named brands of used cars are available in the TradeMe UsedCars category.
    Given I want to buy a used car
    When I browse the Used Cars category
    Then I will be able to count how many makes of used cars are available

  Scenario Outline: Check that the brand ‘Kia’ exists and return the current number of Kia cars listed.
    Given I want to buy a used car
    When I search for a used car made by "<make>"
    Then I should see at least one "<make>" is available
    And I will be able to count how many are available

    Examples:
    | make |
    | Kia  |

  Scenario: Check that the brand ‘Hispano Suiza’ does not exist.
    Given I want to buy a used car
    When I search for a used car made by "Hispano Suiza"
    Then I should see that none are available