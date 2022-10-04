@test
Feature: API Test

  @get_todo_list
  Scenario: GET - /public/v2/todos - verify 20 items
    Given client get the access token
    When client send a GET endpoint "/public/v2/todos"
    Then client verify the todo items are 20

  @get_user_inactive
  Scenario: GET - /public/v2/todos - verify 20 items
    Given client get the access token
    When client send a GET endpoint "/public/v2/users?status=inactive"
    Then client verify the user status is "inactive"

