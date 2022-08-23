package mgs.training.springboot.belajarjdbc.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.sql.DataSource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import mgs.training.springboot.belajarjdbc.dto.CustomException;
import mgs.training.springboot.belajarjdbc.dto.PenggunaDto;
import mgs.training.springboot.belajarjdbc.dto.http.HttpPagedModel;
import mgs.training.springboot.belajarjdbc.dto.http.HttpRespModel;
import mgs.training.springboot.belajarjdbc.mapper.PenggunaMapper;
import mgs.training.springboot.belajarjdbc.service.PenggunaService;
import oracle.jdbc.OracleTypes;

@Service("penggunaPlsqlService")
public class PenggunaServiceImpl implements PenggunaService {

	@Autowired private DataSource dataSource;
	@Autowired private PasswordEncoder encoder;
	
	@Override
	public HttpRespModel save(PenggunaDto dto) {
		if(Objects.isNull(dto)) {
			throw new CustomException("Missing object dto");
		}
		
		SimpleJdbcCall jdbcCall = new SimpleJdbcCall(dataSource)
				.withCatalogName("PKG_PENGGUNA")
				.withProcedureName("save_pengguna");
		
		jdbcCall.addDeclaredParameter(new SqlParameter("p_in_username", OracleTypes.VARCHAR));
		jdbcCall.addDeclaredParameter(new SqlParameter("p_in_password", OracleTypes.VARCHAR));
		jdbcCall.addDeclaredParameter(new SqlParameter("p_in_jabatan", OracleTypes.NUMBER));
		jdbcCall.addDeclaredParameter(new SqlParameter("p_in_unit", OracleTypes.NUMBER));
		jdbcCall.addDeclaredParameter(new SqlOutParameter("p_out_errcode", OracleTypes.INTEGER));
		jdbcCall.addDeclaredParameter(new SqlOutParameter("p_out_errmsg", OracleTypes.VARCHAR));
		
		SqlParameterSource inParam = new MapSqlParameterSource()
				.addValue("p_in_username", dto.getUsername())
				.addValue("p_in_password", encoder.encode(dto.getPassword()))
				.addValue("p_in_jabatan", dto.getJabatan().getId())
				.addValue("p_in_unit", dto.getUnit().getId());
		
		Map<String, Object> result = jdbcCall.execute(inParam);
		if((Integer) result.get("p_out_errcode") != 0) {
			//throw new Exception(MessageFormat.format("Save Error Code:{0} Message:{1}", result.get("p_out_errcode"), result.get("p_out_errmsg")));
			//throw new CustomException((Integer) result.get("p_out_errcode"), (String) result.get("p_out_errmsg"));
			return HttpRespModel.error((Integer) result.get("p_out_errcode"), (String) result.get("p_out_errmsg"));
		}
		
		return HttpRespModel.ok(null, ((String) result.get("p_out_errmsg")));
	}

	@Override
	public HttpPagedModel<PenggunaDto> getData(String filter, Pageable page) {
		SimpleJdbcCall jdbcCall = new SimpleJdbcCall(dataSource)
				.withCatalogName("PKG_PENGGUNA")
				.withProcedureName("get_data")
				.returningResultSet("p_out_data", new PenggunaMapper());
		
		jdbcCall.addDeclaredParameter(new SqlParameter("p_in_nama", OracleTypes.VARCHAR));
		jdbcCall.addDeclaredParameter(new SqlParameter("p_in_start", OracleTypes.NUMBER));
		jdbcCall.addDeclaredParameter(new SqlParameter("p_in_end", OracleTypes.NUMBER));
		jdbcCall.addDeclaredParameter(new SqlOutParameter("p_out_totalel", OracleTypes.INTEGER));
		jdbcCall.addDeclaredParameter(new SqlOutParameter("p_out_errcode", OracleTypes.INTEGER));
		jdbcCall.addDeclaredParameter(new SqlOutParameter("p_out_errmsg", OracleTypes.VARCHAR));
		
		SqlParameterSource inParam = new MapSqlParameterSource()
				.addValue("p_in_nama", filter)
				.addValue("p_in_start", ( page.getPageNumber() * page.getPageSize() ) + 1)
				.addValue("p_in_end", ( page.getPageNumber() + 1 ) * page.getPageSize());
		
		Map<String, Object> result = jdbcCall.execute(inParam);
		System.out.println("Result Code = " + result.get("p_out_errcode"));
		System.out.println("Result Mesg = " + result.get("p_out_errmsg"));
		
		List<PenggunaDto> data = null;
		if((Integer) result.get("p_out_errcode") == 0) {
			data = (List<PenggunaDto>) result.get("p_out_data");
			System.out.println("Result Data : ");
			data.stream().forEach(x -> {
				System.out.println("-> " + x.toString());
			});
		}
		
		return HttpPagedModel.ok(data, ((String) result.get("p_out_errmsg")), data.size(), (Integer) result.get("p_out_totalel"));
	}

