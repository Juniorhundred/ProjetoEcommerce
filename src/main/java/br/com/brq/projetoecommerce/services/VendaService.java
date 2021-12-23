package br.com.brq.projetoecommerce.services;

import java.util.List;
import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.brq.projetoecommerce.domain.VendaEntity;
import br.com.brq.projetoecommerce.repositories.VendaRepository;

@Service
public class VendaService {
	
	@Autowired
	private VendaRepository vendaRepository;

	public List<VendaEntity> listaTodasVendas() {
		return vendaRepository.findAll();
	}

	public VendaEntity salvar(VendaEntity venda) {
		return vendaRepository.save(venda);
	}

	public VendaEntity buscarVendaId(Integer id) {
		Optional<VendaEntity> venda = vendaRepository.findById(id);
		return venda.orElseThrow(() -> new ObjectNotFoundException(new VendaEntity(), "Venda não encontrada"));
	}

	public VendaEntity alterar(Integer id, VendaEntity vendaAlterada) throws ObjectNotFoundException {
		VendaEntity vendaEntity = buscarVendaId(id);
		vendaEntity.setIdVenda(vendaAlterada.getIdVenda());
		vendaEntity.setDataVenda(vendaAlterada.getDataVenda());
		vendaEntity.setItemVenda(vendaAlterada.getItemVenda());
		vendaEntity.setUsuario(vendaAlterada.getUsuario());
		return salvar(vendaEntity);
	}

	public void deletar(Integer id) {
		this.vendaRepository.deleteById(id);
	}
}