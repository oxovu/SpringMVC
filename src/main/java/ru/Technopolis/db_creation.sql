CREATE DATABASE  todo_db;

CREATE SEQUENCE public.todo_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE todo (
    id integer NOT NULL DEFAULT nextval('todo_id_seq'::regclass),
    description text NOT NULL,
    checked boolean,
    CONSTRAINT todo_pkey PRIMARY KEY (id)
);

CREATE SEQUENCE public.todo_user_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE todo_user (
    id integer NOT NULL DEFAULT nextval('todo_user_id_seq'::regclass),
    name character varying(65) NOT NULL,
    password character varying(65) NOT NULL,
    CONSTRAINT todo_user_pkey PRIMARY KEY (id)
);

CREATE TABLE todo_user_connect (
    todo_id integer NOT NULL,
    user_id integer NOT NULL,
    CONSTRAINT todo_user_connect_todo_id_fkey FOREIGN KEY (todo_id)
        REFERENCES public.todo (id) MATCH SIMPLE,
    CONSTRAINT todo_user_connect_user_id_fkey FOREIGN KEY (user_id)
        REFERENCES public.todo_user (id) MATCH SIMPLE
);