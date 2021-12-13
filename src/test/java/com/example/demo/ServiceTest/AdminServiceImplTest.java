package com.example.demo.ServiceTest;


import com.example.demo.dao.AdminRepository;
import com.example.demo.dto.AdminDto;
import com.example.demo.dto.CandidateDto;
import com.example.demo.entities.Admin;
import com.example.demo.exceptions.EntityNotFoundException;
import com.example.demo.services.impl.AdminServiceImp;
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
public class AdminServiceImplTest {


    @InjectMocks
    private AdminServiceImp adminServiceImpl;


    @Mock
    private AdminRepository adminRepository;

    private Admin admin;
    private AdminDto adminDto;
    private Long id;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        adminDto = new AdminDto();
        adminDto.setFirstname("firstname");
        adminDto.setLastname("lastname");
        adminDto.setUsername("username");
        admin = new Admin();
        admin.setFirstname("firstname");
        admin.setLastname("lastname");
        admin.setUsername("username");
        admin.setId(8L);
    }


    AdminDto adminDto() {
        AdminDto dto = new AdminDto();
        dto.setFirstname("nameF");
        dto.setLastname("nameL");
        dto.setUsername("nameU");
        dto.setEmail("email");
        dto.setPhone("0675875456");
        dto.setAddress("casablanca");
        dto.setOrganization("mecanic");
        dto.setPassword("12345o");
        return dto;
    }


    Admin entity() {
        Admin e = new Admin();
        e.setFirstname("MockF");
        e.setLastname("MockL");
        e.setUsername("MockU");
        e.setEmail("MockE");
        e.setPhone("0676565456");
        e.setAddress("casablanca");
        e.setOrganization("aml");
        e.setPassword("12345");
        return e;
    }

    /*@Test
    public void testAddAdmin() {
        when(adminRepository.save(admin)).thenReturn(admin);
        AdminDto addedAdmin = adminServiceImpl.createAdmin(adminDto);
        assertThat(addedAdmin.getFirstname()).isEqualTo(admin.getFirstname());
    }*/

    @Test
    public void testCreateAdmin() {
        Mockito.when(adminRepository.save(entity())).thenReturn(entity());
        AdminDto result = adminServiceImpl.createAdmin(adminDto());
        Assert.assertNotNull(result);
        assertThat(result.getUsername()).isEqualTo(entity().getUsername());
        // NO VALUE PRESENT

    }

    @Test
    public void testGetAdminByID()  {
        when(adminRepository.findById(anyLong())).thenReturn(Optional.of(entity()));
        AdminDto result = adminServiceImpl.getAdminById(Long.valueOf(1));
        Assert.assertNotNull(result);
        Assert.assertEquals("MockU", entity().getUsername());
    }

    @Test
    public void testGetAdminByEmail() {
        when(adminRepository.findByEmail(anyString())).thenReturn(Optional.of(entity()));
        AdminDto result1 = adminServiceImpl.getAdminByEmail("salimaa@gmail.com");
        Assert.assertNotNull(result1);
        Assert.assertEquals("MockE", entity().getEmail());
    }

    @Test
    public void testGetAdminByUserName()  {
        when(adminRepository.findByUsername(anyString())).thenReturn(Optional.of(entity()));
        AdminDto result2 = adminServiceImpl.getAdminByUsername("username");
        Assert.assertNotNull(result2);
        Assert.assertEquals("MockU", entity().getUsername());
    }



    @Test
    public void testGetAllAdmins() {
        List<Admin> admins = new ArrayList<Admin>();
        Admin admin = new Admin();
        admins.add(admin);
        when(adminRepository.findAll()).thenReturn(admins);
        List<AdminDto> adminDtos = adminServiceImpl.getAllAdmins();
        assertEquals(1, adminDtos.size());
        verify(adminRepository, times(1)).findAll();


    }

    @Test
    public void testUpdateAdmin()  {
        when(adminRepository.findById(anyLong())).thenReturn(Optional.of(entity()));
        AdminDto result3 = adminServiceImpl.updateAdmin(id,adminDto);
        Assert.assertNotNull(result3);
        Assert.assertEquals("username", entity().getId());

        //USER NOT FOUND
    }

    @Test
    public void testUpdateAdminForException() {
        Optional<Admin> optionalAdmin = Optional.empty();
        when(adminRepository.findById(id)).thenReturn(optionalAdmin);

        Throwable thrown = catchThrowable(() -> {
            adminServiceImpl.updateAdmin(id, adminDto);
        });
        assertThat(thrown).isInstanceOf(EntityNotFoundException.class);
    }


    @Test
    public void testGetAdminByIdForException() {
        Optional<Admin> optionalAdmin = Optional.empty();
        when(adminRepository.findById(id)).thenReturn(optionalAdmin);

        Throwable thrown = catchThrowable(() -> {
            adminServiceImpl.getAdminById(id);
        });
        assertThat(thrown).isInstanceOf(EntityNotFoundException.class);
    }


    @Test
    public void deleteAdminForException() {
        Optional<Admin> optionalAdmin = Optional.empty();
        Mockito.when(adminRepository.findById(id)).thenReturn(optionalAdmin);
        Throwable thrown = catchThrowable(() -> {
            adminServiceImpl.deleteAdmin("MockU");
        });
        assertThat(thrown).isInstanceOf(EntityNotFoundException.class);

    }

    @Test
    public void deleteAdmin() throws Exception{
        Mockito.doNothing().when(adminRepository).deleteById(Long.valueOf(1));
        Mockito.when(adminRepository.findById(Long.valueOf(1))).thenReturn(Optional.of(entity()));
        adminServiceImpl.deleteAdmin("username");
        Mockito.verify(adminRepository, Mockito.times(1)).deleteById(Long.valueOf(1));
        //User NOT FOUND
    }

}
