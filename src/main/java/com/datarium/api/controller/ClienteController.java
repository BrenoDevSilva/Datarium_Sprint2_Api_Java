package com.datarium.api.controller;

import com.datarium.api.model.Cliente;
import com.datarium.api.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public List<Cliente> buscarTodosClientes() {
        return clienteService.buscarTodosClientes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscarClientePorId(@PathVariable Long id) {
        Optional<Cliente> cliente = clienteService.buscarClientePorId(id);
        return cliente.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/login")
    public ResponseEntity<Cliente> login(@RequestBody Cliente cliente) {
        Optional<Cliente> clienteLogado = clienteService.buscarClientePorEmailESenha(cliente.getEmail(), cliente.getSenha());
        return clienteLogado.map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente salvarCliente(@RequestBody @Valid Cliente cliente) {
        return clienteService.salvarCliente(cliente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> atualizarCliente(@PathVariable Long id, @RequestBody @Valid Cliente cliente) {
        if (!clienteService.buscarClientePorId(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Cliente clienteAtualizado = clienteService.salvarCliente(cliente);
        return ResponseEntity.ok(clienteAtualizado);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarCliente(@PathVariable Long id) {
        clienteService.deletarCliente(id);
    }
}