-- public.administrator_id_seq definition

-- DROP SEQUENCE public.administrator_id_seq;

CREATE SEQUENCE public.administrator_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 2147483647
	START 1
	CACHE 1
	NO CYCLE;


-- public.administrator_seq definition

-- DROP SEQUENCE public.administrator_seq;

CREATE SEQUENCE public.administrator_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;


-- public.assurance_id_seq definition

-- DROP SEQUENCE public.assurance_id_seq;

CREATE SEQUENCE public.assurance_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 2147483647
	START 1
	CACHE 1
	NO CYCLE;


-- public.assurance_seq definition

-- DROP SEQUENCE public.assurance_seq;

CREATE SEQUENCE public.assurance_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;


-- public.entretien_id_seq definition

-- DROP SEQUENCE public.entretien_id_seq;

CREATE SEQUENCE public.entretien_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 2147483647
	START 1
	CACHE 1
	NO CYCLE;


-- public.entretien_seq definition

-- DROP SEQUENCE public.entretien_seq;

CREATE SEQUENCE public.entretien_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;


-- public.marque_id_seq definition

-- DROP SEQUENCE public.marque_id_seq;

CREATE SEQUENCE public.marque_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 2147483647
	START 1
	CACHE 1
	NO CYCLE;


-- public.marque_seq definition

-- DROP SEQUENCE public.marque_seq;

CREATE SEQUENCE public.marque_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;


-- public.modele_id_seq definition

-- DROP SEQUENCE public.modele_id_seq;

CREATE SEQUENCE public.modele_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 2147483647
	START 1
	CACHE 1
	NO CYCLE;


-- public.modele_seq definition

-- DROP SEQUENCE public.modele_seq;

CREATE SEQUENCE public.modele_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;


-- public.token_idtoken_seq definition

-- DROP SEQUENCE public.token_idtoken_seq;

CREATE SEQUENCE public.token_idtoken_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 2147483647
	START 1
	CACHE 1
	NO CYCLE;


-- public.token_seq definition

-- DROP SEQUENCE public.token_seq;

CREATE SEQUENCE public.token_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;


-- public.typeassurance_id_seq definition

-- DROP SEQUENCE public.typeassurance_id_seq;

CREATE SEQUENCE public.typeassurance_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 2147483647
	START 1
	CACHE 1
	NO CYCLE;


-- public.typeassurance_seq definition

-- DROP SEQUENCE public.typeassurance_seq;

CREATE SEQUENCE public.typeassurance_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;


-- public.typeentretien_id_seq definition

-- DROP SEQUENCE public.typeentretien_id_seq;

CREATE SEQUENCE public.typeentretien_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 2147483647
	START 1
	CACHE 1
	NO CYCLE;


-- public.typeentretien_seq definition

-- DROP SEQUENCE public.typeentretien_seq;

CREATE SEQUENCE public.typeentretien_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;


-- public.avion_id_seq definition

-- DROP SEQUENCE public.avion_id_seq;

CREATE SEQUENCE public.avion_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 2147483647
	START 1
	CACHE 1
	NO CYCLE;


-- public.avion_seq definition

-- DROP SEQUENCE public.avion_seq;

CREATE SEQUENCE public.avion_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;

-- public.administrator definition

-- Drop table

-- DROP TABLE administrator;

CREATE TABLE administrator (
	id serial4 NOT NULL,
	idadmin varchar(4) NOT NULL DEFAULT (('AD'::text || nextval('administrator_seq'::regclass))),
	identifiant varchar(50) NOT NULL,
	motdepasse varchar(50) NULL,
	CONSTRAINT administrator_pkey PRIMARY KEY (idadmin)
);


-- public.marque definition

-- Drop table

-- DROP TABLE marque;

