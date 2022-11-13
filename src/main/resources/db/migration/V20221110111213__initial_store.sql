DROP SEQUENCE if EXISTS "store_seq";
CREATE SEQUENCE "store_seq"
    START WITH 1
    INCREMENT BY 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    NOCACHE;

DROP TABLE if EXISTS "store";
CREATE TABLE "store" (
    "id" SERIAL PRIMARY KEY,
    "store_id" varchar(10) NOT NULL,
    "name" varchar(50) NOT NULL,
    "phone" varchar(15) NOT NULL,
    "city" varchar(20) NOT NULL,
    "address" varchar(128) NOT NULL,
    "created_by" varchar(20) NOT NULL,
    "created_at" timestamp NOT NULL,
    "updated_by" varchar(20),
    "updated_at" timestamp
);

INSERT INTO "store" VALUES (1, 'store001', 'Nanshan car rental company', '123456789', 'Shenzhen', 'Nanshan',
'system', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP);
