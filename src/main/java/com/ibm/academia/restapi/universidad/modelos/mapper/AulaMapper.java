package com.ibm.academia.restapi.universidad.modelos.mapper;

import com.ibm.academia.restapi.universidad.modelos.dto.AulaDTO;
import com.ibm.academia.restapi.universidad.modelos.entidades.Aula;

public class AulaMapper {
    public static AulaDTO mapAula(Aula aula){
        AulaDTO aulaDTO = new AulaDTO();
        aulaDTO.setAulaId(aula.getId());
        aulaDTO.setNumeroAula(aula.getNumeroAula());
        aulaDTO.setMedidas(aula.getMedidas());
        aulaDTO.setCantidadPupitres(aula.getCantidadPupitres());
        aulaDTO.setTipoPizarron(aula.getTipoPizarron());
        aulaDTO.setFechaCreacion(aula.getFechaCreacion());
        return aulaDTO;
    }
}