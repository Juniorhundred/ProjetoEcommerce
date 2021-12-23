package br.com.brq.projetoecommerce.dto;



import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;

import br.com.brq.projetoecommerce.domain.UsuarioEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioDTO {

private Integer usuarioId;
private String nome;
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