-- Active: 1698060232258@@127.0.0.1@5432@postgres

CREATE TABLE
    IF NOT EXISTS "country" (
        "id" serial PRIMARY KEY,
        "name" varchar(50)
    );

CREATE TABLE
    "city" (
        "id" bigserial PRIMARY KEY,
        "name" varchar(50),
        "country_id" integer REFERENCES "country" ("id")
    );

CREATE TABLE
    "address" (
        "id" uuid PRIMARY KEY,
        "num" integer,
        "street" text NOT NULL,
        "complement" text,
        "zip_code" integer,
        "city_id" integer REFERENCES "city" ("id")
    );

CREATE TABLE
    "company" (
        "siret" integer PRIMARY KEY,
        "name" varchar(50),
        "address_id" uuid REFERENCES "address" ("id")
    );

CREATE TABLE
    "recruiter" (
        "id" uuid PRIMARY KEY,
        "first_name" varchar(64) NOT NULL,
        "last_name" varchar(64) NOT NULL,
        "phone" varchar(16),
        "email" varchar(128) UNIQUE NOT NULL,
        "password" varchar(128)
    );

CREATE TABLE
    "company_recruiter" (
        "id" uuid PRIMARY KEY,
        "company_id" integer REFERENCES "company" ("siret"),
        "recruiter_id" uuid REFERENCES "recruiter" ("id"),
        "proof" bytea,
        UNIQUE ("company_id", "recruiter_id")
    );

CREATE TABLE
    "feature" (
        "id" serial PRIMARY KEY,
        "name" varchar(50)
    );

CREATE TABLE
    "tier" (
        "id" serial PRIMARY KEY,
        "level" varchar(16),
        "price_biyearly" integer,
        "price_yearly" integer,
        "price_monthly" integer
    );

CREATE TABLE
    "tier_feature" (
        "tier_id" integer REFERENCES "tier" ("id"),
        "feature_id" integer REFERENCES "feature" ("id"),
        "value" varchar(16),
        PRIMARY KEY ("tier_id", "feature_id")
    );

CREATE TABLE
    "subscription" (
        "id" uuid PRIMARY KEY,
        "tier_id" integer REFERENCES "tier" ("id"),
        "recruiter_id" uuid REFERENCES "recruiter" ("id"),
        "start_date" date,
        "end_date" date
    );