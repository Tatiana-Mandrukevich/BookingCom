Feature: Search on Booking.com

  @smoke
  Scenario: Search by city
    Given User is looking for hotels in 'United States' city
    Given User is looking for hotels in 'United States' city
    When User does search
    Then Hotel 'Kwatera Górska 903mnpm' should be on the search results page
    Then Hotel 'Kwatera Górska 903mnpm' rating is '9,0'