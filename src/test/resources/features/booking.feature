Feature: Search on Booking.com

  @smoke
  Scenario: Search by city
    Given User is looking for hotels in 'United Stated' city
    Given User is looking for hotels in 'United Stated' city
    When User does search
    Then Hotel 'North Beach Resort & Villas' should be on the search results page
    Then Hotel 'North Beach Resort & Villas' rating is '8,7'