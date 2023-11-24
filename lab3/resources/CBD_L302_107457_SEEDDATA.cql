INSERT INTO lab03_ex2.user (username, name, email, date)
VALUES ('diana', 'Diana Miranda', 'diana@ua.pt', '2015-05-25 15:35:00.000');
INSERT INTO lab03_ex2.user (username, name, email, date)
VALUES ('jose', 'José Silve', 'jose@ua.pt', '2016-07-12 10:15:00.000');
INSERT INTO lab03_ex2.user (username, name, email, date)
VALUES ('diogo', 'Diogo Falcão', 'diogo@ua.pt', '2017-08-23 11:25:00.000');
INSERT INTO lab03_ex2.user (username, name, email, date)
VALUES ('marta', 'Marta Soares', 'marta@ua.pt', '2018-09-14 12:35:00.000');
INSERT INTO lab03_ex2.user (username, name, email, date)
VALUES ('luis', 'Luis Santos', 'luis@ua.pt', '2019-10-05 13:45:00.000');
INSERT INTO lab03_ex2.user (username, name, email, date)
VALUES ('ana', 'Ana Silva', 'ana@ua.pt', '2020-11-16 14:55:00.000');
INSERT INTO lab03_ex2.user (username, name, email, date)
VALUES ('pedro', 'Pedro Costa', 'pedro@ua.pt', '2021-12-27 15:05:00.000');
INSERT INTO lab03_ex2.user (username, name, email, date)
VALUES ('maria', 'Maria Fernandes', 'maria@ua.pt', '2022-01-28 16:15:00.000');
INSERT INTO lab03_ex2.user (username, name, email, date)
VALUES ('joao', 'João Pereira', 'joao@ua.pt', '2023-02-18 17:25:00.000');
INSERT INTO lab03_ex2.user (username, name, email, date)
VALUES ('isabel', 'Isabel Rodrigues', 'isabel@ua.pt', '2023-03-29 18:35:00.000');
INSERT INTO lab03_ex2.user (username, name, email, date)
VALUES ('carlos', 'Carlos Teixeira', 'carlos@ua.pt', '2015-04-19 19:45:00.000');
INSERT INTO lab03_ex2.user (username, name, email, date)
VALUES ('sara', 'Sara Almeida', 'sara@ua.pt', '2021-05-30 20:55:00.000');
INSERT INTO lab03_ex2.user (username, name, email, date)
VALUES ('antonio', 'Antonio Martins', 'antonio@ua.pt', '2021-06-20 21:05:00.000');
INSERT INTO lab03_ex2.user (username, name, email, date)
VALUES ('rita', 'Rita Barros', 'rita@ua.pt', '2020-07-11 22:15:00.000');
INSERT INTO lab03_ex2.user (username, name, email, date)
VALUES ('manuel', 'Manuel Ribeiro', 'manuel@ua.pt', '2020-08-01 23:25:00.000');


INSERT INTO lab03_ex2.video (id, name, description, author, tags, uploadDate)
VALUES (1, 'Nature Walk', 'A relaxing walk through the forest', 'diana', {'nature', 'walk', 'forest', 'Aveiro'}, '2022-01-25 15:35:00.000');
INSERT INTO lab03_ex2.video (id, name, description, author, tags, uploadDate)
VALUES (2, 'City Tour', 'A tour of the city', 'jose', {'city', 'tour'}, '2022-02-12 10:15:00.000');
INSERT INTO lab03_ex2.video (id, name, description, author, tags, uploadDate)
VALUES (3, 'Mountain Hike', 'Hiking up the mountain', 'diana', {'mountain', 'hike'}, '2022-03-23 11:25:00.000');
INSERT INTO lab03_ex2.video (id, name, description, author, tags, uploadDate)
VALUES (4, 'Beach Day', 'A day at the beach', 'marta', {'beach', 'day', 'Aveiro'}, '2022-04-14 12:35:00.000');
INSERT INTO lab03_ex2.video (id, name, description, author, tags, uploadDate)
VALUES (5, 'Cooking Pasta', 'Cooking a pasta dish', 'luis', {'cooking', 'pasta'}, '2022-05-05 13:45:00.000');
INSERT INTO lab03_ex2.video (id, name, description, author, tags, uploadDate)
VALUES (6, 'Yoga Session', 'A 30-minute yoga session', 'ana', {'yoga', 'session'}, '2022-06-16 14:55:00.000');
INSERT INTO lab03_ex2.video (id, name, description, author, tags, uploadDate)
VALUES (7, 'Guitar Lesson', 'A beginner guitar lesson', 'pedro', {'guitar', 'lesson'}, '2022-07-27 15:05:00.000');
INSERT INTO lab03_ex2.video (id, name, description, author, tags, uploadDate)
VALUES (8, 'Art Tutorial', 'A tutorial on drawing', 'diana', {'art', 'tutorial'}, '2022-08-28 16:15:00.000');
INSERT INTO lab03_ex2.video (id, name, description, author, tags, uploadDate)
VALUES (9, 'Science Experiment', 'A fun science experiment', 'joao', {'science', 'experiment'}, '2022-09-18 17:25:00.000');
INSERT INTO lab03_ex2.video (id, name, description, author, tags, uploadDate)
VALUES (10, 'Basketball Game', 'A local basketball game', 'isabel', {'basketball', 'game'}, '2022-10-29 18:35:00.000');
INSERT INTO lab03_ex2.video (id, name, description, author, tags, uploadDate)
VALUES (11, 'Gardening Tips', 'Tips for gardening', 'carlos', {'gardening', 'tips'}, '2022-11-19 19:45:00.000');
INSERT INTO lab03_ex2.video (id, name, description, author, tags, uploadDate)
VALUES (12, 'Photography Guide', 'A guide to photography', 'sara', {'photography', 'guide', 'Aveiro'}, '2022-12-30 20:55:00.000');
INSERT INTO lab03_ex2.video (id, name, description, author, tags, uploadDate)
VALUES (13, 'Coding Tutorial', 'A tutorial on coding', 'antonio', {'coding', 'tutorial'}, '2023-01-20 21:05:00.000');
INSERT INTO lab03_ex2.video (id, name, description, author, tags, uploadDate)
VALUES (14, 'Fitness Workout', 'A 20-minute fitness workout', 'rita', {'fitness', 'workout'}, '2023-02-11 22:15:00.000');
INSERT INTO lab03_ex2.video (id, name, description, author, tags, uploadDate)
VALUES (15, 'Travel Vlog', 'A vlog of my travels', 'manuel', {'travel', 'vlog', 'Aveiro'}, '2023-03-01 23:25:00.000');
INSERT INTO lab03_ex2.video (id, name, description, author, tags, uploadDate)
VALUES (16, 'Nature Run', 'A relaxing Run through the forest', 'diana', {'nature', 'run', 'forest', 'Aveiro'}, '2022-01-10 10:00:00.000');

