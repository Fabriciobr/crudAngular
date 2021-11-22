package com.teste.crudAngular.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.teste.crudAngular.enums.TipoCliente;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Entity
@Data
@Table(name = "telefone")
public class Telefone {

    @Id
    @SequenceGenerator(name = "TelefoneSequence", sequenceName = "telefone_sequence", schema = "public", allocationSize = 1)
    @GeneratedValue(generator = "TelefoneSequence", strategy = GenerationType.SEQUENCE)
    private long id;

    @NotBlank(message = "Informe o DDD")
    @Size(min = 2, max = 2, message = "o DDD deve ter 2 digitos")
    private String ddd;

    @NotBlank(message = "Informe o Número")
    private String numero;

    @ManyToOne(fetch = FetchType.EAGER,optional = false,cascade=CascadeType.REMOVE)
    @JoinColumn(name="cliente_id", referencedColumnName = "id")
    @JsonIgnoreProperties(value = { "telefones" }, allowSetters = true)
    private Cliente cliente;
}

