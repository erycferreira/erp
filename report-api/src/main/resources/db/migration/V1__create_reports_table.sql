CREATE TABLE reports (
    id          UUID            NOT NULL,
    name        VARCHAR(255)    NOT NULL,
    type        VARCHAR(50)     NOT NULL,
    status      VARCHAR(50)     NOT NULL,
    created_at  TIMESTAMP(6)    NOT NULL,
    updated_at  TIMESTAMP(6)    NOT NULL,

    CONSTRAINT pk_reports PRIMARY KEY (id)
);