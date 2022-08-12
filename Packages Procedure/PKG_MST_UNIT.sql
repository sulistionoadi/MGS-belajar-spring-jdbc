create or replace PACKAGE PKG_MST_UNIT AS 

  /* TODO enter package declarations (types, exceptions, methods etc) here */ 
  procedure save_mst_unit (
                   p_in_id   IN  NUMBER,
                   p_in_kode IN  VARCHAR2,
                   p_in_nama IN  VARCHAR2,
                   p_out_errcode OUT number,
                   p_out_errmsg OUT VARCHAR2);
END PKG_MST_UNIT;