package br.com.brq.projetoecommerce.dto;

import org.modelmapper.ModelMapper;

import br.com.brq.projetoecommerce.domain.ImagemEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ImagemDTO {

	private Integer idImagem;
	private String imagemProduto;
	
	public ImagemEntity toEntity() {
		var mapper = new ModelMapper();
		return mapper.map(this, ImagemEntity.class);

	}
}

