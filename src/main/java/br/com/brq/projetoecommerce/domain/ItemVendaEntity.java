package br.com.brq.projetoecommerce.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.modelmapper.ModelMapper;

import br.com.brq.projetoecommerce.dto.ItemVendaDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "item_venda")
public class ItemVendaEntity implements Serializable{
	 
	private static final long serialVersionUID = 6755240900315097949L;
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Integer idItemVenda;
		
	@OneToMany//(cascade = CascadeType.ALL)
	private List <ProdutoEntity> itemProduto;
	
	private Integer itemQuantidade;
	
	public ItemVendaDTO toDTO () {
		var mapper = new ModelMapper();
		return mapper.map(this, ItemVendaDTO.class);
	}
}
