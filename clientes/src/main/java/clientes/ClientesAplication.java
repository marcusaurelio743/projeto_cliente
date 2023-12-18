package clientes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import clientes.model.Cliente;
import clientes.repository.ClienteRepository;

@SpringBootApplication
public class ClientesAplication {
	@Bean
	public CommandLineRunner run (@Autowired ClienteRepository repository) {
		return args ->{
			Cliente cliente = Cliente.builder().nome("Fulano de Tal").cpf("2345678901").build();
			repository.save(cliente);
			
		};
	}
	
	public static void main(String[] args) { 
		SpringApplication.run(ClientesAplication.class, args);
	}

}
