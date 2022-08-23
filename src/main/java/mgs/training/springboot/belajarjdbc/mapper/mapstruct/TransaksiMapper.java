package mgs.training.springboot.belajarjdbc.mapper.mapstruct;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import mgs.training.springboot.belajarjdbc.dto.TransaksiDto;
import mgs.training.springboot.belajarjdbc.entity.Transaksi;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN,
		uses= {TransaksiDetailMapper.class})
public interface TransaksiMapper {

	Transaksi toEntity(TransaksiDto d);
	TransaksiDto toDto(Transaksi e);
	
	List<Transaksi> toEntity(List<TransaksiDto> d);
	List<TransaksiDto> toDto(List<Transaksi> e);
}
