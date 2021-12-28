package br.com.brq.projetoecommerce.dto;

import javax.validation.constraints.NotNull;

import org.modelmapper.ModelMapper;

import br.com.brq.projetoecommerce.domain.EnderecoEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EnderecoDTO {

	private Integer enderecoId;
	
	@NotNull(message = "Campo obrigatório")
	private String logradouro;
	private String numero;
	private String complemento;

	@NotNull(message = "Campo obrigatório")
	private String cep;
	private String bairro;
	private String cidade;
	private String estado;

	public EnderecoEntity toEntity() {
		var mapper = new ModelMapper();
		return mapper.map(this, EnderecoEntity.class);
	}
}