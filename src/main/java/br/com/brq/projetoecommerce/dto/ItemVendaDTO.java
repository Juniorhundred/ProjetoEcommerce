package br.com.brq.projetoecommerce.dto;

import java.util.List;

import org.modelmapper.ModelMapper;

import br.com.brq.projetoecommerce.domain.ItemVendaEntity;
import br.com.brq.projetoecommerce.domain.ProdutoEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ItemVendaDTO {

	private Integer idItemVenda; 
	private Integer itemQuantidade;
	private List <ProdutoEntity> itemProduto;
	
	public ItemVendaEntity toEntity() {
		var mapper = new ModelMapper();
		return mapper.map(this, ItemVendaEntity.class);
	}
	
}
