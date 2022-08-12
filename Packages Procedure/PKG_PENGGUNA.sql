CREATE OR REPLACE 
PACKAGE PKG_PENGGUNA AS 

  /* TODO enter package declarations (types, exceptions, methods etc) here */ 
    procedure save_pengguna (
                   p_in_username IN  VARCHAR2,
                   p_in_password IN  VARCHAR2,
                   p_in_jabatan IN  NUMBER,
                   p_in_unit IN  NUMBER,
                   p_out_errcode OUT number,
                   p_out_errmsg OUT VARCHAR2);
END PKG_PENGGUNA;