package com.pontoeletronico.pontoeletronico.repositories;

import com.pontoeletronico.pontoeletronico.model.RegistroPonto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;

@Repository
public class RegistroPontoRepository implements RepositorioGenerico<RegistroPonto, Integer> {

    private final JdbcTemplate jdbcTemplate;

    public RegistroPontoRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void create(RegistroPonto ponto) throws SQLException {
        String sql = "INSERT INTO registroPonto (funcionarioId, dataHora) VALUES (?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"}); // Retorna apenas o ID
            ps.setInt(1, ponto.getFuncionarioId());
            ps.setTimestamp(2, Timestamp.valueOf(ponto.getDataHora()));
            return ps;
        }, keyHolder);

        if (keyHolder.getKey() != null) {
            ponto.setId(keyHolder.getKey().intValue());
        } else {
            throw new SQLException("Erro ao obter o ID gerado para o RegistroPonto.");
        }
    }

    @Override
    public List<RegistroPonto> readAll() throws SQLException {
        String sql = "SELECT * FROM registroPonto";
        return jdbcTemplate.query(sql, (rs, rowNum) -> new RegistroPonto(
            rs.getInt("id"),
            rs.getInt("funcionarioId"),
            rs.getTimestamp("dataHora").toLocalDateTime()
        ));
    }

    @Override
    public RegistroPonto read(Integer id) throws SQLException {
        String sql = "SELECT * FROM registroPonto WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> new RegistroPonto(
            rs.getInt("id"),
            rs.getInt("funcionarioId"),
            rs.getTimestamp("dataHora").toLocalDateTime()
        ), id);
    }

    @Override
    public void update(RegistroPonto ponto) throws SQLException {
        String sql = "UPDATE registroPonto SET funcionarioId = ?, dataHora = ? WHERE id = ?";
        jdbcTemplate.update(sql, ponto.getFuncionarioId(), Timestamp.valueOf(ponto.getDataHora()), ponto.getId());
    }


    @Override
    public void delete(Integer id) throws SQLException {
        String sql = "DELETE FROM registroPonto WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    public List<RegistroPonto> getPontosByFuncionario(int funcionarioId) throws SQLException {
        String sql = "SELECT * FROM registroPonto WHERE funcionarioId = ?";
        return jdbcTemplate.query(sql, (rs, rowNum) -> new RegistroPonto(
            rs.getInt("id"),
            rs.getInt("funcionarioId"),
            rs.getTimestamp("dataHora").toLocalDateTime()
        ), funcionarioId);
    }
}



