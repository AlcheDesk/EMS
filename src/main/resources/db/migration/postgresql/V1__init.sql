--
-- PostgreSQL database dump
--

-- Dumped from database version 9.6.5
-- Dumped by pg_dump version 10.0

-- Started on 2017-10-17 01:16:48

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 1 (class 3079 OID 12425)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: -
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2311 (class 0 OID 0)
-- Dependencies: 1
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: -
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


--
-- TOC entry 2 (class 3079 OID 23578)
-- Name: uuid-ossp; Type: EXTENSION; Schema: -; Owner: -
--

CREATE EXTENSION IF NOT EXISTS "uuid-ossp" WITH SCHEMA public;


--
-- TOC entry 2312 (class 0 OID 0)
-- Dependencies: 2
-- Name: EXTENSION "uuid-ossp"; Type: COMMENT; Schema: -; Owner: -
--

COMMENT ON EXTENSION "uuid-ossp" IS 'generate universally unique identifiers (UUIDs)';


SET search_path = public, pg_catalog;

SET default_with_oids = false;

--
-- TOC entry 203 (class 1259 OID 20743)
-- Name: component; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE component (
    id bigint NOT NULL,
    name text NOT NULL,
    version text NOT NULL,
    path text
);


--
-- TOC entry 202 (class 1259 OID 20741)
-- Name: component_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE component_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 2313 (class 0 OID 0)
-- Dependencies: 202
-- Name: component_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE component_id_seq OWNED BY component.id;


--
-- TOC entry 187 (class 1259 OID 20568)
-- Name: job; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE job (
    id bigint NOT NULL,
    name text NOT NULL,
    job_type_id bigint NOT NULL,
    description text,
    priority integer NOT NULL,
    uuid uuid DEFAULT uuid_generate_v4() NOT NULL,
    status_id bigint NOT NULL,
    built boolean DEFAULT false NOT NULL,
    finished boolean DEFAULT false NOT NULL,
    parameter json,
    group_id integer NOT NULL,
    updated_at timestamp with time zone DEFAULT now() NOT NULL,
    created_at timestamp with time zone DEFAULT now() NOT NULL
);


--
-- TOC entry 186 (class 1259 OID 20566)
-- Name: job_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE job_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 2314 (class 0 OID 0)
-- Dependencies: 186
-- Name: job_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE job_id_seq OWNED BY job.id;


--
-- TOC entry 207 (class 1259 OID 20781)
-- Name: job_type; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE job_type (
    name text NOT NULL,
    id bigint NOT NULL
);


--
-- TOC entry 206 (class 1259 OID 20779)
-- Name: job_type_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE job_type_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 2315 (class 0 OID 0)
-- Dependencies: 206
-- Name: job_type_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE job_type_id_seq OWNED BY job_type.id;


--
-- TOC entry 195 (class 1259 OID 20642)
-- Name: module; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE module (
    id bigint NOT NULL,
    uuid uuid NOT NULL,
    md5 text NOT NULL,
    file text NOT NULL,
    version text NOT NULL,
    service_name text NOT NULL,
    function_name text NOT NULL,
    name text NOT NULL,
    description text,
    updated_at timestamp with time zone DEFAULT now() NOT NULL,
    created_at timestamp with time zone DEFAULT now() NOT NULL
);


--
-- TOC entry 194 (class 1259 OID 20640)
-- Name: module_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE module_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 2316 (class 0 OID 0)
-- Dependencies: 194
-- Name: module_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE module_id_seq OWNED BY module.id;


--
-- TOC entry 191 (class 1259 OID 20601)
-- Name: operating_system; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE operating_system (
    id bigint NOT NULL,
    name text NOT NULL
);


--
-- TOC entry 190 (class 1259 OID 20599)
-- Name: operating_system_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE operating_system_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 2317 (class 0 OID 0)
-- Dependencies: 190
-- Name: operating_system_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE operating_system_id_seq OWNED BY operating_system.id;


--
-- TOC entry 199 (class 1259 OID 20674)
-- Name: status; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE status (
    id bigint NOT NULL,
    name text NOT NULL
);


