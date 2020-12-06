Feature: Create customer
  Customer should be created

  Scenario Outline: Create customer
    Given a customer "<firstName>" "<lastName>"
    When I say create customer
    Then I should be told customer "<answer>"

    Examples:
      | firstName | lastName | answer |
      | Sunday | MgalaMgala | true |
      |  |  | false |