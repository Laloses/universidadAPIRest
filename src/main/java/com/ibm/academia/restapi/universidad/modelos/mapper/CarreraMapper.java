package com.ibm.academia.restapi.universidad.modelos.mapper;

import com.ibm.academia.restapi.universidad.modelos.dto.CarreraDTO;
import com.ibm.academia.restapi.universidad.modelos.entidades.Carrera;

public class CarreraMapper 
{
	public static CarreraDTO mapCarrera(Carrera carrera)
	{
		CarreraDTO carreraDTO = new CarreraDTO();
		carreraDTO.setCarreraId(carrera.getId());
		carreraDTO.setNombre(carrera.getNombre());
		carreraDTO.setCantidadAnios(carrera.getCantidadAnios());
		carreraDTO.setCantidadMaterias(carrera.getCantidadMaterias());
		carreraDTO.setFechaCreacion(carrera.getFechaCreacion());
		return carreraDTO;
	}
}