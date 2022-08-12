--------------------------------------------------------
--  File created - Thursday-August-11-2022   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Table MASTER_UNIT
--------------------------------------------------------

  CREATE TABLE "PELATIHAN"."MASTER_UNIT" 
   (	"ID" NUMBER, 
	"KODE_UNIT" VARCHAR2(20 BYTE), 
	"NAMA_UNIT" VARCHAR2(50 BYTE)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;
--------------------------------------------------------
--  DDL for Index TABLE1_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "PELATIHAN"."TABLE1_PK" ON "PELATIHAN"."MASTER_UNIT" ("ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;
--------------------------------------------------------
--  Constraints for Table MASTER_UNIT
--------------------------------------------------------

  ALTER TABLE "PELATIHAN"."MASTER_UNIT" ADD CONSTRAINT "TABLE1_PK" PRIMARY KEY ("ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM"  ENABLE;
  ALTER TABLE "PELATIHAN"."MASTER_UNIT" MODIFY ("NAMA_UNIT" NOT NULL ENABLE);
  ALTER TABLE "PELATIHAN"."MASTER_UNIT" MODIFY ("KODE_UNIT" NOT NULL ENABLE);
  ALTER TABLE "PELATIHAN"."MASTER_UNIT" MODIFY ("ID" NOT NULL ENABLE);