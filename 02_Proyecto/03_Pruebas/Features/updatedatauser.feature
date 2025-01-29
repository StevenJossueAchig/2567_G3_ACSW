Feature: Actualización de Datos Personales
  Probar la funcionalidad de actualización de datos personales desde la actividad principal.

  @update-user-data-feature
  Scenario Outline: Actualizar datos personales correctamente
    Given El usuario está en la pantalla de inicio de sesión
    When Ingreso el nombre de usuario3 <username>
    And Ingreso la contraseña3 <password>
    And Presiono el botón de iniciar sesion
    Then Debería ver la pantalla principal3
    When Navega a la pantalla de actualización de datos
    Then Debería ver la pantalla de actualización de datos personales
    And Ingresa el nuevo nombre <newName>
    And Ingresa el nuevo correo electrónico <newEmail>
    And Ingresa el nuevo número de teléfono <newPhone>
    And Presiona el botón para guardar los cambios
    Then Debería ver un mensaje de confirmación

    Examples:
      | username   | password   | newName      | newEmail                 | newPhone   |
      | 1718605155 | 1718605155 | JuanPerez    | juanperez@example.com    | 0987654321 |
      | 1718605155 | 1718605155 | AnaSanchez   | anasanchez@example.com   | 0987654322 |

  @update-user-data-feature
  Scenario Outline: Actualizar datos personales incorrectamente
    Given El usuario está en la pantalla de inicio de sesión
    When Ingreso el nombre de usuario3 <username>
    And Ingreso la contraseña3 <password>
    And Presiono el botón de iniciar sesion
    Then Debería ver la pantalla principal3
    When Navega a la pantalla de actualización de datos
    Then Debería ver la pantalla de actualización de datos personales
    And Ingresa el nuevo nombre <newName>
    And Ingresa el nuevo correo electrónico <newEmail>
    And Ingresa el nuevo número de teléfono <newPhone>
    And Presiona el botón para guardar los cambios
    Then Debería ver un mensaje de error si hay datos inválidos

    Examples:
      | username   | password   | newName    | newEmail           | newPhone    |
      | 1718605155 | 1718605155 | JuanPerez  | juan.perez@com     | 0987654321  |
      | 1718605155 | 1718605155 | AnaSanchez | ana.sanchez@.com   | 09876543    |