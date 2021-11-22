package com.teste.crudAngular.repository;

import com.teste.crudAngular.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    List<Cliente> findByNomeContainingAndAtivo(String nome,boolean ativo);

    List<Cliente> findByNomeContaining(String nome);

    List<Cliente> findByCpfCnpj(String cpfCnpj);
}
