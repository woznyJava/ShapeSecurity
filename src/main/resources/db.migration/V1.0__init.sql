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
CREATE VIEW shape_view AS
SELECT id,
       dtype,
       type,
       created_by,
       created_at,
       last_modified_at,
       last_modified_by,
       radius,
       width,
       height,
       side,
       version,
       CASE
           WHEN type = 'CIRCLE' THEN 3.14 * radius * radius
           WHEN type = 'RECTANGLE' THEN width * height
           WHEN type = 'SQUARE' THEN side * side
           ELSE 0
           END AS area,
       CASE
           WHEN type = 'CIRCLE' THEN 2 * 3.14 * radius
           WHEN type = 'RECTANGLE' THEN 2 * (width + height)
           WHEN type = 'SQUARE' THEN 4 * side
           ELSE 0
           END AS perimeter
FROM APP_SHAPE;
