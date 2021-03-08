ALTER TABLE "job" ADD COLUMN tenant_id bigint;
UPDATE "job" SET tenant_id = 1000 WHERE tenant_id IS NULL;
ALTER TABLE "job" ALTER COLUMN tenant_id SET NOT NULL;
ALTER TABLE "job" ALTER COLUMN tenant_id SET DEFAULT 1000;

ALTER TABLE "job_log" ADD COLUMN tenant_id bigint;
UPDATE "job_log" SET tenant_id = 1000 WHERE tenant_id IS NULL;
ALTER TABLE "job_log" ALTER COLUMN tenant_id SET NOT NULL;
ALTER TABLE "job_log" ALTER COLUMN tenant_id SET DEFAULT 1000;

ALTER TABLE "property" ADD COLUMN tenant_id bigint;
UPDATE "property" SET tenant_id = 1000 WHERE tenant_id IS NULL;
ALTER TABLE "property" ALTER COLUMN tenant_id SET NOT NULL;
ALTER TABLE "property" ALTER COLUMN tenant_id SET DEFAULT 1000;

ALTER TABLE "task" ADD COLUMN tenant_id bigint;
UPDATE "task" SET tenant_id = 1000 WHERE tenant_id IS NULL;
ALTER TABLE "task" ALTER COLUMN tenant_id SET NOT NULL;
ALTER TABLE "task" ALTER COLUMN tenant_id SET DEFAULT 1000;

ALTER TABLE "task_execution_control" ADD COLUMN tenant_id bigint;
UPDATE "task_execution_control" SET tenant_id = 1000 WHERE tenant_id IS NULL;
ALTER TABLE "task_execution_control" ALTER COLUMN tenant_id SET NOT NULL;
ALTER TABLE "task_execution_control" ALTER COLUMN tenant_id SET DEFAULT 1000;

ALTER TABLE "task_log" ADD COLUMN tenant_id bigint;
UPDATE "task_log" SET tenant_id = 1000 WHERE tenant_id IS NULL;
ALTER TABLE "task_log" ALTER COLUMN tenant_id SET NOT NULL;
ALTER TABLE "task_log" ALTER COLUMN tenant_id SET DEFAULT 1000;

ALTER TABLE "worker" ADD COLUMN tenant_id bigint;
UPDATE "worker" SET tenant_id = 1000 WHERE tenant_id IS NULL;
ALTER TABLE "worker" ALTER COLUMN tenant_id SET NOT NULL;
ALTER TABLE "worker" ALTER COLUMN tenant_id SET DEFAULT 1000;

ALTER TABLE "worker_log" ADD COLUMN tenant_id bigint;
UPDATE "worker_log" SET tenant_id = 1000 WHERE tenant_id IS NULL;
ALTER TABLE "worker_log" ALTER COLUMN tenant_id SET NOT NULL;
ALTER TABLE "worker_log" ALTER COLUMN tenant_id SET DEFAULT 1000;