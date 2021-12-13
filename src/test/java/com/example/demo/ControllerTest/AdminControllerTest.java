package com.example.demo.ControllerTest;

import com.example.demo.ServiceTest.AdminServiceImplTest;
import com.example.demo.dao.AdminRepository;
import com.example.demo.dao.CandidateRepository;
import com.example.demo.dto.AdminDto;
import com.example.demo.dto.CandidateDto;
import com.example.demo.entities.Candidate;
import com.example.demo.services.CandidatService;
import com.example.demo.services.impl.AdminServiceImp;
import com.example.demo.services.impl.CandidatServiceImpl;
import com.example.demo.utils.JsonUtils;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;


import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.mockito.ArgumentMatchers.isA;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


public class AdminControllerTest {

    @Mock
    private AdminRepository adminRepository;
    private CandidateRepository candidateRepository;

    @InjectMocks
    private CandidatServiceImpl candidatServiceImpl;
    private AdminServiceImp adminServiceImp;


    private CandidateDto candidateDto;
    private Candidate candidate;

    private MockMvc mockMvc;



    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        candidateDto = new CandidateDto();
        candidateDto.setFirstname("firstname");
        candidateDto.setLastname("lastname");
        candidateDto.setUsername("username");
        candidateDto.setEmail("naame@gmail.com");
        candidate = new Candidate();
        candidate.setFirstname("firstnameA");
        candidate.setLastname("lastnameZ");
        candidate.setUsername("usernameE");
        candidate.setEmail("naame1@gmail.com");

    }

    @Test
    public void createCandidateTest() throws Exception {
        CandidateDto candidateDto = new CandidateDto();
        candidateDto.setFirstname("name");
        given(candidatServiceImpl.createCandidate(candidateDto)).willReturn(candidateDto);
        mockMvc.perform(post("/candidate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtils.toJson(candidateDto)))
                .andExpect(status().isCreated())
                .andExpect((ResultMatcher) jsonPath("$.firstname", is(candidateDto.getFirstname())));
    }
}



























