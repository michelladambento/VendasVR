package com.michell.vendas.vr.client.VendasVR.controllers;

import com.michell.vendas.vr.client.VendasVR.dtos.PurchaseOrderDTO;
import com.michell.vendas.vr.client.VendasVR.dtos.ResponseDTO;
import com.michell.vendas.vr.client.VendasVR.services.PurchaserOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/purchaserOrder")
@RequiredArgsConstructor
public class PurchaserOrderController {

    @Autowired
    private PurchaserOrderService purchaserOrderService;

    @PostMapping("/")
    public ResponseEntity<ResponseDTO> saveOrder(@RequestBody PurchaseOrderDTO purchaseOrderDTO){
        purchaserOrderService.savePurchaserOrder(purchaseOrderDTO);
        ResponseDTO response = new ResponseDTO(true, "Pedido de compra salvo com sucesso.");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
