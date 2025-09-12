package com.datarium.api.controller;

import com.datarium.api.model.Ativo;
import com.datarium.api.service.AtivoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/ativos")
public class AtivoController {

    @Autowired
    private AtivoService ativoService;

    @GetMapping
    public List<Ativo> buscarTodosAtivos() {
        return ativoService.buscarTodosAtivos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ativo> buscarAtivoPorId(@PathVariable Long id) {
        Optional<Ativo> ativo = ativoService.buscarAtivoPorId(id);
        return ativo.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Ativo salvarAtivo(@RequestBody Ativo ativo) {
        return ativoService.salvarAtivo(ativo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ativo> atualizarAtivo(@PathVariable Long id, @RequestBody Ativo ativo) {
        if (!ativoService.buscarAtivoPorId(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Ativo ativoAtualizado = ativoService.salvarAtivo(ativo);
        return ResponseEntity.ok(ativoAtualizado);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarAtivo(@PathVariable Long id) {
        ativoService.deletarAtivo(id);
    }
}