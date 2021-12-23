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

import br.com.brq.projetoecommerce.domain.CategoriaEntity;
import br.com.brq.projetoecommerce.repositories.CategoriaRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CategoriaServiceTest {

	@Autowired
	private CategoriaService categoriaService;

	@MockBean
	private CategoriaRepository categoriaRepository;

	@Test
	void listaTodasCategoriasTest() {
		
		List<CategoriaEntity> listMock = new ArrayList<>();

		CategoriaEntity categoria = this.createValidCategoria();
		listMock.add(categoria);

		when(categoriaRepository.findAll()).thenReturn(listMock);
		
		List<CategoriaEntity> list = this.categoriaService.listaTodasCategorias();

		assertThat(list.size() >= 0).isTrue();
		verify(categoriaRepository, times(1)).findAll();
	}

	@Test
	void savarTest() {

		int id = 1;

		CategoriaEntity categoria = createValidCategoria();

		when(categoriaRepository.save(categoria)).thenReturn(categoria);

		CategoriaEntity resultCategoria = categoriaRepository.save(categoria);
		resultCategoria.setIdCategoria(id);

		assertThat(categoria.getNomeCategoria()).isEqualTo(resultCategoria.getNomeCategoria());
		assertThat(resultCategoria.getIdCategoria() >= 0).isTrue();
		verify(categoriaRepository, times(id)).save(categoria);

	}

	@Test
	void buscarCategoriaIdSucessoTest() {

		int id = 1;
		CategoriaEntity categoria = this.createValidCategoria();
		Optional<CategoriaEntity> optional = Optional.of(categoria);

		when(categoriaRepository.findById(id)).thenReturn(optional);

		CategoriaEntity search = this.categoriaService.buscarCategoriaId(id);

		assertThat(search.getIdCategoria()).isEqualTo(categoria.getIdCategoria());
	}

	@Test
	void buscarCategoriaIdFalhaTest() {

		int id = 1;

		Optional<CategoriaEntity> optional = Optional.empty();
		when(categoriaRepository.findById(id)).thenReturn(optional);

		assertThrows(ObjectNotFoundException.class, () -> this.categoriaService.buscarCategoriaId(id));
	}

	@Test
	void alterarSucessoTest() {

		int idCategoria = 1;
		CategoriaEntity categoria = this.createValidCategoria();
		categoria.setIdCategoria(idCategoria);

		when(categoriaRepository.findById(idCategoria)).thenReturn(Optional.of(categoria));
		when(categoriaRepository.save(categoria)).thenReturn(categoria);

		CategoriaEntity updated = this.categoriaService.alterar(idCategoria, categoria);

		assertThat(updated.getIdCategoria()).isEqualTo(categoria.getIdCategoria());
		assertThat(updated.getNomeCategoria()).isEqualTo(categoria.getNomeCategoria());
		verify(categoriaRepository, times(1)).save(categoria);
	}

	@Test
	void alterarFalhaTest() {

		int idCategoria = 1;
		CategoriaEntity categoria = this.createValidCategoria();
		categoria.setIdCategoria(idCategoria);

		when(categoriaRepository.findById(idCategoria)).thenReturn(Optional.empty());

		assertThrows(ObjectNotFoundException.class, () -> this.categoriaService.alterar(idCategoria, categoria));
	}

	@Test
	void deletarTest() {

		int idCategoria = 1;

		assertDoesNotThrow(() -> categoriaService.deletar(idCategoria));
		verify(categoriaRepository, times(1)).deleteById(idCategoria);
	}

	private CategoriaEntity createValidCategoria() {
		return CategoriaEntity.builder().nomeCategoria("Eletronico").build();
	}

}