package com.michell.vendas.vr.client.VendasVR.controllers;

import com.michell.vendas.vr.client.VendasVR.dtos.ResponseDTO;
import com.michell.vendas.vr.client.VendasVR.dtos.response.RetrieveAllCustomersDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

//    @Autowired
//    private OrderService orderService;
//
//    @PostMapping("/")
//    public ResponseEntity<ResponseDTO> saveOrder(@RequestBody RetrieveAllCustomersDTO.OrderDTO orderDTO){
//        orderService.saveOrder(orderDTO);
//        ResponseDTO response = new ResponseDTO(true, "Cliente salvo com sucesso.");
//        return new ResponseEntity<>(response, HttpStatus.OK);
//    }
}
