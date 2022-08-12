CREATE OR REPLACE
PACKAGE BODY PKG_PENGGUNA AS

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

END PKG_PENGGUNA;