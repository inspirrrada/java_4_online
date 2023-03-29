CREATE SCHEMA `gameplay` DEFAULT CHARACTER SET utf8;

create table games
(
    id bigint auto_increment primary key,
    created datetime(6) null,
    name varchar(255) null,
    command_type boolean
);

create table players
(
    id bigint auto_increment primary key,
    created datetime(6) null,
    age int null,
    email varchar(50) null,
    nickname varchar(255) null
);

create table games_players
(
    game_id bigint not null,
    player_id bigint not null,
    primary key (game_id, player_id),
    foreign key (game_id) references games(id),
    foreign key (player_id) references players(id)
);