CREATE TABLE marque (
	id serial4 NOT NULL,
	idmarque varchar(5) NOT NULL DEFAULT (('MK'::text || nextval('marque_seq'::regclass))),
	nommarque varchar(50) NOT NULL,
	CONSTRAINT marque_pkey PRIMARY KEY (idmarque)
);


-- public.typeassurance definition

-- Drop table

-- DROP TABLE typeassurance;

CREATE TABLE typeassurance (
	id serial4 NOT NULL,
	idtypeassurance varchar(5) NOT NULL DEFAULT (('TA'::text || nextval('typeassurance_seq'::regclass))),
	intitule varchar(50) NOT NULL,
	CONSTRAINT typeassurance_pkey PRIMARY KEY (idtypeassurance)
);


-- public.typeentretien definition

-- Drop table

-- DROP TABLE typeentretien;

CREATE TABLE typeentretien (
	id serial4 NOT NULL,
	idtype varchar(5) NOT NULL DEFAULT (('TE'::text || nextval('typeentretien_seq'::regclass))),
	intitule varchar(40) NOT NULL,
	CONSTRAINT typeentretien_pkey PRIMARY KEY (idtype)
);


-- public.modele definition

-- Drop table

-- DROP TABLE modele;

CREATE TABLE modele (
	id serial4 NOT NULL,
	idmodele varchar(10) NOT NULL DEFAULT (('MOD'::text || nextval('modele_seq'::regclass))),
	marque varchar(5) NULL,
	nommodele varchar(50) NULL,
	CONSTRAINT modele_pkey PRIMARY KEY (idmodele),
	CONSTRAINT modele_marque_fkey FOREIGN KEY (marque) REFERENCES marque(idmarque)
);


-- public."token" definition

-- Drop table

-- DROP TABLE "token";

CREATE TABLE "token" (
	idtoken serial4 NOT NULL,
	"token" varchar(50) NULL,
	idadmin varchar(50) NULL,
	dateexpiration timestamp NULL,
	CONSTRAINT token_pkey PRIMARY KEY (idtoken),
	CONSTRAINT token_idadmin_fkey FOREIGN KEY (idadmin) REFERENCES administrator(idadmin)
);


-- public.avion definition

-- Drop table

-- DROP TABLE avion;

CREATE TABLE avion (
	id serial4 NOT NULL,
	idavion varchar(10) NOT NULL DEFAULT (('AV'::text || nextval('avion_seq'::regclass))),
	numeroavion varchar(10) NOT NULL,
	modele varchar(10) NULL,
    photo text NULL,
	CONSTRAINT avion_pkey PRIMARY KEY (idavion),
	CONSTRAINT avion_modele_fkey FOREIGN KEY (modele) REFERENCES modele(idmodele)
);


-- public.assurance definition

-- Drop table

-- DROP TABLE assurance;

CREATE TABLE assurance (
	id serial4 NOT NULL,
	idassurance varchar(5) NOT NULL DEFAULT (('AS'::text || nextval('assurance_seq'::regclass))),
	debut date NULL,
	fin date NULL,
	idavion varchar(10) NULL,
	prix int4 NULL,
	idtypeassurance varchar(5) NULL,
	CONSTRAINT assurance_pkey PRIMARY KEY (idassurance),
	CONSTRAINT assurance_prix_check CHECK ((prix >= 0)),
	CONSTRAINT assurance_idtypeassurance_fkey FOREIGN KEY (idtypeassurance) REFERENCES typeassurance(idtypeassurance),
	CONSTRAINT assurance_idavion_fkey FOREIGN KEY (idavion) REFERENCES avion(idavion)
);


-- public.entretien definition

-- Drop table

-- DROP TABLE entretien;

CREATE TABLE entretien (
	id serial4 NOT NULL,
	identretien varchar(10) NOT NULL DEFAULT (('ETV'::text || nextval('entretien_seq'::regclass))),
	idavion varchar(10) NULL,
	dateentretien date NULL DEFAULT CURRENT_DATE,
	idtype varchar(5) NULL,
	CONSTRAINT entretien_pkey PRIMARY KEY (identretien),
	CONSTRAINT entretien_idtype_fkey FOREIGN KEY (idtype) REFERENCES typeentretien(idtype),
	CONSTRAINT entretien_idavion_fkey FOREIGN KEY (idavion) REFERENCES avion(idavion)
);


