--
DROP TABLE "worker_component_link";
--
DROP TABLE "worker_type_module_link";
--
DROP TABLE "component";
--
ALTER TABLE "ems_log" DROP COLUMN "job_id";
DELETE FROM ems_log WHERE task_id IS NULL;
ALTER TABLE "ems_log" ALTER COLUMN task_id SET NOT NULL;
ALTER TABLE "ems_log" RENAME COLUMN "log" TO MESSAGE;
ALTER TABLE "ems_log" ADD PRIMARY KEY ("id");
ALTER TABLE "ems_log" RENAME CONSTRAINT "emslog_fk_task" TO task_log_fk_task;
ALTER TABLE "ems_log" RENAME TO task_log;
ALTER SEQUENCE ems_log_id_seq RENAME TO task_log_id_seq;
--
ALTER TABLE "group" ADD COLUMN is_active BOOLEAN;
UPDATE "group" SET is_active = true WHERE is_active IS NULL;
ALTER TABLE "group" ALTER COLUMN is_active SET NOT NULL;
ALTER TABLE "group" ALTER COLUMN is_active SET DEFAULT true;
ALTER TABLE "group" RENAME COLUMN "preloaded" TO is_predefined;
--
ALTER TABLE "job" RENAME COLUMN "built" TO is_built;
ALTER TABLE "job" RENAME COLUMN "finished" TO is_finished;
--
ALTER TABLE "job_type" RENAME COLUMN "preloaded" TO is_predefined;
ALTER TABLE "job_type" ADD CONSTRAINT job_type_ix_name UNIQUE ("id");
--
DROP TABLE "module";
--
ALTER TABLE "operating_system" ADD COLUMN is_active BOOLEAN;
UPDATE "operating_system" SET is_active = true WHERE is_active IS NULL;
ALTER TABLE "operating_system" ALTER COLUMN is_active SET NOT NULL;
ALTER TABLE "operating_system" ALTER COLUMN is_active SET DEFAULT true;
ALTER TABLE "operating_system" ALTER COLUMN preloaded SET NOT NULL;
ALTER TABLE "operating_system" RENAME COLUMN "preloaded" TO is_predefined;
ALTER TABLE "operating_system" ADD CONSTRAINT operating_system_ix_name UNIQUE ("name");
--
ALTER TABLE "status" ADD COLUMN is_predefined BOOLEAN;
UPDATE "status" SET is_predefined = true WHERE is_predefined IS NULL;
ALTER TABLE "status" ALTER COLUMN is_predefined SET NOT NULL;
ALTER TABLE "status" ALTER COLUMN is_predefined SET DEFAULT true;
ALTER TABLE "status" ADD COLUMN is_active BOOLEAN;
UPDATE "status" SET is_active = true WHERE is_active IS NULL;
ALTER TABLE "status" ALTER COLUMN is_active SET NOT NULL;
ALTER TABLE "status" ALTER COLUMN is_active SET DEFAULT true;
--
ALTER TABLE "task" RENAME COLUMN "cpu_core" TO cpu_core_required;
ALTER TABLE "task" RENAME COLUMN "ram" TO ram_required;
ALTER TABLE "task" RENAME COLUMN "bandwidth" TO bandwidth_required;
ALTER TABLE "task" RENAME COLUMN "finished" TO is_finished;
ALTER TABLE "task" DROP CONSTRAINT "task_fk_job";
ALTER TABLE "task" ADD CONSTRAINT task_fk_job FOREIGN KEY ("job_id") REFERENCES "job" ("id");
--
ALTER TABLE "task_type" RENAME COLUMN "preloaded" TO is_preloaded;
ALTER TABLE "task_type" ADD CONSTRAINT task_type_ix_name UNIQUE ("name");
ALTER TABLE "task_type" ADD COLUMN is_active BOOLEAN;
UPDATE "task_type" SET is_active = true WHERE is_active IS NULL;
ALTER TABLE "task_type" ALTER COLUMN is_active SET NOT NULL;
ALTER TABLE "task_type" ALTER COLUMN is_active SET DEFAULT true;
--
ALTER TABLE "worker" RENAME COLUMN "active" TO is_active;
--
ALTER TABLE "worker_type" RENAME COLUMN "preloaded" TO is_predefined;
ALTER TABLE "worker_type" RENAME CONSTRAINT "workertype_uk_name" TO worker_type_ix_name;
ALTER TABLE "worker_type" ADD COLUMN is_active BOOLEAN;
UPDATE "worker_type" SET is_active = true WHERE is_active IS NULL;
ALTER TABLE "worker_type" ALTER COLUMN is_active SET NOT NULL;
ALTER TABLE "worker_type" ALTER COLUMN is_active SET DEFAULT true;

-- create driver vendor
CREATE TABLE
    vendor
    (
        id BIGSERIAL NOT NULL,
        name TEXT NOT NULL,
        version TEXT NOT NULL,
        is_active BOOLEAN DEFAULT true NOT NULL,
        PRIMARY KEY (id)
    );

CREATE TABLE
    worker_vendor
    (
        id BIGSERIAL NOT NULL,
        worker_id BIGINT NOT NULL,
        vendor_id BIGINT NOT NULL,
        PRIMARY KEY (id),
        CONSTRAINT worker_vendor_fk_vendor FOREIGN KEY (vendor_id) REFERENCES "vendor"("id"),
        CONSTRAINT worker_vendor_fk_worker FOREIGN KEY (worker_id) REFERENCES "worker" ("id")
    );

--job log table
CREATE TABLE
    job_log
    (
        id BIGSERIAL NOT NULL,
        created_at TIMESTAMP(6) WITH TIME ZONE DEFAULT now() NOT NULL,
        log TEXT NOT NULL,
        job_id BIGINT,
        CONSTRAINT job_log_fk_job FOREIGN KEY (job_id) REFERENCES "job" ("id")
    );

-- worker log table
CREATE TABLE
    worker_log
    (
        id BIGSERIAL NOT NULL,
        created_at TIMESTAMP(6) WITH TIME ZONE DEFAULT now() NOT NULL,
        log TEXT NOT NULL,
        worker_id BIGINT,
        CONSTRAINT worker_log_fk_job FOREIGN KEY (worker_id) REFERENCES "worker" ("id")
    );

-- property table
CREATE TABLE
    property
    (
        id BIGSERIAL NOT NULL,
        KEY TEXT NOT NULL,
        value TEXT NOT NULL,
        is_active BOOLEAN DEFAULT true NOT NULL,
        is_predefined BOOLEAN DEFAULT false NOT NULL,
        PRIMARY KEY (id),
        CONSTRAINT property_uk_key UNIQUE (KEY)
    );

