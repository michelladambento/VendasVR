package com.michell.vendas.vr.client.VendasVR.controllers;

import com.michell.vendas.vr.client.VendasVR.dtos.request.OrderRequestDTO;
import com.michell.vendas.vr.client.VendasVR.dtos.response.OrderDTO;
import com.michell.vendas.vr.client.VendasVR.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/")
    public ResponseEntity<OrderDTO> saveOrder(@RequestBody OrderRequestDTO orderRequestDTO){
        return ResponseEntity.ok(orderService.saveOrder(orderRequestDTO));
    }
}
