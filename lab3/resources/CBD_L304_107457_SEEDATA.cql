USE lab03_ex4;

INSERT INTO product (id, name, price, description, categories, details)
VALUES (1, 'Pro Laptop', 1004.5, 'Sleek, powerful, secure, stylish productivity.',
        {'Performance', 'Business', 'Portable Workstations'},
        {'Processor': 'Intel Core i7', 'RAM': '16GB DDR4', 'Storage': '512GB SSD', 'Graphics': 'NVIDIA GeForce GTX', 'Security': 'Fingerprint Reader'});
INSERT INTO product (id, name, price, description, categories, details)
VALUES (2, 'UltraBook', 899.99,
        'Slim, lightweight, and ideal for professionals on the go.', {'Portable', 'Business', 'Ultrabooks'},
        {'Processor': 'Intel Core i5', 'RAM': '8GB DDR4', 'Storage': '256GB SSD', 'Graphics': 'Integrated Intel UHD', 'Security': 'Face Recognition'});
INSERT INTO product (id, name, price, description, categories, details)
VALUES (3, 'Workstation PC', 1899.99,
        'A desktop powerhouse for demanding creative and technical tasks.', {'Performance', 'Design', 'Workstations'},
        {'Processor': 'AMD Ryzen 9', 'RAM': '32GB DDR4', 'Storage': '1TB NVMe SSD + 2TB HDD', 'Graphics': 'NVIDIA Quadro RTX 5000', 'Security': 'Kensington Lock'});
INSERT INTO product (id, name, price, description, categories, details)
VALUES (4, 'Business Tablet', 599.99,
        'Compact and versatile, perfect for on-the-go professionals.', {'Portable', 'Business', 'Tablets'},
        {'Processor': 'Qualcomm Snapdragon', 'RAM': '6GB', 'Storage': '128GB eMMC', 'Display': '10.1-inch Full HD', 'Security': 'Fingerprint Sensor'});
INSERT INTO product (id, name, price, description, categories, details)
VALUES (5, 'Gaming Laptop', 1499.99,
        'Immersive gaming experience with high-performance specs.', {'Gaming', 'Performance', 'Laptops'},
        {'Processor': 'Intel Core i9', 'RAM': '32GB DDR4', 'Storage': '1TB NVMe SSD', 'Graphics': 'NVIDIA GeForce RTX 3080', 'Cooling': 'Advanced Cooling System'});
INSERT INTO product (id, name, price, description, categories, details)
VALUES (6, 'Smartphone', 799.99, 'Premium features in a sleek and compact design.',
        {'Mobile', 'Technology', 'Smartphones'},
        {'Processor': 'Apple A15 Bionic', 'RAM': '6GB', 'Storage': '256GB', 'Camera': 'Triple-lens system', 'Biometrics': 'Face ID'});
INSERT INTO product (id, name, price, description, categories, details)
VALUES (7, '4K Smart TV', 899.99,
        'Immerse yourself in stunning visuals and smart features.', {'Home Entertainment', 'Televisions', '4K TVs'},
        {'Display Size': '55 inches', 'Resolution': '4K UHD', 'Smart Features': 'Built-in streaming apps'});
INSERT INTO product (id, name, price, description, categories, details)
VALUES (8, 'Wireless Earbuds', 129.99,
        'Crystal-clear sound with the freedom of wireless technology.', {'Audio', 'Wireless', 'Earbuds'},
        {'Connection': 'Bluetooth 5.0', 'Battery Life': 'Up to 8 hours', 'Noise Cancellation': 'Active Noise Cancelling', 'Water Resistance': 'IPX4'});
INSERT INTO product (id, name, price, description, categories, details)
VALUES (9, 'Compact Camera', 499.99,
        'Capture memories with high-quality photos and 4K video.', {'Photography', 'Cameras', 'Compact'},
        {'Megapixels': '24.2MP', 'Sensor Type': 'APS-C CMOS'});
INSERT INTO product (id, name, price, description, categories, details)
VALUES (10, 'Fitness Tracker', 59.99,
        'Stay active and monitor your health with smart fitness features.',
        {'Wearables', 'Fitness', 'Activity Trackers'},
        {'Display': 'Color Touchscreen', 'Water Resistance': 'Swim-proof', 'Battery Life': 'Up to 7 days'});
INSERT INTO product (id, name, price, description, categories, details)
VALUES (11, 'External SSD', 169.99,
        'High-speed storage for quick data transfer and backups.', {'Computing', 'Storage', 'External SSDs'},
        {'Capacity': '1TB', 'Interface': 'USB 3.2 Gen 2', 'Read Speed': 'Up to 1000MB/s', 'Compact Design': 'Pocket-sized'});