INSERT INTO lab03_ex2.videoByAuthor (id, name, description, author, tags, uploadDate)
VALUES (1, 'Nature Walk', 'A relaxing walk through the forest', 'diana', {'nature', 'walk', 'forest', 'Aveiro'}, '2022-01-25 15:35:00.000');
INSERT INTO lab03_ex2.videoByAuthor (id, name, description, author, tags, uploadDate)
VALUES (2, 'City Tour', 'A tour of the city', 'jose', {'city', 'tour'}, '2022-02-12 10:15:00.000');
INSERT INTO lab03_ex2.videoByAuthor (id, name, description, author, tags, uploadDate)
VALUES (3, 'Mountain Hike', 'Hiking up the mountain', 'diana', {'mountain', 'hike'}, '2022-03-23 11:25:00.000');
INSERT INTO lab03_ex2.videoByAuthor (id, name, description, author, tags, uploadDate)
VALUES (4, 'Beach Day', 'A day at the beach', 'marta', {'beach', 'day', 'Aveiro'}, '2022-04-14 12:35:00.000');
INSERT INTO lab03_ex2.videoByAuthor (id, name, description, author, tags, uploadDate)
VALUES (5, 'Cooking Pasta', 'Cooking a pasta dish', 'luis', {'cooking', 'pasta'}, '2022-05-05 13:45:00.000');
INSERT INTO lab03_ex2.videoByAuthor (id, name, description, author, tags, uploadDate)
VALUES (6, 'Yoga Session', 'A 30-minute yoga session', 'ana', {'yoga', 'session'}, '2022-06-16 14:55:00.000');
INSERT INTO lab03_ex2.videoByAuthor (id, name, description, author, tags, uploadDate)
VALUES (7, 'Guitar Lesson', 'A beginner guitar lesson', 'pedro', {'guitar', 'lesson'}, '2022-07-27 15:05:00.000');
INSERT INTO lab03_ex2.videoByAuthor (id, name, description, author, tags, uploadDate)
VALUES (8, 'Art Tutorial', 'A tutorial on drawing', 'diana', {'art', 'tutorial'}, '2022-08-28 16:15:00.000');
INSERT INTO lab03_ex2.videoByAuthor (id, name, description, author, tags, uploadDate)
VALUES (9, 'Science Experiment', 'A fun science experiment', 'joao', {'science', 'experiment'}, '2022-09-18 17:25:00.000');
INSERT INTO lab03_ex2.videoByAuthor (id, name, description, author, tags, uploadDate)
VALUES (10, 'Basketball Game', 'A local basketball game', 'isabel', {'basketball', 'game'}, '2022-10-29 18:35:00.000');
INSERT INTO lab03_ex2.videoByAuthor (id, name, description, author, tags, uploadDate)
VALUES (11, 'Gardening Tips', 'Tips for gardening', 'carlos', {'gardening', 'tips'}, '2022-11-19 19:45:00.000');
INSERT INTO lab03_ex2.videoByAuthor (id, name, description, author, tags, uploadDate)
VALUES (12, 'Photography Guide', 'A guide to photography', 'sara', {'photography', 'guide', 'Aveiro'}, '2022-12-30 20:55:00.000');
INSERT INTO lab03_ex2.videoByAuthor (id, name, description, author, tags, uploadDate)
VALUES (13, 'Coding Tutorial', 'A tutorial on coding', 'antonio', {'coding', 'tutorial'}, '2023-01-20 21:05:00.000');
INSERT INTO lab03_ex2.videoByAuthor (id, name, description, author, tags, uploadDate)
VALUES (14, 'Fitness Workout', 'A 20-minute fitness workout', 'rita', {'fitness', 'workout'}, '2023-02-11 22:15:00.000');
INSERT INTO lab03_ex2.videoByAuthor (id, name, description, author, tags, uploadDate)
VALUES (15, 'Travel Vlog', 'A vlog of my travels', 'manuel', {'travel', 'vlog', 'Aveiro'}, '2023-03-01 23:25:00.000');
INSERT INTO lab03_ex2.videoByAuthor (id, name, description, author, tags, uploadDate)
VALUES (16, 'Nature Run', 'A relaxing Run through the forest', 'diana', {'nature', 'run', 'forest', 'Aveiro'}, '2022-01-10 10:00:00.000');

