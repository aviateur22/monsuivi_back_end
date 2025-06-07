BEGIN;
-- Category produit --
create table IF NOT EXISTS sc_monsuivi.product_category(
    "id" INT PRIMARY KEY,
    "category_name" TEXT NOT NULL,
    "category_code" TEXT NOT NULL,
    "created_at" TIMESTAMPTZ NOT NULL DEFAULT now(),
    "updated_at" TIMESTAMPTZ
);
ALTER table IF EXISTS sc_monsuivi.product_category OWNER TO monsuivi;
GRANT SELECT, INSERT, UPDATE, DELETE ON TABLE sc_monsuivi.product_category TO monsuivi;

-- couleur catégorie --
create table IF NOT EXISTS sc_monsuivi.product_category_color(
    "id" INT PRIMARY KEY,
    "category_id" INT NOT NULL REFERENCES sc_monsuivi."product_category"("id") on delete cascade,
    "color" TEXT NOT NULL,
    "touch_color" TEXT NOT NULL,
    "created_at" TIMESTAMPTZ NOT NULL DEFAULT now(),
    "updated_at" TIMESTAMPTZ
);
ALTER table IF EXISTS sc_monsuivi.product_category_color OWNER TO monsuivi;
GRANT SELECT, INSERT, UPDATE, DELETE ON TABLE sc_monsuivi.product_category_color TO monsuivi;

INSERT INTO sc_monsuivi.product_category_color ("id","category_id", "color", "touch_color") VALUES
(1, 1,'#f56218', '#f7915e'),
(2, 2, '#802bf0', '#b889f5'),
(3,3, '#fff536', '#faf58e');

INSERT INTO sc_monsuivi.product_category ("id","category_name", "category_code") VALUES
(1,'livre', 'bk'),
(2,'jeu', 'ga'),
(3,'vétement', 'cl');

-- Suppression colonne prix de vente désiré de la table product --
ALTER TABLE sc_monsuivi.product DROP COLUMN product_desired_sold_price;

COMMIT;