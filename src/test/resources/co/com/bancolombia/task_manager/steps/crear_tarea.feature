Feature: Crear una tarea desde el endpoint

  Scenario: Crear una tarea con un JSON válido
    Given the user provides a valid JSON
    When a POST is made to /tasks
    Then the system responds with status 201
    And the body contains the ID and the saved data