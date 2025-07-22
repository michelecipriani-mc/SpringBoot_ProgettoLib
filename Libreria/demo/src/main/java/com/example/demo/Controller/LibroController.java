package com.example.demo.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.Model.Libro;
import com.example.demo.Service.LibroService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/libreria/libri")
public class LibroController {

    private final LibroService service;

    public LibroController(LibroService service) {
        this.service = service;
    }

    @GetMapping
    public List<Libro> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Libro> getById(@PathVariable Long id) {
        return service.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Libro> create(@RequestBody @Valid Libro nuovoLibro) {
        Libro libroCreato = service.create(nuovoLibro);
        return ResponseEntity.status(HttpStatus.CREATED).body(libroCreato);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Libro> update(@PathVariable Long id, @RequestBody @Valid Libro libroModificato) {
        return service.update(id, libroModificato)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        boolean libroRimosso = service.delete(id);
        return libroRimosso ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
