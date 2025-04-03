package com.pontoeletronico.pontoeletronico.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;

public class RegistroPonto {

    private Integer id;
    private Integer funcionarioId;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dataHora;

    public RegistroPonto() {
    }

    public RegistroPonto(Integer id, Integer funcionarioId, LocalDateTime dataHora) {
        this.id = id;
        this.funcionarioId = funcionarioId;
        this.dataHora = dataHora;
    }

    public RegistroPonto(Integer funcionarioId, LocalDateTime dataHora) {
        this.funcionarioId = funcionarioId;
        this.dataHora = dataHora;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFuncionarioId() {
        return funcionarioId;
    }

    public void setFuncionarioId(Integer funcionarioId) {
        this.funcionarioId = funcionarioId;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }
}