--
-- TOC entry 198 (class 1259 OID 20672)
-- Name: status_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE status_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 2318 (class 0 OID 0)
-- Dependencies: 198
-- Name: status_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE status_id_seq OWNED BY status.id;


--
-- TOC entry 189 (class 1259 OID 20581)
-- Name: task; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE task (
    id bigint NOT NULL,
    name text NOT NULL,
    uuid uuid DEFAULT uuid_generate_v4() NOT NULL,
    priority integer NOT NULL,
    task_type_id bigint NOT NULL,
    group_id integer NOT NULL,
    cpu_core integer NOT NULL,
    ram integer NOT NULL,
    bandwidth integer DEFAULT 1 NOT NULL,
    created_at timestamp with time zone DEFAULT now() NOT NULL,
    updated_at timestamp with time zone DEFAULT now() NOT NULL,
    interactive boolean DEFAULT true NOT NULL,
    status_id bigint NOT NULL,
    log text NOT NULL,
    parameter json,
    timeout bigint DEFAULT 3600 NOT NULL,
    job_id bigint,
    finished boolean DEFAULT false NOT NULL,
    data json NOT NULL,
    operating_system_id bigint NOT NULL,
    execution_result json NOT NULL
);


--
-- TOC entry 188 (class 1259 OID 20579)
-- Name: task_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE task_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 2319 (class 0 OID 0)
-- Dependencies: 188
-- Name: task_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE task_id_seq OWNED BY task.id;


--
-- TOC entry 209 (class 1259 OID 23499)
-- Name: task_type; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE task_type (
    id bigint NOT NULL,
    name text NOT NULL
);


--
-- TOC entry 208 (class 1259 OID 23497)
-- Name: task_type_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE task_type_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 2320 (class 0 OID 0)
-- Dependencies: 208
-- Name: task_type_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE task_type_id_seq OWNED BY task_type.id;


--
-- TOC entry 201 (class 1259 OID 20712)
-- Name: task_worker_link; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE task_worker_link (
    id bigint NOT NULL,
    task_id bigint NOT NULL,
    worker_id bigint NOT NULL,
    active boolean NOT NULL
);


--
-- TOC entry 200 (class 1259 OID 20710)
-- Name: task_worker_link_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE task_worker_link_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 2321 (class 0 OID 0)
-- Dependencies: 200
-- Name: task_worker_link_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE task_worker_link_id_seq OWNED BY task_worker_link.id;


--
-- TOC entry 193 (class 1259 OID 20614)
-- Name: worker; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE worker (
    id bigint NOT NULL,
    architecture text NOT NULL,
    cpu_core integer NOT NULL,
    hostname text NOT NULL,
    bandwidth integer NOT NULL,
    ip_address text NOT NULL,
    name text NOT NULL,
    ram integer NOT NULL,
    uuid uuid DEFAULT uuid_generate_v4() NOT NULL,
    operating_system_id bigint NOT NULL,
    status_id bigint NOT NULL,
    group_id integer NOT NULL,
    active boolean NOT NULL,
    mac_address text NOT NULL,
    created_at timestamp with time zone DEFAULT now() NOT NULL,
    updated_at timestamp with time zone DEFAULT now() NOT NULL
);


--
-- TOC entry 205 (class 1259 OID 20754)
-- Name: worker_component_link; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE worker_component_link (
    id bigint NOT NULL,
    worker_id bigint NOT NULL,
    component_id bigint NOT NULL,
    active boolean NOT NULL
);


--
-- TOC entry 204 (class 1259 OID 20752)
-- Name: worker_component_link_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE worker_component_link_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 2322 (class 0 OID 0)
-- Dependencies: 204
-- Name: worker_component_link_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE worker_component_link_id_seq OWNED BY worker_component_link.id;


--
-- TOC entry 192 (class 1259 OID 20612)
-- Name: worker_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE worker_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 2323 (class 0 OID 0)
-- Dependencies: 192
-- Name: worker_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE worker_id_seq OWNED BY worker.id;


