ALTER TABLE "task" DROP CONSTRAINT "task_fk_job";
ALTER TABLE "task" ADD CONSTRAINT task_fk_job FOREIGN KEY ("job_id") REFERENCES "job" ("id") ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE "task_worker_link" DROP CONSTRAINT "task_worker_fk_task";
ALTER TABLE "task_worker_link" ADD CONSTRAINT task_worker_fk_task FOREIGN KEY ("task_id") REFERENCES "task" ("id") ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE "task_worker_link" DROP CONSTRAINT "task_worker_fk_worker";
ALTER TABLE "task_worker_link" ADD CONSTRAINT task_worker_fk_worker FOREIGN KEY ("worker_id") REFERENCES "worker" ("id") ON UPDATE CASCADE;