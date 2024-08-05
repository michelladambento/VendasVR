package com.michell.vendas.vr.client.VendasVR.controllers;

import com.michell.vendas.vr.client.VendasVR.dtos.request.CustomerRequestDTO;
import com.michell.vendas.vr.client.VendasVR.dtos.response.CustomerResponseDTO;
import com.michell.vendas.vr.client.VendasVR.entities.CustomerEntity;
import com.michell.vendas.vr.client.VendasVR.services.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/")
    public ResponseEntity<CustomerResponseDTO> saveCustomer(@RequestBody CustomerRequestDTO customerRequestDTO){
        return ResponseEntity.ok(customerService.saveCustomer(customerRequestDTO));
    }

    @GetMapping("/")
    public ResponseEntity<List<CustomerResponseDTO>> findAllUser(){
        return ResponseEntity.ok(customerService.findAllUser());
    }

    @DeleteMapping("/{id}/")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/")
    public ResponseEntity<Void> updateCustomer(@RequestBody CustomerRequestDTO customerRequestDTO) {
        customerService.updateCustomer(customerRequestDTO);
        return ResponseEntity.noContent().build();
    }


}
