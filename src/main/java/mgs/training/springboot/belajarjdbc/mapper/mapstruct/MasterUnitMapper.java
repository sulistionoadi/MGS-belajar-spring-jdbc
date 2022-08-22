package mgs.training.springboot.belajarjdbc.mapper.mapstruct;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import mgs.training.springboot.belajarjdbc.dto.MasterUnitDto;
import mgs.training.springboot.belajarjdbc.entity.MasterUnitEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface MasterUnitMapper {

	MasterUnitEntity toEntity(MasterUnitDto d);
	MasterUnitDto toDto(MasterUnitEntity e);
	
	List<MasterUnitEntity> toEntity(List<MasterUnitDto> d);
	List<MasterUnitDto> toDto(List<MasterUnitEntity> e);
}
