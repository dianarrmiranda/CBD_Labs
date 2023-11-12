// 7. Permitir a pesquisa de todos os videos de determinado autor;
SELECT * FROM lab03_ex2.videobyauthor WHERE author='diana';

// 8. Permitir a pesquisa de comentarios por utilizador, ordenado inversamente pela data;
SELECT * FROM lab03_ex2.commentsbyuser WHERE user_id= 'diana';

// 9. Permitir a pesquisa de comentarios por videos, ordenado inversamente pela data;
SELECT * FROM lab03_ex2.commentsbyvideo WHERE video_id=14;

// 10. Permitir a pesquisa do rating medio de um video e quantas vezes foi votado;
SELECT AVG(rating) AS Media, COUNT(*) AS Total_Votos FROM lab03_ex2.ratings WHERE video_id=1;


// 1. Os ultimos 3 comentarios introduzidos para um video;
SELECT * FROM lab03_ex2.commentsbyvideo WHERE video_id=1 LIMIT 3;

// 2. Lista das tags de determinado video;
SELECT tags FROM lab03_ex2.video WHERE id=1;

// 3. Todos os videos com a tag Aveiro;
SELECT * FROM lab03_ex2.video WHERE tags CONTAINS 'Aveiro' ALLOW FILTERING;

// 4. Os ultimos 5 eventos de determinado video realizados por um utilizador;
SELECT * FROM lab03_ex2.events WHERE video_id=1 AND user_id='diana' LIMIT 5;

// 5. Videos partilhados por determinado utilizador (maria1987, por exemplo) num determinado periodo de tempo (Agosto de 2017, por exemplo);
SELECT * FROM lab03_ex2.videobyauthor WHERE author='diana' AND uploaddate > '2022-01-01' AND uploaddate < '2022-01-31'  ALLOW FILTERING ;

// 6. Os ultimos 10 videos, ordenado inversamente pela data da partilhada;
// Não é possível realizar esta querie, pois sem uma partion key não dá para ordenar.
// ORDER BY is only supported when the partition key is restricted by an EQ or an IN.

// 7. Todos os seguidores (followers) de determinado video;
SELECT * FROM lab03_ex2.video_followers WHERE video_id = 1;

// 8. Todos os comentarios (dos videos) que determinado utilizador esta a seguir (following);
SELECT * FROM lab03_ex2.commentsbyfollowers WHERE user_id = 'diana';

// 9. Os 5 videos com maior rating;
// Não é possível realizar esta querie, pois sem uma partion key não dá para ordenar.
// ORDER BY is only supported when the partition key is restricted by an EQ or an IN.

// 10. Uma query que retorne todos os videos e que mostre claramente a forma pela qual estao ordenados;
// PERGUNTAR AO STOR
// 11. Lista com as Tags existentes e o numero de videos catalogados com cada uma delas;
// FAZER NOVA TABELA?
// 12. O número de seguidores de um video
SELECT video_id, COUNT(follower) AS num_seguidores FROM lab03_ex2.video_followers WHERE video_id = 1;

// 13. Todos os vídeos com um rating superior a 4
SELECT * FROM lab03_ex2.ratings WHERE rating > 4 ALLOW FILTERING ;
