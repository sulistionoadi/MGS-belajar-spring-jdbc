create or replace PACKAGE BODY PKG_MST_UNIT AS

  procedure save_mst_unit (
                   p_in_id   IN  NUMBER,
                   p_in_kode IN  VARCHAR2,
                   p_in_nama IN  VARCHAR2,
                   p_out_errcode OUT number,
                   p_out_errmsg OUT VARCHAR2) AS
  BEGIN
    IF p_in_nama IS NULL OR p_in_nama = '' THEN
        p_out_errcode := 1;
        p_out_errmsg := 'Nama Unit is Required';
        return;
    END IF;
    
    INSERT INTO MASTER_UNIT(id, KODE_UNIT, NAMA_UNIT)
        values(p_in_id, p_in_kode, p_in_nama);
        
    p_out_errcode := 0;
    p_out_errmsg := 'Simpan Master Unit Suskes';
    
    EXCEPTION  -- exception handlers begin
        WHEN OTHERS THEN  -- handles all other errors
            p_out_errcode := 99;
            p_out_errmsg := 'Terjadi Kesalahan, cause:(' || SQLERRM || ')';
  END save_mst_unit;

END PKG_MST_UNIT;