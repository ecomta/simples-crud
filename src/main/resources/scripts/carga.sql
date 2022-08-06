CREATE TABLE IF NOT EXISTS cliente (
    uuid VARCHAR(64) NOT NULL,
    nm_cliente VARCHAR(64) NOT NULL,
    email VARCHAR(128) NOT NULL,
    CONSTRAINT PK_CRUDUSUARIO_UUID PRIMARY KEY(uuid)
);

INSERT INTO cliente
    (uuid, nm_cliente, email)
VALUES
    ("AA", "Pedro", "pedro@gmail.com"),
    ("BB", "Marcos", "marcos@gmail.com"),
    ("CC", "Lizandra", "lizandra@gmail.com"),
    ("DD", "Luna", "luna@gmail.com");