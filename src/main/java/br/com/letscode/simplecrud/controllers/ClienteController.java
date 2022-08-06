package br.com.letscode.simplecrud.controllers;

import br.com.letscode.simplecrud.models.Cliente;
import br.com.letscode.simplecrud.models.ClienteDTO;
import br.com.letscode.simplecrud.services.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

/**
 * Acesse por localhost:8080/swagger-ui.html
 */
@RestController
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }


    /**
     * Retornar um cliente sem mapear a informação.
     */
    @GetMapping("/client/{id}")
        @Tag(name = "Get")
        @Operation(
            summary = "Buscar um cliente",
            description = "Informe um id para retorna um cliente"
        )
        @ApiResponse(
            responseCode = "201",
            description = "Client",
            content = {
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Cliente.class)
                )
            }
        )
    public Cliente buscarCliente(@PathVariable("id") Long id){
        return this.clienteService.getById(id).orElseThrow();
    }

    /**
     * Usando DTO para modificar o retorno.
     * FindById: retorna um Optional.
     * Com optional eu posso fazer map.
     */
    @GetMapping("/clientDTO/{id}")
        @Tag(name = "Get")
        @Operation(
            summary = "Buscar um clienteDTO",
            description = "Informe um id para retornar um cliente DTO"
        )
        @ApiResponse(
            responseCode = "201",
            description = "ClientDTO",
            content = {
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ClienteDTO.class)
                )
            }
        )
    public ClienteDTO buscarClienteDTO(@PathVariable("id") Long id) {
        return this.clienteService
                .getById(id)
                .map(cliente -> new ClienteDTO(cliente.getNome(), cliente.getEmail()))
                .orElseThrow();
    }


    @PostMapping("/client")
        @Tag(name = "Crud")
        @Operation(
            summary = "Criar um cliente.",
            description = "Informe os dados necessários para criar um cliente."
        )
        @ApiResponse(
            responseCode = "201",
            description = "Cliente criado com sucesso.",
            content = {
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Cliente.class)
                )
            }
        )
    String novoCliente(@Valid @RequestBody Cliente novoCliente, BindingResult bindingResult) {
        System.out.println(novoCliente);
        if (bindingResult.hasErrors()) {
            return bindingResult.getAllErrors().get(bindingResult.getAllErrors().size() - 1).getDefaultMessage();
        }

        this.clienteService.save(novoCliente);
        return "Cliente cadastrado com sucesso";
    }


    @PutMapping("/client/{id}")
        @Tag(name = "Crud")
        @Operation(
            summary = "Atualizar um cliente.",
            description = "Informe os dados para atualizar um cliente"
        )
        @ApiResponse(
            responseCode = "200",
            description = "Cliente atulizado com sucesso.",
            content = { 
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Cliente.class)
                )
            }
        )
    String atualizarCliente(@PathVariable("id") Long id, @Valid @RequestBody Cliente atualizarCliente, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return bindingResult.getAllErrors().get(bindingResult.getAllErrors().size() - 1).getDefaultMessage();
        }

        atualizarCliente.setId(id);
        return this.clienteService.update(atualizarCliente);
    }


    @DeleteMapping("client/{id}")
        @Tag(name = "Crud")
        @Operation(
            summary = "Deletar um cliente.",
            description = "Informe os dados para deletar um cliente"
        )
        @ApiResponse(
            responseCode = "200",
            description = "Cliente deletedo com sucesso."
        )
    String deletarCliente(@PathVariable("id") Long id) {
        return this.clienteService.delete(id);
    }


    /**
     * Para lembrar a diferença ao retornar: dados originais e dados DTOs.
     */
    @GetMapping("/list-all-clients-original")
        @Tag(name = "List")
        @Operation
    public List<Cliente> buscarTodosClientesSemDTO() {
        return this.clienteService.getAllClientes();
    }

    @GetMapping("/list-all-clients-mapped")
    @Tag(name = "List")
    public List<ClienteDTO> buscarTodosClientesComDTO() {
        return this.clienteService
                .getAllClientes()
                .stream()
                .map(cliente -> new ClienteDTO(cliente.getNome(), cliente.getEmail()))
                .collect(Collectors.toList());
    }
}