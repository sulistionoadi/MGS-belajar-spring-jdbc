package mgs.training.springboot.belajarjdbc.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import mgs.training.springboot.belajarjdbc.dto.JabatanDto;
import mgs.training.springboot.belajarjdbc.dto.MasterUnitDto;
import mgs.training.springboot.belajarjdbc.dto.PenggunaDto;

public class PenggunaMapper implements RowMapper<PenggunaDto>{

	@Override
	public PenggunaDto mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		JabatanDto jab = new JabatanDto(); //membuat object Jabatan Baru
		jab.setId(rs.getLong("ID_JABATAN"));
		jab.setNamaJabatan(rs.getString("NAMA_JABATAN"));
		
		MasterUnitDto unit = new MasterUnitDto();
		unit.setId(rs.getLong("ID_UNIT"));
		unit.setKode(rs.getString("KODE_UNIT"));
		unit.setNama(rs.getString("NAMA_UNIT"));
		
		PenggunaDto dto = new PenggunaDto();
		dto.setId(rs.getLong("ID"));
		dto.setUsername(rs.getString("USERNAME"));
		dto.setPassword(rs.getString("PASSWORD"));
		dto.setJabatan(jab);
		dto.setUnit(unit);
		if(rs.getInt("IS_ACTIVE") == 1) {
			dto.setActive(true);
		} else {
			dto.setActive(false);
		}
		
		return dto;
	}

}
