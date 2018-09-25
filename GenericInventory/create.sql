DROP TABLE attribute CASCADE CONSTRAINTS;
DROP TABLE object CASCADE CONSTRAINTS;
DROP TABLE object_param CASCADE CONSTRAINTS;
DROP TABLE object_param_primary CASCADE CONSTRAINTS;
DROP TABLE object_references CASCADE CONSTRAINTS;
DROP TABLE object_type CASCADE CONSTRAINTS;
DROP TABLE objecttype_struct CASCADE CONSTRAINTS;
DROP sequence hibernate_sequence;
/* Formatted on 29.04.2016 15:26:50 (QP5 v5.185.11230.41888) */
CREATE TABLE attribute
  (
    attribute_id   NUMBER (19, 0) NOT NULL,
    objecttype_id  NUMBER (19, 0),
    attribute_type VARCHAR2 (255 CHAR),
    name           VARCHAR2 (255 CHAR),
    version        NUMBER (19, 0),
    CONSTRAINT PK_ATTRIBUTE_ID PRIMARY KEY (attribute_id)
  );
CREATE TABLE object
  (
    object_id       NUMBER (19, 0) NOT NULL,
    parent_id       NUMBER (19, 0),
    object_class_id NUMBER (19, 0),
    object_type_id  NUMBER (19, 0),
    name            VARCHAR2 (255 CHAR),
    version         NUMBER (19, 0),
    CONSTRAINT PK_OBJECT_ID PRIMARY KEY (object_id)
  );
CREATE TABLE object_param
  (
    object_id    NUMBER (19, 0) NOT NULL,
    attribute_id NUMBER (19, 0),
    VALUE        VARCHAR2 (255 CHAR),
    CONSTRAINT PK_PARAM PRIMARY KEY (object_id, attribute_id)
  );
CREATE TABLE object_param_primary
  (
    object_id    NUMBER (19, 0) NOT NULL,
    attribute_id NUMBER (19, 0),
    VALUE        VARCHAR2 (255 CHAR),
    CONSTRAINT PK_PRIMARY_PARAM PRIMARY KEY (object_id, attribute_id)
  );
CREATE TABLE object_references
  (
    object_id            NUMBER (19, 0) NOT NULL,
    referenced_object_id NUMBER (19, 0) NOT NULL
  );
CREATE TABLE object_type
  (
    objecttype_id   NUMBER (19, 0) NOT NULL,
    name            VARCHAR2 (255 CHAR),
    object_class    VARCHAR2 (255 CHAR),
    object_class_id NUMBER (19, 0),
    parent_id       NUMBER (19, 0),
    version         NUMBER (19, 0) NOT NULL,
    CONSTRAINT PK_OBJECTTYPE_ID PRIMARY KEY (objecttype_id)
  );

CREATE TABLE objecttype_struct
  (
    objecttype_id    NUMBER(19,0) NOT NULL,
	    related_type_id  NUMBER(19,0),
		    related_class_id NUMBER(19,0),
			    object_class     VARCHAR2(255 CHAR),
    geometry         VARCHAR2(255 CHAR)
  );

CREATE INDEX IDX_OBJECT_TYPE_ID ON object
  (object_type_id
  );
CREATE INDEX IDX_OBJECT_PARENT_ID ON object
  (parent_id
  );
CREATE INDEX IDX_OBJECT_ID ON object_param
  (object_id
  );
ALTER TABLE object_param_primary ADD CONSTRAINT R_UNIQUE_VALUE UNIQUE (VALUE);
CREATE INDEX IDX_PARAM_PRIMARY_OBJECT_ID ON object_param_primary
  (object_id
  );
ALTER TABLE object_references ADD CONSTRAINT R_UNIQUE_REF UNIQUE (object_id, referenced_object_id);
CREATE INDEX IDX_TYPE_PARENT_ID ON object_type
  (parent_id
  );
ALTER TABLE attribute ADD CONSTRAINT FK_ATTRIBUTE FOREIGN KEY (objecttype_id) REFERENCES object_type ON
DELETE CASCADE;
ALTER TABLE object ADD CONSTRAINT FK_PARENT_OBJECT FOREIGN KEY (parent_id) REFERENCES object ON
DELETE CASCADE;
ALTER TABLE object_param ADD CONSTRAINT FK_PARAM FOREIGN KEY (object_id) REFERENCES object ON
DELETE CASCADE;
ALTER TABLE object_param_primary ADD CONSTRAINT FK_PARAM_PRIMARY FOREIGN KEY (object_id) REFERENCES object ON
DELETE CASCADE;
ALTER TABLE object_references ADD CONSTRAINT FK_REF_OBJECT FOREIGN KEY (referenced_object_id) REFERENCES object;
ALTER TABLE object_references ADD CONSTRAINT FK_OBJECT FOREIGN KEY (object_id) REFERENCES object;
ALTER TABLE object_type ADD CONSTRAINT FK_PARENT_TYPE FOREIGN KEY (parent_id) REFERENCES object_type ON
DELETE CASCADE;
ALTER TABLE objecttype_struct ADD CONSTRAINT FK_STRUCT_TYPE_ID FOREIGN KEY (objecttype_id) REFERENCES object_type ON
DELETE CASCADE;
CREATE SEQUENCE hibernate_sequence;