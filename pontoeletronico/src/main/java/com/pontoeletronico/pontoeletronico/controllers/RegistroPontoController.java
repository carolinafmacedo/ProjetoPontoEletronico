package com.pontoeletronico.pontoeletronico.controllers;

import com.pontoeletronico.pontoeletronico.model.RegistroPonto;
import com.pontoeletronico.pontoeletronico.repositories.FacadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class RegistroPontoController {

    private final FacadeRepository facadeRepository;

    @Autowired
    public RegistroPontoController(FacadeRepository facadeRepository) {
        this.facadeRepository = facadeRepository;
    }

    @PostMapping("/registroPonto/{idFuncionario}")
    public RegistroPonto create(@PathVariable int idFuncionario) {
        try {
            RegistroPonto ponto = new RegistroPonto();
            ponto.setFuncionarioId(idFuncionario);
            ponto.setDataHora(java.time.LocalDateTime.now());

            facadeRepository.createRegistroPonto(ponto);
            return ponto;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


    @GetMapping("/registroPonto/{id}")
    public List<RegistroPonto> readAll(@PathVariable int id) {
        try {
            List<RegistroPonto> registros = facadeRepository.getPontosByFuncionario(id);
            if (registros != null && !registros.isEmpty()) {
                return registros;
            } else {
                return List.of(); 
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return List.of();
        }
    }

    @PutMapping("/registroPonto/{id}")
    public String update(@PathVariable int id, @RequestBody RegistroPonto ponto) {
        try {
            ponto.setId(id); 
            facadeRepository.updateRegistroPonto(ponto);
            return "Ponto atualizado com sucesso!";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Falha na atualização do ponto";
        }
    }

    @DeleteMapping("/registroPonto/{id}")
    public String delete(@PathVariable int id) {
        try {
            facadeRepository.deleteRegistroPonto(id);
            return "Ponto deletado com sucesso!";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Falha ao deletar o ponto";
        }
    }
}






