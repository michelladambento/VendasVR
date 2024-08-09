package com.michell.vendas.vr.client.VendasVR.services;

import com.michell.vendas.vr.client.VendasVR.converters.CustomerConverterImpl;
import com.michell.vendas.vr.client.VendasVR.dtos.CustomerDTO;
import com.michell.vendas.vr.client.VendasVR.dtos.ProductItemDTO;
import com.michell.vendas.vr.client.VendasVR.dtos.PurchaseOrderDTO;
import com.michell.vendas.vr.client.VendasVR.entities.CustomerEntity;
import com.michell.vendas.vr.client.VendasVR.entities.ProductEntity;
import com.michell.vendas.vr.client.VendasVR.entities.PurchaserOrderEntity;
import com.michell.vendas.vr.client.VendasVR.exceptions.DateOrderValidException;
import com.michell.vendas.vr.client.VendasVR.exceptions.DefaultNotFoundException;
import com.michell.vendas.vr.client.VendasVR.exceptions.TotalOrderValidException;
import com.michell.vendas.vr.client.VendasVR.repositories.CustomerRepository;
import com.michell.vendas.vr.client.VendasVR.repositories.ProductItemRepository;
import com.michell.vendas.vr.client.VendasVR.repositories.ProductRepository;
import com.michell.vendas.vr.client.VendasVR.repositories.PurchaserOrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class PurchaserOrderServiceTest {

    @InjectMocks
    private PurchaserOrderService service;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ProductItemRepository productItemRepository;

    @Mock
    private PurchaserOrderRepository purchaserOrderRepository;

    private PurchaseOrderDTO purchaseOrderDTO;

    private ProductItemDTO productItemDTO;

    private CustomerEntity customerEntity;

    private ProductEntity productEntity;



    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        purchaseOrderDTO = new PurchaseOrderDTO();
        purchaseOrderDTO.setCustomerId(1L);
        purchaseOrderDTO.setOrderDateAt(LocalDate.now());
        purchaseOrderDTO.setOrderTotal(100.0);

        productItemDTO = new ProductItemDTO();
        productItemDTO.setProductId(1L);
        productItemDTO.setUnitPrice(50.0);
        productItemDTO.setQtd(2);

        List<ProductItemDTO> producties = new ArrayList<>();
        producties.add(productItemDTO);

        purchaseOrderDTO.setProductItens(producties);

        customerEntity = new CustomerEntity();
        customerEntity.setClosingDateAt(LocalDate.now());
        customerEntity.setPurchaseLimit(200.0);

        productEntity = new ProductEntity();
        productEntity.setId(1L);
        productEntity.setPrice(50.0);
        productEntity.setDescription("Detergente");
    }

    @Test
    public void savePurchaserOrderSuccessfully(){
        when(customerRepository.findById(purchaseOrderDTO.getCustomerId())).thenReturn(Optional.of(customerEntity));
        when(productRepository.findById(productItemDTO.getProductId())).thenReturn(Optional.of(productEntity));
        service.savePurchaserOrder(purchaseOrderDTO);
        verify(customerRepository).findById(purchaseOrderDTO.getCustomerId());
        verify(productRepository).findById(productItemDTO.getProductId());
        verify(purchaserOrderRepository).saveAndFlush(Mockito.any(PurchaserOrderEntity.class));
    }

    @Test
    public void failedDueToCustumerNotFounded(){
        when(customerRepository.findById(purchaseOrderDTO.getCustomerId())).thenReturn(Optional.empty());
        DefaultNotFoundException exception = assertThrows(DefaultNotFoundException.class, ()->{
            service.savePurchaserOrder(purchaseOrderDTO);
        });
        String message = String.format("Cliente de ID (%s) não encontrado", purchaseOrderDTO.getCustomerId());
        assertEquals(message, exception.getMessage());
        verify(customerRepository).findById(purchaseOrderDTO.getCustomerId());
        verify(productRepository, never()).findById(productItemDTO.getProductId());
        verify(purchaserOrderRepository, never()).saveAndFlush(any(PurchaserOrderEntity.class));
    }

    @Test
    public void failedDueToOrderDateIsGraterThanCosingDate(){
        purchaseOrderDTO.setOrderDateAt(LocalDate.now().plusDays(1));
        when(customerRepository.findById(purchaseOrderDTO.getCustomerId())).thenReturn(Optional.of(customerEntity));
        when(productRepository.findById(productItemDTO.getProductId())).thenReturn(Optional.of(productEntity));
        DateOrderValidException exception = assertThrows(DateOrderValidException.class, ()->{
            service.savePurchaserOrder(purchaseOrderDTO);
        });
        String message = String.format("Data do fechamento: (%s) está vencida, altere no cadastro de cliente.", customerEntity.getClosingDateAt());
        assertEquals(message, exception.getMessage());
        verify(customerRepository).findById(purchaseOrderDTO.getCustomerId());
        verify(productRepository, never()).findById(productItemDTO.getProductId());
        verify(purchaserOrderRepository, never()).saveAndFlush(any(PurchaserOrderEntity.class));
    }

    @Test
    public void failedDueToOrderTotalIsGraterThanPurchaseLimit(){
        purchaseOrderDTO.setOrderTotal(300.0);
        when(customerRepository.findById(purchaseOrderDTO.getCustomerId())).thenReturn(Optional.of(customerEntity));
        when(productRepository.findById(productItemDTO.getProductId())).thenReturn(Optional.of(productEntity));
        TotalOrderValidException exception = assertThrows(TotalOrderValidException.class, ()->{
            service.savePurchaserOrder(purchaseOrderDTO);
        });
        String message = String.format("Limite disponível: RS(%s) - Data de fechamento: (%s).", customerEntity.getPurchaseLimit(), customerEntity.getClosingDateAt());
        assertEquals(message, exception.getMessage());
        verify(customerRepository).findById(purchaseOrderDTO.getCustomerId());
        verify(productRepository, never()).findById(productItemDTO.getProductId());
        verify(purchaserOrderRepository, never()).saveAndFlush(any(PurchaserOrderEntity.class));
    }

    @Test
    public void failedDueToProductItemListIsEmpty(){
        purchaseOrderDTO.getProductItens().clear();
        when(customerRepository.findById(purchaseOrderDTO.getCustomerId())).thenReturn(Optional.of(customerEntity));
        when(productRepository.findById(productItemDTO.getProductId())).thenReturn(Optional.of(productEntity));
        DefaultNotFoundException exception = assertThrows(DefaultNotFoundException.class, ()->{
            service.savePurchaserOrder(purchaseOrderDTO);
        });
        assertEquals("A lista de produto não pode ser vazio.", exception.getMessage());
        verify(customerRepository).findById(purchaseOrderDTO.getCustomerId());
        verify(productRepository, never()).findById(productItemDTO.getProductId());
        verify(purchaserOrderRepository, never()).saveAndFlush(any(PurchaserOrderEntity.class));
    }

    @Test
    public void failedDueToProductItemListIsDuplicated(){
        purchaseOrderDTO.getProductItens().add(productItemDTO);
        when(customerRepository.findById(purchaseOrderDTO.getCustomerId())).thenReturn(Optional.of(customerEntity));
        when(productRepository.findById(productItemDTO.getProductId())).thenReturn(Optional.of(productEntity));
        DefaultNotFoundException exception = assertThrows(DefaultNotFoundException.class, ()->{
            service.savePurchaserOrder(purchaseOrderDTO);
        });
        String message = String.format("Produto de ID:(%s) duplicado.", productItemDTO.getProductId());
        assertEquals(message, exception.getMessage());
        verify(customerRepository).findById(purchaseOrderDTO.getCustomerId());
        verify(productRepository, never()).findById(productItemDTO.getProductId());
        verify(purchaserOrderRepository, never()).saveAndFlush(any(PurchaserOrderEntity.class));
    }

