package com.example.demo.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.Model.Libro;
import com.example.demo.Repository.LibroRepository;

@Service
public class LibroService {
    private final LibroRepository libroRepository;

    public LibroService(LibroRepository libroRepository) {
        this.libroRepository = libroRepository;
    }

    public List<Libro> getAll() {
        List<Libro> libri = new ArrayList<>();
        libroRepository.findAll().forEach(libri::add);
        return libri;
    }

    public Optional<Libro> getById(Long id) {
        return libroRepository.findById(id);
    }

    public Libro create(Libro nuovoLibro) {
        return libroRepository.save(nuovoLibro);
    }

    public Optional<Libro> update(Long id, Libro libroModificato) {
        return libroRepository.findById(id).map(libro -> {
            libro.setTitolo(libroModificato.getTitolo());
            libro.setAutore(libroModificato.getAutore());
            libro.setPrezzo(libroModificato.getPrezzo());
            return libroRepository.save(libro);
        });
    }

    public boolean delete(Long id) {
        if (libroRepository.existsById(id)) {
            libroRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
