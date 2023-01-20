-----script reinitializes database 
\c postgres
drop database encheres5;
create database encheres5;
\c encheres5

create sequence admin_seq;
CREATE TABLE administrateur (
  id SERIAL ,
  refAdmin VARCHAR(5) default 'AD'||TO_CHAR(nextval('admin_seq'),'FM000'),
  email VARCHAR(60),
  mdp VARCHAR(255),
  nom VARCHAR(50),
  PRIMARY KEY (id)
);

create sequence categorie_seq;
CREATE TABLE categorie (
  id SERIAL ,
  refCategorie VARCHAR(10) default 'CT'||TO_CHAR(nextval('categorie_seq'),'FM000'),
  nomCategorie VARCHAR(10),
  PRIMARY KEY (id)
);
CREATE TABLE Categorie_Lot (
  Categorieid int4 ,
  Lotid int4 ,
  PRIMARY KEY (Categorieid, Lotid)
);
create sequence demande_seq;
CREATE TABLE demandeRecharge (
  id SERIAL ,
  refDemande VARCHAR(5) default 'DM'||TO_CHAR(nextval('demande_seq'),'FM000'),
  somme float8,
  utilisateurid int4 ,
  PRIMARY KEY (id)
);
create sequence encheres_seq;
CREATE TABLE enchere (
  id SERIAL ,
  refEnchere VARCHAR(5) default 'EN'||TO_CHAR(nextval('encheres_seq'),'FM000'),
  debut TIMESTAMP,
  duree INTERVAL,
  prixDeMisEnEnchere float8,
  Lotid int4 ,
  commission float8,
  PRIMARY KEY (id)
);
CREATE TABLE historique_commission (
  id serial, daty date default current_date, valeur float8);

create sequence lot_seq;
CREATE TABLE lot (
  id SERIAL ,
  refLot VARCHAR(5) default 'LO'||TO_CHAR(nextval('lot_seq'),'FM000'),
  nomLot VARCHAR(60),
  descriptionLot VARCHAR(100),
  Valeur float8,
  nombre float8,
  Utilisateurid int4 ,
  PRIMARY KEY (id)
);
create sequence mise_seq;
CREATE TABLE mise (
  id SERIAL ,
  refMise VARCHAR(10)default 'MI'||TO_CHAR(nextval('mise_seq'),'FM000'),
  somme float8,
  daty TIMESTAMP,
  utilisateurid int4 ,
  enchereid int4 ,
  PRIMARY KEY (id)
);

CREATE TABLE miseGagnante (
  id SERIAL ,
  miseid int4 ,
  PRIMARY KEY (id)
);
create sequence mouvement_seq;
CREATE TABLE mouvementArgent (
  id SERIAL ,
  refMouvement VARCHAR(5) default 'MV'||TO_CHAR(nextval('mouvement_seq'),'FM000'),
  motif VARCHAR(255),
  demandeRechargeid int4 unique,
  miseGagnanteid int4 ,
  daty TIMESTAMP,
  PRIMARY KEY (id)
);
create sequence user_seq;
CREATE TABLE utilisateur (
  id SERIAL ,
  refUtilisateur VARCHAR(5) default 'UT'||TO_CHAR(nextval('user_seq'),'FM000'),
  email VARCHAR(60),
  mdp VARCHAR(255),
  nom VARCHAR(50),
  prenom VARCHAR(50),
  dateDeNaissance date,
  PRIMARY KEY (id)
);
----- Mialy 
create table TokenAdmin(
    idToken serial PRIMARY KEY,
    token varchar(100),
    id integer,
    dateExpiration Timestamp without time zone,
    FOREIGN KEY(id) REFERENCES administrateur(id) 
);
create table TokenUser(
    idToken serial PRIMARY KEY,
    token varchar(100),
    id integer,
    dateExpiration Timestamp without time zone,
    FOREIGN KEY(id) REFERENCES utilisateur(id) 
);


ALTER TABLE
  Categorie_Lot
