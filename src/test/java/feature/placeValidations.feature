Feature: Validating place API

@AddPlace @Regression
Scenario Outline: Verifying if AddPlace API is working
Given Add place payload "<Address>" "<Name>" "<Language>"
When user call "AddPlaceAPI" with "post" http request
Then the API call got success with status code 200
And "status" in response body is "OK"
And "scope" in response body is "APP"
And verify place_Id created maps to "<Name>" using "GetPlaceAPI"

Examples:
        |Address     |Name |Language|
        |32 Baker std|Bingo|Bingi   |
#       |2nd Street  |Tony |English |

@DeletePlace @Regression
Scenario: Verify if deletePlace API is working
Given Delete place payload
When user call "DeletePlaceAPI" with "post" http request
Then the API call got success with status code 200
And "status" in response body is "OK"