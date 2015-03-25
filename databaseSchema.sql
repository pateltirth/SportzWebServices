--------------------------------------------------------
--  File created - Sunday-February-22-2015   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Table CITIES
--------------------------------------------------------

  CREATE TABLE "TIRTH"."CITIES" 
   (	"ID" NUMBER(10,0), 
	"NAME" VARCHAR2(255 BYTE), 
	"STATE" VARCHAR2(2 BYTE), 
	"STATUS" VARCHAR2(16 BYTE), 
	"LATITUDE" FLOAT(126), 
	"LONGITUDE" FLOAT(126)
   )
--------------------------------------------------------
--  DDL for Table VISITS
--------------------------------------------------------

  CREATE TABLE "TIRTH"."VISITS" 
   (	"ID" NUMBER, 
	"USER_ID" NUMBER, 
	"CITY_ID" NUMBER
   )
--------------------------------------------------------
--  DDL for Table USERS
--------------------------------------------------------

  CREATE TABLE "TIRTH"."USERS" 
   (	"ID" NUMBER(10,0), 
	"FIRST_NAME" VARCHAR2(10 BYTE), 
	"LAST_NAME" VARCHAR2(10 BYTE)
   )
--------------------------------------------------------
--  DDL for Index VISITS_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "TIRTH"."VISITS_PK" ON "TIRTH"."VISITS" ("ID")
--------------------------------------------------------
--  Constraints for Table VISITS
--------------------------------------------------------

  ALTER TABLE "TIRTH"."VISITS" ADD CONSTRAINT "VISITS_PK" PRIMARY KEY ("ID") ENABLE
  ALTER TABLE "TIRTH"."VISITS" MODIFY ("CITY_ID" NOT NULL ENABLE)
  ALTER TABLE "TIRTH"."VISITS" MODIFY ("USER_ID" NOT NULL ENABLE)
  ALTER TABLE "TIRTH"."VISITS" MODIFY ("ID" NOT NULL ENABLE)
