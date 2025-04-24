--Update schema, database, role, table, column as needed
BEGIN;
DO
$do$
BEGIN
    IF NOT EXISTS (SELECT 1 FROM pg_roles WHERE rolname = 'monsuivi') THEN
        CREATE ROLE monsuivi WITH LOGIN PASSWORD 'monsuivi';
        GRANT CONNECT ON DATABASE monsuivi TO monsuivi;
    END IF;
END
$do$;

CREATE SCHEMA IF NOT EXISTS sc_monsuivi;
ALTER SCHEMA sc_monsuivi OWNER TO monsuivi;

DROP TABLE IF EXISTS sc_monsuivi."login", sc_monsuivi."jwt", sc_monsuivi."delay_login", sc_monsuivi."seller_account", sc_monsuivi."role_seller", sc_monsuivi."role", sc_monsuivi."image",sc_monsuivi.product, sc_monsuivi."seller" CASCADE;

-- Utilisateur --
CREATE TABLE if NOT EXISTS sc_monsuivi.seller(
    "id" BIGINT PRIMARY KEY,
    "nickname" text,
    "email" text not null,
    "password" text not null,
    "created_at" TIMESTAMPTZ NOT NULL DEFAULT now(),
    "updated_at" TIMESTAMPTZ
);
CREATE INDEX IF NOT EXISTS idx_seller_email ON sc_monsuivi.seller (email);

-- Produit --
CREATE TABLE if NOT EXISTS sc_monsuivi.product(
    "id" BIGINT PRIMARY KEY,
    "seller_id" BIGINT NOT NULL REFERENCES sc_monsuivi."seller"("id") on delete cascade,
    "product_name" text NOT NULL,
    "product_purchase_price" float not null,
    "product_category" text NOT NULL,
    "product_status" text NOT NULL,
    "product_desired_sold_price" float,
    "product_sold_price" float,
    "product_sold_at" DATE,
    "product_buy_at" DATE NOT NULL,
    "created_at" TIMESTAMPTZ NOT NULL DEFAULT now(),
    "updated_at" TIMESTAMPTZ
);
CREATE INDEX IF NOT EXISTS idx_product_seller ON sc_monsuivi.product (seller_id);
CREATE INDEX IF NOT EXISTS idx_product_name ON sc_monsuivi.product (product_name);
CREATE INDEX IF NOT EXISTS idx_product_category ON sc_monsuivi.product (product_category);


-- Professionnel image --
CREATE TABLE if NOT EXISTS sc_monsuivi.image(
    "id" BIGINT PRIMARY KEY,
    "product_id" BIGINT NOT NULL REFERENCES sc_monsuivi."product"("id") on delete cascade,
    "image_path" TEXT NOT NULL,
    "created_at" TIMESTAMPTZ NOT NULL DEFAULT now(),
    "updated_at" TIMESTAMPTZ
);
CREATE INDEX IF NOT EXISTS idx_image_product ON sc_monsuivi.image (product_id);

-- Role --
create table IF NOT EXISTS sc_monsuivi.role(
    "id" INT PRIMARY KEY,
    "role" TEXT NOT NULL,
    "created_at" TIMESTAMPTZ NOT NULL DEFAULT now(),
    "updated_at" TIMESTAMPTZ
);

-- Liasion Role et utilisateur --
create table IF NOT EXISTS sc_monsuivi.role_seller(
    "id" BIGINT PRIMARY KEY,
    "seller_id" BIGINT NOT NULL REFERENCES sc_monsuivi."seller"("id") on delete cascade,
    "role_id" INT NOT NULL REFERENCES sc_monsuivi."role"("id") on delete cascade,
    "created_at" TIMESTAMPTZ NOT NULL DEFAULT now(),
    "updated_at" TIMESTAMPTZ
);
CREATE INDEX IF NOT EXISTS idx_role_user ON sc_monsuivi.role_seller(seller_id, role_id);

-- Compte utilisateur--
create table IF NOT EXISTS sc_monsuivi.seller_account(
    "id" BIGINT PRIMARY KEY,
    "seller_id" BIGINT NOT NULL REFERENCES sc_monsuivi."seller"("id") on delete cascade,
    "account_activation_at" TIMESTAMPTZ,
    "account_activation_limit_date_at" TIMESTAMPTZ,
    "is_account_active" BOOLEAN NOT NULL DEFAULT TRUE,
    "created_at" TIMESTAMPTZ NOT NULL DEFAULT now(),
    "updated_at" TIMESTAMPTZ
);
CREATE INDEX IF NOT EXISTS idx_user_account ON sc_monsuivi.seller_account(seller_id);


