package com.pontoeletronico.pontoeletronico.controllers;

import com.pontoeletronico.pontoeletronico.model.Funcionario;
import com.pontoeletronico.pontoeletronico.repositories.FacadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class FuncionarioController {

    private final FacadeRepository facadeRepository;

    @Autowired
    public FuncionarioController(FacadeRepository facadeRepository) {
        this.facadeRepository = facadeRepository;
    }

    @GetMapping("/funcionario")
    public List<Funcionario> readAll() throws SQLException {
        return facadeRepository.readAllFuncionarios();
    }

    @GetMapping("/funcionario/{id}")
    public Funcionario read(@PathVariable int id) throws SQLException {
        return facadeRepository.readFuncionario(id);
    }

    @PostMapping("/funcionario")
    public String create(@RequestBody Funcionario f) {
        try {
            facadeRepository.create(f);
            return "Cadastro realizado com sucesso!";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Não foi possível realizar o cadastro";
        }
    }

    @PutMapping("/funcionario/{id}")
    public String update(@PathVariable int id, @RequestBody Funcionario f) throws SQLException {
        f.setId(id); 
        
        try {
            facadeRepository.update(f);
            return "Funcionario atualizado com sucesso!";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Erro ao atualizar o funcionário";
        }
    }

    @DeleteMapping("/funcionario/{id}")
    public void delete(@PathVariable int id) throws SQLException {
        facadeRepository.deleteFuncionario(id);
    }
}


