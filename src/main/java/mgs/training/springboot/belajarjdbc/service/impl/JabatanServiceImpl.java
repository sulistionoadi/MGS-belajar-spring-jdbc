package mgs.training.springboot.belajarjdbc.service.impl;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;

import mgs.training.springboot.belajarjdbc.dto.JabatanDto;
import mgs.training.springboot.belajarjdbc.mapper.JabatanMapper;
import mgs.training.springboot.belajarjdbc.service.JabatanService;
import oracle.jdbc.OracleTypes;

@Service
public class JabatanServiceImpl implements JabatanService {

	@Autowired private DataSource dataSource;
	
	@Override
	public void save(JabatanDto dto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<JabatanDto> getData(String filter) {
		SimpleJdbcCall jdbcCall = new SimpleJdbcCall(dataSource)
				.withCatalogName("PKG_JABATAN")
				.withProcedureName("get_data")
				.returningResultSet("p_out_data", new JabatanMapper());
		
		jdbcCall.addDeclaredParameter(new SqlParameter("p_in_nama", OracleTypes.VARCHAR));
		jdbcCall.addDeclaredParameter(new SqlOutParameter("p_out_errcode", OracleTypes.INTEGER));
		jdbcCall.addDeclaredParameter(new SqlOutParameter("p_out_errmsg", OracleTypes.VARCHAR));
		
		SqlParameterSource inParam = new MapSqlParameterSource().addValue("p_in_nama", filter);
		
		Map<String, Object> result = jdbcCall.execute(inParam);
		System.out.println("Result Code = " + result.get("p_out_errcode"));
		System.out.println("Result Mesg = " + result.get("p_out_errmsg"));
		
		List<JabatanDto> data = null;
		if((Integer) result.get("p_out_errcode") == 0) {
			data = (List<JabatanDto>) result.get("p_out_data");
			System.out.println("Result Data : ");
			data.stream().forEach(x -> {
				System.out.println("-> " + x.toString());
			});
		}
		
		return data;
	}

}