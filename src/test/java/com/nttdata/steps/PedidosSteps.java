package com.nttdata.steps;

import com.nttdata.models.Pedido;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import org.junit.Assert;

import java.net.URL;

public class PedidosSteps {

    private final String URL_Base = "https://petstore.swagger.io/v2";
    private Pedido ultimoPedidoCreado;
    private Pedido ultimoPedidoConsultado;

    @Step
    public void crearPedido(int mascotaId, int cantidad, String estado) {

        Response response = SerenityRest
                .given()
                .baseUri(URL_Base)
                .contentType("application/json")
                .relaxedHTTPSValidation()
                .body("{\n" +
                        "  \"petId\": "+mascotaId+",\n" +
                        "  \"quantity\": "+cantidad+",\n" +
                        "  \"status\": \""+estado+"\"\n" +
                        "}")
                .log().all()
                .when()
                .post("/store/order")
                .then()
                .extract().response();

        ultimoPedidoCreado = response.as(Pedido.class);
    }

    @Step
    public void validarStatusCode(int statusCodeEsperado){
        int statusCodeActual = SerenityRest.lastResponse().getStatusCode();
        Assert.assertEquals(statusCodeEsperado, statusCodeActual);
    }

    @Step
    public void validarQueExisteIdEnRespuesta(){

        Assert.assertNotNull("La respuesta debe contener un ID",
                SerenityRest.lastResponse().jsonPath().get("id"));

    }

    @Step
    public void validarPropiedadesEnRespuesta(int petId, int quantity, String status){

        Assert.assertTrue(
                "Las propiedades en la respuesta no coinciden con las esperadas",
                ultimoPedidoCreado.getPetId() == petId &&
                        ultimoPedidoCreado.getQuantity() == quantity &&
                        ultimoPedidoCreado.getStatus().equals(status)
        );

    }

    @Step
    public void consultarPedidoPorId(int pedidoId){

        ultimoPedidoConsultado = SerenityRest
                .given()
                .baseUri(URL_Base)
                .relaxedHTTPSValidation()
                .when()
                .get("/store/order/" + String.valueOf(pedidoId))
                .as(Pedido.class);
    }

    public void validarValorDeId(int pedidoId){
        Assert.assertEquals(ultimoPedidoConsultado.getId(),pedidoId);
    }

    public void validarPresenciaDePropiedadesEnRespuestaDeConsultaDePedido(){

        Assert.assertTrue("La respuesta debe contener todas las propiedades requeridas",
                SerenityRest.lastResponse().jsonPath().get("petId") != null &&
                        SerenityRest.lastResponse().jsonPath().get("quantity") != null &&
                        SerenityRest.lastResponse().jsonPath().get("shipDate") != null &&
                        SerenityRest.lastResponse().jsonPath().get("status") != null &&
                        SerenityRest.lastResponse().jsonPath().get("complete") != null
        );


    }

}
