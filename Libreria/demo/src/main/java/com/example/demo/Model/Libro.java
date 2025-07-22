package com.example.demo.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "libro")
public class Libro {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "titolo", nullable = false, length = 50)
    @NotBlank(message = "Il titolo è obbligatorio!")
    private String titolo;

    @Column(name = "autore", nullable = false)
    @Size(min = 3, max = 25, message = "Il nominativo dell'autore deve essere compreso tra 3 e 25 caratteri")
    @NotBlank(message = "L'autore è obbligatorio!")
    private String autore;

    @Column(name = "prezzo", nullable = false)
    @DecimalMin(value = "5.00", inclusive = true, message = "Il prezzo è obbligatorio, ed il minimo e 5.00 euro")
    @DecimalMax(value = "100.00", inclusive = false, message = "Il prezzo è obbligatorio, ed il massimo e 99.99 euro")
    private double prezzo;

    public Libro(String titolo, String autore, double prezzo) {
        this.titolo = titolo;
        this.autore = autore;
        this.prezzo = prezzo;
    }
}