INSERT INTO product (id, name, price, description, categories, details)
VALUES (12, 'Home Security Camera', 79.99,
        'Keep an eye on your home with advanced security features.', {'Smart Home', 'Security', 'Cameras'},
        {'Resolution': '1080p HD', 'Field of View': '120 degrees', 'Night Vision': 'Infrared LEDs', 'Connectivity': 'Wi-Fi'});

INSERT INTO costumer (id, name, email)
VALUES (1, 'João Silva', 'joao@gmail.com');
INSERT INTO costumer (id, name, email)
VALUES (2, 'Maria Santos', 'maria@gmail.com');
INSERT INTO costumer (id, name, email)
VALUES (3, 'Carlos Oliveira', 'carlos@gmail.com');
INSERT INTO costumer (id, name, email)
VALUES (4, 'Ana Costa', 'ana@gmail.com');
INSERT INTO costumer (id, name, email)
VALUES (5, 'Pedro Almeida', 'pedro@gmail.com');
INSERT INTO costumer (id, name, email)
VALUES (6, 'Sofia Lima', 'sofia@gmail.com');
INSERT INTO costumer (id, name, email)
VALUES (7, 'Rui Fernandes', 'rui@gmail.com');
INSERT INTO costumer (id, name, email)
VALUES (8, 'Marta Pereira', 'marta@gmail.com');
INSERT INTO costumer (id, name, email)
VALUES (9, 'Joaquim Ribeiro', 'joaquim@gmail.com');
INSERT INTO costumer (id, name, email)
VALUES (10, 'Inês Oliveira', 'ines@gmail.com');
INSERT INTO costumer (id, name, email)
VALUES (11, 'Hugo Sousa', 'hugo@gmail.com');
INSERT INTO costumer (id, name, email)
VALUES (12, 'Catarina Ferreira', 'catarina@gmail.com');

INSERT INTO "order" (costumer_id, products, total, date)
VALUES (1, [1, 9], 1505.49, '2022-12-27 18:54:24.000');
INSERT INTO "order" (costumer_id, products, total, date)
VALUES (2, [2, 3], 2799.98, '203-05-04 12:25:05.000');
INSERT INTO "order" (costumer_id, products, total, date)
VALUES (3, [4, 5], 2099.98, '2023-11-01 20:05:14.000');
INSERT INTO "order" (costumer_id, products, total, date)
VALUES (4, [5, 6], 2299.98, '2023-07-24 16:00:47.000');
INSERT INTO "order" (costumer_id, products, total, date)
VALUES (5, [7, 8], 1029.98, '2023-10-01 23:18:00.000');
INSERT INTO "order" (costumer_id, products, total, date)
VALUES (6, [9], 499.99, '2023-02-10 14:25:04.000');
INSERT INTO "order" (costumer_id, products, total, date)
VALUES (7, [6, 8, 4], 1529.97, '2023-04-10 14:25:04.000');
INSERT INTO "order" (costumer_id, products, total, date)
VALUES (8, [10, 11], 229.98, '2023-08-30 17:05:15.000');
INSERT INTO "order" (costumer_id, products, total, date)
VALUES (9, [12, 10], 139.98, '2023-08-14 21:47:14.000');
INSERT INTO "order" (costumer_id, products, total, date)
VALUES (10, [11, 2], 1069.98, '2023-02-24 16:17:43.000');
INSERT INTO "order" (costumer_id, products, total, date)
VALUES (11, [9, 7], 1399.98, '2023-5-27 07:41:21.000');
INSERT INTO "order" (costumer_id, products, total, date)
VALUES (12, [5, 11, 12], 1749.97, '2023-03-19 09:27:03.000');
INSERT INTO "order" (costumer_id, products, total, date)
VALUES (1, [6, 8, 7, 10], 1889.96, '2023-01-01 10:30:00.000');

