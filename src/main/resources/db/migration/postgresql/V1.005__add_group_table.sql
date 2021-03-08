CREATE TABLE "group"
(
    id bigserial NOT NULL,
    name text NOT NULL,
    preloaded BOOLEAN DEFAULT false NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT group_uk_name UNIQUE (name)
);
    
INSERT INTO "group" (name,preloaded) VALUES ('DEFAULT',true);
INSERT INTO "group" (name,preloaded) VALUES ('DEV',true);
INSERT INTO "group" (name,preloaded) VALUES ('TEST',true);
INSERT INTO "group" (name,preloaded) VALUES ('CI',true);

ALTER TABLE "job" ADD CONSTRAINT job_fk_group FOREIGN KEY ("group_id") REFERENCES "group" ("id");
ALTER TABLE "task" ADD CONSTRAINT task_fk_group FOREIGN KEY ("group_id") REFERENCES "group" ("id");
ALTER TABLE "worker" ADD CONSTRAINT worker_fk_group FOREIGN KEY ("group_id") REFERENCES "group" ("id");