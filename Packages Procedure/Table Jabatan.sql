--------------------------------------------------------
--  File created - Thursday-August-11-2022   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Table JABATAN
--------------------------------------------------------

  CREATE TABLE "PELATIHAN"."JABATAN" 
   (	"ID" NUMBER, 
	"NAMA_JABATAN" VARCHAR2(50 BYTE), 
	"IS_ACTIVE" NUMBER(1,0) DEFAULT 0
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;
--------------------------------------------------------
--  DDL for Index SYS_C0027923
--------------------------------------------------------

  CREATE UNIQUE INDEX "PELATIHAN"."SYS_C0027923" ON "PELATIHAN"."JABATAN" ("ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;
--------------------------------------------------------
--  DDL for Trigger TRG_JABATAN
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "PELATIHAN"."TRG_JABATAN" 
    BEFORE INSERT ON JABATAN 
    FOR EACH ROW
BEGIN
  if inserting then 
      if :NEW."ID" is null then 
         select SEQ_JABATAN.nextval into :NEW."ID" from dual; 
      end if; 
   end if; 
END;
/
ALTER TRIGGER "PELATIHAN"."TRG_JABATAN" ENABLE;
--------------------------------------------------------
--  Constraints for Table JABATAN
--------------------------------------------------------

  ALTER TABLE "PELATIHAN"."JABATAN" ADD PRIMARY KEY ("ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM"  ENABLE;
