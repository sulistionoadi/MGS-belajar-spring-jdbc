create or replace PACKAGE PKG_JABATAN AS 

    TYPE ref_cursor IS REF CURSOR;

    /* TODO enter package declarations (types, exceptions, methods etc) here */ 
    procedure save_jabatan (
                   p_in_nama IN  VARCHAR2,
                   p_out_errcode OUT number,
                   p_out_errmsg OUT VARCHAR2);
    
    procedure update_jabatan (
                   p_in_id   IN  NUMBER,
                   p_in_nama IN  VARCHAR2,
                   p_out_errcode OUT number,
                   p_out_errmsg OUT VARCHAR2);

    procedure delete_jabatan (
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
                   
END PKG_JABATAN;