-- public.kilometrage definition

-- Drop table

-- DROP TABLE kilometrage;

CREATE TABLE kilometrage (
	idavion varchar(10) NULL,
	daty date NULL,
	debut float8 NULL,
	fin float8 NULL,
	CONSTRAINT kilometrage_idavion_fkey FOREIGN KEY (idavion) REFERENCES avion(idavion)
);
-- public.v_insurance_expiration source

CREATE OR REPLACE VIEW public.v_insurance_expiration
AS SELECT assurance.idavion,
    max(assurance.fin) AS finassurance
   FROM assurance
  GROUP BY assurance.idavion;

-- public.v_car_ins source

CREATE OR REPLACE VIEW public.v_car_ins
AS SELECT avion.id,
    avion.idavion,
    avion.numeroavion,
    avion.modele,
    v_insurance_expiration.finassurance
   FROM v_insurance_expiration
     JOIN avion ON avion.idavion::text = v_insurance_expiration.idavion::text;


-- public.v_car_ins_del source

CREATE OR REPLACE VIEW public.v_car_ins_del
AS SELECT v_car_ins.id,
    v_car_ins.idavion,
    v_car_ins.numeroavion,
    modele.nommodele,
    marque.nommarque,
    v_car_ins.finassurance,
    ( SELECT date_part('epoch'::text, v_car_ins.finassurance::timestamp with time zone - CURRENT_TIMESTAMP) / 2592000::double precision) AS diff
   FROM v_car_ins
     JOIN modele ON modele.idmodele::text = v_car_ins.modele::text
     JOIN marque ON marque.idmarque::text = modele.marque::text;

CREATE OR REPLACE VIEW public.v_modele 
AS 
SELECT modele.idmodele,modele.nommodele,
marque.idmarque ,marque.nommarque
from modele
join marque
on 
modele.marque = idmarque 
;

CREATE OR REPLACE VIEW public.v_vehicule
AS SELECT avion.idavion,
    avion.numeroavion,
    v_modele.idmodele,
    v_modele.nommodele,
    v_modele.idmarque,
    v_modele.nommarque,
	avion.photo
   FROM avion
     JOIN v_modele on avion.modele=v_modele.idmodele;


insert into administrator (identifiant,motdepasse) values ('koto','koto');
insert into administrator(identifiant,motdepasse) values('admin','admin');
insert into administrator(identifiant,motdepasse) values('admin1','admin1');
insert into marque(nommarque) values('BOEING');

insert into modele(marque, nommodele) values('MK1','737');
insert into avion(numeroavion, modele) values ('BEFJ7858','MOD1');
insert into typeassurance (intitule) values ('tiers');
insert into assurance(debut,fin,idavion,prix,idtypeassurance) values ('2022-12-13','2023-02-13','AV1',30000,'TA1');

insert into kilometrage values ('AV1','13/11/2022',550,580);
insert into kilometrage values ('AV1','14/11/2022',580,650);
insert into kilometrage values ('AV1','15/11/2022',650,690);

-- public.v_kilometrage source

CREATE OR REPLACE VIEW public.v_kilometrage
AS SELECT kilometrage.daty,
    kilometrage.debut,
    kilometrage.fin,
    v_vehicule.idavion,
    v_vehicule.numeroavion,
    v_vehicule.idmodele,
    v_vehicule.nommodele,
    v_vehicule.idmarque,
    v_vehicule.nommarque,
	v_vehicule.photo
   FROM v_vehicule
     JOIN kilometrage ON v_vehicule.idavion::text = kilometrage.idavion::text;
