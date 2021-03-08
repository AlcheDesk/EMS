DELETE FROM task_worker_link WHERE active IS NULL;

ALTER TABLE "task_worker_link" DROP COLUMN "active";