package br.com.brq.projetoecommerce.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.brq.projetoecommerce.domain.EnderecoEntity;
import br.com.brq.projetoecommerce.domain.UsuarioEntity;
import br.com.brq.projetoecommerce.dto.EnderecoDTO;
import br.com.brq.projetoecommerce.dto.UsuarioDTO;
import br.com.brq.projetoecommerce.exceptions.UsuarioNaoEncontradoException;
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

<<<<<<< Updated upstream
	public List<UsuarioEntity> listaTodasUsuarios() {
=======
	public List<UsuarioEntity> listaTodosUsuarios() {
>>>>>>> Stashed changes
		return usuarioRepository.findAll();
	}

	public UsuarioEntity buscarUsuarioId(Integer id) {
		Optional<UsuarioEntity> usuario = usuarioRepository.findById(id);
<<<<<<< Updated upstream
		return usuario.orElseThrow(() -> new ObjectNotFoundException(new UsuarioEntity(), "Usuario não encontrado"));
=======
		return usuario.orElseThrow(() -> new UsuarioNaoEncontradoException("Usuario não encontrado"));
>>>>>>> Stashed changes
	}

	public UsuarioEntity alterar(int usuarioId, UsuarioDTO alteracao) {
		Optional<UsuarioEntity> usuario = usuarioRepository.findById(usuarioId);

		if (usuario.isPresent()) {
			UsuarioEntity usuarioExistente = usuario.get();

			usuarioExistente.setNome(alteracao.getNome());
<<<<<<< Updated upstream
			usuarioExistente.setEmail(alteracao.getEmail());
			usuarioExistente.setCelular(alteracao.getCelular());
			usuarioExistente.setTelefone(alteracao.getTelefone());

//Validando se existem enderecos para serem alterado
			if (!alteracao.getEnderecos().isEmpty()) {
=======
			usuarioExistente.setCpf(alteracao.getCpf());
			usuarioExistente.setEmail(alteracao.getEmail()); 
			usuarioExistente.setCelular(alteracao.getCelular());
			usuarioExistente.setTelefone(alteracao.getTelefone());

		//Validando se existem enderecos para serem alterados
		if (!alteracao.getEnderecos().isEmpty()) {
>>>>>>> Stashed changes
				int enderecoId = alteracao.getEnderecos().get(0).getEnderecoId();
				List<EnderecoEntity> enderecos = alteracao.getEnderecos().stream().map(EnderecoDTO::toEntity)
						.collect(Collectors.toList());

				this.enderecoService.alterar(enderecoId, enderecos.get(0));
			}

			return this.usuarioRepository.save(usuarioExistente);
		} else {
<<<<<<< Updated upstream
			throw new RuntimeException("Usuario(a) nao encontrado(a)");
=======
			throw new UsuarioNaoEncontradoException("Usuario(a) nao encontrado(a)");
>>>>>>> Stashed changes
		}
	}

	public void excluir(Integer id) {
		usuarioRepository.deleteById(id);
	}

}