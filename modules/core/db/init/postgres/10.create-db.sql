-- begin CARSALE_CAR_MODEL
create table CARSALE_CAR_MODEL (
    ID uuid,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    VERSION integer,
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    NAME varchar(50) not null,
    CODE integer not null,
    DESCRIPTION varchar(250),
    --
    primary key (ID)
)^
-- end CARSALE_CAR_MODEL
-- begin CARSALE_CAR
create table CARSALE_CAR (
    CARD_ID uuid,
    --
    NUMBER_ varchar(50),
    CAR_MODEL_ID uuid,
    NAME varchar(250),
    YEAR_OF_RELEASE integer,
    PRICE decimal(19, 2),
    CAR_TYPE integer,
    --
    primary key (CARD_ID)
)^
-- end CARSALE_CAR
-- begin CARSALE_CAR_PURCHASE
create table CARSALE_CAR_PURCHASE (
    CARD_ID uuid,
    --
    CAR_ID uuid not null,
    CUSTOMER_ID uuid,
    BANK_ID uuid,
    IS_PAID boolean,
    --
    primary key (CARD_ID)
)^
-- end CARSALE_CAR_PURCHASE