ADD
  CONSTRAINT FKCategorie_712021 FOREIGN KEY (Categorieid) REFERENCES categorie (id);

ALTER TABLE
  Categorie_Lot
ADD
  CONSTRAINT FKCategorie_373239 FOREIGN KEY (Lotid) REFERENCES lot (id);

ALTER TABLE
  lot
ADD
  CONSTRAINT FKlot381508 FOREIGN KEY (Utilisateurid) REFERENCES utilisateur (id);

ALTER TABLE
  enchere
ADD
  CONSTRAINT FKenchere856187 FOREIGN KEY (Lotid) REFERENCES lot (id);

ALTER TABLE
  demandeRecharge
ADD
  CONSTRAINT FKenchere123456 FOREIGN KEY (utilisateurid) REFERENCES utilisateur (id);


ALTER TABLE
  mouvementArgent
ADD
  CONSTRAINT FKmouvementA87923 FOREIGN KEY (demandeRechargeid) REFERENCES demandeRecharge (id);

ALTER TABLE
  miseGagnante
ADD
  CONSTRAINT FKmiseGagnan864832 FOREIGN KEY (miseid) REFERENCES mise (id);

ALTER TABLE
  mise
ADD
  CONSTRAINT FKmise99491 FOREIGN KEY (utilisateurid) REFERENCES utilisateur (id);

ALTER TABLE
  mouvementArgent
ADD
  CONSTRAINT FKmouvementA118260 FOREIGN KEY (miseGagnanteid) REFERENCES miseGagnante (id);

ALTER TABLE
  mise
ADD
  CONSTRAINT FKmise163119 FOREIGN KEY (enchereid) REFERENCES enchere (id);


---insert 
insert into administrateur (email, mdp, nom) values ('test@example.com','test', 'testAdmin');

insert into categorie(nomCategorie) values ('vin');
insert into categorie(nomCategorie) values ('peinture');
insert into categorie(nomCategorie) values ('voiture');
insert into categorie(nomCategorie) values ('collection');

insert into utilisateur (email,mdp,nom,prenom,dateDeNaissance) values ('test@example.com','test','testUtilisateur1', 'u1','17/01/1995');
insert into utilisateur (email,mdp,nom,prenom,dateDeNaissance) values ('test2@example.com','test2','testUtilisateur2', 'u2','17/01/1994');
insert into utilisateur (email,mdp,nom,prenom,dateDeNaissance) values ('test3@example.com','test3','testUtilisateur3', 'u3','07/12/1970');
insert into utilisateur (email,mdp,nom,prenom,dateDeNaissance) values ('test4@example.com','test4','testUtilisateur4', 'u4','07/12/1970');

insert into demandeRecharge(somme, utilisateurid) values (500000, 1);
insert into demandeRecharge(somme, utilisateurid) values (4000000, 2);
insert into demandeRecharge(somme, utilisateurid) values (400000, 4);
insert into demandeRecharge(somme, utilisateurid) values (300000, 3);
insert into demandeRecharge(somme, utilisateurid) values (300000, 3);

insert into lot(nomlot, descriptionLot, valeur, nombre, utilisateurid) values ('cageot vin', '20 bouteilles', 200000, 1, 1);
insert into lot(nomlot, descriptionLot, valeur, nombre, utilisateurid) values ('Ford Fiesta', 'Bon état', 2000000, 1, 1);
insert into lot(nomlot, descriptionLot, valeur, nombre, utilisateurid) values ('Subaru imprezza', 'Bon état', 2000000, 1, 1);


insert into enchere (debut, duree, prixDeMisEnEnchere, lotid, commission) values ('10/01/2023 08:00','20 days 2 hours 3 minutes 4 seconds'::interval, 20000,1 ,5);
insert into enchere (debut, duree, prixDeMisEnEnchere, lotid, commission) values ('10/01/2023 08:00','20 days'::interval, 500000,2 ,5);
insert into enchere (debut, duree, prixDeMisEnEnchere, lotid, commission) values ('10/01/2023 08:00','2 days'::interval, 500000,3 ,5);


