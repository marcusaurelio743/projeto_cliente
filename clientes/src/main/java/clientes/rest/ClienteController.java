package clientes.rest;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import clientes.model.Cliente;
import clientes.repository.ClienteRepository;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {
	
	
	
	private  final ClienteRepository repository;
	
	@Autowired
	public ClienteController(ClienteRepository repository) {
		this.repository = repository;
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente salvar( @RequestBody @Valid Cliente cliente) {
		return repository.save(cliente);
	}
	
	@GetMapping("{id}")
	
	public ResponseEntity<?> getById( @PathVariable Long id) {
		
		Optional<Cliente> clienteExistente = repository.findById(id);
		
		Cliente cliente = new Cliente();
		
		if(clienteExistente.isEmpty()) {
			return new ResponseEntity<String>("Cliente Não encontrado", HttpStatus.NOT_FOUND);
		}
		else {
			cliente = clienteExistente.get();
		}
		
		return ResponseEntity.ok(cliente);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<?> deletar(@PathVariable Long id) {
		Optional<Cliente> clienteExistente = repository.findById(id);
		
		if(clienteExistente.isEmpty()) {
			return  new ResponseEntity<String>("Usuario não encontrado", HttpStatus.NOT_FOUND);
		}else {
			repository.deleteById(id);
			return ResponseEntity.noContent().build();
		}
		
	}
	
	@PutMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody @Valid Cliente clienteAtualizado){
		
		Optional<Cliente> clienteExistente = repository.findById(id);
		
		if(clienteExistente.isEmpty()) {
			return new ResponseEntity<String>("cliente Não encontrado", HttpStatus.NOT_FOUND);
		}else {
			clienteExistente.get().setNome(clienteAtualizado.getNome());
			clienteExistente.get().setCpf(clienteAtualizado.getCpf());
			
			repository.save(clienteExistente.get());
			
			return ResponseEntity.noContent().build();
		}
		
	}
	

}
