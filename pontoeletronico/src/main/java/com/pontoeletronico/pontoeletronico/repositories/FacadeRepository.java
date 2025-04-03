package com.pontoeletronico.pontoeletronico.repositories;

import com.pontoeletronico.pontoeletronico.model.Funcionario;
import com.pontoeletronico.pontoeletronico.model.RegistroPonto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class FacadeRepository {

    private final RepositorioGenerico<Funcionario, Integer> rFuncionario;
    private final RepositorioGenerico<RegistroPonto, Integer> rRegistroPonto;
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public FacadeRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.rRegistroPonto = new RegistroPontoRepository(jdbcTemplate);
        this.rFuncionario = new FuncionarioRepository(jdbcTemplate);
    }

    public void create(Funcionario funcionario) throws SQLException {
        this.rFuncionario.create(funcionario);
    }

    public void update(Funcionario funcionario) throws SQLException {
        this.rFuncionario.update(funcionario);
    }

    public Funcionario readFuncionario(int id) throws SQLException {
        return this.rFuncionario.read(id);
    }

    public void deleteFuncionario(int id) throws SQLException {
        this.rFuncionario.delete(id);
    }

    public List<Funcionario> readAllFuncionarios() throws SQLException {
        List<Funcionario> funcionarios = ((FuncionarioRepository) this.rFuncionario).readAll();
        return (funcionarios != null) ? funcionarios : new ArrayList<>();
    }

    public void createRegistroPonto(RegistroPonto ponto) throws SQLException {
        this.rRegistroPonto.create(ponto);
    }

    public void updateRegistroPonto(RegistroPonto ponto) throws SQLException {
        ((RegistroPontoRepository) this.rRegistroPonto).update(ponto);
    }

    public void deleteRegistroPonto(int id) throws SQLException {
        ((RegistroPontoRepository) this.rRegistroPonto).delete(id);
    }

    public List<RegistroPonto> getPontosByFuncionario(int funcionarioId) throws SQLException {
        return ((RegistroPontoRepository) this.rRegistroPonto).getPontosByFuncionario(funcionarioId);
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }
}


