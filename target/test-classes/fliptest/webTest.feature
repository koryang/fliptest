@testweb
Feature: Web Test

  @verify_language
  Scenario Outline: Verify Language
    Given user visit Filp website
    When user click update toggle language to "<language>"
    Then user should see the "<text>" displayed

     Examples:
        | language       | text                                     |
        | English        | Free financial transactions, to anyone.  |
        | Indonesia      | Bebas transaksi, ke siapa aja.           |

  @verify_provider
  Scenario: Verify Provider
    Given user visit Filp website
    When user click Digital Product sumbenu in Product menu
    Then user should see the "indosat" provider displayed
    And user should see the "smartfren" provider displayed
    And user should see the "telkomsel" provider displayed
    And user should see the "tri" provider displayed
    And user should see the "XL" provider displayed

  @verify_calculation_exchange
  Scenario: Verify Provider
    Given user visit Filp website
    When user visit Flip Globe
    And user select negara tujuan as "United Kingdom"
    And user fill the nominal IDR "100000"
    Then user verify the nominal GBP is correct
    And user verify the nominal to pay

