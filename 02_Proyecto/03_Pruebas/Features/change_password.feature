Feature: Cambio de Contraseña
  Probar la funcionalidad de cambio de contraseña desde la actividad principal.

  @change-password-feature
  Scenario Outline: Actualizar contraseña correctamente
    Given Yo tengo una actividad de inicio de sesión1
    When Ingreso el nombre de usuario1 <username>
    And Ingreso la contraseña1 <password>
    And Presiono el botón de enviar1
    And Debería ver la siguiente actividad1
    When Presiono el botón de actualizar contraseña
    Then Debería ver la actividad de actualizar contraseña
    And Ingreso la contraseña actual <currentPassword>
    And Ingreso la nueva contraseña <newPassword>
    And Confirmo la nueva contraseña <confirmPassword>
    And Presiono el botón para guardar la nueva contraseña
    Then Debería ver un mensaje de éxito

    Examples:
      | password   | currentPassword | newPassword | confirmPassword | username   |
      | 1718605155 | 1718605155      | 1718605156  | 1718605155      | 1718605155 |
      | 1718605155 | 1718605155      | 1718605156  | 1718605156      | 1718605155 |
      | 1718605156 | 1718605156      | 1718605155  | 1718605155      | 1718605155 |
  @change-password-feature
  Scenario Outline: Actualizar contraseña incorrectamente
    Given Yo tengo una actividad de inicio de sesión1
    When Ingreso el nombre de usuario1 <username>
    And Ingreso la contraseña1 <password>
    And Presiono el botón de enviar1
    And Debería ver la siguiente actividad1
    When Presiono el botón de actualizar contraseña
    Then Debería ver la actividad de actualizar contraseña
    And Ingreso la contraseña actual <currentPassword>
    And Ingreso la nueva contraseña <newPassword>
    And Confirmo la nueva contraseña <confirmPassword>
    And Presiono el botón para guardar la nueva contraseña
    Then Debería ver un mensaje de error en password

    Examples:
      | password   | currentPassword | newPassword | confirmPassword | username   |
      | 1718605155 | 1718605155      | nueva       | nueva1          | 1718605155 |