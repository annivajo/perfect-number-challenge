package code.challenge.perfectnumber.controller;

import code.challenge.perfectnumber.PerfectnumberApplication;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.validation.ConstraintViolationException;
import javax.validation.constraints.Null;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest(classes = PerfectnumberApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PerfectNumberControllerIT {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp(){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @SneakyThrows
    @Test
    public void givenCheckNumberURIWithPathVariablePerfectNumber_whenMockMVC_thenResponseOK() {
        this.mockMvc
                .perform(get("/perfect-numbers/check/{number}", "6"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").value("true"));
    }

    @SneakyThrows
    @Test
    public void givenCheckNumberURIWithPathVariableNotPerfectNumber_whenMockMVC_thenResponseOK() {
        this.mockMvc
                .perform(get("/perfect-numbers/check/{number}", "20"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").value("false"));
    }

    @SneakyThrows
    @Test
    public void givenFindInRangeURIWithPathVariable_whenMockMVC_thenResponseOK() {
        this.mockMvc
                .perform(get("/perfect-numbers/find-numbers-in-range/{start}/{end}", "6","100"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[0]").value("6"))
                .andExpect(jsonPath("$.[1]").value("28"));
    }

    @SneakyThrows
    @Test
    public void givenFindInRangeURIWithViolationConstraint_whenMockMVC_thenBadRequest(){

        String expectedMsg = "findAllPerfectNumbersInRange.start: id must be greater than or equal to 1";
        this.mockMvc
                .perform(get("/perfect-numbers/find-numbers-in-range/{start}/{end}", "0","100"))
                .andDo(print()).andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof ConstraintViolationException))
                .andExpect(result -> assertEquals(expectedMsg,result.getResolvedException().getMessage()));
    }

    @SneakyThrows
    @Test
    public void givenFindInRangeURIWithTypeMismatch_whenMockMVC_thenBadRequest(){

        String expectedMsg = "Failed to convert value of type 'java.lang.String' to required type 'java.lang.Integer'; " +
                "nested exception is java.lang.NumberFormatException: For input string: ";
        this.mockMvc
                .perform(get("/perfect-numbers/find-numbers-in-range/{start}/{end}", "abc","100"))
                .andDo(print()).andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof MethodArgumentTypeMismatchException))
                .andExpect(result -> assertEquals(expectedMsg + "\"abc\"",result.getResolvedException().getMessage()));
    }


}