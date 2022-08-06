package br.com.letscode.simplecrud;

import br.com.letscode.simplecrud.models.Cliente;
import br.com.letscode.simplecrud.repositories.ClienteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class SimpleCrudApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimpleCrudApplication.class, args);
	}

}

@Component
class DemoCommandLineRunner implements CommandLineRunner{

	@Autowired
	private ClienteRepository clienteRepository;

	/**
	 * Inserir alguns clientes automaticamente.
	 */
	@Override
	public void run(String... args) throws Exception {

		Cliente pedro = new Cliente();
		Cliente marcos = new Cliente();
		Cliente andre = new Cliente();

		pedro = Cliente.builder()
					.id(1L)
					.nome("Pedro")
					.email("Pedro@gmail.com")
					.vatNumber("XX99999999999")
					.age(30)
					.build();

		marcos = Cliente.builder()
					.id(2L)
					.nome("Marcos")
					.email("Marcos@gmail.com")
					.vatNumber("XX99999999998")
					.age(25)
					.build();

		andre = Cliente.builder()
					.id(3L)
					.nome("Andre")
					.email("Andre@gmail.com")
					.vatNumber("XX99999999997")
					.age(20)
					.build();

		clienteRepository.save(pedro);
		clienteRepository.save(marcos);
		clienteRepository.save(andre);		
	}
}