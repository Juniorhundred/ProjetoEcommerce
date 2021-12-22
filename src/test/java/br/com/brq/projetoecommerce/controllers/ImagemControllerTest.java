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

import br.com.brq.projetoecommerce.dto.ImagemDTO;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ImagemControllerTest {

	@Autowired
	private MockMvc mockMvc;

	private ObjectMapper objectMapper = new ObjectMapper();
	
	@Test
	void buscarIdTest() throws Exception {

		ResultActions response = mockMvc.perform(get("/imagens/1").contentType("application/json"));

		MvcResult result = response.andReturn();

		String resultStr = result.getResponse().getContentAsString();

		ImagemDTO imagemDTO = objectMapper.readValue(resultStr, ImagemDTO.class);
		System.out.println(imagemDTO);

		// apenas comparando o status da resposta
		assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
		assertThat(imagemDTO.getIdImagem()).isEqualTo(1);

	}
	
	@Test
	void buscarTodasImagensTest() throws Exception {

		ResultActions response = mockMvc.perform(get("/imagens").contentType("application/json"));
		MvcResult result = response.andReturn();

		String resultStr = result.getResponse().getContentAsString();

		ImagemDTO[] list = objectMapper.readValue(resultStr, ImagemDTO[].class);

		assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
		assertThat(list.length >= 0).isTrue();
	}
	
	@Test
	void cadastrarTest() throws JsonProcessingException, Exception {
		ImagemDTO dto = this.createValidImagem();

		ResultActions response = mockMvc.perform(
				post("/imagens").content(objectMapper.writeValueAsString(dto)).contentType("application/json"));

		MvcResult result = response.andReturn();

		String objStr = result.getResponse().getContentAsString();

		ImagemDTO dtoResult = objectMapper.readValue(objStr, ImagemDTO.class);

		assertThat(dtoResult.getIdImagem() > 0).isTrue();
		assertThat(dtoResult.getImagemProduto()).isEqualTo(dto.getImagemProduto());
		;
	}
	
	@Test	
	void alterarTest() throws Exception {
		ImagemDTO dto = this.createValidImagem();

		int id = 1;

		ResultActions response = mockMvc.perform(
				put("/imagens/" + id).content(objectMapper.writeValueAsString(dto)).contentType("application/json"));

		MvcResult result = response.andReturn();

		String resultStr = result.getResponse().getContentAsString();

		ImagemDTO updated = objectMapper.readValue(resultStr, ImagemDTO.class);

		assertThat(updated.getIdImagem()).isEqualTo(id);
		assertThat(updated.getImagemProduto()).isEqualTo(dto.getImagemProduto());
		assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());

	}
	
	@Test	
	void deleteTest() throws Exception {
		int id = 1;

		ResultActions response = mockMvc.perform(delete("/imagens/" + id).contentType("application/json"));

		MvcResult result = response.andReturn();

		assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
	}
	
	private ImagemDTO createValidImagem() {			
		ImagemDTO dto = ImagemDTO.builder().imagemProduto("test").build();
		return dto;
	}
}

