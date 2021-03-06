-- schema for H2 database, uses it's syntax
CREATE USER IF NOT EXISTS CM_CONNECT PASSWORD 'CM_CONNECT' ADMIN;

create table TSCM_LANGUAGES ( 
LNG_ID_LANGUAGE INT PRIMARY KEY,
LNG_LANGUAGE_NAME VARCHAR(20));

create table TSCM_ADDRESSES (
ADR_USER_ID INT PRIMARY KEY,
ADR_NAME VARCHAR(80),
ADR_EXTRA VARCHAR(50),
ADR_STREET VARCHAR(100),
ADR_CITY VARCHAR(100),
ADR_ZIP VARCHAR(50),
ADR_COUNTRY VARCHAR(50));

create table TSCM_USERS(
USR_ID_USER INT PRIMARY KEY,
USR_USERNAME VARCHAR(50),
USR_REGISTRATION_DATE TIMESTAMP WITH TIME ZONE,
USR_IS_SELLER BOOLEAN,
USR_NAME VARCHAR(50),
USR_PHONE VARCHAR(20),
USR_EMAIL VARCHAR(50),
USR_VAT VARCHAR(50),
USR_LEGAL_INFO VARCHAR(1000),
USR_RISK_GROUP INT,
USR_LOSS_PERCENTAGE VARCHAR(20),
USR_UNSENT_SHIPMENTS INT,
USR_REPUTATION INT,
USR_SHIPS_FAST INT,
USR_SELL_COUNT INT,
USR_SOLD_ITEMS INT,
USR_AVG_SHIPPING_TIME INT,
USR_ON_VACATION BOOLEAN);

CREATE TABLE TDCM_ARTICLES (
ART_ID_ARTICLE INT PRIMARY KEY, 
ART_ID_PRODUCT INT,
ART_ID_LANGUAGE INT,
ART_COMMENTS VARCHAR(100),
ART_PRICE DECIMAL(12,2),
ART_COUNT INT,
ART_IN_SHOPPING_CART BOOLEAN,
ART_ID_USER INT,
ART_CONDITION VARCHAR(20),
ART_IS_FOIL BOOLEAN,
ART_IS_SIGNED BOOLEAN,
ART_IS_PLAYSET BOOLEAN,
ART_IS_ALTERED BOOLEAN);

CREATE TABLE TSCM_SINGLES (
  SGL_ID_PRODUCT INT PRIMARY KEY,
  SGL_ID_METAPRODUCT INT,
  SGL_REPRINT_AMT INT,
  SGL_EN_NAME VARCHAR(180),
  SGL_LOC_NAME VARCHAR(180), 
  SGL_ID_GAME INT(10),
  SGL_EXP_NAME VARCHAR(60),
  SGL_RARITY VARCHAR(15),
  SGL_IMAGE VARCHAR(40));

create table TSCM_EXPANSIONS (
EXP_ID_EXPANSION INT PRIMARY KEY,
EXP_EN_NAME VARCHAR(100),
EXP_ABBREVIATION VARCHAR(5),
EXP_RELEASE_DATE TIMESTAMP WITH TIME ZONE,
EXP_IS_RELEASED BOOLEAN,
EXP_ID_GAME INT);

