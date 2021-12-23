package br.com.brq.projetoecommerce.dto;

import java.util.List;

import org.modelmapper.ModelMapper;

import br.com.brq.projetoecommerce.domain.ItemVendaEntity;
import br.com.brq.projetoecommerce.domain.UsuarioEntity;
import br.com.brq.projetoecommerce.domain.VendaEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VendaDTO {

	private Integer idVenda;
	private String dataVenda;

	private List<ItemVendaEntity> itemVenda;

	private UsuarioEntity usuario;

	public VendaEntity toEntity() {
		var mapper = new ModelMapper();
		return mapper.map(this, VendaEntity.class);
	}

}
