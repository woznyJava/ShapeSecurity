CREATE TABLE APP_SHAPE
(
    dtype            VARCHAR(31),
    id               INT AUTO_INCREMENT NOT NULL UNIQUE,
    created_by       VARCHAR(255),
    type             VARCHAR(255),
    version          BIGINT,
    created_at       TIMESTAMP,
    last_modified_at TIMESTAMP,
    last_modified_by VARCHAR(255),
    radius           DOUBLE PRECISION,
    height           DOUBLE PRECISION,
    width            DOUBLE PRECISION,
    side             DOUBLE PRECISION,

    PRIMARY KEY (id)

);
CREATE TABLE APP_USER
(
    id         INT NOT NULL AUTO_INCREMENT,
    email      VARCHAR(255),
    first_name VARCHAR(255),
    last_name  VARCHAR(255),
    password   VARCHAR(255),
    role       VARCHAR(255),

    PRIMARY KEY (id)
);
CREATE TABLE SHAPE_VIEW
(
    id               INTEGER AUTO_INCREMENT NOT NULL UNIQUE,
    area             DOUBLE PRECISION,
    created_at       TIMESTAMP,
    created_by       VARCHAR(255),
    height           DOUBLE PRECISION,
    last_modified_at TIMESTAMP,
    perimeter        DOUBLE PRECISION,
    radius           DOUBLE PRECISION,
    side             DOUBLE PRECISION,
    type             VARCHAR(255),
    version          BIGINT,
    width            DOUBLE PRECISION,
    shape_id         INTEGER,
    PRIMARY KEY (id)
);