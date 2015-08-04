-- Generated by Oracle SQL Developer Data Modeler 4.1.1.888
--   at:        2015-07-28 08:25:42 AST
--   site:      Oracle Database 12c
--   type:      Oracle Database 12c




CREATE TABLE College
  (
    id           VARCHAR(1) NOT NULL ,
    name         VARCHAR(50) ,
    abbreviation VARCHAR (6)
  ) ;
ALTER TABLE College ADD CONSTRAINT College_PK PRIMARY KEY ( id ) ;


CREATE TABLE Course
  (
    "number"   NUMERIC(4) NOT NULL ,
    title      VARCHAR (50) ,
    "level"    NUMERIC(3) ,
    Major_Code VARCHAR(5) NOT NULL
  ) ;
ALTER TABLE Course ADD CONSTRAINT Course_PK PRIMARY KEY ( "number" ) ;


CREATE TABLE Department
  (
    id           VARCHAR(1) NOT NULL ,
    name         VARCHAR (50) ,
    abbreviation VARCHAR(5) ,
    College_id   VARCHAR(1) NOT NULL
  ) ;
ALTER TABLE Department ADD CONSTRAINT Department_PK PRIMARY KEY ( id ) ;


CREATE TABLE Enrollment
  (
    registration_date        DATE NOT NULL ,
    Student_id               NUMERIC(9) NOT NULL ,
    Section_reference_number VARCHAR(6) NOT NULL
  ) ;
ALTER TABLE Enrollment ADD CONSTRAINT Enrollment_PK PRIMARY KEY ( Section_reference_number, Student_id ) ;


CREATE TABLE Grading_Component
  (
    id             NUMERIC(3) NOT NULL ,
    name           VARCHAR(15) ,
    maximum_points VARCHAR (3) ,
    weight         NUMERIC(2) ,
    Course_number  NUMERIC(4) NOT NULL ,
    Instructor_id  NUMERIC(9) NOT NULL
  ) ;
ALTER TABLE Grading_Component ADD CONSTRAINT Grading_Component_PK PRIMARY KEY ( id ) ;


CREATE TABLE Instructor
  (
    id         NUMERIC(9) NOT NULL ,
    first_name VARCHAR (15) ,
    last_name  VARCHAR (15)
  ) ;
ALTER TABLE Instructor ADD CONSTRAINT Instructor_PK PRIMARY KEY ( id ) ;


CREATE TABLE Major
  (
    Name          VARCHAR (20) ,
    Code          VARCHAR(5) NOT NULL ,
    Department_id VARCHAR (1) NOT NULL
  ) ;
ALTER TABLE Major ADD CONSTRAINT Major_PK PRIMARY KEY ( Code ) ;


CREATE TABLE Point
  (
    Grading_Component_id     NUMERIC(3) NOT NULL ,
    earned_points            NUMERIC(3) ,
    Student_id               NUMERIC(9) NOT NULL ,
    Section_reference_number VARCHAR (6) NOT NULL
  ) ;
ALTER TABLE Point ADD CONSTRAINT Point_PK PRIMARY KEY ( Section_reference_number, Grading_Component_id, Student_id ) ;


CREATE TABLE Section
  (
    reference_number VARCHAR (6) NOT NULL ,
    section_number   NUMERIC(2) ,
    Instructor_id    NUMERIC(9) NOT NULL ,
    Course_number    NUMERIC(4) NOT NULL
  ) ;
ALTER TABLE Section ADD CONSTRAINT Section_PK PRIMARY KEY ( reference_number ) ;


CREATE TABLE Student
  (
    id         NUMERIC(9) NOT NULL ,
    first_name VARCHAR (15) ,
    last_name  VARCHAR (15) ,
    Major_Code VARCHAR (5) NOT NULL
  ) ;
ALTER TABLE Student ADD CONSTRAINT Student_PK PRIMARY KEY ( id ) ;


ALTER TABLE Course ADD CONSTRAINT Course_Major_FK FOREIGN KEY ( Major_Code ) REFERENCES Major ( Code ) ;

ALTER TABLE Department ADD CONSTRAINT Department_College_FK FOREIGN KEY ( College_id ) REFERENCES College ( id ) ;

ALTER TABLE Enrollment ADD CONSTRAINT Enrollment_Sec_FK FOREIGN KEY ( Section_reference_number ) REFERENCES Section ( reference_number ) ;

ALTER TABLE Enrollment ADD CONSTRAINT Enrollment_Stu_FK FOREIGN KEY ( Student_id ) REFERENCES Student ( id ) ;

ALTER TABLE Grading_Component ADD CONSTRAINT Grading_Comp_Course_FK FOREIGN KEY ( Course_number ) REFERENCES Course ( "number" ) ;

ALTER TABLE Grading_Component ADD CONSTRAINT Grading_Comp_Inst_FK FOREIGN KEY ( Instructor_id ) REFERENCES Instructor ( id ) ;

ALTER TABLE Major ADD CONSTRAINT Major_Department_FK FOREIGN KEY ( Department_id ) REFERENCES Department ( id ) ;

ALTER TABLE Point ADD CONSTRAINT Point_Enrollment_FK FOREIGN KEY ( Section_reference_number, Student_id ) REFERENCES Enrollment ( Section_reference_number, Student_id ) ;

ALTER TABLE Point ADD CONSTRAINT Point_Grading_Component_FK FOREIGN KEY ( Grading_Component_id ) REFERENCES Grading_Component ( id ) ;

ALTER TABLE Section ADD CONSTRAINT Sec_Course_FK FOREIGN KEY ( Course_number ) REFERENCES Course ( "number" ) ;

ALTER TABLE Section ADD CONSTRAINT Sec_Ins_FK FOREIGN KEY ( Instructor_id ) REFERENCES Instructor ( id ) ;

ALTER TABLE Student ADD CONSTRAINT Student_Major_FK FOREIGN KEY ( Major_Code ) REFERENCES Major ( Code ) ;


-- Oracle SQL Developer Data Modeler Summary Report: 
-- 
-- CREATE TABLE                            10
-- CREATE INDEX                             0
-- ALTER TABLE                             22
-- CREATE VIEW                              0
-- ALTER VIEW                               0
-- CREATE PACKAGE                           0
-- CREATE PACKAGE BODY                      0
-- CREATE PROCEDURE                         0
-- CREATE FUNCTION                          0
-- CREATE TRIGGER                           0
-- ALTER TRIGGER                            0
-- CREATE COLLECTION TYPE                   0
-- CREATE STRUCTURED TYPE                   0
-- CREATE STRUCTURED TYPE BODY              0
-- CREATE CLUSTER                           0
-- CREATE CONTEXT                           0
-- CREATE DATABASE                          0
-- CREATE DIMENSION                         0
-- CREATE DIRECTORY                         0
-- CREATE DISK GROUP                        0
-- CREATE ROLE                              0
-- CREATE ROLLBACK SEGMENT                  0
-- CREATE SEQUENCE                          0
-- CREATE MATERIALIZED VIEW                 0
-- CREATE SYNONYM                           0
-- CREATE TABLESPACE                        0
-- CREATE USER                              0
-- 
-- DROP TABLESPACE                          0
-- DROP DATABASE                            0
-- 
-- REDACTION POLICY                         0
-- TSDP POLICY                              0
-- 
-- ORDS DROP SCHEMA                         0
-- ORDS ENABLE SCHEMA                       0
-- ORDS ENABLE OBJECT                       0
-- 
-- ERRORS                                   0
-- WARNINGS                                 0