package mgs.training.springboot.belajarjdbc.mapper.mapstruct;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import mgs.training.springboot.belajarjdbc.dto.JabatanDto;
import mgs.training.springboot.belajarjdbc.entity.JabatanEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface JabatanMapper {

	JabatanEntity toEntity(JabatanDto d);
	JabatanDto toDto(JabatanEntity e);
	
	List<JabatanEntity> toEntity(List<JabatanDto> d);
	List<JabatanDto> toDto(List<JabatanEntity> e);
}
