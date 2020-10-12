--Add default numerator for carsale$Car
CREATE OR REPLACE FUNCTION baseInsert()
    RETURNS integer
AS $$
DECLARE
    cnt integer = 0;
BEGIN
    cnt = (select count(id) from DF_NUMERATOR where CODE = 'CarNumerator' and delete_ts is null);
    if(cnt = 0) then
        INSERT INTO DF_NUMERATOR (ID, CREATE_TS, CREATED_BY, VERSION, CODE, NUMERATOR_FORMAT, SCRIPT_ENABLED,
                                  PERIODICITY, NUMBER_INITIAL_VALUE, LOC_NAME)
        VALUES ('8e55b265-5ac7-45e0-b44f-0a3d1a5436a0', now(), 'system', 1, 'CarNumerator', '[number]', FALSE, 'Y', 1,
                '{"captionWithLanguageList":[{"language":"ru","caption":"Car"},{"language":"en","caption":"Car"}]}'
               );
    end if;
    return 0;
END;
$$
    LANGUAGE plpgsql;
^
select baseInsert()^
       drop function if exists baseInsert()^
--Add default numerator for carsale$CarPurchase
CREATE OR REPLACE FUNCTION baseInsert()
    RETURNS integer
AS $$
DECLARE
    cnt integer = 0;
BEGIN
    cnt = (select count(id) from DF_NUMERATOR where CODE = 'CarPurchaseNumerator' and delete_ts is null);
    if(cnt = 0) then
        INSERT INTO DF_NUMERATOR (ID, CREATE_TS, CREATED_BY, VERSION, CODE, NUMERATOR_FORMAT, SCRIPT_ENABLED,
                                  PERIODICITY, NUMBER_INITIAL_VALUE, LOC_NAME)
        VALUES ('56068861-6615-4128-8d0d-f824245c01fc', now(), 'system', 1, 'CarPurchaseNumerator', '[number]', FALSE, 'Y', 1,
                '{"captionWithLanguageList":[{"language":"ru","caption":"CarPurchase"},{"language":"en","caption":"CarPurchase"}]}'
               );
    end if;
    return 0;
END;
$$
    LANGUAGE plpgsql;
^
select baseInsert()^
       drop function if exists baseInsert()^
--Insert new doc type for carsale$CarPurchase
insert into TS_CARD_TYPE (ID, CREATE_TS, CREATED_BY, NAME, DISCRIMINATOR,FIELDS_XML) values ('baff6386-de6d-47f3-8881-f3c573771d22', current_timestamp, 'admin', 'carsale$CarPurchase', 1100, '')^
--Add default doc kind for carsale$CarPurchase
CREATE OR REPLACE FUNCTION baseInsert()
    RETURNS integer
AS $$
DECLARE
    cnt integer = 0;
BEGIN
    cnt = (select count(CATEGORY_ID) from DF_DOC_KIND where category_id = 'ec94a78f-31d5-4f90-ba44-6d289a336ff3');
    if(cnt = 0) then
        insert into SYS_CATEGORY (ID, NAME, ENTITY_TYPE, IS_DEFAULT, CREATE_TS, CREATED_BY, VERSION, DISCRIMINATOR)
        values ( 'ec94a78f-31d5-4f90-ba44-6d289a336ff3', 'Заявка на покупку автомобиля', 'carsale$CarPurchase', false, now(), USER, 1, 1);
        insert into DF_DOC_KIND (category_id, create_ts, created_by, version, doc_type_id, numerator_id,
                                 numerator_type, category_attrs_place, tab_name, portal_publish_allowed, disable_add_process_actors, create_only_by_template)
        values ('ec94a78f-31d5-4f90-ba44-6d289a336ff3', 'now()', 'admin', 1, 'baff6386-de6d-47f3-8881-f3c573771d22', '56068861-6615-4128-8d0d-f824245c01fc',
                1, 1, 'Ð”Ð¾Ð¿. Ð¿Ð¾Ð»Ñ�', false, false, false);
    end if;return 0;
END;
$$
    LANGUAGE plpgsql;
