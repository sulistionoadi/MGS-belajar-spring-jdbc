create or replace PACKAGE BODY PKG_JABATAN AS

  procedure save_jabatan (
                   p_in_nama IN  VARCHAR2,
                   p_out_errcode OUT number,
                   p_out_errmsg OUT VARCHAR2) AS
  BEGIN
    -- TODO: Implementation required for procedure PKG_JABATAN.save_jabatan
    
    IF p_in_nama IS NULL OR p_in_nama = '' THEN
        p_out_errcode := 1;
        p_out_errmsg := 'Nama Jabatan is Required';
        return;
    END IF;
    
    INSERT INTO JABATAN(nama_jabatan, is_active) values(p_in_nama, 1);
        
    p_out_errcode := 0;
    p_out_errmsg := 'Simpan Jabatan Suskes';
    
    EXCEPTION  -- exception handlers begin
        WHEN OTHERS THEN  -- handles all other errors
            p_out_errcode := 99;
            p_out_errmsg := 'Terjadi Kesalahan, cause:(' || SQLERRM || ')';
  END save_jabatan;

  procedure update_jabatan (
                   p_in_id   IN  NUMBER,
                   p_in_nama IN  VARCHAR2,
                   p_out_errcode OUT number,
                   p_out_errmsg OUT VARCHAR2) AS
        v_existing_id NUMBER;
  BEGIN
    -- TODO: Implementation required for procedure PKG_JABATAN.update_jabatan
    IF p_in_nama IS NULL OR p_in_nama = '' THEN
        p_out_errcode := 1;
        p_out_errmsg := 'Nama Jabatan is Required';
        return;
    END IF;
    
    SELECT ID INTO v_existing_id FROM JABATAN WHERE id = p_in_id;
    
    UPDATE JABATAN SET nama_jabatan = p_in_nama WHERE id = p_in_Id;
    p_out_errcode := 0;
    p_out_errmsg := 'Simpan Jabatan Suskes';
    
    EXCEPTION  -- exception handlers begin
        WHEN NO_DATA_FOUND THEN 
            p_out_errcode := 2;
            p_out_errmsg := 'Jabatan tidak ditemukan';
        WHEN OTHERS THEN  -- handles all other errors
            p_out_errcode := 99;
            p_out_errmsg := 'Terjadi Kesalahan, cause:(' || SQLERRM || ')';
  END update_jabatan;

  procedure get_data(
                   p_in_nama IN  VARCHAR2,
                   p_out_errcode OUT NUMBER,
                   p_out_errmsg OUT VARCHAR2, 
                   p_out_data OUT ref_cursor) AS
  BEGIN
    p_out_errcode := 0;
    p_out_errmsg := 'Successful';
    
    OPEN p_out_data FOR 
    SELECT ID, nama_jabatan, is_active FROM JABATAN 
    WHERE (p_in_nama is null or lower(nama_jabatan) like '%' || lower(p_in_nama) || '%');
    
    EXCEPTION  -- exception handlers begin
        WHEN OTHERS THEN  -- handles all other errors
            p_out_errcode := 99;
            p_out_errmsg := 'Terjadi Kesalahan, cause:(' || SQLERRM || ')';
  END get_data;

END PKG_JABATAN;