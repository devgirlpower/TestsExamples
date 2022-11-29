package com.example.demo.controller;

import com.example.demo.model.Address;
import com.example.demo.model.PlanDto;
import com.example.demo.model.ResponseDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.mockserver.integration.ClientAndServer;
import org.mockserver.model.HttpRequest;
import org.mockserver.model.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

@SpringBootTest
//@ExtendWith(MockitoExtension.class)
class ControllerTest2 {
    static ClientAndServer server;
    ObjectMapper mapper;
    private Controller controller;

    Address addressExpect;
    private PlanDto dto;

    @BeforeAll
    static void setupServer() {
        server = ClientAndServer.startClientAndServer();
    }

    @BeforeEach
    void setupClass() {
        // o que eu espero
        addressExpect = new Address("Rua Evaristo da Veiga", "Walderez", "Sapucaia do Sul");
        // objeto para testes de cadastro
        dto = new PlanDto();
        dto.setCep("93228120");
        dto.setCpf("jade");
        dto.setDescription("sla");
        dto.setTime("asdfg");
    }

    @AfterAll
    static void shutdownServer() {
        server.close();
    }

    @Test
    void integrationViaCepTest() throws Exception {
        String json = mapper.writeValueAsString(dto);
        String jsonRes = mapper.writeValueAsString(new ResponseDTO("sla", "asdfg", "jade", addressExpect));

        server.when(HttpRequest.request()
                        .withMethod("POST")
                        .withBody(json)
                        .withPath("/clinic"))
                .respond(HttpResponse.response()
                        .withContentType(org.mockserver.model.MediaType.APPLICATION_JSON)
                        .withStatusCode(201)
                        .withBody(jsonRes));

        ResponseEntity<ResponseDTO> planRes = controller.save(dto);
        Assertions.assertEquals(addressExpect.getBairro(), planRes.getBody().getAddress().getBairro());
    }
}