^
select baseInsert();
^
drop function if exists baseInsert()^
--Update process card_types for entity carsale$CarPurchase
update wf_proc set card_types = regexp_replace(card_types, E',carsale\\$CarPurchase', '') where code in ('Endorsement','Resolution','Acquaintance','Registration')^
                                                                                                update wf_proc set updated_by='admin', card_types = card_types || 'carsale$CarPurchase,' where code in ('Endorsement','Resolution','Acquaintance','Registration')^
--Update security for entity carsale$CarPurchase
insert into SEC_PERMISSION (ID, CREATE_TS, CREATED_BY, VERSION, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, PERMISSION_TYPE, TARGET, VALUE, ROLE_ID) values (newid(),now(),'system',1,now(),null,null,null,20,'carsale$CarPurchase:create',0,(select ID from SEC_ROLE where NAME = 'SimpleUser'));
insert into SEC_PERMISSION (ID, CREATE_TS, CREATED_BY, VERSION, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, PERMISSION_TYPE, TARGET, VALUE, ROLE_ID) values (newid(),now(),'system',1,now(),null,null,null,20,'carsale$CarPurchase:update',0,(select ID from SEC_ROLE where NAME = 'SimpleUser'));
insert into SEC_PERMISSION (ID, CREATE_TS, CREATED_BY, VERSION, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, PERMISSION_TYPE, TARGET, VALUE, ROLE_ID) values (newid(),now(),'system',1,now(),null,null,null,20,'carsale$CarPurchase:delete',0,(select ID from SEC_ROLE where NAME = 'SimpleUser'));
insert into SEC_PERMISSION (ID, CREATE_TS, CREATED_BY, VERSION, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, PERMISSION_TYPE, TARGET, VALUE, ROLE_ID) values (newid(),now(),'system',1,now(),null,null,null,20,'carsale$CarPurchase:create',1,(select ID from SEC_ROLE where NAME = 'Administrators'));
insert into SEC_PERMISSION (ID, CREATE_TS, CREATED_BY, VERSION, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, PERMISSION_TYPE, TARGET, VALUE, ROLE_ID) values (newid(),now(),'system',1,now(),null,null,null,20,'carsale$CarPurchase:update',1,(select ID from SEC_ROLE where NAME = 'Administrators'));
insert into SEC_PERMISSION (ID, CREATE_TS, CREATED_BY, VERSION, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, PERMISSION_TYPE, TARGET, VALUE, ROLE_ID) values (newid(),now(),'system',1,now(),null,null,null,20,'carsale$CarPurchase:delete',1,(select ID from SEC_ROLE where NAME = 'Administrators'));
insert into SEC_PERMISSION (ID, CREATE_TS, CREATED_BY, VERSION, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, PERMISSION_TYPE, TARGET, VALUE, ROLE_ID) values (newid(),now(),'system',1,now(),null,null,null,20,'carsale$CarPurchase:create',1,(select ID from SEC_ROLE where NAME = 'doc_initiator'));
insert into SEC_PERMISSION (ID, CREATE_TS, CREATED_BY, VERSION, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, PERMISSION_TYPE, TARGET, VALUE, ROLE_ID) values (newid(),now(),'system',1,now(),null,null,null,20,'carsale$CarPurchase:update',1,(select ID from SEC_ROLE where NAME = 'doc_initiator'));
insert into SEC_PERMISSION (ID, CREATE_TS, CREATED_BY, VERSION, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, PERMISSION_TYPE, TARGET, VALUE, ROLE_ID) values (newid(),now(),'system',1,now(),null,null,null,20,'carsale$CarPurchase:delete',1,(select ID from SEC_ROLE where NAME = 'doc_initiator'));

-- добавляем роли

insert into sec_role(id, create_ts, created_by, version, name, loc_name)
values (newid(), current_timestamp, 'admin', 1, 'Manager', 'Менеджер');

insert into sec_role(id, create_ts, created_by, version, name, loc_name)
values (newid(), current_timestamp, 'admin', 1, 'Bank_operator', 'Оператор банка');

