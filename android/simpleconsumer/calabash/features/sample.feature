Feature: Sample Calabash Tests

  Scenario: The "Get Data" button is visible
    Given I see "Get Data"
    Then I take a screenshot

  Scenario: Pressing the "Get Data" button loads a list of male names
    Given I press "Get Data"
    Then I wait up to 30 seconds for "10 results." to appear
    Then I take a screenshot
