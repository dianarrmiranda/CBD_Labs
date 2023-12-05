USE lab03_ex4;

//e- Utilização de, pelo menos, 5 updates e 5 deletes de dados:
UPDATE product SET categories = {'Computing', 'Storage', 'Business'} WHERE id = 11;
UPDATE product SET details = details + {'Graphics': 'NVIDIA GeForce RTX 3060'} WHERE id = 10;
UPDATE "order" SET products = products - [8], total = 1759.97 WHERE costumer_id = 1 and date = '2023-01-01 10:30:00.000';
UPDATE review SET comment = 'This is a great product' WHERE costumer_id = 1 and product_id = 10 and date = '2023-06-10 10:30:00.000';
UPDATE reviewbycostumerandrating SET comment = 'This is a great product' WHERE costumer_id = 1 and product_id = 10 and rating = 3;
UPDATE costumer SET name = 'João Manuel Silva' WHERE id = 1;

DELETE FROM review WHERE costumer_id = 1 and product_id = 10 and date = '2023-06-10 10:30:00.000';
DELETE FROM reviewbycostumerandrating WHERE costumer_id = 1 and product_id = 10 and rating = 3;
DELETE FROM product WHERE id = 10;
DELETE FROM "order" WHERE costumer_id = 1 and date = '2023-01-01 10:30:00.000';
DELETE FROM costumer WHERE id = 1;

//f- Criação de 10 queries expressivas do seu domínio de conhecimento da cláusula

//1. Obter todos os produtos de uma determinada categoria.
SELECT * FROM product WHERE categories CONTAINS 'Performance';

//2. Obter todas as reviews de um determinado produto ordenadas por data.
SELECT * FROM review WHERE product_id = 11 ORDER BY date DESC;

//3. Obter a avaliação média de um produto
SELECT  AVG(CAST(rating AS DOUBLE)) AS AVG_Rating FROM review WHERE product_id = 11;

//4. Obter a order mais cara de um cliente
SELECT costumer_id, date, products, MAX(total) AS MAX_Total FROM "order" WHERE costumer_id = 1;

//5. Obter todos os pedidos que contêm um produto específico
SELECT * FROM "order" WHERE products CONTAINS 6;

//6. Obter todas as reviews do mês de Outubro de 2023
SELECT * FROM review WHERE date >= '2023-10-01' AND date <= '2023-10-31' ALLOW FILTERING ;

//7. Obter o número de reviews por produto
SELECT product_id, COUNT(*) as count FROM review GROUP BY product_id;

//8. Obter os produtos com uma determinada categoria e com um preço inferior a 1000
SELECT name, categories, price FROM product WHERE categories CONTAINS 'Business' AND price < 1000 ALLOW FILTERING;

//9. Obter o número de encomendas feitas por um cliente e o total gasto
SELECT costumer_id, COUNT(*) AS NUM_OF_ORDERS,  SUM(total) AS Total FROM "order" WHERE costumer_id = 1;

//10. Obter os 3 produtos preferidos de um cliente
SELECT rating, product_id, comment, date FROM reviewbycostumerandrating WHERE costumer_id = 1 ORDER BY rating DESC LIMIT 3;

//11. Obter os produtos em que os detalhes têm a chave "Graphics"
SELECT * FROM product WHERE details CONTAINS KEY 'Graphics' ;


