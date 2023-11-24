DROP KEYSPACE IF EXISTS lab03_ex4;
CREATE KEYSPACE IF NOT EXISTS lab03_ex4 WITH REPLICATION = {'class' : 'SimpleStrategy', 'replication_factor' : '1'};
USE lab03_ex4;

DROP TABLE IF EXISTS product;
DROP TABLE IF EXISTS costumer;
DROP TABLE IF EXISTS "order";
DROP TABLE IF EXISTS review;
DROP TABLE IF EXISTS reviewByCostumerAndRating;

DROP INDEX IF EXISTS product_categories_idx;
DROP INDEX IF EXISTS order_products_idx;
DROP INDEX IF EXISTS product_details_idx;;

CREATE TABLE product
(
    id          int,
    name        text,
    price       float,
    description text,
    categories  set<text>,
    details     map<text, text>,
    PRIMARY KEY (id)
);

CREATE TABLE costumer
(
    id    int,
    name  text,
    email text,
    PRIMARY KEY (id)
);
CREATE TABLE "order"
(
    costumer_id int,
    products    list<int>,
    total       float,
    date        timestamp,
    PRIMARY KEY ((costumer_id), date)
) WITH CLUSTERING ORDER BY (date DESC);

CREATE TABLE review
(
    product_id  int,
    costumer_id int,
    rating      int,
    comment     text,
    date        timestamp,
    PRIMARY KEY (product_id, date, costumer_id)
) WITH CLUSTERING ORDER BY (date DESC);

CREATE TABLE reviewByCostumerAndRating
(
    product_id  int,
    costumer_id int,
    rating      int,
    comment     text,
    date        timestamp,
    PRIMARY KEY (costumer_id, rating, product_id)
) WITH CLUSTERING ORDER BY (rating DESC);

CREATE INDEX ON product (KEYS(details));
CREATE INDEX ON product (categories);
CREATE INDEX ON "order" (products);