insert into mise(somme, daty, utilisateurid, enchereid) values (25000, '10/01/2023 08:25', 2, 1 );
insert into mise(somme, daty, utilisateurid, enchereid) values (30000, '10/01/2023 09:25', 3, 1 );
insert into mise(somme, daty, utilisateurid, enchereid) values (35000, '10/01/2023 10:25', 2, 1 );
insert into mise(somme, daty, utilisateurid, enchereid) values (600000, '13/01/2023 19:25', 2, 2 );
insert into mise(somme, daty, utilisateurid, enchereid) values (600000, '13/01/2023 19:25', 2, 3 );

insert into categorie_lot values (1,1);
insert into categorie_lot values (3,2);
insert into categorie_lot values (4,2);

insert into mouvementArgent (motif, demandeRechargeId, daty) values ('init client', 1, current_timestamp);
insert into mouvementArgent (motif, demandeRechargeId, daty) values ('init client', 2, current_timestamp);
insert into mouvementArgent (motif, demandeRechargeId, daty) values ('init client', 3, current_timestamp);
insert into mouvementArgent (motif, demandeRechargeId, daty) values ('init client', 4, current_timestamp);

insert into miseGagnante(daty, miseid) values (current_timestamp, 3);

create or replace view v_derniere_mise as 
WITH last_month AS (
    SELECT date_trunc('month', current_date)AS start_date, date_trunc('month', current_date)  + INTERVAL '1' MONTH  AS end_date
)
SELECT *
FROM mise
JOIN last_month
ON daty BETWEEN start_date AND end_date;


create or replace view v_commission_preleve as 
select enchere.id, enchere.commission*v_derniere_mise.somme/100 as commission from miseGagnante join v_derniere_mise on v_derniere_mise.id=miseGagnante.miseid join enchere on enchere.id=v_derniere_mise.enchereid;

create or replace  view v_moy_commission as 
select avg(v_commission_preleve.commission) as moyenne_commission from v_commission_preleve;

create or replace  view v_depense_moy as 
select utilisateur.*, avg(somme) as somme from miseGagnante join mise on miseGagnante.miseid=mise.id join utilisateur on utilisateur.id=mise.utilisateurid group by utilisateur.id order by somme desc;
----vue de participation en tant que acheteur
create or replace  view v_part_moy as 
select count(id) as nbParticipation,utilisateurid from v_derniere_mise group by utilisateurid;
create or replace view v_participation_moy as
select utilisateur.*,  CAST(nbParticipation AS INT) as nbParticipation from utilisateur join v_part_moy on v_part_moy.utilisateurid=utilisateur.id order by nbParticipation desc;
----vue de participation en tant que vendeur
create or replace  view v_enchere_moy as 
select count(enchere.id) as isa, lot.utilisateurid from enchere join lot on lot.id=enchere.LotId group by lot.utilisateurid;
create or replace view v_lot_owner_nb as 
select utilisateur.*, CAST(isa AS INT) as isa from utilisateur join v_enchere_moy on v_enchere_moy.utilisateurid=utilisateur.id order by isa desc;

--vue nombre de participation par enchere
create view v_participation_miseur as 
select enchereid, count(utilisateurid) as isa from v_derniere_mise group by utilisateurid, enchereid;
create or replace  view v_nb_miseur_par_enchere as
select enchereid,count(isa) as isa from v_participation_miseur group by enchereid;
create or replace view full_v_nb_miseur_par_enchere as
select enchere.*, CAST(isa AS INT) as isa from enchere join v_nb_miseur_par_enchere on enchere.id=v_nb_miseur_par_enchere.enchereid order by isa desc;
--vue nombre de mise par enchere
create or replace  view v_nb_mise_par_enchere as
select enchereid,count(id) as isa from v_derniere_mise group by enchereid;
create or replace view full_v_nb_mise_par_enchere as
select enchere.*, CAST(isa AS INT) as isa from enchere join v_nb_mise_par_enchere on enchere.id=v_nb_mise_par_enchere.enchereid order by isa desc;

