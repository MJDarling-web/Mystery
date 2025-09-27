-- schema.sql  (wrap in a transaction if you like)
BEGIN;

drop table if exists scene cascade;
drop table if exists clue cascade;
drop table if exists character cascade;
drop table if exists story cascade;
drop table if exists users cascade;

create table users (
                       id serial primary key,
                       is_admin boolean default false,
                       username text not null unique,
                       password text not null,
                       email text not null unique
);

create table story (
                       id serial primary key,
                       title text not null,
                       description text,
                       setting text,
                       creator_id int not null references users(id)
);

create table character (
                           id serial primary key,
                           story_id int not null references story(id) on delete cascade,
                           picture_url text,
                           name text not null,
                           bio text,
                           role varchar(50),
                           is_guilty boolean default false
);

create table clue (
                      id serial primary key,
                      story_id int not null references story(id) on delete cascade,
                      image_url text,
                      title text not null,
                      description text,
                      location_found text,
                      is_found boolean default false
);

create table scene (
                       id serial primary key,
                       story_id int not null references story(id) on delete cascade,
                       title text,
                       description text,
                       image_url text,
                       display_order int,
                       is_active boolean default false,
                       started_at timestamp,
                       ended_at timestamp
);

COMMIT;
