package br.com.brq.projetoecommerce.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.brq.projetoecommerce.domain.VendaEntity;
import br.com.brq.projetoecommerce.exceptions.ObjetoNaoEncontradoException;
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
		return this.vendaRepository.findById(id)
				.orElseThrow( () -> new ObjetoNaoEncontradoException("Venda não encontrada nova versão") );
	}

	public VendaEntity alterar(Integer id, VendaEntity vendaAlterada) {
		VendaEntity vendaEntity = buscarVendaId(id);		
		vendaEntity.setDataVenda(vendaAlterada.getDataVenda());
		vendaEntity.setItens(vendaAlterada.getItens());
		vendaEntity.setUsuario(vendaAlterada.getUsuario());
		return salvar(vendaEntity);
	}

	public void deletar(Integer id) {
		this.vendaRepository.deleteById(id);
	}
}