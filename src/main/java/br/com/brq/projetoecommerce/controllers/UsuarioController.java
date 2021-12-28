package br.com.brq.projetoecommerce.controllers;

import java.util.List;
import java.util.stream.Collectors;

<<<<<<< Updated upstream
=======
import javax.validation.Valid;

>>>>>>> Stashed changes
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.brq.projetoecommerce.domain.UsuarioEntity;
import br.com.brq.projetoecommerce.dto.UsuarioDTO;
import br.com.brq.projetoecommerce.services.UsuarioService;

@RestController
@RequestMapping(path = "usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;

	@PostMapping
<<<<<<< Updated upstream
	public ResponseEntity<UsuarioDTO> cadastrar(@RequestBody UsuarioDTO usuario) {
=======
	public ResponseEntity<UsuarioDTO> cadastrar(@Valid @RequestBody UsuarioDTO usuario) {
>>>>>>> Stashed changes
		UsuarioEntity entity = usuarioService.salvar(usuario.toEntity());
		UsuarioDTO dto = entity.toDTO();
		return ResponseEntity.ok().body(dto);

	}

	@GetMapping
	public ResponseEntity<List<UsuarioDTO>> listaTodosUsuarios() {
<<<<<<< Updated upstream
		List<UsuarioEntity> result = usuarioService.listaTodasUsuarios();
=======
		List<UsuarioEntity> result = usuarioService.listaTodosUsuarios();
>>>>>>> Stashed changes
		return ResponseEntity.ok().body(result.stream().map(UsuarioEntity::toDTO).collect(Collectors.toList()));
	}

	@GetMapping(value = "buscar/{id}")
	public ResponseEntity<UsuarioDTO> buscarUsuarioId(@PathVariable int id) {
		UsuarioEntity entity = usuarioService.buscarUsuarioId(id);
		UsuarioDTO dto = entity.toDTO();
		return ResponseEntity.ok().body(dto);
	}

	@PutMapping(value = "/{usuarioId}")
<<<<<<< Updated upstream
	public ResponseEntity<UsuarioDTO> alterar(@PathVariable int usuarioId, @RequestBody UsuarioDTO usuarioAlterado) {
=======
	public ResponseEntity<UsuarioDTO> alterar(@PathVariable int usuarioId, @Valid @RequestBody UsuarioDTO usuarioAlterado) {
>>>>>>> Stashed changes
		UsuarioEntity entity = usuarioService.alterar(usuarioId, usuarioAlterado);
		return ResponseEntity.ok().body(entity.toDTO());
	}

	@DeleteMapping("/{idUsuario}")
	public void delete(@PathVariable int idUsuario) {
		usuarioService.excluir(idUsuario);
	}
}