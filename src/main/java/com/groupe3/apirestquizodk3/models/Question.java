package com.groupe3.apirestquizodk3.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "question")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_id")
    private Long questionId;

    @NotNull(message = "text doit pas etre null")
    @Size(min = 10, max = 100, message = "text est entre 10 et 100")
    @Column(name = "texte")
    private String text;

    @NotNull(message = "type ne doit pas etre null")
    @Pattern(regexp = "^(choix-multiple|vrai-faux)$", message = " type est incorrecte")
    private String type; // Les valeurs possibles sont: "choix-multiple" et "vrai-faux"

    @NotNull(message = "point ne doit pas etre null")
    @Min(value = 5, message = "point doit etre au minimum 5")
    @Max(value = 100, message = "point doit etre au maximum 100")
    private int point=5;

    @Column(name = "rang")
    private int rank; // le rang de la question. Elle est toujour inferieur ou égale au nombre de question dans le quiz

    @OneToMany(
            cascade = CascadeType.ALL, // Permet de s'assurer que tous changement effectué sur une question va impacter ses choix et vise versa
            orphanRemoval = true, // Permet de s'assurer que lorqu'on supprime une question par exemple, les choix seront aussi supprimer
            fetch = FetchType.EAGER //Permet d'avoir la liste des choix lorsqu'on appele un objet Question
    )
    @JoinColumn(name = "question_id") // La clé etrangère de chaque choix correspond à l'id de Question
    private List<Choise> choises = new ArrayList<>();

    @ManyToOne(
            cascade = CascadeType.ALL
    )
    @JoinColumn(name = "quiz_id") // La clé etrangère de la classe Question correspond à l'id de Quiz
    @JsonIgnore
    private Quiz quiz;


}
