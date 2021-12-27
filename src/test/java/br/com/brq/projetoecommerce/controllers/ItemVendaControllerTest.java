package br.com.brq.projetoecommerce.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import java.util.ArrayList;
import java.util.List;

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

import br.com.brq.projetoecommerce.domain.ItemVendaEntity;
import br.com.brq.projetoecommerce.domain.ProdutoEntity;
import br.com.brq.projetoecommerce.dto.CategoriaDTO;
import br.com.brq.projetoecommerce.dto.ImagemDTO;
import br.com.brq.projetoecommerce.dto.ItemVendaDTO;
import br.com.brq.projetoecommerce.dto.ProdutoDTO;
import br.com.brq.projetoecommerce.services.CategoriaService;
import br.com.brq.projetoecommerce.services.ImagemService;
import br.com.brq.projetoecommerce.services.ItemVendaService;
import br.com.brq.projetoecommerce.services.ProdutoService;
import br.com.brq.projetoecommerce.utils.MockUtil;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ItemVendaControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ItemVendaService itemVendaService;

	@Autowired
	private ProdutoService produtoService;

	@Autowired
	private CategoriaService categoriaService;

	@Autowired
	private ImagemService imagemService;

	private MockUtil mockUtil = new MockUtil();

	private ObjectMapper objectMapper = new ObjectMapper();

	@Test
	void buscarIdTest() throws Exception {

		ItemVendaDTO dto = itemMock();
		
		ItemVendaEntity prod = itemVendaService.salvar(dto.toEntity());

		ResultActions response = mockMvc.perform(
				get("/itemVenda/" + prod.getIdItemVenda()).content(objectMapper.writeValueAsString(dto)).contentType("application/json"));

		MvcResult result = response.andReturn();

		String resultStr = result.getResponse().getContentAsString();

		ItemVendaDTO itemVendaDTO = objectMapper.readValue(resultStr, ItemVendaDTO.class);

		assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
		assertThat(itemVendaDTO.getIdItemVenda()).isEqualTo(prod.getIdItemVenda());

	}

	@Test
	void buscarTodasVendasTest() throws Exception {

		ResultActions response = mockMvc.perform(get("/itemVenda").contentType("application/json"));
		MvcResult result = response.andReturn();

		assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
	}

	@Test
	void cadastrarTest() throws JsonProcessingException, Exception {
		ItemVendaDTO dto = mockUtil.itemMock(null);

		ResultActions response = mockMvc.perform(
				post("/itemVenda").content(objectMapper.writeValueAsString(dto)).contentType("application/json"));

		MvcResult result = response.andReturn();

		String objStr = result.getResponse().getContentAsString();

		ItemVendaDTO dtoResult = objectMapper.readValue(objStr, ItemVendaDTO.class);

		assertThat(dtoResult.getIdItemVenda() > 0).isTrue();
		assertThat(dtoResult.getItemQuantidade() >= 0).isTrue();

	}

	@Test
	void alterarTest() throws Exception {
		ItemVendaDTO dto = this.itemMock();

		int id = 1;
		dto.setIdItemVenda(id);

		ResultActions response = mockMvc.perform(
				put("/itemVenda/" + id).content(objectMapper.writeValueAsString(dto)).contentType("application/json"));

		MvcResult result = response.andReturn();

		String resultStr = result.getResponse().getContentAsString();

		ItemVendaDTO updated = objectMapper.readValue(resultStr, ItemVendaDTO.class);

		assertThat(updated.getIdItemVenda()).isEqualTo(id);
		assertThat(updated.getItemQuantidade() >= 0).isTrue();
		assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());

	}

	@Test
	void deleteTest() throws Exception {
		ItemVendaDTO dto = this.itemMock();

		int id = 1;	
		
		ResultActions response = mockMvc.perform(delete("/itemVenda/" + id).contentType("application/json"));
		
//		ResultActions response = mockMvc.perform(
//				delete("/itemVenda/" + id).content(objectMapper.writeValueAsString(dto)).contentType("application/json"));


		MvcResult result = response.andReturn();

		assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
	}
	
	// novo metodo
	private ItemVendaDTO itemMock() {
		int id = 1;

		CategoriaDTO cat = mockUtil.categoriaMock();		
		cat.setIdCategoria(id);
		List<CategoriaDTO> listCat = new ArrayList<>();
		listCat.add(cat);

		ImagemDTO ima = mockUtil.imagemMock();		
		ima.setIdImagem(id);
		List<ImagemDTO> listIma = new ArrayList<>();
		listIma.add(ima);

		ProdutoDTO prod = mockUtil.produtoMock(null, null);		
		prod.setIdProduto(id);
		List<ProdutoDTO> listProduto = new ArrayList<>();
		listProduto.add(prod);

		ItemVendaDTO dto = mockUtil.itemMock(null);	

		return dto;
	}

	private ItemVendaDTO createValidFindId() {
		int id = 1;

		CategoriaDTO cat = mockUtil.categoriaMock();
		categoriaService.salvar(cat.toEntity());
		cat.setIdCategoria(id);
		List<CategoriaDTO> listCat = new ArrayList<>();
		listCat.add(cat);

		ImagemDTO ima = mockUtil.imagemMock();
		imagemService.salvar(ima.toEntity());
		ima.setIdImagem(id);
		List<ImagemDTO> listIma = new ArrayList<>();
		listIma.add(ima);

		ProdutoDTO prod = mockUtil.produtoMock(listCat, listIma);
		produtoService.salvar(prod.toEntity());
		prod.setIdProduto(id);
		List<ProdutoDTO> listProduto = new ArrayList<>();
		listProduto.add(prod);

		ItemVendaDTO dto = mockUtil.itemMock(listProduto);
		itemVendaService.salvar(dto.toEntity());

		return dto;
	}

}
