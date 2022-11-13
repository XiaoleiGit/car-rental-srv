DROP SEQUENCE if EXISTS "order_history_seq";
CREATE SEQUENCE "order_history_seq"
    INCREMENT BY 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    START WITH 1
    NOCACHE;

DROP TABLE if EXISTS "order_history";
CREATE TABLE "order_history" (
    "id" SERIAL PRIMARY KEY,
    "order_id" varchar(32) NOT NULL,
    "customer_id" varchar(10) NOT NULL,
    "car_id" varchar(10) NOT NULL,
    "book_start_time" timestamp NOT NULL,
    "book_end_time" timestamp NOT NULL,
    "actual_start_time" timestamp,
    "actual_end_time" timestamp,
    "status" varchar(10) NOT NULL,
    "created_by" varchar(20) NOT NULL,
    "created_at" timestamp NOT NULL,
    "updated_by" varchar(20),
    "updated_at" timestamp
);