--
-- TOC entry 197 (class 1259 OID 20655)
-- Name: worker_module_link; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE worker_module_link (
    id bigint NOT NULL,
    worker_id bigint NOT NULL,
    module_id bigint NOT NULL,
    active boolean NOT NULL
);


--
-- TOC entry 196 (class 1259 OID 20653)
-- Name: worker_module_link_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE worker_module_link_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 2324 (class 0 OID 0)
-- Dependencies: 196
-- Name: worker_module_link_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE worker_module_link_id_seq OWNED BY worker_module_link.id;


--
-- TOC entry 2148 (class 2604 OID 20746)
-- Name: component id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY component ALTER COLUMN id SET DEFAULT nextval('component_id_seq'::regclass);


--
-- TOC entry 2123 (class 2604 OID 20571)
-- Name: job id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY job ALTER COLUMN id SET DEFAULT nextval('job_id_seq'::regclass);


--
-- TOC entry 2150 (class 2604 OID 23513)
-- Name: job_type id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY job_type ALTER COLUMN id SET DEFAULT nextval('job_type_id_seq'::regclass);


--
-- TOC entry 2142 (class 2604 OID 20645)
-- Name: module id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY module ALTER COLUMN id SET DEFAULT nextval('module_id_seq'::regclass);


--
-- TOC entry 2137 (class 2604 OID 20604)
-- Name: operating_system id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY operating_system ALTER COLUMN id SET DEFAULT nextval('operating_system_id_seq'::regclass);


--
-- TOC entry 2146 (class 2604 OID 20677)
-- Name: status id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY status ALTER COLUMN id SET DEFAULT nextval('status_id_seq'::regclass);


--
-- TOC entry 2129 (class 2604 OID 20584)
-- Name: task id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY task ALTER COLUMN id SET DEFAULT nextval('task_id_seq'::regclass);


--
-- TOC entry 2151 (class 2604 OID 23502)
-- Name: task_type id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY task_type ALTER COLUMN id SET DEFAULT nextval('task_type_id_seq'::regclass);


--
-- TOC entry 2147 (class 2604 OID 20715)
-- Name: task_worker_link id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY task_worker_link ALTER COLUMN id SET DEFAULT nextval('task_worker_link_id_seq'::regclass);


--
-- TOC entry 2138 (class 2604 OID 20617)
-- Name: worker id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY worker ALTER COLUMN id SET DEFAULT nextval('worker_id_seq'::regclass);


--
-- TOC entry 2149 (class 2604 OID 20757)
-- Name: worker_component_link id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY worker_component_link ALTER COLUMN id SET DEFAULT nextval('worker_component_link_id_seq'::regclass);


--
-- TOC entry 2145 (class 2604 OID 20658)
-- Name: worker_module_link id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY worker_module_link ALTER COLUMN id SET DEFAULT nextval('worker_module_link_id_seq'::regclass);


--
-- TOC entry 2169 (class 2606 OID 20751)
-- Name: component component_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY component
    ADD CONSTRAINT component_pkey PRIMARY KEY (id);


--
-- TOC entry 2163 (class 2606 OID 20660)
-- Name: worker_module_link config_group_module_link_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY worker_module_link
    ADD CONSTRAINT config_group_module_link_pkey PRIMARY KEY (id);


--
-- TOC entry 2153 (class 2606 OID 20578)
-- Name: job job_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY job
    ADD CONSTRAINT job_pkey PRIMARY KEY (id);


--
-- TOC entry 2173 (class 2606 OID 23515)
-- Name: job_type job_type_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY job_type
    ADD CONSTRAINT job_type_pkey PRIMARY KEY (id);


--
-- TOC entry 2161 (class 2606 OID 20652)
-- Name: module module_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY module
    ADD CONSTRAINT module_pkey PRIMARY KEY (id);


--
-- TOC entry 2157 (class 2606 OID 20609)
-- Name: operating_system operating_system_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY operating_system
    ADD CONSTRAINT operating_system_pkey PRIMARY KEY (id);


--
-- TOC entry 2165 (class 2606 OID 20682)
-- Name: status status_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY status
    ADD CONSTRAINT status_pkey PRIMARY KEY (id);


