CREATE TABLE ems_log
    (
        id bigserial NOT NULL,
        created_at TIMESTAMP WITH TIME zone DEFAULT now() NOT NULL,
        log text NOT NULL,
        job_id bigint,
        task_id bigint,
        CONSTRAINT emslog_ck_id_not_null CHECK (job_id IS NOT NULL OR  task_id IS NOT NULL),
        CONSTRAINT emslog_fk_job FOREIGN KEY (job_id) REFERENCES "job" ("id"),
        CONSTRAINT emslog_fk_task FOREIGN KEY (task_id) REFERENCES "task" ("id")
    );
    
ALTER TABLE "job" DROP COLUMN "log";
ALTER TABLE "task" DROP COLUMN "log";
    