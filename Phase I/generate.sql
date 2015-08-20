

CREATE TABLE College
  (
    name VARCHAR (50) ,
    id   VARCHAR (5) NOT NULL
  ) ;
ALTER TABLE College ADD CONSTRAINT College_PK PRIMARY KEY ( id ) ;


CREATE TABLE Course
  (
    "number"   numeric(4) NOT NULL ,
    title      VARCHAR (50),
    "level"    numeric(3) ,
    Major_code VARCHAR (4)NOT NULL
  ) ;
ALTER TABLE Course ADD CONSTRAINT Course_PK PRIMARY KEY ( "number" ) ;


CREATE TABLE Department
  (
    name       VARCHAR (50),
    id         VARCHAR (5)NOT NULL ,
    College_id VARCHAR (5)NOT NULL
  ) ;
ALTER TABLE Department ADD CONSTRAINT Department_PK PRIMARY KEY ( id ) ;


CREATE TABLE Enrollment
  (
    reg_date                DATE ,
    Student_id              numeric(9) NOT NULL ,
    Section_refrence_number VARCHAR (20)NOT NULL
  ) ;
ALTER TABLE Enrollment ADD CONSTRAINT Enrollment_PK PRIMARY KEY ( Student_id, Section_refrence_number ) ;


CREATE TABLE Grading_Component
  (
    id            numeric(3) NOT NULL ,
    name          VARCHAR (15),
    max_points    numeric(2) ,
    weight        numeric(2) ,
    Course_number numeric(4) NOT NULL ,
    Instructor_id numeric(9) NOT NULL
  ) ;
ALTER TABLE Grading_Component ADD CONSTRAINT Grading_Component_PK PRIMARY KEY ( id ) ;


CREATE TABLE Instructor
  (
    id         numeric(9) NOT NULL ,
    first_name VARCHAR(15),
    last_name  VARCHAR (15)
  ) ;
ALTER TABLE Instructor ADD CONSTRAINT Instructor_PK PRIMARY KEY ( id ) ;


CREATE TABLE Major
  (
    name          VARCHAR(50),
    code          VARCHAR (4) NOT NULL ,
    Department_id VARCHAR (5) NOT NULL
  ) ;
ALTER TABLE Major ADD CONSTRAINT Major_PK PRIMARY KEY ( code ) ;


CREATE TABLE Point
  (
    earned_Points              numeric(3) ,
    Grading_Component_id       numeric(3) NOT NULL ,
    Enrollment_Student_id      numeric(9) NOT NULL ,
    Enrollment_refrence_number VARCHAR(20) NOT NULL
  ) ;


CREATE TABLE Section
  (
    refrence_number VARCHAR (20) NOT NULL ,
    "number"        numeric(2) ,
    Instructor_id   numeric(9) NOT NULL ,
    Course_number   numeric(4) NOT NULL
  ) ;
ALTER TABLE Section ADD CONSTRAINT Section_PK PRIMARY KEY ( refrence_number ) ;


CREATE TABLE Student
  (
    id         numeric(9) NOT NULL ,
    first_name VARCHAR (15),
    last_name  VARCHAR (15),
    Major_code VARCHAR (4)NOT NULL
  ) ;
ALTER TABLE Student ADD CONSTRAINT Student_PK PRIMARY KEY ( id ) ;


ALTER TABLE Course ADD CONSTRAINT Course_Major_FK FOREIGN KEY ( Major_code ) REFERENCES Major ( code ) ON
DELETE CASCADE ;

ALTER TABLE Department ADD CONSTRAINT Department_College_FK FOREIGN KEY ( College_id ) REFERENCES College ( id ) ON
DELETE CASCADE ;

ALTER TABLE Enrollment ADD CONSTRAINT Enrollment_Section_FK FOREIGN KEY ( Section_refrence_number ) REFERENCES Section ( refrence_number ) ON
DELETE CASCADE ;

ALTER TABLE Enrollment ADD CONSTRAINT Enrollment_Student_FK FOREIGN KEY ( Student_id ) REFERENCES Student ( id ) ;

ALTER TABLE Grading_Component ADD CONSTRAINT Grading_Comp_Course_FK FOREIGN KEY ( Course_number ) REFERENCES Course ( "number" ) ON
DELETE CASCADE ;

ALTER TABLE Grading_Component ADD CONSTRAINT Grading_Comp_Ins_FK FOREIGN KEY ( Instructor_id ) REFERENCES Instructor ( id ) ON
DELETE CASCADE ;

ALTER TABLE Major ADD CONSTRAINT Major_Department_FK FOREIGN KEY ( Department_id ) REFERENCES Department ( id ) ;

ALTER TABLE Point ADD CONSTRAINT Point_Enroll_FK FOREIGN KEY ( Enrollment_Student_id, Enrollment_refrence_number ) REFERENCES Enrollment ( Student_id, Section_refrence_number ) ON
DELETE CASCADE ;

ALTER TABLE Point ADD CONSTRAINT Point_Grading_Comp_FK FOREIGN KEY ( Grading_Component_id ) REFERENCES Grading_Component ( id ) ON
DELETE CASCADE ;

ALTER TABLE Section ADD CONSTRAINT Sec_Course_FK FOREIGN KEY ( Course_number ) REFERENCES Course ( "number" ) ON
DELETE CASCADE ;

ALTER TABLE Section ADD CONSTRAINT Sec_Instr_FK FOREIGN KEY ( Instructor_id ) REFERENCES Instructor ( id ) ;

ALTER TABLE Student ADD CONSTRAINT Student_Major_FK FOREIGN KEY ( Major_code ) REFERENCES Major ( code ) ;



