DROP SEQUENCE if EXISTS "car_info_seq";
CREATE SEQUENCE "car_info_seq"
    START WITH 1
    INCREMENT BY 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    NOCACHE;

DROP TABLE if EXISTS "car_info";
CREATE TABLE "car_info" (
    "id" int NOT NULL PRIMARY KEY,
    "car_id" varchar(10) NOT NULL,
    "type" varchar(30) NOT NULL,
    "store_id" varchar(10) NOT NULL,
    "price_per_day" numeric(10,2) NOT NULL,
    "currency" varchar(10) NOT NULL
);

INSERT INTO "car_info" VALUES (1, 'car001', 'Toyota Camry', 'store001', 180, 'CNY');
INSERT INTO "car_info" VALUES (2, 'car002', 'Toyota Camry', 'store001', 180, 'CNY');
INSERT INTO "car_info" VALUES (3, 'car003', 'BMW 650', 'store001', 1080, 'CNY');
INSERT INTO "car_info" VALUES (4, 'car004', 'BMW 650', 'store001', 1080, 'CNY');

