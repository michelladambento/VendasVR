package com.michell.vendas.vr.client.VendasVR.converters;

import com.michell.vendas.vr.client.VendasVR.dtos.CustomerDTO;
import com.michell.vendas.vr.client.VendasVR.entities.CustomerEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CustomerConverterTest {
    @Autowired
    private CustomerConverter converter;

    @Test
    public void convertedToEntityProvidedEntityAndDto(){
        CustomerDTO dto = loadDTO();
        CustomerEntity entity = new CustomerEntity();
        CustomerEntity entityUpdated = converter.convert(entity, dto);
        assertSame(entity, entityUpdated);
    }

    @Test
    public void convertedDtoToEntitySuccessfully(){
        CustomerDTO dto = loadDTO();
        CustomerEntity entity = converter.converter(dto);
        assertEquals(dto.getId(), entity.getId());
        assertEquals(dto.getCustomerName(), entity.getCustomerName());
        assertEquals(dto.getPurchaseLimit(), entity.getPurchaseLimit());
        assertEquals(dto.getClosingDateAt(), entity.getClosingDateAt());
    }

    @Test
    public void convertedListDtoToListEntitySuccessfully(){
        List<CustomerDTO> list = new ArrayList<>();
        CustomerDTO customerDTO = loadDTO();
        list.add(customerDTO);
        List<CustomerEntity> entities = converter.converter(list);
        CustomerEntity customerEntity = entities.get(0);
        assertFalse(entities.isEmpty());
    }

    @Test
    public void convertedEntityToDtoSuccessfully(){
        CustomerEntity entity = loadEntity();
        CustomerDTO dto = converter.converter(entity);
        assertEquals(dto.getId(), entity.getId());
        assertEquals(dto.getCustomerName(), entity.getCustomerName());
        assertEquals(dto.getPurchaseLimit(), entity.getPurchaseLimit());
        assertEquals(dto.getClosingDateAt(), entity.getClosingDateAt());
    }

    @Test
    public void convertListEntityToListDtosSuccessfully(){
        List<CustomerEntity> list = new ArrayList<>();
        CustomerEntity entity = loadEntity();
        list.add(entity);
        List<CustomerDTO> dtos = converter.converterToListDto(list);
        assertFalse(dtos.isEmpty());
    }


    private CustomerDTO loadDTO(){
        CustomerDTO dto = new CustomerDTO();
        dto.setId(1L);
        dto.setCustomerName("michell adam bento");
        dto.setClosingDateAt(LocalDate.now());
        dto.setPurchaseLimit(1.0);
        return dto;
    }

    private CustomerEntity loadEntity(){
        CustomerEntity entity = new CustomerEntity();
        entity.setId(1L);
        entity.setCustomerName("michell adam bento");
        entity.setClosingDateAt(LocalDate.now());
        entity.setPurchaseLimit(1.0);
        return entity;
    }
}
