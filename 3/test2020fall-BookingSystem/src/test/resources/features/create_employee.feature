Feature: Create employee
  Employee should be created

  Scenario Outline: Create employee
    Given a employee "<firstName>" "<lastName>"
    When I say create employee
    Then I should be told employee "<answer>"

    Examples:
      | firstName | lastName | answer |
      | Revisor | Kurt | true |
      |  |  | false |