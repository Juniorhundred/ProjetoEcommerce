package br.com.brq.projetoecommerce.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

<<<<<<< Updated upstream
=======
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

>>>>>>> Stashed changes
import org.modelmapper.ModelMapper;

import br.com.brq.projetoecommerce.domain.UsuarioEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioDTO {
	private Integer usuarioId;
	
	@NotNull(message = "Campo obrigatório")
	private String nome;

<<<<<<< Updated upstream
	private Integer usuarioId;
	private String nome;
=======
	@NotNull(message = "Campo obrigatório")
>>>>>>> Stashed changes
	private String cpf;
	private LocalDate dataDeNascimento;
	private String celular;
	private String telefone;
	private String email;

	private List<EnderecoDTO> enderecos = new ArrayList<>();

	public UsuarioEntity toEntity() {
		var mapper = new ModelMapper();
		return mapper.map(this, UsuarioEntity.class);

	}

}