INSERT INTO lab03_ex2.nVideosBytag(tag, nVideos) VALUES ('nature', 2);
INSERT INTO lab03_ex2.nVideosBytag(tag, nVideos) VALUES ('walk', 1);
INSERT INTO lab03_ex2.nVideosBytag(tag, nVideos) VALUES ('forest', 2);
INSERT INTO lab03_ex2.nVideosBytag(tag, nVideos) VALUES ('Aveiro', 5);
INSERT INTO lab03_ex2.nVideosBytag(tag, nVideos) VALUES ('city', 1);
INSERT INTO lab03_ex2.nVideosBytag(tag, nVideos) VALUES ('tour', 1);
INSERT INTO lab03_ex2.nVideosBytag(tag, nVideos) VALUES ('mountain', 1);
INSERT INTO lab03_ex2.nVideosBytag(tag, nVideos) VALUES ('hike', 1);
INSERT INTO lab03_ex2.nVideosBytag(tag, nVideos) VALUES ('beach', 1);
INSERT INTO lab03_ex2.nVideosBytag(tag, nVideos) VALUES ('day', 1);
INSERT INTO lab03_ex2.nVideosBytag(tag, nVideos) VALUES ('cooking', 1);
INSERT INTO lab03_ex2.nVideosBytag(tag, nVideos) VALUES ('pasta', 1);
INSERT INTO lab03_ex2.nVideosBytag(tag, nVideos) VALUES ('yoga', 1);
INSERT INTO lab03_ex2.nVideosBytag(tag, nVideos) VALUES ('session', 1);
INSERT INTO lab03_ex2.nVideosBytag(tag, nVideos) VALUES ('guitar', 1);
INSERT INTO lab03_ex2.nVideosBytag(tag, nVideos) VALUES ('lesson', 1);
INSERT INTO lab03_ex2.nVideosBytag(tag, nVideos) VALUES ('art', 1);
INSERT INTO lab03_ex2.nVideosBytag(tag, nVideos) VALUES ('cooking', 1);
INSERT INTO lab03_ex2.nVideosBytag(tag, nVideos) VALUES ('tutorial', 2);
INSERT INTO lab03_ex2.nVideosBytag(tag, nVideos) VALUES ('science', 1);
INSERT INTO lab03_ex2.nVideosBytag(tag, nVideos) VALUES ('experiment', 1);
INSERT INTO lab03_ex2.nVideosBytag(tag, nVideos) VALUES ('basketball', 1);
INSERT INTO lab03_ex2.nVideosBytag(tag, nVideos) VALUES ('game', 1);
INSERT INTO lab03_ex2.nVideosBytag(tag, nVideos) VALUES ('gardening', 1);
INSERT INTO lab03_ex2.nVideosBytag(tag, nVideos) VALUES ('tips', 1);
INSERT INTO lab03_ex2.nVideosBytag(tag, nVideos) VALUES ('photography', 1);
INSERT INTO lab03_ex2.nVideosBytag(tag, nVideos) VALUES ('guide', 1);
INSERT INTO lab03_ex2.nVideosBytag(tag, nVideos) VALUES ('coding', 1);
INSERT INTO lab03_ex2.nVideosBytag(tag, nVideos) VALUES ('fitness', 1);
INSERT INTO lab03_ex2.nVideosBytag(tag, nVideos) VALUES ('workout', 1);
INSERT INTO lab03_ex2.nVideosBytag(tag, nVideos) VALUES ('travel', 1);
INSERT INTO lab03_ex2.nVideosBytag(tag, nVideos) VALUES ('vlog', 1);
INSERT INTO lab03_ex2.nVideosBytag(tag, nVideos) VALUES ('run', 1);

INSERT INTO lab03_ex2.comments (video_id, user_id, comment, timestamp)
VALUES (1, 'diana', 'Adorei este vídeo!', '2022-01-25 15:35:00.000');
INSERT INTO lab03_ex2.comments (video_id, user_id, comment, timestamp)
VALUES (9, 'jose', 'Muito informativo, obrigado!', '2022-02-12 10:15:00.000');
INSERT INTO lab03_ex2.comments (video_id, user_id, comment, timestamp)
VALUES (3, 'diogo', 'Excelente conteúdo!', '2022-03-23 11:25:00.000');
INSERT INTO lab03_ex2.comments (video_id, user_id, comment, timestamp)
VALUES (8, 'marta', 'Aprendi muito com este vídeo.', '2022-04-14 12:35:00.000');
INSERT INTO lab03_ex2.comments (video_id, user_id, comment, timestamp)
VALUES (6, 'luis', 'Continuem com o bom trabalho!', '2022-05-05 13:45:00.000');
INSERT INTO lab03_ex2.comments (video_id, user_id, comment, timestamp)
VALUES (7, 'ana', 'Este vídeo ajudou-me bastante.', '2022-06-16 14:55:00.000');
INSERT INTO lab03_ex2.comments (video_id, user_id, comment, timestamp)
VALUES (10, 'pedro', 'Muito bem explicado.', '2022-07-27 15:05:00.000');
INSERT INTO lab03_ex2.comments (video_id, user_id, comment, timestamp)
VALUES (2, 'maria', 'Adorei a explicação!', '2022-08-28 16:15:00.000');
INSERT INTO lab03_ex2.comments (video_id, user_id, comment, timestamp)
VALUES (12, 'joao', 'Vídeo muito útil.', '2022-09-18 17:25:00.000');
INSERT INTO lab03_ex2.comments (video_id, user_id, comment, timestamp)
VALUES (15, 'isabel', 'Obrigado por compartilhar.', '2022-10-29 18:35:00.000');
INSERT INTO lab03_ex2.comments (video_id, user_id, comment, timestamp)
VALUES (11, 'carlos', 'Vou recomendar este vídeo.', '2022-11-19 19:45:00.000');
INSERT INTO lab03_ex2.comments (video_id, user_id, comment, timestamp)
VALUES (4, 'sara', 'Gostei muito deste vídeo.', '2022-12-30 20:55:00.000');
INSERT INTO lab03_ex2.comments (video_id, user_id, comment, timestamp)
VALUES (5, 'antonio', 'Muito bem feito!', '2023-01-20 21:05:00.000');
INSERT INTO lab03_ex2.comments (video_id, user_id, comment, timestamp)
VALUES (13, 'rita', 'Vídeo muito interessante.', '2023-02-11 22:15:00.000');
INSERT INTO lab03_ex2.comments (video_id, user_id, comment, timestamp)
VALUES (14, 'manuel', 'Vou assistir mais vezes.', '2023-03-01 23:25:00.000');
INSERT INTO lab03_ex2.comments (video_id, user_id, comment, timestamp)
VALUES (1, 'maria', 'Maravilhoso!', '2023-03-01 23:25:00.000');
INSERT INTO lab03_ex2.comments (video_id, user_id, comment, timestamp)
VALUES (5, 'diana', 'Maravilhoso!', '2023-01-25 22:51:23.000');
INSERT INTO lab03_ex2.comments (video_id, user_id, comment, timestamp)
VALUES (14, 'diana', 'Muito Bom.', '2023-02-12 09:03:14.245');
INSERT INTO lab03_ex2.comments (video_id, user_id, comment, timestamp)
VALUES (1, 'luis', 'Muito Bom.', '2023-02-12 09:03:14.245');
INSERT INTO lab03_ex2.comments (video_id, user_id, comment, timestamp)
VALUES (1, 'rita', 'Perfeito', '2022-01-26 09:03:14.245');

