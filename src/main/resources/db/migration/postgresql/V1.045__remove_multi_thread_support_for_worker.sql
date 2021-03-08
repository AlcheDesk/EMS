ALTER TABLE "task" ADD COLUMN worker_id bigint;
ALTER TABLE "task" ADD CONSTRAINT task_fk_worker FOREIGN KEY (worker_id) REFERENCES "worker" ("id");
DROP TABLE "task_worker_link";