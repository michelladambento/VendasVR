package com.michell.vendas.vr.client.VendasVR.controllers;

import com.michell.vendas.vr.client.VendasVR.dtos.MessageDTO;
import com.michell.vendas.vr.client.VendasVR.dtos.ProductDTO;
import com.michell.vendas.vr.client.VendasVR.dtos.PurchaseOrderDTO;
import com.michell.vendas.vr.client.VendasVR.dtos.ResponseDTO;
import com.michell.vendas.vr.client.VendasVR.services.PurchaserOrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

public class PurchaserOrderControllerTest {

    @InjectMocks
    private PurchaserOrderController controller;

    @Mock
    private PurchaserOrderService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void saveOrderSuccessfully(){
        PurchaseOrderDTO dto = new PurchaseOrderDTO();
        doNothing().when(service).savePurchaserOrder(dto);
        ResponseEntity<ResponseDTO> response = controller.saveOrder(dto);
        ResponseDTO body = response.getBody();
        MessageDTO message = body.getMessage();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(message.isSuccess());
        assertEquals("Pedido de compra salvo com sucesso.", message.getDetails());
        verify(service).savePurchaserOrder(dto);
    }



}
