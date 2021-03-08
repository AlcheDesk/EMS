ALTER TABLE "job" ALTER COLUMN status_id SET DEFAULT 7;
ALTER TABLE "task" ALTER COLUMN status_id SET DEFAULT 1;
ALTER TABLE "worker" ADD CONSTRAINT worker_fk_staus FOREIGN KEY ("status_id") REFERENCES "status" ("id");
ALTER TABLE "worker" ADD CONSTRAINT worker_fk_operating_system FOREIGN KEY ("operating_system_id") REFERENCES "operating_system" ("id");