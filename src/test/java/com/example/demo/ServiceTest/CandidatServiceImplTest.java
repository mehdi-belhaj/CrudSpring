package com.example.demo.ServiceTest;

import com.example.demo.dao.AdminRepository;
import com.example.demo.dao.CandidateRepository;
import com.example.demo.dto.AdminDto;
import com.example.demo.dto.CandidateDto;
import com.example.demo.entities.Admin;
import com.example.demo.entities.Candidate;
import com.example.demo.entities.Role;
import com.example.demo.enumerations.Gender;
import com.example.demo.enumerations.RoleName;
import com.example.demo.exceptions.EntityNotFoundException;
import com.example.demo.services.AdminService;
import com.example.demo.services.CandidatService;
import com.example.demo.services.impl.AdminServiceImp;


import com.example.demo.services.impl.CandidatServiceImpl;
import org.junit.Assert;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.*;
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


import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;



@RunWith(SpringRunner.class)
public class CandidatServiceImplTest {


    @InjectMocks
    private CandidatServiceImpl candidateServiceImpl;

    @Mock
    private CandidateRepository candidateRepository;

    private Candidate candidate;
    private CandidateDto candidateDto;
    private Long id;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        candidateDto = new CandidateDto();
        candidateDto.setFirstname("firstname");
        candidateDto.setLastname("lastname");
        candidateDto.setUsername("username");
        candidate = new Candidate();
        candidate.setFirstname("firstname");
        candidate.setLastname("lastname");
        candidate.setUsername("username");
        candidate.setId(8L);
    }



    CandidateDto candidateDto() {
        CandidateDto dto = new CandidateDto();
        dto.setFirstname("MockF");
        dto.setLastname("MockL");
        dto.setUsername("MockU");
        dto.setEmail("email1@gmail.com");
        dto.setPhone("0676545456");
        dto.setAddress("casablanca");
        dto.setPassword("123459");
        dto.setGender(Gender.FEMALE);
        return dto;
    }


    Candidate entity() {
        Candidate e = new Candidate();
        e.setFirstname("MockF");
        e.setLastname("MockL");
        e.setUsername("MockU");
        e.setEmail("email1@gmail.com");
        e.setPhone("0676565456");
        e.setPassword("123459");
        return e;
    }

   /* @Test
    public void testAddCandidate() {
        when(candidateRepository.save(candidate)).thenReturn(candidate);
        CandidateDto addedCandidate = candidateServiceImpl.createCandidate(candidateDto);
        assertThat(addedCandidate.getFirstname()).isEqualTo(candidate.getFirstname());
    }*/


    @Test
    public void testCreateCandidate() {
        Mockito.when(candidateRepository.save(entity())).thenReturn(entity());
        CandidateDto result = candidateServiceImpl.createCandidate(candidateDto());
        Assert.assertNotNull(result);
        assertThat(result.getUsername()).isEqualTo(entity().getUsername());

    }

    @Test
    public void testGetCandidateByID()  {
        when(candidateRepository.findById(anyLong())).thenReturn(Optional.of(entity()));
        CandidateDto result = candidateServiceImpl.getCandidateById(Long.valueOf(1));
        Assert.assertNotNull(result);
        Assert.assertEquals("MockU", entity().getUsername());

    }


    @Test
    public void testGetCandidateByUserName()  {
        when(candidateRepository.findByUsername(anyString())).thenReturn(Optional.of(entity()));
        CandidateDto result2 = candidateServiceImpl.getCandidateByUsername("username");
        Assert.assertNotNull(result2);
        Assert.assertEquals("MockU", entity().getUsername());
    }



    @Test
    public void testGetAllCandidate() {
        List<Candidate> candidates = new ArrayList<Candidate>();
        Candidate candidate = new Candidate();
        candidates.add(candidate);
        when(candidateRepository.findAll()).thenReturn(candidates);
        List<CandidateDto> candidateDtos = candidateServiceImpl.getAllCandidates();
        assertEquals(1, candidateDtos.size());
        verify(candidateRepository, times(1)).findAll();


    }

    @Test
    public void testUpdateCandidate()  {
        when(candidateRepository.findById(anyLong())).thenReturn(Optional.of(entity()));
        CandidateDto result3 = candidateServiceImpl.updateCandidate(id,candidateDto);
        Assert.assertNotNull(result3);
        Assert.assertEquals("username", entity().getId());
        //USER NOT FOUND
    }

    @Test
    public void testUpdateAdminForException() {
        Optional<Candidate> optionalCandidate = Optional.empty();
        when(candidateRepository.findById(id)).thenReturn(optionalCandidate);

        Throwable thrown = catchThrowable(() -> {
            candidateServiceImpl.updateCandidate(id, candidateDto);
        });
        assertThat(thrown).isInstanceOf(EntityNotFoundException.class);
    }




    @Test
    public void deleteCandidateForException() {
        Optional<Candidate> optionalCandidate = Optional.empty();
        Mockito.when(candidateRepository.findById(id)).thenReturn(optionalCandidate);
        Throwable thrown = catchThrowable(() -> {
            candidateServiceImpl.deleteCandidate(Long.valueOf(12));
        });
        assertThat(thrown).isInstanceOf(EntityNotFoundException.class);

    }

    @Test
    public void deleteCandidate() throws Exception{
        Mockito.doNothing().when(candidateRepository).deleteById(Long.valueOf("1"));
        Mockito.when(candidateRepository.findById(Long.valueOf("1"))).thenReturn(Optional.of(entity()));
        candidateServiceImpl.deleteCandidate(Long.valueOf("1"));
        Mockito.verify(candidateRepository, Mockito.times(1)).deleteById(Long.valueOf("1"));



    }

}