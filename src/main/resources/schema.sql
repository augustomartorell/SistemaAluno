-- auto-generated definition
create table aluno
(
    id            bigint auto_increment
        primary key,
    cpf           varchar(255) null,
    dt_nascimento date         null,
    email         varchar(255) null,
    nome_aluno    varchar(255) null,
    sexo          int          null,
    telefone      varchar(255) null,
    constraint UK_3wpes15e0anbfaa4do0pey97k
        unique (email),
    constraint UK_crrvmtky7d9tfarahi4jahewg
        unique (cpf)
);


-- auto-generated definition
create table perfil
(
    id   bigint auto_increment
        primary key,
    nome varchar(255) null
);

-- auto-generated definition
create table usuario
(
    id    bigint auto_increment
        primary key,
    nome  varchar(255) null,
    senha varchar(255) null
);

-- auto-generated definition
create table usuario_perfis
(
    usuario_id bigint not null,
    perfis_id  bigint not null,
    constraint FK7bhs80brgvo80vhme3u8m6ive
        foreign key (perfis_id) references perfil (id),
    constraint FKs91tgiyagbilt959wbufiphgc
        foreign key (usuario_id) references usuario (id)
);