INSERT INTO lab03_ex2.commentsByUser (video_id, user_id, comment, timestamp)
VALUES (1, 'diana', 'Adorei este vídeo!', '2022-01-25 15:35:00.000');
INSERT INTO lab03_ex2.commentsByUser (video_id, user_id, comment, timestamp)
VALUES (9, 'jose', 'Muito informativo, obrigado!', '2022-02-12 10:15:00.000');
INSERT INTO lab03_ex2.commentsByUser (video_id, user_id, comment, timestamp)
VALUES (3, 'diogo', 'Excelente conteúdo!', '2022-03-23 11:25:00.000');
INSERT INTO lab03_ex2.commentsByUser (video_id, user_id, comment, timestamp)
VALUES (8, 'marta', 'Aprendi muito com este vídeo.', '2022-04-14 12:35:00.000');
INSERT INTO lab03_ex2.commentsByUser (video_id, user_id, comment, timestamp)
VALUES (6, 'luis', 'Continuem com o bom trabalho!', '2022-05-05 13:45:00.000');
INSERT INTO lab03_ex2.commentsByUser (video_id, user_id, comment, timestamp)
VALUES (7, 'ana', 'Este vídeo ajudou-me bastante.', '2022-06-16 14:55:00.000');
INSERT INTO lab03_ex2.commentsByUser (video_id, user_id, comment, timestamp)
VALUES (10, 'pedro', 'Muito bem explicado.', '2022-07-27 15:05:00.000');
INSERT INTO lab03_ex2.commentsByUser (video_id, user_id, comment, timestamp)
VALUES (2, 'maria', 'Adorei a explicação!', '2022-08-28 16:15:00.000');
INSERT INTO lab03_ex2.commentsByUser (video_id, user_id, comment, timestamp)
VALUES (12, 'joao', 'Vídeo muito útil.', '2022-09-18 17:25:00.000');
INSERT INTO lab03_ex2.commentsByUser (video_id, user_id, comment, timestamp)
VALUES (15, 'isabel', 'Obrigado por compartilhar.', '2022-10-29 18:35:00.000');
INSERT INTO lab03_ex2.commentsByUser (video_id, user_id, comment, timestamp)
VALUES (11, 'carlos', 'Vou recomendar este vídeo.', '2022-11-19 19:45:00.000');
INSERT INTO lab03_ex2.commentsByUser (video_id, user_id, comment, timestamp)
VALUES (4, 'sara', 'Gostei muito deste vídeo.', '2022-12-30 20:55:00.000');
INSERT INTO lab03_ex2.commentsByUser (video_id, user_id, comment, timestamp)
VALUES (5, 'antonio', 'Muito bem feito!', '2023-01-20 21:05:00.000');
INSERT INTO lab03_ex2.commentsByUser (video_id, user_id, comment, timestamp)
VALUES (13, 'rita', 'Vídeo muito interessante.', '2023-02-11 22:15:00.000');
INSERT INTO lab03_ex2.commentsByUser (video_id, user_id, comment, timestamp)
VALUES (14, 'manuel', 'Vou assistir mais vezes.', '2023-03-01 23:25:00.000');
INSERT INTO lab03_ex2.commentsByUser (video_id, user_id, comment, timestamp)
VALUES (1, 'maria', 'Maravilhoso!', '2023-03-01 23:25:00.000');
INSERT INTO lab03_ex2.commentsByUser (video_id, user_id, comment, timestamp)
VALUES (5, 'diana', 'Maravilhoso!', '2023-01-25 22:51:23.000');
INSERT INTO lab03_ex2.commentsByUser (video_id, user_id, comment, timestamp)
VALUES (14, 'diana', 'Muito Bom.', '2023-02-12 09:03:14.245');
INSERT INTO lab03_ex2.commentsByUser (video_id, user_id, comment, timestamp)
VALUES (1, 'luis', 'Muito Bom.', '2023-02-12 09:03:14.245');
INSERT INTO lab03_ex2.commentsByUser (video_id, user_id, comment, timestamp)
VALUES (1, 'rita', 'Perfeito', '2022-01-26 09:03:14.245');

