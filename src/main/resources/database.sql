-- SCRIPT DE CREACIÓN DE BASE DE DATOS
drop database if exists bd_ejercicio3_maven;
create database bd_ejercicio3_maven;
use bd_ejercicio3_maven;

-- Tabla empleados
create table empleados(
	id int auto_increment primary key,
    nombre varchar(50) not null,
    salario decimal(8,2) not null default 1221
);

-- Tabla departamentos
create table departamentos(
	id int auto_increment primary key,
    nombre varchar(50) not null,
    localidad varchar(50) not null
);

-- Tabla proyectos
create table proyectos(
	id int auto_increment primary key,
    nombre varchar(50) not null,
    presupuesto decimal(8,2) not null default 10000
); 