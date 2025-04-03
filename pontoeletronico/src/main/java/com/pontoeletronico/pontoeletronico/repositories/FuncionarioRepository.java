package com.pontoeletronico.pontoeletronico.repositories;

import com.pontoeletronico.pontoeletronico.model.Funcionario;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

@Repository
public class FuncionarioRepository implements RepositorioGenerico<Funcionario, Integer> {

    private final JdbcTemplate jdbcTemplate;

    public FuncionarioRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void create(Funcionario funcionario) throws SQLException {
        String sql = "INSERT INTO funcionario (nome, cargo, setor) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, funcionario.getNome(), funcionario.getCargo(), funcionario.getSetor());
    }

    @Override
    public List<Funcionario> readAll() throws SQLException {
        String sql = "SELECT * FROM funcionario";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            return new Funcionario(
                rs.getInt("id"),
                rs.getString("nome"),
                rs.getString("cargo"),
                rs.getString("setor")
            );
        });
    }

    @Override
    public Funcionario read(Integer id) throws SQLException {
        String sql = "SELECT * FROM funcionario WHERE id = ?";
        Funcionario funcionario = jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
            return new Funcionario(
                rs.getInt("id"),
                rs.getString("nome"),
                rs.getString("cargo"),
                rs.getString("setor")
            );
        }, id);
        return funcionario;
    }

    @Override
    public void update(Funcionario funcionario) throws SQLException {
        String sql = "UPDATE funcionario SET nome = ?, cargo = ?, setor = ? WHERE id = ?";
        jdbcTemplate.update(sql, funcionario.getNome(), funcionario.getCargo(), funcionario.getSetor(), funcionario.getId());
    }

    @Override
    public void delete(Integer id) throws SQLException {
        String sql = "DELETE FROM funcionario WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}



