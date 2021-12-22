package br.com.brq.projetoecommerce.dto;

import java.util.List;

import org.modelmapper.ModelMapper;

import br.com.brq.projetoecommerce.domain.CategoriaEntity;
import br.com.brq.projetoecommerce.domain.ImagemEntity;
import br.com.brq.projetoecommerce.domain.ProdutoEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoDTO {
	
	private Integer idProduto; 
	private String nome; 
	private double preco; 
	private String descricao;
	
	private List<CategoriaEntity> categorias;
	
	private List <ImagemEntity> imagens;
	
	public ProdutoEntity toEntity() {
		var mapper = new ModelMapper();
		return mapper.map(this, ProdutoEntity.class);

	}
}