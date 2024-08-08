package com.michell.vendas.vr.client.VendasVR.controllers;

import com.michell.vendas.vr.client.VendasVR.converters.CustomerConverter;
import com.michell.vendas.vr.client.VendasVR.services.CustomerService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

public class CustomerControllerTest {

    @Autowired
    private Mock mvc;

    @MockBean
    private CustomerService service;

    @MockBean
    private CustomerConverter converter;

    @Test
    public void retrievesuccessfully(){


    }

}
