package br.com.brq.projetoecommerce.services;



import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.brq.projetoecommerce.domain.EnderecoEntity;
import br.com.brq.projetoecommerce.domain.UsuarioEntity;
import br.com.brq.projetoecommerce.dto.EnderecoDTO;
import br.com.brq.projetoecommerce.dto.UsuarioDTO;
import br.com.brq.projetoecommerce.repositories.UsuarioRepository;



@Service
public class UsuarioService {

@Autowired
private UsuarioRepository usuarioRepository;

@Autowired
private EnderecoService enderecoService;

public UsuarioEntity salvar(UsuarioEntity usuario) {

enderecoService.salvar(usuario.getEnderecos().get(0));

return usuarioRepository.save(usuario);
}

public List<UsuarioEntity> listaTodasCategorias() {
return usuarioRepository.findAll();
}



public UsuarioEntity buscarUsuarioId(Integer id) {
Optional<UsuarioEntity> usuario = usuarioRepository.findById(id);
return usuario.orElseThrow(()-> new ObjectNotFoundException(new UsuarioEntity(), "Usuario não encontrado"));
}

public UsuarioEntity alterar(int usuarioId, UsuarioDTO alteracao) {
Optional<UsuarioEntity> usuario = usuarioRepository.findById(usuarioId);

if (usuario.isPresent()) {
UsuarioEntity usuarioExistente = usuario.get();

usuarioExistente.setNome(alteracao.getNome());
usuarioExistente.setEmail(alteracao.getEmail());
usuarioExistente.setCelular(alteracao.getCelular());
usuarioExistente.setTelefone(alteracao.getTelefone());

//Validando se existem enderecos para serem alterados
if (!alteracao.getEnderecos().isEmpty()) {
int enderecoId = alteracao.getEnderecos().get(0).getEnderecoId();
List<EnderecoEntity> enderecos = alteracao.getEnderecos().stream().map(EnderecoDTO::toEntity)
.collect(Collectors.toList());

this.enderecoService.alterar(enderecoId, enderecos.get(0));
}

return this.usuarioRepository.save(usuarioExistente);
} else {
throw new RuntimeException("Usuario(a) nao encontrado(a)");
}
}

public void excluir(Integer id) {
usuarioRepository.deleteById(id);
}

}