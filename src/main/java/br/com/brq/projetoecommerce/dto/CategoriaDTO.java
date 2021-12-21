package br.com.brq.projetoecommerce.dto;

import org.modelmapper.ModelMapper;

import br.com.brq.projetoecommerce.domain.CategoriaEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoriaDTO {

	private Integer idCategoria;
	private String nome;

	public CategoriaEntity toEntity() {
		var mapper = new ModelMapper();
		return mapper.map(this, CategoriaEntity.class);

	}
}
