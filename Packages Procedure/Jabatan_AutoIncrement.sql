CREATE SEQUENCE  "PELATIHAN"."SEQ_JABATAN"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 30 CACHE 20 NOORDER  NOCYCLE ;

CREATE OR REPLACE TRIGGER TRG_JABATAN 
    BEFORE INSERT ON JABATAN 
    FOR EACH ROW
BEGIN
  if inserting then 
      if :NEW."ID" is null then 
         select SEQ_JABATAN.nextval into :NEW."ID" from dual; 
      end if; 
   end if; 
END;