INSERT INTO lab03_ex2.commentsByVideo (video_id, user_id, comment, timestamp)
VALUES (1, 'diana', 'Adorei este vídeo!', '2022-01-25 15:35:00.000');
INSERT INTO lab03_ex2.commentsByVideo (video_id, user_id, comment, timestamp)
VALUES (9, 'jose', 'Muito informativo, obrigado!', '2022-02-12 10:15:00.000');
INSERT INTO lab03_ex2.commentsByVideo (video_id, user_id, comment, timestamp)
VALUES (3, 'diogo', 'Excelente conteúdo!', '2022-03-23 11:25:00.000');
INSERT INTO lab03_ex2.commentsByVideo (video_id, user_id, comment, timestamp)
VALUES (8, 'marta', 'Aprendi muito com este vídeo.', '2022-04-14 12:35:00.000');
INSERT INTO lab03_ex2.commentsByVideo (video_id, user_id, comment, timestamp)
VALUES (6, 'luis', 'Continuem com o bom trabalho!', '2022-05-05 13:45:00.000');
INSERT INTO lab03_ex2.commentsByVideo (video_id, user_id, comment, timestamp)
VALUES (7, 'ana', 'Este vídeo ajudou-me bastante.', '2022-06-16 14:55:00.000');
INSERT INTO lab03_ex2.commentsByVideo (video_id, user_id, comment, timestamp)
VALUES (10, 'pedro', 'Muito bem explicado.', '2022-07-27 15:05:00.000');
INSERT INTO lab03_ex2.commentsByVideo (video_id, user_id, comment, timestamp)
VALUES (2, 'maria', 'Adorei a explicação!', '2022-08-28 16:15:00.000');
INSERT INTO lab03_ex2.commentsByVideo (video_id, user_id, comment, timestamp)
VALUES (12, 'joao', 'Vídeo muito útil.', '2022-09-18 17:25:00.000');
INSERT INTO lab03_ex2.commentsByVideo (video_id, user_id, comment, timestamp)
VALUES (15, 'isabel', 'Obrigado por compartilhar.', '2022-10-29 18:35:00.000');
INSERT INTO lab03_ex2.commentsByVideo (video_id, user_id, comment, timestamp)
VALUES (11, 'carlos', 'Vou recomendar este vídeo.', '2022-11-19 19:45:00.000');
INSERT INTO lab03_ex2.commentsByVideo (video_id, user_id, comment, timestamp)
VALUES (4, 'sara', 'Gostei muito deste vídeo.', '2022-12-30 20:55:00.000');
INSERT INTO lab03_ex2.commentsByVideo (video_id, user_id, comment, timestamp)
VALUES (5, 'antonio', 'Muito bem feito!', '2023-01-20 21:05:00.000');
INSERT INTO lab03_ex2.commentsByVideo (video_id, user_id, comment, timestamp)
VALUES (13, 'rita', 'Vídeo muito interessante.', '2023-02-11 22:15:00.000');
INSERT INTO lab03_ex2.commentsByVideo (video_id, user_id, comment, timestamp)
VALUES (14, 'manuel', 'Vou assistir mais vezes.', '2023-03-01 23:25:00.000');
INSERT INTO lab03_ex2.commentsByVideo (video_id, user_id, comment, timestamp)
VALUES (1, 'maria', 'Maravilhoso!', '2023-03-01 23:25:00.000');
INSERT INTO lab03_ex2.commentsByVideo (video_id, user_id, comment, timestamp)
VALUES (5, 'diana', 'Maravilhoso!', '2023-01-25 22:51:23.000');
INSERT INTO lab03_ex2.commentsByVideo (video_id, user_id, comment, timestamp)
VALUES (14, 'diana', 'Muito Bom.', '2023-02-12 09:03:14.245');
INSERT INTO lab03_ex2.commentsByVideo (video_id, user_id, comment, timestamp)
VALUES (1, 'luis', 'Muito Bom.', '2023-02-12 09:03:14.245');
INSERT INTO lab03_ex2.commentsByVideo (video_id, user_id, comment, timestamp)
VALUES (1, 'rita', 'Perfeito', '2022-01-26 09:03:14.245');

