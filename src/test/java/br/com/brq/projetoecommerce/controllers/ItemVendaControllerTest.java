package br.com.brq.projetoecommerce.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.brq.projetoecommerce.dto.ItemVendaDTO;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ItemVendaControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	private ObjectMapper objectMapper = new ObjectMapper();
	
	@Test
	void buscarIdTest() throws Exception {

		ResultActions response = mockMvc.perform(get("/itemvenda/1").contentType("application/json"));

		MvcResult result = response.andReturn();

		String resultStr = result.getResponse().getContentAsString();

		ItemVendaDTO itemVendaDTO = objectMapper.readValue(resultStr, ItemVendaDTO.class);
		

		// apenas comparando o status da resposta
		assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
		assertThat(itemVendaDTO.getIdItemVenda()).isEqualTo(1);

	}
	
	@Test
	void buscarTodasImagensTest() throws Exception {

		ResultActions response = mockMvc.perform(get("/itemvenda").contentType("application/json"));
		MvcResult result = response.andReturn();

		String resultStr = result.getResponse().getContentAsString();

		ItemVendaDTO[] list = objectMapper.readValue(resultStr, ItemVendaDTO[].class);

		assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
		assertThat(list.length >= 0).isTrue();
	}
	
	@Test
	void cadastrarTest() throws JsonProcessingException, Exception {
		ItemVendaDTO dto = this.createValidItemVenda();

		ResultActions response = mockMvc.perform(
				post("/itemvenda").content(objectMapper.writeValueAsString(dto)).contentType("application/json"));

		MvcResult result = response.andReturn();

		String objStr = result.getResponse().getContentAsString();

		ItemVendaDTO dtoResult = objectMapper.readValue(objStr, ItemVendaDTO.class);

		assertThat(dtoResult.getIdItemVenda() > 0).isTrue();
		assertThat(dtoResult.getItemQuantidade() >=0).isTrue();
		
	}
	
	@Test	
	void alterarTest() throws Exception {
		ItemVendaDTO dto = this.createValidItemVenda();

		int id = 1;

		ResultActions response = mockMvc.perform(
				put("/itemvenda/" + id).content(objectMapper.writeValueAsString(dto)).contentType("application/json"));

		MvcResult result = response.andReturn();

		String resultStr = result.getResponse().getContentAsString();

		ItemVendaDTO updated = objectMapper.readValue(resultStr, ItemVendaDTO.class);

		assertThat(updated.getIdItemVenda()).isEqualTo(id);
		assertThat(updated.getItemQuantidade() >=0).isTrue();
		assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());

	}
	
	@Test	
	void deleteTest() throws Exception {
		int id = 1;

		ResultActions response = mockMvc.perform(delete("/itemvenda/" + id).contentType("application/json"));

		MvcResult result = response.andReturn();

		assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
	}
	
	private ItemVendaDTO createValidItemVenda() {			
		ItemVendaDTO dto = ItemVendaDTO.builder().idItemVenda(1).itemQuantidade(1).build();
		return dto;
	}


}
