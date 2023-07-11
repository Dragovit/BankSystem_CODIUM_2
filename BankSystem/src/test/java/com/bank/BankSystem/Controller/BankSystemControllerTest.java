package com.bank.BankSystem.Controller;

import com.bank.BankSystem.Entity.BankCard;
import com.bank.BankSystem.Entity.Person;
import com.bank.BankSystem.Repository.PersonRepository;
import com.bank.BankSystem.Services.PersonService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BankSystemControllerTest {

    @Mock
    private PersonService personService;

    @InjectMocks
    private BankSystemController bankSystemController;

    private MockMvc mockMvc;

    @Test
    public void testAddPerson_ValidInput_ShouldReturnOk() throws Exception {

        mockMvc = MockMvcBuilders.standaloneSetup(bankSystemController).build();

        mockMvc.perform(MockMvcRequestBuilders.post("/addPerson")
                        .content("{\"name\":\"Jozo\",\"surname\":\"Maly\",\"personalNumber\":\"0105214591\"}")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Person successfully added!"));
    }

    @Test
    public void testAddPerson_InvalidInput_ShouldReturnBadRequest() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(bankSystemController).build();

        mockMvc.perform(MockMvcRequestBuilders.post("/addPerson")
                        .content("{\"name\":\"Jozo\",\"personalNumber\":\"0105214591\"}")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().string("Invalid input missing: surname "));
    }

    @Test
    public void testFindAll_NoPersonsFound_ShouldReturnInternalServerError() throws Exception {
        List<Person> emptyList = new ArrayList<>();

        when(personService.findAll()).thenReturn(emptyList);

        mockMvc = MockMvcBuilders.standaloneSetup(bankSystemController).build();

        mockMvc.perform(MockMvcRequestBuilders.get("/findAll"))
                .andExpect(MockMvcResultMatchers.status().isInternalServerError());
    }

    @Test
    public void testFindAll_PersonsFound_ShouldReturnOk() throws Exception {
        List<Person> persons = new ArrayList<>();
        List<BankCard> bankCards = new ArrayList<>();

        bankCards.add(new BankCard("0010_0472_9494_6583", "0503", "Jozo Maly", "442"));
        persons.add(new Person("Jozo", "Maly", "0105214591", bankCards));

        when(personService.findAll()).thenReturn(persons);

        mockMvc = MockMvcBuilders.standaloneSetup(bankSystemController).build();

        mockMvc.perform(MockMvcRequestBuilders.get("/findAll"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(persons.size()));
    }
}