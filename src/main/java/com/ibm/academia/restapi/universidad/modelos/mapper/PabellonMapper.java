package com.ibm.academia.restapi.universidad.modelos.mapper;

import com.ibm.academia.restapi.universidad.modelos.dto.PabellonDTO;
import com.ibm.academia.restapi.universidad.modelos.entidades.Pabellon;

public class PabellonMapper {
    public static PabellonDTO mapPabellon(Pabellon pabellon){
        PabellonDTO empleadoDTO = new PabellonDTO();
        empleadoDTO.setPabellonId(pabellon.getId());
        empleadoDTO.setNombre(pabellon.getNombre());
        empleadoDTO.setDireccion(pabellon.getDireccion());
        empleadoDTO.setFechaCreacion(pabellon.getFechaCreacion());
        empleadoDTO.setMetrosCuadrados(pabellon.getMetrosCuadrados());
        return empleadoDTO;
    }
}