INSERT INTO lab03_ex2.commentsByFollowers (video_id, user_id, comments)
VALUES (1, 'diana', {'diana':['Adorei este vídeo!'],'maria' : ['Maravilhoso!'], 'luis' : ['Muito Bom.'], 'rita' : ['Perfeito'] });
INSERT INTO lab03_ex2.commentsByFollowers (video_id, user_id, comments)
VALUES (1, 'jose', {'diana':['Adorei este vídeo!'],'maria' : ['Maravilhoso!'], 'luis' : ['Muito Bom.'], 'rita' : ['Perfeito'] });
INSERT INTO lab03_ex2.commentsByFollowers (video_id, user_id, comments)
VALUES (1, 'diogo', {'diana':['Adorei este vídeo!'],'maria' : ['Maravilhoso!'], 'luis' : ['Muito Bom.'], 'rita' : ['Perfeito'] });
INSERT INTO lab03_ex2.commentsByFollowers (video_id, user_id, comments)
VALUES (1, 'marta', {'diana':['Adorei este vídeo!'],'maria' : ['Maravilhoso!'], 'luis' : ['Muito Bom.'], 'rita' : ['Perfeito'] });
INSERT INTO lab03_ex2.commentsByFollowers (video_id, user_id, comments)
VALUES (1, 'luis', {'diana':['Adorei este vídeo!'],'maria' : ['Maravilhoso!'], 'luis' : ['Muito Bom.'], 'rita' : ['Perfeito'] });
INSERT INTO lab03_ex2.commentsByFollowers (video_id, user_id, comments)
VALUES (1, 'ana', {'diana':['Adorei este vídeo!'],'maria' : ['Maravilhoso!'], 'luis' : ['Muito Bom.'], 'rita' : ['Perfeito'] });
INSERT INTO lab03_ex2.commentsByFollowers (video_id, user_id, comments)
VALUES (2, 'marta', {'maria' : ['Adorei a explicação!']});
INSERT INTO lab03_ex2.commentsByFollowers (video_id, user_id, comments)
VALUES (2, 'luis', {'maria' : ['Adorei a explicação!']});
INSERT INTO lab03_ex2.commentsByFollowers (video_id, user_id, comments)
VALUES (2, 'ana', {'maria' : ['Adorei a explicação!']});
INSERT INTO lab03_ex2.commentsByFollowers (video_id, user_id, comments)
VALUES (3, 'pedro', {'diogo' : ['Excelente conteúdo!']});
INSERT INTO lab03_ex2.commentsByFollowers (video_id, user_id, comments)
VALUES (3, 'joao', {'diogo' : ['Excelente conteúdo!']});
INSERT INTO lab03_ex2.commentsByFollowers (video_id, user_id, comments)
VALUES (4, 'isabel', {'sara' : ['Gostei muito deste vídeo.']});
INSERT INTO lab03_ex2.commentsByFollowers (video_id, user_id, comments)
VALUES (5, 'antonio', {'antonio' : ['Muito bem feito!'], 'diana':['Maravilhoso!']});
INSERT INTO lab03_ex2.commentsByFollowers (video_id, user_id, comments)
VALUES (5, 'rita', {'antonio' : ['Muito bem feito!'], 'diana':['Maravilhoso!']});
INSERT INTO lab03_ex2.commentsByFollowers (video_id, user_id, comments)
VALUES (5, 'manuel', {'antonio' : ['Muito bem feito!'], 'diana':['Maravilhoso!']});
INSERT INTO lab03_ex2.commentsByFollowers (video_id, user_id, comments)
VALUES (6, 'diana', {'luis' : ['Continuem com o bom trabalho!']});
INSERT INTO lab03_ex2.commentsByFollowers (video_id, user_id, comments)
VALUES (6, 'marta', {'luis' : ['Continuem com o bom trabalho!']});
INSERT INTO lab03_ex2.commentsByFollowers (video_id, user_id, comments)
VALUES (6, 'luis', {'luis' : ['Continuem com o bom trabalho!']});
INSERT INTO lab03_ex2.commentsByFollowers (video_id, user_id, comments)
VALUES (7, 'ana', {'ana' : ['Este vídeo ajudou-me bastante.']});
INSERT INTO lab03_ex2.commentsByFollowers (video_id, user_id, comments)
VALUES (7, 'pedro', {'ana' : ['Este vídeo ajudou-me bastante.']});
INSERT INTO lab03_ex2.commentsByFollowers (video_id, user_id, comments)
VALUES (8, 'joao', {'marta' : ['Aprendi muito com este vídeo.']});
INSERT INTO lab03_ex2.commentsByFollowers (video_id, user_id, comments)
VALUES (8, 'isabel',  {'marta' : ['Aprendi muito com este vídeo.']});
INSERT INTO lab03_ex2.commentsByFollowers (video_id, user_id, comments)
VALUES (8, 'carlos',  {'marta' : ['Aprendi muito com este vídeo.']});
INSERT INTO lab03_ex2.commentsByFollowers (video_id, user_id, comments)
VALUES (9, 'sara', {'jose' : ['Muito informativo, obrigado!']});
INSERT INTO lab03_ex2.commentsByFollowers (video_id, user_id, comments)
VALUES (9, 'antonio',{'jose' : ['Muito informativo, obrigado!']});
INSERT INTO lab03_ex2.commentsByFollowers (video_id, user_id, comments)
VALUES (9, 'rita', {'jose' : ['Muito informativo, obrigado!']});
INSERT INTO lab03_ex2.commentsByFollowers (video_id, user_id, comments)
VALUES (10, 'manuel', {'pedro' : ['Muito bem explicado.']});
INSERT INTO lab03_ex2.commentsByFollowers (video_id, user_id, comments)
VALUES (10, 'diana', {'pedro' : ['Muito bem explicado.']});
INSERT INTO lab03_ex2.commentsByFollowers (video_id, user_id, comments)
VALUES (10, 'jose', {'pedro' : ['Muito bem explicado.']});
INSERT INTO lab03_ex2.commentsByFollowers (video_id, user_id, comments)
VALUES (10, 'marta', {'pedro' : ['Muito bem explicado.']});
INSERT INTO lab03_ex2.commentsByFollowers (video_id, user_id, comments)
VALUES (10, 'luis', {'pedro' : ['Muito bem explicado.']});
INSERT INTO lab03_ex2.commentsByFollowers (video_id, user_id, comments)
VALUES (10, 'ana', {'pedro' : ['Muito bem explicado.']});
INSERT INTO lab03_ex2.commentsByFollowers (video_id, user_id, comments)
VALUES (11, 'diogo', {'carlos' : ['Vou recomendar este vídeo.']});
INSERT INTO lab03_ex2.commentsByFollowers (video_id, user_id, comments)
VALUES (11, 'marta', {'carlos' : ['Vou recomendar este vídeo.']});
INSERT INTO lab03_ex2.commentsByFollowers (video_id, user_id, comments)
VALUES (11, 'luis', {'carlos' : ['Vou recomendar este vídeo.']});
INSERT INTO lab03_ex2.commentsByFollowers (video_id, user_id, comments)
VALUES (12, 'ana', {'joao' : ['Vídeo muito útil.']});
INSERT INTO lab03_ex2.commentsByFollowers (video_id, user_id, comments)
VALUES (12, 'pedro', {'joao' : ['Vídeo muito útil.']});
INSERT INTO lab03_ex2.commentsByFollowers (video_id, user_id, comments)
VALUES (12, 'maria', {'joao' : ['Vídeo muito útil.']});
INSERT INTO lab03_ex2.commentsByFollowers (video_id, user_id, comments)
VALUES (13, 'joao', {'rita' : ['Vídeo muito interessante.']});
INSERT INTO lab03_ex2.commentsByFollowers (video_id, user_id, comments)
VALUES (13, 'isabel', {'rita' : ['Vídeo muito interessante.']});
INSERT INTO lab03_ex2.commentsByFollowers (video_id, user_id, comments)
VALUES (13, 'carlos', {'rita' : ['Vídeo muito interessante.']});
INSERT INTO lab03_ex2.commentsByFollowers (video_id, user_id, comments)
VALUES (14, 'sara', {'manuel' : ['Vou assistir mais vezes.'], 'diana': ['Muito Bom.']});
INSERT INTO lab03_ex2.commentsByFollowers (video_id, user_id, comments)
VALUES (14, 'antonio', {'manuel' : ['Vou assistir mais vezes.'], 'diana': ['Muito Bom.']});
INSERT INTO lab03_ex2.commentsByFollowers (video_id, user_id, comments)
VALUES (14, 'rita', {'manuel' : ['Vou assistir mais vezes.'], 'diana': ['Muito Bom.']});
INSERT INTO lab03_ex2.commentsByFollowers (video_id, user_id, comments)
VALUES (14, 'pedro', {'manuel' : ['Vou assistir mais vezes.'], 'diana': ['Muito Bom.']});
INSERT INTO lab03_ex2.commentsByFollowers (video_id, user_id, comments)
VALUES (14, 'maria', {'manuel' : ['Vou assistir mais vezes.'], 'diana': ['Muito Bom.']});
INSERT INTO lab03_ex2.commentsByFollowers (video_id, user_id, comments)
VALUES (14, 'manuel', {'manuel' : ['Vou assistir mais vezes.'], 'diana': ['Muito Bom.']});
INSERT INTO lab03_ex2.commentsByFollowers (video_id, user_id, comments)
VALUES (15, 'manuel', {'isabel' : ['Obrigado por compartilhar.']});
INSERT INTO lab03_ex2.commentsByFollowers (video_id, user_id, comments)
VALUES (15, 'diana', {'isabel' : ['Obrigado por compartilhar.']});
INSERT INTO lab03_ex2.commentsByFollowers (video_id, user_id, comments)
VALUES (15, 'jose', {'isabel' : ['Obrigado por compartilhar.']});
INSERT INTO lab03_ex2.commentsByFollowers (video_id, user_id, comments)
VALUES (15, 'pedro', {'isabel' : ['Obrigado por compartilhar.']});
INSERT INTO lab03_ex2.commentsByFollowers (video_id, user_id, comments)
VALUES (15, 'maria', {'isabel' : ['Obrigado por compartilhar.']});

