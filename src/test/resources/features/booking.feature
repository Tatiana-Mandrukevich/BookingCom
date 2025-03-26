Feature: Search on Booking.com

  @smoke
  Scenario: Search by city
    Given User is looking for hotels in 'United States' city
    Given User is looking for hotels in 'United States' city
    And User goes to the site Booking.com
    When User does search
    Then Hotel 'RentPlanet - Apartament Krokiew' should be on the search results page
    Then Hotel 'RentPlanet - Apartament Krokiew' rating is '9,0'

  @smoke
  Scenario: Change language, search by city and filters
    Given User is looking for hotels in 'Antalya' city
    Given User is looking for hotels in 'Antalya' city
    And User goes to the site Booking.com
    And User changes language to 'English (UK)'
    And User does search
    When User filters by 'Property rating' and selects '5 stars'
    Then Hotel 'Radisson Blu Hotel & Residences' should be on the search results page
    Then Hotel 'Radisson Blu Hotel & Residences' rating is '8.6'