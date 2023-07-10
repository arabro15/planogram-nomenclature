CREATE TABLE IF NOT EXISTS public.brand
(
    id   uuid PRIMARY KEY,
    name text NOT NULL
);

CREATE TABLE IF NOT EXISTS public.category
(
    id       uuid PRIMARY KEY,
    name     text NOT NULL,
    color    text NOT NULL,
    parentID uuid NOT NULL
);

CREATE TABLE IF NOT EXISTS public.producer
(
    id   uuid PRIMARY KEY,
    name text NOT NULL
);

CREATE TABLE IF NOT EXISTS public.product
(
    id        uuid PRIMARY KEY,
    rusName   text NOT NULL,
    kazName   text NOT NULL,
    category  text NOT NULL,
    brand     text NOT NULL,
    producer  text NOT NULL,
    barcode   text NOT NULL,
    price     text NOT NULL,
    height    text NOT NULL,
    weight    text NOT NULL,
    length    text NOT NULL,
    imagePath text NOT NULL
);