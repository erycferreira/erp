package com.erycferreira.enterprise.api.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class ReportControllerIT {

  @Autowired
  private MockMvc mockMvc;

  @Test
  void shouldCreateReportAndReturn200() throws Exception {
    mockMvc.perform(
        post("/reports")
            .contentType(MediaType.APPLICATION_JSON)
            .content("""
                {
                  "name": "Q1 Financial",
                  "type": "FINANCIAL"
                }
                """))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.status").value("CREATED"))
        .andExpect(jsonPath("$.id").isNotEmpty());
  }

  @Test
  void shouldReturn422WhenNameIsBlank() throws Exception {
    mockMvc.perform(
        post("/reports")
            .contentType(MediaType.APPLICATION_JSON)
            .content(
                """
                    {
                       "name": "",
                       "type": "FINANCIAL"
                     }
                     """))
        .andExpect(status().isUnprocessableContent());
  }
}
