package com.nttdata.glue;

import com.nttdata.steps.PedidosSteps;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Steps;

public class PedidosStepsDef {

    @Steps
    PedidosSteps pedidos;

    @Given("que la API de PetStore se encuentra operativa")
    public void queLaAPIDePetStoreSeEncuentraOperativa() {
    }


    @When("Creo un pedido para la mascota con id {int} con la cantidad {int} y el estado {string}")
    public void creoUnPedidoParaLaMascotaConIdConLaCantidadYElEstado(int mascotaId, int cantidad, String estado) {

        pedidos.crearPedido(mascotaId,cantidad,estado);

    }

    @Then("El codigo de estado de la respuesta deberia ser {int}")
    public void elCodigoDeEstadoDeLaRespuestaDeberiaSer(int statuscode) {
        pedidos.validarStatusCode(statuscode);
    }

    @And("La respuesta deberia tener el id del pedido recien creado")
    public void laRespuestaDeberiaTenerElIdDelPedidoRecienCreado() {
        pedidos.validarQueExisteIdEnRespuesta();
    }


    @And("Adicionalmente la respuesta debe tener las propiedades: petId con valor {int}, quantity con valor {int} y status con valor {string}")
    public void adicionalmenteLaRespuestaDebeTenerLosDatosPetIdConValorQuantityConValorYStatusConValor(int petId, int quantity, String status) {

        pedidos.validarPropiedadesEnRespuesta(petId,quantity,status);

    }

    @When("Consulto los datos de un pedido con el Id {int}")
    public void consultoLosDatosDeUnPedidoConElId(int pedidoId) {
        pedidos.consultarPedidoPorId(pedidoId);
    }


    @And("La propiedad id de la respuesta deberia tener el valor {int}")
    public void laPropiedadIdDeLaRespuestaDeberiaTenerElValor(int pedidoId) {

        pedidos.validarValorDeId(pedidoId);

    }

    @And("Adicionalmente la respuesta debe tener las propiedades: petId, quantity, shipDate, status y complete")
    public void adicionalmenteLaRespuestaDebeTenerLasPropiedadesPetIdQuantityShipDateStatusYComplete() {

        pedidos.validarPresenciaDePropiedadesEnRespuestaDeConsultaDePedido();

    }
}
