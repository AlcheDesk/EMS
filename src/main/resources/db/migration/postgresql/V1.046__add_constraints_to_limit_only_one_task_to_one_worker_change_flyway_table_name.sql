ALTER TABLE "worker" ADD COLUMN task_id bigint;
ALTER TABLE "worker" ADD CONSTRAINT worker_fk_task FOREIGN KEY (task_id) REFERENCES "task" ("id");