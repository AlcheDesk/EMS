CREATE TABLE osgi_bundle
    (
        path text NOT NULL,
        md5 text NOT NULL,
        name text NOT NULL,
        version text NOT NULL,
        id bigserial NOT NULL,
        PRIMARY KEY (id),
        CONSTRAINT osgibundle_uk_name_version UNIQUE (name,version),
        CONSTRAINT osgibundle_uk_md5 UNIQUE (md5),
        CONSTRAINT osgibundle_uk_path UNIQUE (path)
    );
    
CREATE TABLE worker_type
    (
        name text NOT NULL,
        id bigserial NOT NULL,
        preloaded boolean DEFAULT false NOT NULL,
        PRIMARY KEY (id),
        CONSTRAINT workertype_uk_name UNIQUE (name)
    );

INSERT INTO worker_type (name,preloaded) VALUES ('DEFAULT',true);    
ALTER TABLE "worker" ADD COLUMN worker_type_id bigint;
ALTER TABLE "worker" ADD CONSTRAINT worker_fk_worker_type FOREIGN KEY (worker_type_id) REFERENCES "worker_type" ("id");
UPDATE "worker" SET worker_type_id = 1 WHERE worker_type_id IS NULL;
ALTER TABLE "worker" ALTER COLUMN worker_type_id SET DEFAULT 1;
ALTER TABLE "worker" ALTER COLUMN worker_type_id SET NOT NULL;

CREATE TABLE worker_type_osgi_bundle_link
    (
        id bigserial NOT NULL,
        worker_type_id bigint NOT NULL,
        osgi_bundle_id bigint NOT NULL,
        PRIMARY KEY (id),
        CONSTRAINT workertypeosgibundlelink_uk_type_bundle UNIQUE (osgi_bundle_id, worker_type_id),
        CONSTRAINT workertypeosgibundlelink_fk_type FOREIGN KEY (worker_type_id) REFERENCES "worker_type" ("id"),
        CONSTRAINT workertypeosgibundlelink_fk_bundle FOREIGN KEY (osgi_bundle_id) REFERENCES "osgi_bundle" ("id")
    );