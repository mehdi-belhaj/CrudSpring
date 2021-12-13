package com.example.demo.ControllerTest;


import org.junit.Assert;

import org.junit.Test;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import javax.swing.text.html.parser.Entity;

import static org.assertj.core.api.Assertions.*;

import static org.assertj.core.api.InstanceOfAssertFactories.OPTIONAL;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.when;


import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;




import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import com.example.demo.dto.CandidateDto;

import com.example.demo.services.CandidatService;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;

import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.spi.CalendarNameProvider;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.BDDMockito.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verifyNoMoreInteractions;





import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import com.example.demo.controllers.CandidatController;
import com.example.demo.dao.CandidateRepository;
import com.example.demo.dto.CandidateDto;
import com.example.demo.dto.requests.CandidateRequest;
import com.example.demo.entities.Candidate;
import com.example.demo.services.CandidatService;
import com.example.demo.services.impl.CandidatServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.mapping.model.CamelCaseSplittingFieldNamingStrategy;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

@DisplayName("Employee Controller")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@RunWith(SpringRunner.class)
public class CandidatControllerTest {

    private MockMvc mockMvc;
    private CandidateDto CandidatDTO;
    private List<CandidateDto> mockCandidatDTOList;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CandidatService candidatService;

    /*@BeforeAll
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();

        CandidatDTO = new CandidateDto("foo","nom", "userr", "email1@gmail.com");

        mockCandidatDTOList = Stream.of(
                new CandidateDto("foo","nameL","user1" ,"foo@gmail.com"),
                new CandidateDto( "voli","nameLL" ,"user2","voli@gmail.com"),
                new CandidateDto( "po", "nameLLL","user3","po@gmail.com")
        ).collect(Collectors.toList());

    }*/

    @Test
    @DisplayName("Update")
    public void shouldUpdateCandidate() {
        try {
            Long Id = 101L;
            when(candidatService.getCandidateById(Id)).thenReturn(CandidatDTO);
            when(candidatService.updateCandidate(Id,Mockito.any(CandidateDto.class)));

            String jsonRequest = objectMapper.writeValueAsString(CandidatDTO);

            mockMvc.perform(post("/personalData")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(jsonRequest))
                    .andExpect(status().isOk())
                    .andExpect(content().string("true"));

            verify(candidatService, times(1)).updateCandidate(Id,Mockito.any(CandidateDto.class));
            verifyNoMoreInteractions(candidatService);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }



   /* @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper mapper;
    @Mock
    CandidatServiceImpl candidatServiceImpl;
    @InjectMocks
    CandidatController candidatController;
    @MockBean
    CandidateRepository candidateRepository;
    @Test
    public void update() {

        CandidateDto mockCandidate = mock(CandidateDto.class);
        Long id = 1L;
        String user = "nom";

        when(mockCandidate.getFirstname()).thenReturn("name1");
        when(mockCandidate.getLastname()).thenReturn("last1");
        when(candidatServiceImpl.updateCandidate(id, mockCandidate)).thenReturn(mockCandidate);
        when(candidatServiceImpl.existUsername(user)).thenReturn(true);

        ResponseEntity<CandidateDto> responseEntity = candidatController.updateCandidate();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(mockCandidate, responseEntity.getBody());*/



}
