DROP KEYSPACE IF EXISTS lab03_ex2;
CREATE KEYSPACE IF NOT EXISTS lab03_ex2 WITH REPLICATION = {'class' : 'SimpleStrategy', 'replication_factor' : '1'};

-- Gestão de utilizadores: entre outros, registar o username, nome, email, selo temporal de registo na plataforma
CREATE TABLE lab03_ex2.user(
    username VARCHAR PRIMARY KEY,
    name VARCHAR,
    email VARCHAR,
    date timestamp
);
-- Gestão de vídeos: entre outros, registar o autor da partilha, nome do vídeo, descrição, uma ou mais etiquetas (tag) e selo temporal de upload/partilha
CREATE TABLE lab03_ex2.video(
    id int,
    name VARCHAR,
    description text,
    author VARCHAR,
    tags set<VARCHAR>,
    uploadDate timestamp,
    PRIMARY KEY ( (id), uploadDate )
);

CREATE TABLE lab03_ex2.videoByAuthor(
    id int,
    name VARCHAR,
    description TEXT,
    author VARCHAR,
    tags set<VARCHAR>,
    uploadDate timestamp,
    PRIMARY KEY (author, id)
);

CREATE TABLE lab03_ex2.nVideosBytag(
    tag VARCHAR,
    nVideos INT,
    primary key ( tag, nVideos )
);

-- Gestão de comentários: realizados para um vídeo em determinado momento temporal e tem como autor um utilizador;
CREATE TABLE lab03_ex2.comments(
    video_id int,
    user_id VARCHAR,
    comment VARCHAR,
    timestamp timestamp,
    PRIMARY KEY (user_id,video_id, timestamp )
);

CREATE TABLE lab03_ex2.commentsByUser(
    video_id int,
    user_id VARCHAR,
    comment text,
    timestamp timestamp,
    PRIMARY KEY (user_id, timestamp )
) WITH CLUSTERING ORDER BY (timestamp DESC );

CREATE TABLE lab03_ex2.commentsByVideo(
    video_id int,
    user_id VARCHAR,
    comment VARCHAR,
    timestamp timestamp,
    PRIMARY KEY (video_id, timestamp )
) WITH CLUSTERING ORDER BY (timestamp DESC );

CREATE TABLE lab03_ex2.commentsByFollowers(
    video_id int,
    user_id text,
    comments MAP<VARCHAR, FROZEN<LIST<TEXT>>>,
    PRIMARY KEY ( user_id, video_id)
);

-- Gestão de vídeo followers: permitir o registo de utilizadores que seguem determinado vídeo.
CREATE TABLE lab03_ex2.video_followers (
    video_id int,
    follower VARCHAR,
    PRIMARY KEY (video_id, follower));

-- Registo de eventos: por vídeo e utilizador e podem ser do tipo play/pause/stop, incluindo o registo temporal do evento e o momento (temporal) em que ocorre no vídeo.
CREATE TABLE lab03_ex2.events(
    video_id INT,
    user_id VARCHAR,
    type VARCHAR, --play, pause, stop
    videoTime INT,
    eventTime timestamp,
    PRIMARY KEY ( (user_id, video_id), eventTime )
)WITH CLUSTERING ORDER BY (eventTime DESC);

-- Rating de vídeos: valor inteiro de 1-5, por vídeo e não necessita de registo do autor.
CREATE TABLE lab03_ex2.ratings(
    user_id TEXT,
    video_id int,
    rating INT,
    PRIMARY KEY ( (video_id, user_id), rating )
)WITH CLUSTERING ORDER BY (rating DESC );