INSERT INTO lab03_ex2.video_followers (video_id, follower)
VALUES (1, 'diana');
INSERT INTO lab03_ex2.video_followers (video_id, follower)
VALUES (1, 'jose');
INSERT INTO lab03_ex2.video_followers (video_id, follower)
VALUES (1, 'diogo');
INSERT INTO lab03_ex2.video_followers (video_id, follower)
VALUES (1, 'marta');
INSERT INTO lab03_ex2.video_followers (video_id, follower)
VALUES (1, 'luis');
INSERT INTO lab03_ex2.video_followers (video_id, follower)
VALUES (1, 'ana');
INSERT INTO lab03_ex2.video_followers (video_id, follower)
VALUES (2, 'marta');
INSERT INTO lab03_ex2.video_followers (video_id, follower)
VALUES (2, 'luis');
INSERT INTO lab03_ex2.video_followers (video_id, follower)
VALUES (2,  'ana');
INSERT INTO lab03_ex2.video_followers (video_id, follower)
VALUES (3, 'pedro');
INSERT INTO lab03_ex2.video_followers (video_id, follower)
VALUES (3,  'joao');
INSERT INTO lab03_ex2.video_followers (video_id, follower)
VALUES (4, 'isabel');
INSERT INTO lab03_ex2.video_followers (video_id, follower)
VALUES (5, 'antonio');
INSERT INTO lab03_ex2.video_followers (video_id, follower)
VALUES (5,  'rita');
INSERT INTO lab03_ex2.video_followers (video_id, follower)
VALUES (5,'manuel');
INSERT INTO lab03_ex2.video_followers (video_id, follower)
VALUES (6, 'diana');
INSERT INTO lab03_ex2.video_followers (video_id, follower)
VALUES (6, 'marta');
INSERT INTO lab03_ex2.video_followers (video_id, follower)
VALUES (6, 'luis');
INSERT INTO lab03_ex2.video_followers (video_id, follower)
VALUES (7, 'ana');
INSERT INTO lab03_ex2.video_followers (video_id, follower)
VALUES (7, 'pedro');
INSERT INTO lab03_ex2.video_followers (video_id, follower)
VALUES (8, 'joao');
INSERT INTO lab03_ex2.video_followers (video_id, follower)
VALUES (8,'isabel');
INSERT INTO lab03_ex2.video_followers (video_id, follower)
VALUES (8, 'carlos');
INSERT INTO lab03_ex2.video_followers (video_id, follower)
VALUES (9, 'sara');
INSERT INTO lab03_ex2.video_followers (video_id, follower)
VALUES (9,  'antonio');
INSERT INTO lab03_ex2.video_followers (video_id, follower)
VALUES (9, 'rita');
INSERT INTO lab03_ex2.video_followers (video_id, follower)
VALUES (10, 'manuel');
INSERT INTO lab03_ex2.video_followers (video_id, follower)
VALUES (10,  'diana');
INSERT INTO lab03_ex2.video_followers (video_id, follower)
VALUES (10, 'jose');
INSERT INTO lab03_ex2.video_followers (video_id, follower)
VALUES (10,  'marta');
INSERT INTO lab03_ex2.video_followers (video_id, follower)
VALUES (10,  'luis');
INSERT INTO lab03_ex2.video_followers (video_id, follower)
VALUES (10, 'ana');
INSERT INTO lab03_ex2.video_followers (video_id, follower)
VALUES (11, 'diogo');
INSERT INTO lab03_ex2.video_followers (video_id, follower)
VALUES (11,  'marta');
INSERT INTO lab03_ex2.video_followers (video_id, follower)
VALUES (11, 'luis');
INSERT INTO lab03_ex2.video_followers (video_id, follower)
VALUES (12, 'ana');
INSERT INTO lab03_ex2.video_followers (video_id, follower)
VALUES (12,  'pedro');
INSERT INTO lab03_ex2.video_followers (video_id, follower)
VALUES (12, 'maria');
INSERT INTO lab03_ex2.video_followers (video_id, follower)
VALUES (13,'carlos');
INSERT INTO lab03_ex2.video_followers (video_id, follower)
VALUES (13, 'joao');
INSERT INTO lab03_ex2.video_followers (video_id, follower)
VALUES (13, 'isabel');
INSERT INTO lab03_ex2.video_followers (video_id, follower)
VALUES (14,'sara');
INSERT INTO lab03_ex2.video_followers (video_id, follower)
VALUES (14, 'antonio');
INSERT INTO lab03_ex2.video_followers (video_id, follower)
VALUES (14, 'rita');
INSERT INTO lab03_ex2.video_followers (video_id, follower)
VALUES (14, 'pedro');
INSERT INTO lab03_ex2.video_followers (video_id, follower)
VALUES (14,  'maria');
INSERT INTO lab03_ex2.video_followers (video_id, follower)
VALUES (14,  'manuel');
INSERT INTO lab03_ex2.video_followers (video_id, follower)
VALUES (15, 'manuel');
INSERT INTO lab03_ex2.video_followers (video_id, follower)
VALUES (15, 'diana');
INSERT INTO lab03_ex2.video_followers (video_id, follower)
VALUES (15,'jose');
INSERT INTO lab03_ex2.video_followers (video_id, follower)
VALUES (15,  'pedro');
INSERT INTO lab03_ex2.video_followers (video_id, follower)
VALUES (15, 'maria');

