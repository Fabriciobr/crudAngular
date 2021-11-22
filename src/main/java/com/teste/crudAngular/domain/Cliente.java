package com.teste.crudAngular.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.teste.crudAngular.enums.TipoCliente;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name="cliente")
public class Cliente {

    @Id
    @SequenceGenerator(name = "ClienteSequence", sequenceName = "cliente_sequence", schema = "public", allocationSize = 1)
    @GeneratedValue(generator = "ClienteSequence", strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotBlank(message = "O cliente precisa ter um nome")
    @Size(min = 3, max = 100,message = "o nome deve ter de 3 a 100 Caracteres")
    private String nome;

    @NotNull(message = "Escolha o tipo do cliente")
    private TipoCliente tipo;

    @Size(min =11, max = 20,message = "Digiteo CPF com 11 digitos ou o CNPJ com 14")
    //@NotBlank(message = "O cliente precisa ter um CPF ou CNPJ")
    @Column(unique = true,name = "email")
    private String cpfCnpj;

    private LocalDate dataCadastro;

    private Boolean ativo;

    @OneToMany(
            mappedBy = "cliente",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Telefone> telefones = new ArrayList<>();

}