--vue tendance catégorie
create or replace view v_trends_categorie as
select categorieid, sum(v_nb_mise_par_enchere.isa) as nbMise from categorie_lot join enchere on enchere.lotid=categorie_lot.lotid join v_nb_mise_par_enchere on v_nb_mise_par_enchere.enchereid=enchere.id group by categorieid;
create or replace view full_v_trends as 
select categorie.*, CAST(nbMise AS INT) as nbMise from categorie join v_trends_categorie on categorie.id=v_trends_categorie.categorieid order by nbMise desc;


create view v_month_enchere_nb as
WITH current_month AS (
    SELECT date_trunc('month', current_date) AS start_date, date_trunc('month', current_date) + INTERVAL '1 month' - INTERVAL '1 day' AS end_date
)
SELECT CAST(COUNT(*) AS INT) as isa 
FROM enchere
JOIN current_month
ON debut BETWEEN start_date AND end_date;

create view v_month_enchere as
WITH current_month AS (
    SELECT date_trunc('month', current_date) AS start_date, date_trunc('month', current_date) + INTERVAL '1 month' - INTERVAL '1 day' AS end_date
)
SELECT *
FROM enchere
JOIN current_month
ON debut BETWEEN start_date AND end_date;

create or replace view v_today_enchere_nb as
SELECT CAST(COUNT(*) AS INT) as isa
FROM enchere
WHERE date(debut) = current_date;

create or replace view v_today_enchere as
SELECT * 
FROM enchere
WHERE date(debut) = current_date;

create or replace view v_enchere_en_cours as 
SELECT *
FROM enchere
WHERE debut + (duree)::interval > current_timestamp and id not in (select enchereid from misegagnante join mise on mise.id =misegagnante.miseid);

create or replace view v_enchere_en_cours_nb as select CAST(COUNT(id) AS INT) as isa from v_enchere_en_cours;

create or replace view v_vola_miditra as 
select mouvementArgent.id, mouvementArgent.refmouvement, mouvementArgent.motif, mouvementArgent.demanderechargeid as idMotif, mouvementArgent.daty, demandeRecharge.somme, demandeRecharge.utilisateurid from mouvementArgent join demandeRecharge on demandeRecharge.id=mouvementArgent.demanderechargeid where demandeRechargeId is not null;

create or replace view v_vola_mivoaka as 
select mouvementArgent.id, mouvementArgent.refMouvement, mouvementArgent.motif, mouvementArgent.miseGagnanteid as idMotif, mouvementArgent.daty, somme, utilisateurid from mouvementArgent join misegagnante on misegagnante.id=mouvementArgent.misegagnanteid join mise on mise.id=misegagnante.miseid where misegagnanteid is not null;

create view v_enchere_tokn_vitaina as 
SELECT *
FROM enchere 
WHERE debut + duree < CURRENT_TIMESTAMP and id not in ( select enchereid from miseGagnante join mise on mise.id =misegagnante.miseid);

create view v_historique_mouvement as
select * from v_vola_miditra union select * from v_vola_mivoaka where daty > NOW() - INTERVAL '3 weeks' order by daty desc;

create view v_depense_utilisateur as 
select utilisateurid, sum(somme) as depense from v_vola_mivoaka group by utilisateurid;

create view v_entree_utilisateur as 
select utilisateurid, sum(somme) as entree from v_vola_miditra group by utilisateurid;

create view v_solde_user as
SELECT utilisateur.*,
-COALESCE(v_depense_utilisateur.depense, 0) +COALESCE(v_entree_utilisateur.entree, 0) as solde
FROM utilisateur
LEFT JOIN v_depense_utilisateur
ON utilisateur.id = v_depense_utilisateur.utilisateurid
LEFT JOIN v_entree_utilisateur
ON utilisateur.id = v_entree_utilisateur.utilisateurid;

