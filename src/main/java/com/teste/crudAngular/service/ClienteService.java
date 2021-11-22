package com.teste.crudAngular.service;

import com.teste.crudAngular.domain.Cliente;
import com.teste.crudAngular.domain.Telefone;
import com.teste.crudAngular.repository.ClienteRepository;
import com.teste.crudAngular.validation.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    ClienteRepository clienteRepository;

    public List<Cliente> filterCliente(String nome,Boolean somenteAtivo) {

        if(somenteAtivo){
            return clienteRepository.findByNomeContainingAndAtivo(nome,true);
        }else{
            return clienteRepository.findByNomeContaining(nome);
        }
    }


    public Cliente getClienteById(long id) throws NotFoundException{
        Optional<Cliente> clienteData = clienteRepository.findById(id);

        if (clienteData.isPresent()) {
            return clienteData.get();
        } else {
            throw new NotFoundException("Esse cliente não foi encontrado");
        }
    }

    public Cliente createCliente(Cliente cliente) throws Exception{
        List<Cliente> exisentes = clienteRepository.findByCpfCnpj(cliente.getCpfCnpj());
        if(exisentes != null && exisentes.size()> 0){
            throw new Exception("já existe um cliente com esse CPF/CNPJ");
        }
        if(cliente.getDataCadastro() == null){
            cliente.setDataCadastro(LocalDate.now());
        }
        if(cliente.getTelefones() != null){
            for (Telefone tel:cliente.getTelefones()  ) {
               tel.setCliente(cliente);
            }
        }
            Cliente _cliente = clienteRepository.save(cliente);
            return _cliente;
    }


    public Cliente updateCliente(Cliente cliente) throws NotFoundException{
        Optional<Cliente> clienteData = clienteRepository.findById(cliente.getId());

        if (clienteData.isPresent()) {
            return clienteRepository.save(cliente);
        } else {
            throw new NotFoundException("Esse cliente não foi encontrado");
        }
    }


    public void deleteCliente(long id) {
        clienteRepository.deleteById(id);
    }

}
