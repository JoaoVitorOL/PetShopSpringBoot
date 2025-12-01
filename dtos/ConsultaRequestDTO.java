package com.example.demo.dtos;

import java.time.LocalDate;
import java.util.List;
import com.example.demo.enums.Status;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public class ConsultaRequestDTO {

    // ID do animal
    @NotNull(message = "O id do animal é obrigatório.")
    @Positive(message = "O id do animal deve ser um número positivo.")
    private Long idAnimal;

    // ID do veterinário
    @NotNull(message = "O id do veterinário é obrigatório.")
    @Positive(message = "O id do veterinário deve ser um número positivo.")
    private Long idVeterinario;

    // IDs das vacinas opcional
    private List<Long> vacinasIds;

    // Data da consulta
    @NotNull(message = "A data da consulta é obrigatória.")
    private LocalDate dataConsulta;

    // Motivo da consulta
    @NotBlank(message = "O motivo da consulta é obrigatório.")
    private String motivo;

    // Status da consulta (código do enum)
    @NotNull(message = "O status da consulta é obrigatório.")
    private Integer status;

    // Getters e Setters

    public Long getIdAnimal() {
        return idAnimal;
    }

    public void setIdAnimal(Long idAnimal) {
        this.idAnimal = idAnimal;
    }

    public Long getIdVeterinario() {
        return idVeterinario;
    }

    public void setIdVeterinario(Long idVeterinario) {
        this.idVeterinario = idVeterinario;
    }

    public List<Long> getVacinasIds() {
        return vacinasIds;
    }

    public void setVacinasIds(List<Long> vacinasIds) {
        this.vacinasIds = vacinasIds;
    }

    public LocalDate getDataConsulta() {
        return dataConsulta;
    }

    public void setDataConsulta(LocalDate dataConsulta) {
        this.dataConsulta = dataConsulta;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    // Converte automaticamente o código em Enum
    public Status getStatus() {
        return Status.toEnum(status);
    }

    public void setStatus(Status status) {
        this.status = (status != null) ? status.getCodigo() : null;
    }
}
