--Suppression de la table delayLogin
drop table if exists sc_monsuivi.delay_login;

-- Delai de connexion au compte --
create table IF NOT EXISTS sc_monsuivi.disable_login(
    "id" BIGINT PRIMARY KEY,
    "seller_id" BIGINT NOT NULL REFERENCES sc_monsuivi."seller"("id") on delete cascade,
    "delay_login_until" TIMESTAMPTZ NOT NULL DEFAULT NOW() + INTERVAL '5 minutes',
    "created_at" TIMESTAMPTZ NOT NULL DEFAULT now(),
    "updated_at" TIMESTAMPTZ
);
CREATE INDEX IF NOT EXISTS idx_delay_login ON sc_monsuivi.disable_login(seller_id);
ALTER table IF EXISTS sc_monsuivi.disable_login OWNER TO monsuivi;
GRANT SELECT, INSERT, UPDATE, DELETE ON TABLE sc_monsuivi.disable_login TO monsuivi;

CREATE SEQUENCE if not exists sc_monsuivi.disable_login_pk_seq START WITH 1 INCREMENT BY 1 NO CYCLE;
ALTER SEQUENCE if exists sc_monsuivi.disable_login_pk_seq OWNER TO monsuivi;
ALTER SEQUENCE if exists sc_monsuivi.disable_login_pk_seq owned by sc_monsuivi.disable_login.id;
ALTER TABLE sc_monsuivi.disable_login ALTER COLUMN id SET DEFAULT NEXTVAL('sc_monsuivi.disable_login_pk_seq');