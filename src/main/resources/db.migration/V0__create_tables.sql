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
    id          uuid PRIMARY KEY,
    code1c      text NOT NULL,
    rus_name    text NOT NULL,
    kaz_name    text NOT NULL,
    category_id uuid NOT NULL,
    brand_id    uuid NOT NULL,
    producer_id uuid NOT NULL,
    barcode     text NOT NULL,
    price       text NOT NULL,
    height      text NOT NULL,
    weight      text NOT NULL,
    length      text NOT NULL,
    image_path  text NOT NULL
);