INSERT INTO review (product_id, costumer_id, rating, comment, date)
VALUES (1, 1, 5, 'Great laptop!', '2023-01-01 10:30:00.000');
INSERT INTO review (product_id, costumer_id, rating, comment, date)
VALUES (9, 6, 4, 'Good but a bit heavy', '2023-02-15 14:45:00.000');
INSERT INTO review (product_id, costumer_id, rating, comment, date)
VALUES (2, 10, 5, 'Excellent tablet!', '2023-03-10 18:20:00.000');
INSERT INTO review (product_id, costumer_id, rating, comment, date)
VALUES (5, 12, 4, 'Solid performance', '2023-04-05 12:15:00.000');
INSERT INTO review (product_id, costumer_id, rating, comment, date)
VALUES (10, 9, 3, 'Decent camera quality', '2023-05-20 09:30:00.000');
INSERT INTO review (product_id, costumer_id, rating, comment, date)
VALUES (9, 1, 5, 'Impressive gaming performance', '2023-06-15 16:40:00.000');
INSERT INTO review (product_id, costumer_id, rating, comment, date)
VALUES (11, 10, 4, 'Good for everyday use', '2023-07-01 11:10:00.000');
INSERT INTO review (product_id, costumer_id, rating, comment, date)
VALUES (12, 9, 4, 'Compact and efficient', '2023-08-25 13:55:00.000');
INSERT INTO review (product_id, costumer_id, rating, comment, date)
VALUES (11, 8, 5, 'Fast and reliable', '2023-09-10 17:30:00.000');
INSERT INTO review (product_id, costumer_id, rating, comment, date)
VALUES (8, 5, 4, 'Great smart features', '2023-10-05 14:20:00.000');
INSERT INTO review (product_id, costumer_id, rating, comment, date)
VALUES (5, 3, 5, 'Perfect for photography', '2023-10-18 09:45:00.000');
INSERT INTO review (product_id, costumer_id, rating, comment, date)
VALUES (7, 1, 5, 'Good value for money', '2023-11-15 11:30:00.000');
INSERT INTO review (product_id, costumer_id, rating, comment, date)
VALUES (6, 1, 4, 'Great battery life', '2023-05-01 15:00:00.000');
INSERT INTO review (product_id, costumer_id, rating, comment, date)
VALUES (10, 1, 3, 'Good for work', '2023-06-10 10:30:00.000');

INSERT INTO reviewByCostumerAndRating (product_id, costumer_id, rating, comment, date)
VALUES (1, 1, 5, 'Great laptop!', '2023-01-01 10:30:00.000');
INSERT INTO reviewByCostumerAndRating (product_id, costumer_id, rating, comment, date)
VALUES (9, 6, 4, 'Good but a bit heavy', '2023-02-15 14:45:00.000');
INSERT INTO reviewByCostumerAndRating (product_id, costumer_id, rating, comment, date)
VALUES (2, 10, 5, 'Excellent tablet!', '2023-03-10 18:20:00.000');
INSERT INTO reviewByCostumerAndRating (product_id, costumer_id, rating, comment, date)
VALUES (5, 12, 4, 'Solid performance', '2023-04-05 12:15:00.000');
INSERT INTO reviewByCostumerAndRating (product_id, costumer_id, rating, comment, date)
VALUES (10, 9, 3, 'Decent camera quality', '2023-05-20 09:30:00.000');
INSERT INTO reviewByCostumerAndRating (product_id, costumer_id, rating, comment, date)
VALUES (9, 1, 5, 'Impressive gaming performance', '2023-06-15 16:40:00.000');
INSERT INTO reviewByCostumerAndRating (product_id, costumer_id, rating, comment, date)
VALUES (11, 10, 4, 'Good for everyday use', '2023-07-01 11:10:00.000');
INSERT INTO reviewByCostumerAndRating (product_id, costumer_id, rating, comment, date)
VALUES (12, 9, 4, 'Compact and efficient', '2023-08-25 13:55:00.000');
INSERT INTO reviewByCostumerAndRating (product_id, costumer_id, rating, comment, date)
VALUES (11, 8, 5, 'Fast and reliable', '2023-09-10 17:30:00.000');
INSERT INTO reviewByCostumerAndRating (product_id, costumer_id, rating, comment, date)
VALUES (8, 5, 4, 'Great smart features', '2023-10-05 14:20:00.000');
INSERT INTO reviewByCostumerAndRating (product_id, costumer_id, rating, comment, date)
VALUES (5, 3, 5, 'Perfect for photography', '2023-10-18 09:45:00.000');
INSERT INTO reviewByCostumerAndRating (product_id, costumer_id, rating, comment, date)
VALUES (7, 1, 5, 'Good value for money', '2023-11-15 11:30:00.000');
INSERT INTO reviewByCostumerAndRating (product_id, costumer_id, rating, comment, date)
VALUES (6, 1, 4, 'Great battery life', '2023-05-01 15:00:00.000');
INSERT INTO reviewByCostumerAndRating (product_id, costumer_id, rating, comment, date)
VALUES (10, 1, 3, 'Good for work', '2023-06-10 10:30:00.000');