INSERT INTO lab03_ex2.events (video_id, user_id, type, videoTime, eventTime)
VALUES (1, 'diana', 'play', 0, '2022-01-25 15:35:00.000');
INSERT INTO lab03_ex2.events (video_id, user_id, type, videoTime, eventTime)
VALUES (1, 'marta', 'play', 0, '2022-02-12 10:15:00.000');
INSERT INTO lab03_ex2.events (video_id, user_id, type, videoTime, eventTime)
VALUES (1, 'diana', 'stop', 30, '2022-03-23 11:25:00.000');
INSERT INTO lab03_ex2.events (video_id, user_id, type, videoTime, eventTime)
VALUES (2, 'jose', 'play', 0, '2022-04-14 12:35:00.000');
INSERT INTO lab03_ex2.events (video_id, user_id, type, videoTime, eventTime)
VALUES (2, 'jose', 'pause', 20, '2022-05-05 13:45:00.000');
INSERT INTO lab03_ex2.events (video_id, user_id, type, videoTime, eventTime)
VALUES (2, 'jose', 'stop', 40, '2022-06-16 14:55:00.000');
INSERT INTO lab03_ex2.events (video_id, user_id, type, videoTime, eventTime)
VALUES (3, 'diogo', 'play', 0, '2022-07-27 15:05:00.000');
INSERT INTO lab03_ex2.events (video_id, user_id, type, videoTime, eventTime)
VALUES (8, 'isabel', 'play', 0, '2022-08-28 16:15:00.000');
INSERT INTO lab03_ex2.events (video_id, user_id, type, videoTime, eventTime)
VALUES (3, 'diogo', 'pause', 25, '2022-09-18 17:25:00.000');
INSERT INTO lab03_ex2.events (video_id, user_id, type, videoTime, eventTime)
VALUES (3, 'diogo', 'stop', 50, '2022-10-29 18:35:00.000');
INSERT INTO lab03_ex2.events (video_id, user_id, type, videoTime, eventTime)
VALUES (8, 'isabel', 'pause', 30, '2022-11-19 19:45:00.000');
INSERT INTO lab03_ex2.events (video_id, user_id, type, videoTime, eventTime)
VALUES (4, 'marta', 'pause', 30, '2022-12-30 20:55:00.000');
INSERT INTO lab03_ex2.events (video_id, user_id, type, videoTime, eventTime)
VALUES (5, 'luis', 'play', 0, '2023-01-20 21:05:00.000');
INSERT INTO lab03_ex2.events (video_id, user_id, type, videoTime, eventTime)
VALUES (5, 'luis', 'pause', 35, '2023-02-11 22:15:00.000');
INSERT INTO lab03_ex2.events (video_id, user_id, type, videoTime, eventTime)
VALUES (5, 'luis', 'stop', 70, '2023-03-01 23:25:00.000');
INSERT INTO lab03_ex2.events (video_id, user_id, type, videoTime, eventTime)
VALUES (14, 'manuel', 'play', 0, '2023-04-01 00:35:00.000');
INSERT INTO lab03_ex2.events (video_id, user_id, type, videoTime, eventTime)
VALUES (8, 'isabel', 'play', 30, '2023-05-01 01:45:00.000');
INSERT INTO lab03_ex2.events (video_id, user_id, type, videoTime, eventTime)
VALUES (1, 'diana', 'play', 30, '2023-06-01 02:55:00.000');
INSERT INTO lab03_ex2.events (video_id, user_id, type, videoTime, eventTime)
VALUES (1, 'diana', 'pause', 52, '2023-07-01 03:05:00.000');
INSERT INTO lab03_ex2.events (video_id, user_id, type, videoTime, eventTime)
VALUES (1, 'diana', 'play', 52, '2023-08-01 04:15:00.000');
INSERT INTO lab03_ex2.events (video_id, user_id, type, videoTime, eventTime)
VALUES (1, 'diana', 'stop', 80, '2023-09-01 05:25:00.000');

INSERT INTO lab03_ex2.ratings (user_id, video_id, rating)
VALUES ('diana', 1, 5);
INSERT INTO lab03_ex2.ratings (user_id, video_id, rating)
VALUES ('jose', 1, 4);
INSERT INTO lab03_ex2.ratings (user_id, video_id, rating)
VALUES ('diogo', 1, 5);
INSERT INTO lab03_ex2.ratings (user_id, video_id, rating)
VALUES ('marta', 1, 4);
INSERT INTO lab03_ex2.ratings (user_id, video_id, rating)
VALUES ('luis', 1, 5);
INSERT INTO lab03_ex2.ratings (user_id, video_id, rating)
VALUES ('ana', 1, 3);
INSERT INTO lab03_ex2.ratings (user_id, video_id, rating)
VALUES ('pedro', 7, 4);
INSERT INTO lab03_ex2.ratings (user_id, video_id, rating)
VALUES ('maria', 8, 5);
INSERT INTO lab03_ex2.ratings (user_id, video_id, rating)
VALUES ('joao', 9, 4);
INSERT INTO lab03_ex2.ratings (user_id, video_id, rating)
VALUES ('isabel', 10, 3);
INSERT INTO lab03_ex2.ratings (user_id, video_id, rating)
VALUES ('carlos', 11, 5);
INSERT INTO lab03_ex2.ratings (user_id, video_id, rating)
VALUES ('sara', 12, 4);
INSERT INTO lab03_ex2.ratings (user_id, video_id, rating)
VALUES ('antonio', 13, 3);
INSERT INTO lab03_ex2.ratings (user_id, video_id, rating)
VALUES ('rita', 14, 5);
INSERT INTO lab03_ex2.ratings (user_id, video_id, rating)
VALUES ('manuel', 15, 4);

