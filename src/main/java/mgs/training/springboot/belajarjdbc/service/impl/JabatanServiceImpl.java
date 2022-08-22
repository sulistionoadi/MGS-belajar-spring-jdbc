package mgs.training.springboot.belajarjdbc.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;

import mgs.training.springboot.belajarjdbc.dto.CustomException;
import mgs.training.springboot.belajarjdbc.dto.JabatanDto;
import mgs.training.springboot.belajarjdbc.dto.http.HttpPagedModel;
import mgs.training.springboot.belajarjdbc.dto.http.HttpRespModel;
import mgs.training.springboot.belajarjdbc.mapper.JabatanMapper;
import mgs.training.springboot.belajarjdbc.service.JabatanService;
import oracle.jdbc.OracleTypes;

@Service("jabatanPlsqlService")
public class JabatanServiceImpl implements JabatanService {

	@Autowired private DataSource dataSource;
	
	@Override
	public HttpRespModel save(JabatanDto dto) {
		if(Objects.isNull(dto)) {
			throw new CustomException("Missing object dto");
		}
		
		SimpleJdbcCall jdbcCall = new SimpleJdbcCall(dataSource)
				.withCatalogName("PKG_JABATAN")
				.withProcedureName("save_jabatan");
		
		jdbcCall.addDeclaredParameter(new SqlParameter("p_in_nama", OracleTypes.VARCHAR));
		jdbcCall.addDeclaredParameter(new SqlOutParameter("p_out_errcode", OracleTypes.INTEGER));
		jdbcCall.addDeclaredParameter(new SqlOutParameter("p_out_errmsg", OracleTypes.VARCHAR));
		
		SqlParameterSource inParam = new MapSqlParameterSource().addValue("p_in_nama", dto.getNamaJabatan());
		
		Map<String, Object> result = jdbcCall.execute(inParam);
		if((Integer) result.get("p_out_errcode") != 0) {
			//throw new Exception(MessageFormat.format("Save Error Code:{0} Message:{1}", result.get("p_out_errcode"), result.get("p_out_errmsg")));
			//throw new CustomException((Integer) result.get("p_out_errcode"), (String) result.get("p_out_errmsg"));
			return HttpRespModel.error((Integer) result.get("p_out_errcode"), (String) result.get("p_out_errmsg"));
		}
		
		return HttpRespModel.ok(null, ((String) result.get("p_out_errmsg")));
	}

	@Override
	public HttpPagedModel<JabatanDto> getData(String filter, Pageable page) {
		SimpleJdbcCall jdbcCall = new SimpleJdbcCall(dataSource)
				.withCatalogName("PKG_JABATAN")
				.withProcedureName("get_data")
				.returningResultSet("p_out_data", new JabatanMapper());
		
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
		
		List<JabatanDto> data = null;
		if((Integer) result.get("p_out_errcode") == 0) {
			data = (List<JabatanDto>) result.get("p_out_data");
			System.out.println("Result Data : ");
			data.stream().forEach(x -> {
				System.out.println("-> " + x.toString());
			});
		}
		
		return HttpPagedModel.ok(data, ((String) result.get("p_out_errmsg")), page.getPageSize(), (Integer) result.get("p_out_totalel"));
	}

	@Override
	public HttpRespModel update(JabatanDto dto) {
		if(Objects.isNull(dto)) {
			throw new CustomException("Missing object dto");
		}
		
		SimpleJdbcCall jdbcCall = new SimpleJdbcCall(dataSource)
				.withCatalogName("PKG_JABATAN")
				.withProcedureName("update_jabatan");
		
		jdbcCall.addDeclaredParameter(new SqlParameter("p_in_id", OracleTypes.NUMBER));
		jdbcCall.addDeclaredParameter(new SqlParameter("p_in_nama", OracleTypes.VARCHAR));
		jdbcCall.addDeclaredParameter(new SqlOutParameter("p_out_errcode", OracleTypes.INTEGER));
		jdbcCall.addDeclaredParameter(new SqlOutParameter("p_out_errmsg", OracleTypes.VARCHAR));
		
		SqlParameterSource inParam = new MapSqlParameterSource()
				.addValue("p_in_id", dto.getId())
				.addValue("p_in_nama", dto.getNamaJabatan());
		
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
				.withCatalogName("PKG_JABATAN")
				.withProcedureName("delete_jabatan");
		
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
