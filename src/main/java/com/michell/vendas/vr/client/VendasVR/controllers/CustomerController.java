package com.michell.vendas.vr.client.VendasVR.controllers;

import com.michell.vendas.vr.client.VendasVR.converters.CustomerConverter;
import com.michell.vendas.vr.client.VendasVR.dtos.CustomerDTO;
import com.michell.vendas.vr.client.VendasVR.dtos.ResponseDTO;
import com.michell.vendas.vr.client.VendasVR.dtos.response.RetrieveAllCustomersDTO;
import com.michell.vendas.vr.client.VendasVR.entities.CustomerEntity;
import com.michell.vendas.vr.client.VendasVR.services.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @Autowired
    private CustomerConverter customerConverter;

    @PostMapping("/")
    public ResponseEntity<ResponseDTO> saveCustomer(@RequestBody CustomerDTO customerDTO){
        customerService.saveCustomer(customerDTO);
        ResponseDTO response = new ResponseDTO(true, "Cliente salvo com sucesso.");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/")
    public ResponseEntity<RetrieveAllCustomersDTO> findAllUser(){
        List<CustomerEntity> customersEntities = customerService.findAllUser();
        List<CustomerDTO> customersResponse = customerConverter.convert(customersEntities);
        RetrieveAllCustomersDTO response = new RetrieveAllCustomersDTO(true, "Clientes recuperado com sucesso.");
        response.setCustomers(customersResponse);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @DeleteMapping("/{id}/")
    public ResponseEntity<ResponseDTO> deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        ResponseDTO response = new ResponseDTO(true, "Cliente deletado com sucesso.");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/")
    public ResponseEntity<ResponseDTO> updateCustomer(@RequestBody CustomerDTO customerDTO) {
        Long customerId = customerDTO.getId();
        CustomerEntity customerEntity = customerService.findById(customerId);
        customerService.updateCustomer(customerEntity, customerDTO);
        ResponseDTO response = new ResponseDTO(true, "Cliente atualizado com sucesso.");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