--
-- TOC entry 2155 (class 2606 OID 20598)
-- Name: task task_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY task
    ADD CONSTRAINT task_pkey PRIMARY KEY (id);


--
-- TOC entry 2175 (class 2606 OID 23507)
-- Name: task_type task_type_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY task_type
    ADD CONSTRAINT task_type_pkey PRIMARY KEY (id);


--
-- TOC entry 2167 (class 2606 OID 20717)
-- Name: task_worker_link task_worker_link_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY task_worker_link
    ADD CONSTRAINT task_worker_link_pkey PRIMARY KEY (id);


--
-- TOC entry 2171 (class 2606 OID 20759)
-- Name: worker_component_link worker_component_link_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY worker_component_link
    ADD CONSTRAINT worker_component_link_pkey PRIMARY KEY (id);


--
-- TOC entry 2159 (class 2606 OID 20622)
-- Name: worker worker_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY worker
    ADD CONSTRAINT worker_pkey PRIMARY KEY (id);


--
-- TOC entry 2176 (class 2606 OID 20693)
-- Name: job job_fk_status; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY job
    ADD CONSTRAINT job_fk_status FOREIGN KEY (status_id) REFERENCES status(id);


--
-- TOC entry 2177 (class 2606 OID 23636)
-- Name: job job_fk_type; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY job
    ADD CONSTRAINT job_fk_type FOREIGN KEY (job_type_id) REFERENCES job_type(id);


--
-- TOC entry 2178 (class 2606 OID 20683)
-- Name: task task_fk_job; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY task
    ADD CONSTRAINT task_fk_job FOREIGN KEY (job_id) REFERENCES job(id);


--
-- TOC entry 2180 (class 2606 OID 20698)
-- Name: task task_fk_operating_system; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY task
    ADD CONSTRAINT task_fk_operating_system FOREIGN KEY (operating_system_id) REFERENCES operating_system(id);


--
-- TOC entry 2179 (class 2606 OID 20688)
-- Name: task task_fk_status; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY task
    ADD CONSTRAINT task_fk_status FOREIGN KEY (status_id) REFERENCES status(id);


--
-- TOC entry 2181 (class 2606 OID 23508)
-- Name: task task_fk_type; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY task
    ADD CONSTRAINT task_fk_type FOREIGN KEY (task_type_id) REFERENCES task_type(id);


--
-- TOC entry 2184 (class 2606 OID 20718)
-- Name: task_worker_link task_worker_fk_task; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY task_worker_link
    ADD CONSTRAINT task_worker_fk_task FOREIGN KEY (task_id) REFERENCES task(id);


--
-- TOC entry 2185 (class 2606 OID 20723)
-- Name: task_worker_link task_worker_fk_worker; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY task_worker_link
    ADD CONSTRAINT task_worker_fk_worker FOREIGN KEY (worker_id) REFERENCES worker(id);


--
-- TOC entry 2187 (class 2606 OID 20765)
-- Name: worker_component_link worker_component_fk_component; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY worker_component_link
    ADD CONSTRAINT worker_component_fk_component FOREIGN KEY (component_id) REFERENCES component(id);


--
-- TOC entry 2186 (class 2606 OID 20760)
-- Name: worker_component_link worker_component_fk_worker; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY worker_component_link
    ADD CONSTRAINT worker_component_fk_worker FOREIGN KEY (worker_id) REFERENCES worker(id);


--
-- TOC entry 2182 (class 2606 OID 20666)
-- Name: worker_module_link worker_module_fk_module; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY worker_module_link
    ADD CONSTRAINT worker_module_fk_module FOREIGN KEY (module_id) REFERENCES module(id);


--
-- TOC entry 2183 (class 2606 OID 20736)
-- Name: worker_module_link worker_module_fk_worker; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY worker_module_link
    ADD CONSTRAINT worker_module_fk_worker FOREIGN KEY (worker_id) REFERENCES worker(id);


-- Completed on 2017-10-17 01:16:51

--
-- PostgreSQL database dump complete
--