//    @Test
//    public void savePurchaserOrderSuccessfully(){
//        when(customerRepository.findById(purchaseOrderDTO.getCustomerId())).thenReturn(Optional.of(customerEntity));
//        when(productRepository.findById(productItemDTO.getProductId())).thenReturn(Optional.of(productEntity));
//        service.savePurchaserOrder(purchaseOrderDTO);
//        verify(customerRepository).findById(purchaseOrderDTO.getCustomerId());
//        verify(productRepository).findById(productItemDTO.getProductId());
//        verify(purchaserOrderRepository).saveAndFlush(Mockito.any(PurchaserOrderEntity.class));
//    }

    @Test
    public void failedBecauseProductIDNotFounded(){
        when(customerRepository.findById(purchaseOrderDTO.getCustomerId())).thenReturn(Optional.of(customerEntity));
        when(productRepository.findById(productItemDTO.getProductId())).thenReturn(Optional.empty());
        DefaultNotFoundException exception = assertThrows(DefaultNotFoundException.class, ()->{
            service.savePurchaserOrder(purchaseOrderDTO);
        });
        String message = String.format("Produto de ID(%s) não encontrado.", productItemDTO.getProductId());
        assertEquals(message, exception.getMessage());
        verify(customerRepository).findById(purchaseOrderDTO.getCustomerId());
        verify(productRepository).findById(productItemDTO.getProductId());
        verify(purchaserOrderRepository, never()).saveAndFlush(any(PurchaserOrderEntity.class));
    }






}