	@Override
	public HttpRespModel update(PenggunaDto dto) {
		if(Objects.isNull(dto)) {
			throw new CustomException("Missing object dto");
		}
		
		SimpleJdbcCall jdbcCall = new SimpleJdbcCall(dataSource)
				.withCatalogName("PKG_PENGGUNA")
				.withProcedureName("update_pengguna");
		
		jdbcCall.addDeclaredParameter(new SqlParameter("p_in_id", OracleTypes.NUMBER));
		jdbcCall.addDeclaredParameter(new SqlParameter("p_in_username", OracleTypes.VARCHAR));
		jdbcCall.addDeclaredParameter(new SqlParameter("p_in_password", OracleTypes.VARCHAR));
		jdbcCall.addDeclaredParameter(new SqlParameter("p_in_jabatan", OracleTypes.NUMBER));
		jdbcCall.addDeclaredParameter(new SqlParameter("p_in_unit", OracleTypes.NUMBER));
		jdbcCall.addDeclaredParameter(new SqlOutParameter("p_out_errcode", OracleTypes.INTEGER));
		jdbcCall.addDeclaredParameter(new SqlOutParameter("p_out_errmsg", OracleTypes.VARCHAR));
		
		String password = "";
		if(StringUtils.isNotBlank(dto.getPassword())) {
			password = encoder.encode(dto.getPassword());
		}
		
		SqlParameterSource inParam = new MapSqlParameterSource()
				.addValue("p_in_id", dto.getId())
				.addValue("p_in_username", dto.getUsername())
				.addValue("p_in_password", password)
				.addValue("p_in_jabatan", dto.getJabatan().getId())
				.addValue("p_in_unit", dto.getUnit().getId());
		
		Map<String, Object> result = jdbcCall.execute(inParam);
		if((Integer) result.get("p_out_errcode") != 0) {
			//throw new Exception(MessageFormat.format("Save Error Code:{0} Message:{1}", result.get("p_out_errcode"), result.get("p_out_errmsg")));
			//throw new CustomException((Integer) result.get("p_out_errcode"), (String) result.get("p_out_errmsg"));
			return HttpRespModel.error((Integer) result.get("p_out_errcode"), (String) result.get("p_out_errmsg"));
		}
		return HttpRespModel.ok(null, ((String) result.get("p_out_errmsg")));
	}

	@Override
	public HttpRespModel delete(Long id) {
		SimpleJdbcCall jdbcCall = new SimpleJdbcCall(dataSource)
				.withCatalogName("PKG_PENGGUNA")
				.withProcedureName("delete_pengguna");
		
		jdbcCall.addDeclaredParameter(new SqlParameter("p_in_id", OracleTypes.NUMBER));
		jdbcCall.addDeclaredParameter(new SqlOutParameter("p_out_errcode", OracleTypes.INTEGER));
		jdbcCall.addDeclaredParameter(new SqlOutParameter("p_out_errmsg", OracleTypes.VARCHAR));
		
		SqlParameterSource inParam = new MapSqlParameterSource().addValue("p_in_id", id);
		
		Map<String, Object> result = jdbcCall.execute(inParam);
		if((Integer) result.get("p_out_errcode") != 0) {
			//throw new Exception(MessageFormat.format("Save Error Code:{0} Message:{1}", result.get("p_out_errcode"), result.get("p_out_errmsg")));
			//throw new CustomException((Integer) result.get("p_out_errcode"), (String) result.get("p_out_errmsg"));
			return HttpRespModel.error((Integer) result.get("p_out_errcode"), (String) result.get("p_out_errmsg"));
		}
		return HttpRespModel.ok(null, ((String) result.get("p_out_errmsg")));
	}

}
