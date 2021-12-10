create table if not exists users
(
    id         serial primary key,
    first_name   varchar(50) not null,
    last_name    varchar(50) not null,
    password   varchar(255),
    email      varchar(100) unique,
    approved boolean default false,
    confirmed boolean default false,
    created_at date,
    rating double precision default 0.0
    );

create table if not exists roles
(
    id   serial primary key,
    name varchar(50) unique not null
    );

INSERT INTO roles(id, name) values(1, 'TRADER') ON CONFLICT DO NOTHING;
INSERT INTO roles(id, name) values(2, 'ADMIN') ON CONFLICT DO NOTHING;

create table if not exists users_roles
(
    user_id int not null,
    role_id int not null,
    primary key (user_id, role_id),
    foreign key (user_id) references users (id),
    foreign key (role_id) references roles (id)
    );

create table if not exists games
(
    id   serial primary key,
    name varchar(50)
    );

create table if not exists comments
(
    id           serial,
    trader_id    int,
    approved     boolean default false,
    message      text not null,
    created_at   date,
    rating       int  not null,
    author_email varchar,
    primary key (id, trader_id, author_email),
    foreign key (trader_id) references users (id)
    );

create table if not exists game_objects
(
    id         serial,
    title      varchar(50),
    text       text,
    status     varchar(50),
    trader_id  int,
    created_at date,
    updated_at date,
    game_id    int,
    primary key (id, trader_id, game_id),
    foreign key (game_id) references games (id),
    foreign key (trader_id) references users (id)
    );