-- JWT --
CREATE TABLE if NOT EXISTS sc_monsuivi.jwt(
    "id" BIGINT PRIMARY KEY,
    "seller_id" BIGINT NOT NULL REFERENCES sc_monsuivi."seller"("id") on delete cascade,
    "email" TEXT NOT NULL,
    "jwt_token" TEXT NOT NULL,
    "jwt_id" TEXT NOT NULL,
    "is_valid" BOOLEAN NOT NULL DEFAULT FALSE,
    "expired_at" TIMESTAMPTZ NOT NULL DEFAULT now(),
    "created_at" TIMESTAMPTZ NOT NULL DEFAULT now(),
    "updated_at" TIMESTAMPTZ
);
CREATE INDEX IF NOT EXISTS idx_jwt ON sc_monsuivi.jwt(seller_id);

-- Connexion utilisateur --
create table IF NOT EXISTS sc_monsuivi.login(
    "id" BIGINT PRIMARY KEY,
    "seller_id" BIGINT NOT NULL REFERENCES sc_monsuivi."seller"("id") on delete cascade,
    "is_login_success" BOOLEAN NOT NULL,
    "has_to_be_check" BOOLEAN NOT NULL DEFAULT TRUE,
    "login_at" TIMESTAMPTZ NOT NULL
);
CREATE INDEX IF NOT EXISTS idx_login ON sc_monsuivi.login(seller_id);

-- Delai de connexion au compte --
create table IF NOT EXISTS sc_monsuivi.delay_login(
    "id" BIGINT PRIMARY KEY,
    "seller_id" BIGINT NOT NULL REFERENCES sc_monsuivi."seller"("id") on delete cascade,
    "delay_login_until" TIMESTAMPTZ NOT NULL DEFAULT NOW() + INTERVAL '5 minutes',
    "created_at" TIMESTAMPTZ NOT NULL DEFAULT now(),
    "updated_at" TIMESTAMPTZ
);
CREATE INDEX IF NOT EXISTS idx_delay_login ON sc_monsuivi.delay_login(seller_id);


ALTER table IF EXISTS sc_monsuivi.seller OWNER TO monsuivi;
ALTER table IF EXISTS sc_monsuivi.product OWNER TO monsuivi;
ALTER table IF EXISTS sc_monsuivi.image OWNER TO monsuivi;
ALTER table IF EXISTS sc_monsuivi.jwt OWNER TO monsuivi;
ALTER table IF EXISTS sc_monsuivi.role OWNER TO monsuivi;
ALTER table IF EXISTS sc_monsuivi.role_seller OWNER TO monsuivi;
ALTER table IF EXISTS sc_monsuivi.seller_account OWNER TO monsuivi;
ALTER table IF EXISTS sc_monsuivi.login OWNER TO monsuivi;
ALTER table IF EXISTS sc_monsuivi.delay_login OWNER TO monsuivi;

GRANT SELECT, INSERT, UPDATE, DELETE ON TABLE sc_monsuivi.seller TO monsuivi;
GRANT SELECT, INSERT, UPDATE, DELETE ON TABLE sc_monsuivi.image TO monsuivi;
GRANT SELECT, INSERT, UPDATE, DELETE ON TABLE sc_monsuivi.product TO monsuivi;
GRANT SELECT, INSERT, UPDATE, DELETE ON TABLE sc_monsuivi.jwt TO monsuivi;
GRANT SELECT, INSERT, UPDATE, DELETE ON TABLE sc_monsuivi.role TO monsuivi;
GRANT SELECT, INSERT, UPDATE, DELETE ON TABLE sc_monsuivi.role_seller TO monsuivi;
GRANT SELECT, INSERT, UPDATE, DELETE ON TABLE sc_monsuivi.seller_account TO monsuivi;
GRANT SELECT, INSERT, UPDATE, DELETE ON TABLE sc_monsuivi.login TO monsuivi;
GRANT SELECT, INSERT, UPDATE, DELETE ON TABLE sc_monsuivi.delay_login TO monsuivi;