insert into sec_role(id, create_ts, created_by, version, name, loc_name)
values (newid(), current_timestamp, 'admin', 1, 'Master', 'Мастер');

-- ограничения для Мастер

insert into sec_permission(id, create_ts, created_by, version, permission_type, target, value, role_id)
values (newid(), current_timestamp, 'admin', 1, 20, 'carsale$CarPurchase:update', 1, (select id from sec_role where name = 'Master'));

insert into sec_permission(id, create_ts, created_by, version, permission_type, target, value, role_id)
values (newid(), current_timestamp, 'admin', 1, 20, 'carsale$CarPurchase:delete', 1, (select id from sec_role where name = 'Master'));

insert into sec_permission(id, create_ts, created_by, version, permission_type, target, value, role_id)
values (newid(), current_timestamp, 'admin', 1, 20, 'carsale$Car:delete', 1, (select id from sec_role where name = 'Master'));

insert into sec_permission(id, create_ts, created_by, version, permission_type, target, value, role_id)
values (newid(), current_timestamp, 'admin', 1, 20, 'carsale$Car:update', 1, (select id from sec_role where name = 'Master'));

insert into sec_permission(id, create_ts, created_by, version, permission_type, target, value, role_id)
values (newid(), current_timestamp, 'admin', 1, 20, 'carsale$Car:create', 1, (select id from sec_role where name = 'Master'));

-- ограничения для Оператор банка

insert into sec_permission(id, create_ts, created_by, version, permission_type, target, value, role_id)
values (newid(), current_timestamp, 'admin', 1, 20, 'carsale$CarPurchase:update', 1, (select id from sec_role where name = 'Bank_operator'));

insert into sec_permission(id, create_ts, created_by, version, permission_type, target, value, role_id)
values (newid(), current_timestamp, 'admin', 1, 20, 'carsale$CarPurchase:delete', 1, (select id from sec_role where name = 'Bank_operator'));

insert into sec_permission(id, create_ts, created_by, version, permission_type, target, value, role_id)
values (newid(), current_timestamp, 'admin', 1, 20, 'carsale$Car:delete', 1, (select id from sec_role where name = 'Bank_operator'));

insert into sec_permission(id, create_ts, created_by, version, permission_type, target, value, role_id)
values (newid(), current_timestamp, 'admin', 1, 20, 'carsale$Car:update', 1, (select id from sec_role where name = 'Bank_operator'));

insert into sec_permission(id, create_ts, created_by, version, permission_type, target, value, role_id)
values (newid(), current_timestamp, 'admin', 1, 20, 'carsale$Car:create', 1, (select id from sec_role where name = 'Bank_operator'));

-- ограничения для менеджер

insert into sec_permission(id, create_ts, created_by, version, permission_type, target, value, role_id)
values (newid(), current_timestamp, 'admin', 1, 20, 'carsale$Car:create', 1, (select id from sec_role where name = 'Manager'));

insert into sec_permission(id, create_ts, created_by, version, permission_type, target, value, role_id)
values (newid(), current_timestamp, 'admin', 1, 20, 'carsale$Car:update', 1, (select id from sec_role where name = 'Manager'));

insert into sec_permission(id, create_ts, created_by, version, permission_type, target, value, role_id)
values (newid(), current_timestamp, 'admin', 1, 20, 'carsale$Car:delete', 1, (select id from sec_role where name = 'Manager'));

insert into sec_permission(id, create_ts, created_by, version, permission_type, target, value, role_id)
values (newid(), current_timestamp, 'admin', 1, 20, 'carsale$CarPurchase:create', 1, (select id from sec_role where name = 'Manager'));

insert into sec_permission(id, create_ts, created_by, version, permission_type, target, value, role_id)
values (newid(), current_timestamp, 'admin', 1, 20, 'carsale$CarPurchase:update', 1, (select id from sec_role where name = 'Manager'));

insert into sec_permission(id, create_ts, created_by, version, permission_type, target, value, role_id)
values (newid(), current_timestamp, 'admin', 1, 20, 'carsale$CarPurchase:delete', 1, (select id from sec_role where name = 'Manager'));
