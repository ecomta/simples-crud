package br.com.letscode.simplecrud.services;

import br.com.letscode.simplecrud.models.Cliente;
import br.com.letscode.simplecrud.models.ClienteDTO;
import br.com.letscode.simplecrud.repositories.ClienteRepository;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Optional<Cliente> getById(Long id) {
        return this.clienteRepository.findById(id);
    }

    public List<Cliente> getAllClientes() {
        return this.clienteRepository.findAll();
    }

    public Cliente save(@Valid Cliente cliente) {
        return this.clienteRepository.save(cliente);
    }

    public String update(@Valid Cliente cliente) {

        if (this.clienteRepository.findById(cliente.getId()).isPresent()) {
            this.clienteRepository.save(cliente);
            return "Cliente atualizado com sucesso";
        }

        return "Não existe esse cliente";
    }

    Cliente save(@Valid ClienteDTO clienteDTO) {
        Cliente cliente = new Cliente();
        return null;
    }

    public String delete(Long id) {        
        if (this.clienteRepository.findById(id).isPresent()) {
             this.clienteRepository.deleteById(id);
             return "Cliente deletado com sucesso";
        }

        return "Cliente não existe";
    }

}