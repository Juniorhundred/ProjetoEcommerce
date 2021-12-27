package br.com.brq.projetoecommerce.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.brq.projetoecommerce.domain.ProdutoEntity;
import br.com.brq.projetoecommerce.repositories.ProdutoRepository;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ProdutoServiceTest {
	
	@Autowired
	private ProdutoService produtoService;

	@MockBean
	private ProdutoRepository produtoRepository;

	@Test	
	void listaTodosProdutosTest() {

		List<ProdutoEntity> listMock = new ArrayList<>();

		ProdutoEntity produto = this.createValidProduto();
		listMock.add(produto);

		when(produtoRepository.findAll()).thenReturn(listMock);
		
		List<ProdutoEntity> list = this.produtoService.listaTodosProdutos();
		
		assertThat(list.size() >= 0).isTrue();
		verify(produtoRepository, times(1)).findAll();
	}
	
	@Test
	void savarTest() {

		int id = 1;

		ProdutoEntity produto = createValidProduto();

		when(produtoRepository.save(produto)).thenReturn(produto);

		ProdutoEntity resultProduto = produtoRepository.save(produto);
		resultProduto.setIdProduto(id);
		
		assertThat(produto.getNome()).isEqualTo(resultProduto.getNome());
		assertThat(produto.getPreco()).isEqualTo(resultProduto.getPreco());
		assertThat(resultProduto.getIdProduto() >= 0).isTrue();
		verify(produtoRepository, times(id)).save(produto);

	}

	@Test
	void buscarProdutoIdSucessoTest() {
		
		int id = 1;
		ProdutoEntity produto = this.createValidProduto();
		Optional<ProdutoEntity> optional = Optional.of(produto);

		when(produtoRepository.findById(id)).thenReturn(optional);

		ProdutoEntity search = this.produtoService.buscarProdutoId(id);

		assertThat(search.getNome()).isEqualTo(produto.getNome());
		assertThat(search.getPreco()).isEqualTo(produto.getPreco());
		assertThat(search.getDescricao()).isEqualTo(produto.getDescricao());		
	}

	@Test
	void buscarProdutoIdFalhaTest() {
		
		int id = 1;

		Optional<ProdutoEntity> optional = Optional.empty();
		when(produtoRepository.findById(id)).thenReturn(optional);

		assertThrows(ObjectNotFoundException.class, () -> this.produtoService.buscarProdutoId(id));
	}	

	@Test
	void alterarSucessoTest() {
		
		int idProduto = 1;
		ProdutoEntity produto = this.createValidProduto();
		produto.setIdProduto(idProduto);

		when(produtoRepository.findById(idProduto)).thenReturn(Optional.of(produto));
		when(produtoRepository.save(produto)).thenReturn(produto);
		
		ProdutoEntity updated = this.produtoService.alterar(idProduto, produto);
		
		assertThat(updated.getIdProduto()).isEqualTo(produto.getIdProduto());
		assertThat(updated.getNome()).isEqualTo(produto.getNome());
		assertThat(updated.getPreco()).isEqualTo(produto.getPreco());
		assertThat(updated.getDescricao()).isEqualTo(produto.getDescricao());
		verify(produtoRepository, times(1)).save(produto);
	}

	@Test
	void alterarFalhaTest() {
		
		int idProduto = 1;
		ProdutoEntity produto = this.createValidProduto();
		produto.setIdProduto(idProduto);

		when(produtoRepository.findById(idProduto)).thenReturn(Optional.empty());

		assertThrows(ObjectNotFoundException.class, () -> this.produtoService.alterar(idProduto, produto));
	}

	@Test
	void deletarTest() {
		
		int idProduto = 1;

		assertDoesNotThrow(() -> produtoService.deletar(idProduto));
		verify(produtoRepository, times(1)).deleteById(idProduto);
	}	


	private ProdutoEntity createValidProduto() {
		return ProdutoEntity.builder().nome("Xiaomi").preco(2000).descricao("Celular Chines").build();
	}

}
