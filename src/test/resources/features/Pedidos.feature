@GestionPedidosDeMascotas
Feature: Gestión de Pedidos de Mascotas

  Background:
    Given que la API de PetStore se encuentra operativa

  @CrearPedido
  Scenario Outline: Crear Pedido de una Mascota
    When Creo un pedido para la mascota con id <petId> con la cantidad <quantity> y el estado "<status>"
    Then El codigo de estado de la respuesta deberia ser <statuscode>
    And La respuesta deberia tener el id del pedido recien creado
    And Adicionalmente la respuesta debe tener las propiedades: petId con valor <petId>, quantity con valor <quantity> y status con valor "<status>"
    Examples:
      | petId | quantity | status | statuscode |
      | 8     | 5        | placed | 200        |
      | 6     | 3        | placed | 200        |
      | 8     | 7        | placed | 200        |

  @ConsultarPedido
  Scenario Outline: Consultar un Pedido por su Id
    When Consulto los datos de un pedido con el Id <orderId>
    Then El codigo de estado de la respuesta deberia ser <statuscode>
    And La propiedad id de la respuesta deberia tener el valor <orderId>
    And Adicionalmente la respuesta debe tener las propiedades: petId, quantity, shipDate, status y complete
    Examples:
      | orderId | statuscode |
      | 2       | 200        |
      | 3       | 200        |
      | 80      | 200        |