Feature: Gestión de Sugerencias
  Probar la funcionalidad de enviar sugerencias desde la actividad de sugerencias.

  @sugerencias-feature
  Scenario Outline: Enviar sugerencias exitosamente
    Given Yo tengo una actividad de inicio de sesión2
    When Ingreso el nombre de usuario2 <username>
    And Ingreso la contraseña2 <password>
    And Presiono el botón de enviar2
    And Debería ver la siguiente actividad2
    When Presiono el botón Actualizar Campo profesional
    Then Debería ver la actividad profesional
    When Presiono el botón Sugerencias
    Then Debería ver la actividad sugerencias
    And Activo el check "Sí" para indicar que tengo sugerencias
    And Ingreso el tema <tema>
    And Ingreso la descripción <descripcion>
    And Presiono el botón para guardar la sugerencia
    Then Debería ver un mensaje de éxito de sugerencia guardada

    Examples:
      | username   | password   | tema         | descripcion       |
      | 1718605155 | 1718605155 | MejorUX      | Agregarmascolores |

  @sugerencias-feature
  Scenario Outline: Intentar enviar sugerencias sin activar el check
    Given Yo tengo una actividad de inicio de sesión2
    When Ingreso el nombre de usuario2 <username>
    And Ingreso la contraseña2 <password>
    And Presiono el botón de enviar2
    And Debería ver la siguiente actividad2
    When Presiono el botón Actualizar Campo profesional
    Then Debería ver la actividad profesional
    When Presiono el botón Sugerencias
    Then Debería ver la actividad sugerencias
    And Activo el check "No" para indicar que no tengo sugerencias
    And Intento ingresar el tema <tema>
    And Intento ingresar la descripción <descripcion>
    And Presiono el botón para guardar la sugerencia
    Then Debería ver un mensaje de error indicando que no se activó el check

    Examples:
      | username   | password   | tema    | descripcion    |
      | 1718605155 | 1718605155 | "TemaA" | "DescripcionA" |
