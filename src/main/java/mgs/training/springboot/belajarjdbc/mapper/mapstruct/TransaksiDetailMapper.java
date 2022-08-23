package mgs.training.springboot.belajarjdbc.mapper.mapstruct;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import mgs.training.springboot.belajarjdbc.dto.TransaksiDetailDto;
import mgs.training.springboot.belajarjdbc.entity.TransaksiDetail;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface TransaksiDetailMapper {

	TransaksiDetail toEntity(TransaksiDetailDto d);
	TransaksiDetailDto toDto(TransaksiDetail e);
	
	List<TransaksiDetail> toEntity(List<TransaksiDetailDto> d);
	List<TransaksiDetailDto> toDto(List<TransaksiDetail> e);
}
