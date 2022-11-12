DROP SEQUENCE if EXISTS "store_seq";
CREATE SEQUENCE "store_seq"
    START WITH 1
    INCREMENT BY 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    NOCACHE;

DROP TABLE if EXISTS "store";
CREATE TABLE "store" (
    "id" int NOT NULL PRIMARY KEY,
    "store_id" varchar(10) NOT NULL,
    "name" varchar(30) NOT NULL,
    "phone" varchar(15) NOT NULL,
    "city" varchar(20) NOT NULL,
    "address" varchar(128) NOT NULL
);

INSERT INTO "store" VALUES (1, 'company001', 'company1', '123456789', 'Shenzhen', 'Nanshan');
