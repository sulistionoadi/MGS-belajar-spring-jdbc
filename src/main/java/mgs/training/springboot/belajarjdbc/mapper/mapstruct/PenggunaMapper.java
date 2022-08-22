package mgs.training.springboot.belajarjdbc.mapper.mapstruct;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import mgs.training.springboot.belajarjdbc.dto.PenggunaDto;
import mgs.training.springboot.belajarjdbc.entity.PenggunaEntity;

@Mapper(
		componentModel = "spring", 
		unmappedTargetPolicy = ReportingPolicy.WARN)
public interface PenggunaMapper {

	PenggunaEntity toEntity(PenggunaDto d);
	PenggunaDto toDto(PenggunaEntity e);
	
	List<PenggunaEntity> toEntity(List<PenggunaDto> d);
	List<PenggunaDto> toDto(List<PenggunaEntity> e);
}
