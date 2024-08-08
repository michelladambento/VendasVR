package com.michell.vendas.vr.client.VendasVR.controllers;

import com.michell.vendas.vr.client.VendasVR.converters.CustomerConverter;
import com.michell.vendas.vr.client.VendasVR.dtos.CustomerDTO;
import com.michell.vendas.vr.client.VendasVR.dtos.MessageDTO;
import com.michell.vendas.vr.client.VendasVR.dtos.ResponseDTO;
import com.michell.vendas.vr.client.VendasVR.dtos.response.RetrieveAllCustomersDTO;
import com.michell.vendas.vr.client.VendasVR.entities.CustomerEntity;
import com.michell.vendas.vr.client.VendasVR.services.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CustomerControllerTest {

    @InjectMocks
    private CustomerController customerController;

    @Mock
    private CustomerService service;

    @Mock
    private CustomerConverter converter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void retrieveAllCustomerSuccessfully(){
        CustomerEntity customerEntity1 = new CustomerEntity();
        List<CustomerEntity> customerEntities = new ArrayList<>();
        customerEntities.add(customerEntity1);

        CustomerDTO customerDTO1 = new CustomerDTO();
        List<CustomerDTO> customerDTOs = new ArrayList<>();
        customerDTOs.add(customerDTO1);

        when(service.findAllUser()).thenReturn(customerEntities);
        when(converter.converterToListDto(customerEntities)).thenReturn(customerDTOs);

        ResponseEntity<RetrieveAllCustomersDTO> response = customerController.findAllUser();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody().getMessage().isSuccess());
        assertEquals("Clientes recuperado com sucesso.", response.getBody().getMessage().getDetails());
        assertNotNull(response.getBody().getCustomers());
        assertEquals(1, response.getBody().getCustomers().size());

        verify(service).findAllUser();
        verify(converter).converterToListDto(customerEntities);
    }

    @Test
    public void deleteCustomerSuccessfully(){
        Long id = 1L;
        doNothing().when(service).deleteCustomer(id);
        ResponseEntity<ResponseDTO> response = customerController.deleteCustomer(id);
        MessageDTO message = response.getBody().getMessage();
        assertTrue(message.isSuccess());
        assertEquals("Cliente deletado com sucesso.", message.getDetails());
        verify(service).deleteCustomer(id);
    }

    @Test
    public void saveCustomerSuccessfully(){
        CustomerDTO dto = new CustomerDTO();
        doNothing().when(service).saveCustomer(dto);
        ResponseEntity<ResponseDTO> response = customerController.saveCustomer(dto);
        MessageDTO message = response.getBody().getMessage();
        assertTrue(message.isSuccess());
        assertEquals("Cliente salvo com sucesso.", message.getDetails());
        verify(service).saveCustomer(dto);

    }

}
