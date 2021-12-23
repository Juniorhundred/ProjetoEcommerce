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

import br.com.brq.projetoecommerce.domain.ItemVendaEntity;
import br.com.brq.projetoecommerce.repositories.ItemVendaRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ItemVendaServiceTest {
	
	@Autowired
	private ItemVendaService itemVendaService;

	@MockBean
	private ItemVendaRepository itemVendaRepository;

	@Test
	void listaTodosItemVendaTest() {
		
		List<ItemVendaEntity> listMock = new ArrayList<>();

		ItemVendaEntity itemvenda = this.createValidItemVenda();
		listMock.add(itemvenda);

		when(itemVendaRepository.findAll()).thenReturn(listMock);
		
		List<ItemVendaEntity> list = this.itemVendaService.listaTodosItemsVenda();

		assertThat(list.size() >= 0).isTrue();
		verify(itemVendaRepository, times(1)).findAll();
	}

	@Test
	void savarTest() {

		int id = 1;

		ItemVendaEntity itemVenda = createValidItemVenda();

		when(itemVendaRepository.save(itemVenda)).thenReturn(itemVenda);

		ItemVendaEntity resultItemVenda = itemVendaRepository.save(itemVenda);
		resultItemVenda.setIdItemVenda(id);

		
		
		assertThat(resultItemVenda.getIdItemVenda() >= 0).isTrue();
		assertThat(resultItemVenda.getItemQuantidade() >= 0).isTrue();
		verify(itemVendaRepository, times(id)).save(itemVenda);

	}

	@Test
	void buscarItemVendaIdSucessoTest() {

		int id = 1;
		ItemVendaEntity itemVenda = this.createValidItemVenda();
		Optional<ItemVendaEntity> optional = Optional.of(itemVenda);

		when(itemVendaRepository.findById(id)).thenReturn(optional);

		ItemVendaEntity search = this.itemVendaService.buscarItemVendaId(id);

		assertThat(search.getIdItemVenda()).isEqualTo(itemVenda.getIdItemVenda());
	}

	@Test
	void buscarItemVendaIdFalhaTest() {

		int id = 1;

		Optional<ItemVendaEntity> optional = Optional.empty();
		when(itemVendaRepository.findById(id)).thenReturn(optional);

		assertThrows(ObjectNotFoundException.class, () -> this.itemVendaService.buscarItemVendaId(id));
	}

	@Test
	void alterarSucessoTest() {

		int idItemVenda = 1;
		ItemVendaEntity itemVenda = this.createValidItemVenda();
		itemVenda.setIdItemVenda(idItemVenda);

		when(itemVendaRepository.findById(idItemVenda)).thenReturn(Optional.of(itemVenda));
		when(itemVendaRepository.save(itemVenda)).thenReturn(itemVenda);

		ItemVendaEntity updated = this.itemVendaService.alterar(idItemVenda, itemVenda);

		assertThat(updated.getIdItemVenda()).isEqualTo(itemVenda.getIdItemVenda());
		verify(itemVendaRepository, times(1)).save(itemVenda);
	}

	@Test
	void alterarFalhaTest() {

		int idItemVenda = 1;
		ItemVendaEntity itemVenda = this.createValidItemVenda();
		itemVenda.setIdItemVenda(idItemVenda);

		when(itemVendaRepository.findById(idItemVenda)).thenReturn(Optional.empty());

		assertThrows(ObjectNotFoundException.class, () -> this.itemVendaService.alterar(idItemVenda, itemVenda));
	}

	@Test
	void deletarTest() {

		int idItemVenda = 1;

		assertDoesNotThrow(() -> itemVendaService.deletar(idItemVenda));
		verify(itemVendaRepository, times(1)).deleteById(idItemVenda);
	}

	private ItemVendaEntity createValidItemVenda() {
		return ItemVendaEntity.builder().idItemVenda(1).itemQuantidade(1).build();
	}

}


