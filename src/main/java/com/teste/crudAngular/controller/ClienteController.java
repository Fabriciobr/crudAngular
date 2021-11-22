package com.teste.crudAngular.controller;

import com.teste.crudAngular.domain.Cliente;
import com.teste.crudAngular.service.ClienteService;
import com.teste.crudAngular.validation.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.validation.ValidationErrors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/cliente")
public class ClienteController {

    @Autowired
    ClienteService clienteService;


    @GetMapping("")
    public ResponseEntity<List<Cliente>> filterCliente(@RequestParam(required = true) String nome, @RequestParam(required = true) Boolean somenteAtivo) {
        try {

            List<Cliente> clientes = clienteService.filterCliente(nome, somenteAtivo);

            return new ResponseEntity(clientes, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<Cliente> getClienteById(@PathVariable("id") long id) {
        try {
            Cliente cliente =  clienteService.getClienteById(id);
            return new ResponseEntity(cliente, HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("")
    public ResponseEntity<Cliente> createCliente(@Validated @RequestBody Cliente cliente) throws Exception {

            cliente = clienteService.createCliente(cliente);
            return new ResponseEntity(cliente, HttpStatus.CREATED);

    }

    @PutMapping("")
    public ResponseEntity<Cliente> updateCliente(@RequestBody Cliente cliente) throws Exception {
            cliente =  clienteService.updateCliente(cliente);
            return new ResponseEntity(cliente, HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteCliente(@PathVariable("id") long id) {
        try {
            clienteService.deleteCliente(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