create or replace view v_categorie_lot as 
select categorie.id as idcat, refcategorie, nomcategorie, lot.* from categorie_lot join categorie on categorie.id=categorie_lot.categorieid join lot on lot.id=categorie_lot.lotid;
-- create view v_enchere_lot as
-- select enchere.*, lot.reflot, lot.nomlot, lot.descriptionLot, lot.valeur, lot.nombre, lot.utilisateurid  from enchere join lot on lot.id=enchere.lotid;

-- create view full_v_enchere_en_cours as 
-- select * from v_enchere_lot where id in (select id from v_enchere_en_cours);

Update administrateur set mdp='098f6bcd4621d373cade4e832627b4f6' where email='test@example.com';
Update utilisateur set mdp='098f6bcd4621d373cade4e832627b4f6' where email='test@example.com';
Update utilisateur set mdp='ad0234829205b9033196ba818f7a872b' where email='test2@example.com';
Update utilisateur set mdp='8ad8757baa8564dc136c1e07507f4a98' where email='test3@example.com';
Update utilisateur set mdp='86985e105f79b95d6bc918fb45ec7727' where email='test4@example.com';

-- public.v_mise_max source

CREATE OR REPLACE VIEW public.v_mise_max
AS SELECT enchere.id AS idenchere,
    max(mise.somme) AS maxmise
   FROM enchere
     JOIN mise ON mise.enchereid = enchere.id
  GROUP BY enchere.id;

-- public.demanderecharge_non_valider source

CREATE OR REPLACE VIEW public.demanderecharge_non_valider
AS SELECT demanderecharge.id,
    demanderecharge.refdemande,
    demanderecharge.somme,
    utilisateur.id AS idutilisateur,
    utilisateur.refutilisateur,
    utilisateur.nom,
    utilisateur.prenom
   FROM demanderecharge
     JOIN utilisateur ON utilisateur.id = demanderecharge.utilisateurid
  WHERE NOT (demanderecharge.id IN ( SELECT mouvementargent.demanderechargeid
           FROM mouvementargent));

create view full_v_enchere_en_cours as
select v_enchere_en_cours.*, maxmise, utilisateurid from v_enchere_en_cours join v_mise_max on v_mise_max.idenchere=v_enchere_en_cours.id join mise on mise.somme=v_mise_max.maxmise and v_enchere_en_cours.id=mise.enchereid;

create or replace view full_v_enchere_tokn_vitaina as
select v_enchere_tokn_vitaina.*, maxmise, utilisateurid, mise.id as miseid from V_enchere_tokn_vitaina join v_mise_max on v_mise_max.idenchere=v_enchere_tokn_vitaina.id join mise on mise.somme=v_mise_max.maxmise and v_enchere_tokn_vitaina.id=mise.enchereid;
create view v_enchere_vita as
select enchereid from miseGagnante join mise on mise.id=miseid join enchere on enchere.id=enchereid group by enchereid;

CREATE OR REPLACE VIEW v_enchere_status AS
SELECT enchere.id, enchere.refEnchere, enchere.debut, enchere.duree, enchere.prixDeMisEnEnchere, enchere.Lotid, enchere.commission,
CASE
    WHEN enchere.id IN (SELECT id FROM v_enchere_vita) AND enchere.debut + enchere.duree < NOW() THEN 'finished'
    WHEN enchere.id NOT IN (SELECT id FROM v_enchere_vita) AND enchere.debut + enchere.duree < NOW() THEN 'to be finished'
    WHEN enchere.debut + enchere.duree > NOW() AND enchere.debut < NOW() THEN 'going on'
    ELSE 'did not begin'
END AS status
FROM enchere;

create or replace view full_v_enchere_statut as 
select v_enchere_status.*, utilisateurid from (select utilisateurid, enchereid from mise group by utilisateurid, enchereid) as mise join v_enchere_status on enchereid=v_enchere_status.id;