CREATE SEQUENCE if not exists sc_monsuivi.seller_pk_seq START WITH 1 INCREMENT BY 1 NO CYCLE;
CREATE SEQUENCE if not exists sc_monsuivi.product_pk_seq START WITH 1 INCREMENT BY 1 NO CYCLE;
CREATE SEQUENCE if not exists sc_monsuivi.image_pk_seq START WITH 1 INCREMENT BY 1 NO CYCLE;
CREATE SEQUENCE if not exists sc_monsuivi.jwt_pk_seq START WITH 1 INCREMENT BY 1 NO CYCLE;
CREATE SEQUENCE if not exists sc_monsuivi.role_seller_pk_seq START WITH 1 INCREMENT BY 1 NO CYCLE;
CREATE SEQUENCE if not exists sc_monsuivi.seller_account_pk_seq START WITH 1 INCREMENT BY 1 NO CYCLE;
CREATE SEQUENCE if not exists sc_monsuivi.login_pk_seq START WITH 1 INCREMENT BY 1 NO CYCLE;
CREATE SEQUENCE if not exists sc_monsuivi.delay_login_pk_seq START WITH 1 INCREMENT BY 1 NO CYCLE;

ALTER SEQUENCE if exists sc_monsuivi.seller_pk_seq OWNER TO monsuivi;
ALTER SEQUENCE if exists sc_monsuivi.seller_pk_seq owned by sc_monsuivi.seller.id;
ALTER TABLE sc_monsuivi.seller ALTER COLUMN id SET DEFAULT NEXTVAL('sc_monsuivi.seller_pk_seq');

ALTER SEQUENCE if exists sc_monsuivi.product_pk_seq OWNER TO monsuivi;
ALTER SEQUENCE if exists sc_monsuivi.product_pk_seq owned by sc_monsuivi.product.id;
ALTER TABLE sc_monsuivi.product ALTER COLUMN id SET DEFAULT NEXTVAL('sc_monsuivi.product_pk_seq');


ALTER SEQUENCE if exists sc_monsuivi.image_pk_seq OWNER TO monsuivi;
ALTER SEQUENCE if exists sc_monsuivi.image_pk_seq owned by sc_monsuivi.image.id;
ALTER TABLE sc_monsuivi.image ALTER COLUMN id SET DEFAULT NEXTVAL('sc_monsuivi.image_pk_seq');

ALTER SEQUENCE if exists sc_monsuivi.jwt_pk_seq OWNER TO monsuivi;
ALTER SEQUENCE if exists sc_monsuivi.jwt_pk_seq owned by sc_monsuivi.jwt.id;
ALTER TABLE sc_monsuivi.jwt ALTER COLUMN id SET DEFAULT NEXTVAL('sc_monsuivi.jwt_pk_seq');

ALTER SEQUENCE if exists sc_monsuivi.role_seller_pk_seq OWNER TO monsuivi;
ALTER SEQUENCE if exists sc_monsuivi.role_seller_pk_seq owned by sc_monsuivi.role_seller.id;
ALTER TABLE sc_monsuivi.role_seller ALTER COLUMN id SET DEFAULT NEXTVAL('sc_monsuivi.role_seller_pk_seq');

ALTER SEQUENCE if exists sc_monsuivi.seller_account_pk_seq OWNER TO monsuivi;
ALTER SEQUENCE if exists sc_monsuivi.seller_account_pk_seq owned by sc_monsuivi.seller_account.id;
ALTER TABLE sc_monsuivi.seller_account ALTER COLUMN id SET DEFAULT NEXTVAL('sc_monsuivi.seller_account_pk_seq');

ALTER SEQUENCE if exists sc_monsuivi.login_pk_seq OWNER TO monsuivi;
ALTER SEQUENCE if exists sc_monsuivi.login_pk_seq owned by sc_monsuivi.login.id;
ALTER TABLE sc_monsuivi.login ALTER COLUMN id SET DEFAULT NEXTVAL('sc_monsuivi.login_pk_seq');

ALTER SEQUENCE if exists sc_monsuivi.delay_login_pk_seq OWNER TO monsuivi;
ALTER SEQUENCE if exists sc_monsuivi.delay_login_pk_seq owned by sc_monsuivi.delay_login.id;
ALTER TABLE sc_monsuivi.delay_login ALTER COLUMN id SET DEFAULT NEXTVAL('sc_monsuivi.delay_login_pk_seq');

--Clear password: test
INSERT INTO sc_monsuivi.seller ("nickname", "email" , "password") VALUES
('seller', 'client@hotmail.fr', '$2y$10$9PSCTWQiEIbXulYGOZi7.u6x5S6.8XuM0dL3EH72sigNHLlUW2wzy');

INSERT INTO sc_monsuivi.role ("id", "role") VALUES
(1, 'ROLE_SELLER'),
(2, 'ROLE_ADMIN');

INSERT INTO sc_monsuivi.seller_account ("seller_id", "is_account_active") VALUES
(1, TRUE);

INSERT INTO sc_monsuivi.role_seller ("seller_id", "role_id") VALUES
(1, 1),
(1, 2);




COMMIT;