package mgs.training.springboot.belajarjdbc.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import mgs.training.springboot.belajarjdbc.dto.JabatanDto;

public class JabatanMapper implements RowMapper<JabatanDto>{

	@Override
	public JabatanDto mapRow(ResultSet rs, int rowNum) throws SQLException {
//		JabatanDto dto = new JabatanDto(
//				rs.getLong("ID"),
//				rs.getString("NAMA_JABATAN"),
//				false); //membuat object Jabatan Baru
		JabatanDto dto = new JabatanDto(); //membuat object Jabatan Baru
		dto.setId(rs.getLong("ID"));
		dto.setNamaJabatan(rs.getString("NAMA_JABATAN"));
		
		if(rs.getInt("IS_ACTIVE") == 1) {
			dto.setActive(true);
		} else {
			dto.setActive(false);
		}
		
		return dto;
	}

}
