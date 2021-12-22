package br.com.brq.projetoecommerce.services;

import java.util.List;
import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.brq.projetoecommerce.domain.CategoriaEntity;
import br.com.brq.projetoecommerce.repositories.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;

	public List<CategoriaEntity> listaTodasCategorias() {
		return categoriaRepository.findAll();
	}

	public CategoriaEntity salvar(CategoriaEntity categoria) {
		return categoriaRepository.save(categoria);
	}

	public CategoriaEntity buscarCategoriaId(Integer id) {
		Optional<CategoriaEntity> categoria = categoriaRepository.findById(id);
		return categoria
				.orElseThrow(() -> new ObjectNotFoundException(new CategoriaEntity(), "categoria não encontrada"));
	}

	public CategoriaEntity alterar(Integer id, CategoriaEntity categoriaAlterada) throws ObjectNotFoundException {
		CategoriaEntity categoriaEntity = buscarCategoriaId(id);
		categoriaEntity.setNomeCategoria(categoriaAlterada.getNomeCategoria());
		return salvar(categoriaEntity);
	}

	public void deletar(Integer id) {
		this.categoriaRepository.deleteById(id);
	}
}
