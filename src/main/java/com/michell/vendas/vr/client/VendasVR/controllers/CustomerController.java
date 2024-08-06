package com.michell.vendas.vr.client.VendasVR.controllers;

import com.michell.vendas.vr.client.VendasVR.converters.CustomerConverter;
import com.michell.vendas.vr.client.VendasVR.converters.CustomerStoreParamConverter;
import com.michell.vendas.vr.client.VendasVR.dtos.CustomerDTO;
import com.michell.vendas.vr.client.VendasVR.dtos.CustomerParam;
import com.michell.vendas.vr.client.VendasVR.dtos.CustomerStoreDTO;
import com.michell.vendas.vr.client.VendasVR.dtos.response.CustomerResponseDTO;
import com.michell.vendas.vr.client.VendasVR.dtos.response.DeleteCustomerResponseDTO;
import com.michell.vendas.vr.client.VendasVR.dtos.response.RetrieveAllCustomersResponseDTO;
import com.michell.vendas.vr.client.VendasVR.dtos.response.SaveCustomerResponseDTO;
import com.michell.vendas.vr.client.VendasVR.entities.CustomerEntity;
import com.michell.vendas.vr.client.VendasVR.repositories.CustomerRepository;
import com.michell.vendas.vr.client.VendasVR.services.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerConverter customerConverter;

    @Autowired
    private CustomerStoreParamConverter customerStoreParamConverter;

    @PostMapping("/")
    public ResponseEntity<SaveCustomerResponseDTO> saveCustomer(@RequestBody CustomerStoreDTO request){
        CustomerParam customerParam = customerStoreParamConverter.convert(request);
        customerService.saveCustomer(customerParam);
        SaveCustomerResponseDTO response = new SaveCustomerResponseDTO(true, "Cliente salvo com sucesso.");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/")
    public ResponseEntity<RetrieveAllCustomersResponseDTO> findAllUser(){
        List<CustomerEntity> customersEntities = customerService.findAllUser();
        List<CustomerResponseDTO> customersResponse = customerConverter.convert(customersEntities);
        RetrieveAllCustomersResponseDTO response = new RetrieveAllCustomersResponseDTO(true, "Clientes recuperado com sucesso.");
        response.setCustomers(customersResponse);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @DeleteMapping("/{id}/")
    public ResponseEntity<DeleteCustomerResponseDTO> deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        DeleteCustomerResponseDTO response = new DeleteCustomerResponseDTO(true, "Cliente deletado com sucesso.");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/")
    public ResponseEntity<SaveCustomerResponseDTO> updateCustomer(@RequestBody CustomerStoreDTO request) {
        CustomerDTO customerDTO = request.getCustomer();
        Long customerId = customerDTO.getId();
        CustomerEntity customerEntity = customerService.findById(customerId);
        customerService.updateCustomer(customerEntity, request);
        SaveCustomerResponseDTO response = new SaveCustomerResponseDTO(true, "Cliente atualizado com sucesso.");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
