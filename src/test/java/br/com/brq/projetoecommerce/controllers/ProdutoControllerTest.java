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

import br.com.brq.projetoecommerce.domain.ProdutoEntity;
import br.com.brq.projetoecommerce.dto.ProdutoDTO;
import br.com.brq.projetoecommerce.services.ProdutoService;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ProdutoControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ProdutoService produtoService;

	private ObjectMapper objectMapper = new ObjectMapper();
	
	@Test
	void buscarIdTest() throws Exception {
		
		ProdutoDTO dto = this.createValidProduto();
		ProdutoEntity enty = produtoService.salvar(dto.toEntity());				

		ResultActions response = mockMvc.perform(get("/produtos/"+ enty.getIdProduto()).content(objectMapper.writeValueAsString(dto)).contentType("application/json"));

		MvcResult result = response.andReturn();

		String resultStr = result.getResponse().getContentAsString();

		ProdutoDTO produtoDTO = objectMapper.readValue(resultStr, ProdutoDTO.class);
	
		assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
		assertThat(produtoDTO.getIdProduto()).isEqualTo(enty.getIdProduto());

	}	
	
	
	@Test
	void buscarTodosProdutosTest() throws Exception {

		ResultActions response = mockMvc.perform(get("/produtos").contentType("application/json"));
		MvcResult result = response.andReturn();

		String resultStr = result.getResponse().getContentAsString();

		ProdutoDTO[] list = objectMapper.readValue(resultStr, ProdutoDTO[].class);

		assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
		assertThat(list.length >= 0).isTrue();
	}
	
	@Test
	void cadastrarTest() throws JsonProcessingException, Exception {
		ProdutoDTO dto = this.createValidProduto();

		ResultActions response = mockMvc.perform(
				post("/produtos").content(objectMapper.writeValueAsString(dto)).contentType("application/json"));

		MvcResult result = response.andReturn();

		String objStr = result.getResponse().getContentAsString();

		ProdutoDTO dtoResult = objectMapper.readValue(objStr, ProdutoDTO.class);

		assertThat(dtoResult.getIdProduto() > 0).isTrue();
		assertThat(dtoResult.getNome()).isEqualTo(dto.getNome());
		assertThat(dtoResult.getDescricao()).isEqualTo(dto.getDescricao());
		assertThat(dtoResult.getPreco()).isEqualTo(dto.getPreco());
		assertThat(dtoResult.getCategorias()).isEqualTo(dto.getCategorias());
		assertThat(dtoResult.getImagens()).isEqualTo(dto.getImagens());
	}
	
	@Test	
	void alterarTest() throws Exception {
		ProdutoDTO dto = this.createValidProduto();

		int id = 1;

		ResultActions response = mockMvc.perform(
				put("/produtos/" + id).content(objectMapper.writeValueAsString(dto)).contentType("application/json"));

		MvcResult result = response.andReturn();

		String resultStr = result.getResponse().getContentAsString();

		ProdutoDTO updated = objectMapper.readValue(resultStr, ProdutoDTO.class);

		assertThat(updated.getIdProduto()).isEqualTo(id);
		assertThat(updated.getNome()).isEqualTo(dto.getNome());
		assertThat(updated.getPreco()).isEqualTo(dto.getPreco());
		assertThat(updated.getDescricao()).isEqualTo(dto.getDescricao());
		//assertThat(updated.getCategorias()).isEmpty();
		//assertThat(updated.getImagens()).isEmpty();

		assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());

	}
	
	@Test	
	void deleteTest() throws Exception {
		int id = 1;

		ResultActions response = mockMvc.perform(delete("/produtos/" + id).contentType("application/json"));

		MvcResult result = response.andReturn();

		assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
	}
	
	private ProdutoDTO createValidProduto() {	
		
		ProdutoDTO dto = ProdutoDTO.builder().nome("Xiaomi").preco(2000).descricao("Celular").build();
		return dto;
	}
}
