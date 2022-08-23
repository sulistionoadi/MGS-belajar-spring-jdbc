create or replace PACKAGE BODY PKG_PENGGUNA AS

  procedure save_pengguna (
                   p_in_username IN  VARCHAR2,
                   p_in_password IN  VARCHAR2,
                   p_in_jabatan IN  NUMBER,
                   p_in_unit IN  NUMBER,
                   p_out_errcode OUT number,
                   p_out_errmsg OUT VARCHAR2) AS
        v_jabatan NUMBER;
        v_unit NUMBER;
  BEGIN
    -- TODO: Implementation required for procedure PKG_PENGGUNA.save_pengguna
    IF p_in_username IS NULL OR p_in_username = '' THEN
        p_out_errcode := 1;
        p_out_errmsg := 'Nama User is Required';
        return;
    END IF;
    
    SELECT j.ID INTO v_jabatan FROM JABATAN j WHERE j.ID = p_in_jabatan;
    SELECT u.ID INTO v_unit FROM MASTER_UNIT u WHERE u.ID = p_in_unit;
    
    INSERT INTO PENGGUNA(USERNAME, PASSWORD, is_active, ID_JABATAN, ID_UNIT) 
        VALUES(p_in_username, p_in_password, 1, v_jabatan, v_unit);
        
    p_out_errcode := 0;
    p_out_errmsg := 'Simpan Pengguna Suskes';
    
    EXCEPTION  -- exception handlers begin
        WHEN NO_DATA_FOUND THEN 
            p_out_errcode := 2;
            p_out_errmsg := 'Referensi tidak ditemukan';
        WHEN OTHERS THEN  -- handles all other errors
            p_out_errcode := 99;
            p_out_errmsg := 'Terjadi Kesalahan, cause:(' || SQLERRM || ')';
  END save_pengguna;

  procedure update_pengguna (
                   p_in_id IN  NUMBER,
                   p_in_username IN  VARCHAR2,
                   p_in_password IN  VARCHAR2,
                   p_in_jabatan IN  NUMBER,
                   p_in_unit IN  NUMBER,
                   p_out_errcode OUT number,
                   p_out_errmsg OUT VARCHAR2) AS
        v_jabatan NUMBER;
        v_unit NUMBER;
        v_exist NUMBER;
  BEGIN
    -- TODO: Implementation required for procedure PKG_PENGGUNA.save_pengguna
    IF p_in_username IS NULL OR p_in_username = '' THEN
        p_out_errcode := 1;
        p_out_errmsg := 'Nama User is Required';
        return;
    END IF;
    
    SELECT p.ID INTO v_exist FROM PENGGUNA p WHERE p.ID = p_in_id;
    SELECT j.ID INTO v_jabatan FROM JABATAN j WHERE j.ID = p_in_jabatan;
    SELECT u.ID INTO v_unit FROM MASTER_UNIT u WHERE u.ID = p_in_unit;
    
    IF p_in_password IS NULL OR p_in_password = '' THEN
        UPDATE PENGGUNA SET USERNAME = p_in_username, ID_JABATAN = p_in_jabatan, ID_UNIT = p_in_unit WHERE ID = p_in_Id;
    ELSE
        UPDATE PENGGUNA SET USERNAME = p_in_username, PASSWORD = p_in_password, ID_JABATAN = p_in_jabatan, ID_UNIT = p_in_unit WHERE ID = p_in_Id;
    END IF;
    
        
    p_out_errcode := 0;
    p_out_errmsg := 'Simpan Pengguna Suskes';
    
    EXCEPTION  -- exception handlers begin
        WHEN NO_DATA_FOUND THEN 
            p_out_errcode := 2;
            p_out_errmsg := 'Referensi tidak ditemukan';
        WHEN OTHERS THEN  -- handles all other errors
            p_out_errcode := 99;
            p_out_errmsg := 'Terjadi Kesalahan, cause:(' || SQLERRM || ')';
  END update_pengguna;

  procedure delete_pengguna (
                   p_in_id IN  NUMBER,
                   p_out_errcode OUT number,
                   p_out_errmsg OUT VARCHAR2) AS
        v_existing_id NUMBER;
  BEGIN
    -- TODO: Implementation required for procedure PKG_JABATAN.update_jabatan
    IF p_in_id IS NULL OR p_in_id = 0 THEN
        p_out_errcode := 1;
        p_out_errmsg := 'ID Pengguna is Required';
        return;
    END IF;
    
    SELECT ID INTO v_existing_id FROM PENGGUNA WHERE id = p_in_id;
    
    DELETE FROM PENGGUNA WHERE id = p_in_Id;
    p_out_errcode := 0;
    p_out_errmsg := 'Delete Pengguna Suskes';
    
    EXCEPTION  -- exception handlers begin
        WHEN NO_DATA_FOUND THEN 
            p_out_errcode := 2;
            p_out_errmsg := 'Pengguna tidak ditemukan';
        WHEN OTHERS THEN  -- handles all other errors
            p_out_errcode := 99;
            p_out_errmsg := 'Terjadi Kesalahan, cause:(' || SQLERRM || ')';
  END delete_pengguna;

  procedure get_data(
                   p_in_nama IN  VARCHAR2,
                   p_in_start IN NUMBER,
                   p_in_end IN NUMBER,
                   p_out_errcode OUT NUMBER,
                   p_out_errmsg OUT VARCHAR2, 
                   p_out_totalel OUT NUMBER, 
                   p_out_data OUT ref_cursor) AS
        v_count NUMBER;
  BEGIN
    p_out_errcode := 0;
    p_out_errmsg := 'Successful';
    
    SELECT COUNT(*) INTO v_count FROM PENGGUNA 
    WHERE (p_in_nama is null or lower(username) like '%' || lower(p_in_nama) || '%');
    p_out_totalel := v_count;
    
    OPEN p_out_data FOR
    SELECT * FROM (
        SELECT ROWNUM rn, p.ID, p.USERNAME, p.PASSWORD, p.is_active, 
            j.ID ID_JABATAN, j.NAMA_JABATAN, 
            u.ID ID_UNIT, u.KODE_UNIT, u.NAMA_UNIT
        FROM PENGGUNA p 
        LEFT JOIN JABATAN j ON j.id = p.ID_JABATAN
        LEFT JOIN MASTER_UNIT u ON u.id = p.ID_UNIT
        WHERE (p_in_nama is null or lower(username) like '%' || lower(p_in_nama) || '%')
        ORDER BY ID DESC
    ) data WHERE data.rn >= p_in_start AND data.rn <= p_in_end ;
    
    EXCEPTION  -- exception handlers begin
        WHEN OTHERS THEN  -- handles all other errors
            p_out_errcode := 99;
            p_out_errmsg := 'Terjadi Kesalahan, cause:(' || SQLERRM || ')';
  END get_data;

END PKG_PENGGUNA;