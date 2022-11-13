DROP SEQUENCE if EXISTS "customer_seq";
CREATE SEQUENCE "customer_seq"
    START WITH 1
    INCREMENT BY 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    NOCACHE;

DROP TABLE if EXISTS "customer";
CREATE TABLE "customer" (
    "id" SERIAL PRIMARY KEY,
    "customer_id" varchar(10) NOT NULL,
    "name" varchar(30) NOT NULL,
    "id_card_num" varchar(20) NOT NULL,
    "mobile" varchar(15) NOT NULL,
    "email" varchar(128) NOT NULL,
    "created_by" varchar(20) DEFAULT 'system',
    "created_at" timestamp DEFAULT CURRENT_TIMESTAMP,
    "updated_by" varchar(20) DEFAULT 'system',
    "updated_at" timestamp DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO "customer" VALUES (1, '1000000001', 'Xiao Wang', '123456199001011234', '13800001234', 'wang@hotmail.com',
'system', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP);
INSERT INTO "customer" VALUES (2, '1000000002', 'Xiao Zhang', '123456199001021234', '13900001234', 'zhang@hotmail.com',
'system', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP);