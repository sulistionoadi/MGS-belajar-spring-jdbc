create or replace PACKAGE PKG_PENGGUNA AS 

 TYPE ref_cursor IS REF CURSOR;
  /* TODO enter package declarations (types, exceptions, methods etc) here */ 
    procedure save_pengguna (
                   p_in_username IN  VARCHAR2,
                   p_in_password IN  VARCHAR2,
                   p_in_jabatan IN  NUMBER,
                   p_in_unit IN  NUMBER,
                   p_out_errcode OUT number,
                   p_out_errmsg OUT VARCHAR2);
                   
    procedure update_pengguna (
                   p_in_id IN  NUMBER,
                   p_in_username IN  VARCHAR2,
                   p_in_password IN  VARCHAR2,
                   p_in_jabatan IN  NUMBER,
                   p_in_unit IN  NUMBER,
                   p_out_errcode OUT number,
                   p_out_errmsg OUT VARCHAR2);
                   
    procedure delete_pengguna (
                   p_in_id IN  NUMBER,
                   p_out_errcode OUT number,
                   p_out_errmsg OUT VARCHAR2);
                   
    procedure get_data(
                   p_in_nama IN  VARCHAR2,
                   p_in_start IN NUMBER,
                   p_in_end IN NUMBER,
                   p_out_errcode OUT NUMBER,
                   p_out_errmsg OUT VARCHAR2, 
                   p_out_totalel OUT NUMBER, 
                   p_out_data OUT ref_cursor);
END PKG_PENGGUNA;