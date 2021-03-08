ALTER TABLE "job" DROP CONSTRAINT "job_ck_status";
ALTER TABLE "task" DROP CONSTRAINT "task_ck_status";
ALTER TABLE "worker" DROP CONSTRAINT "worker_ck_status";
DROP FUNCTION "check_job_status" ( bigint );
DROP FUNCTION "check_task_status" ( bigint );
DROP FUNCTION "check_worker_status" ( bigint );
DROP FUNCTION "update_uuid_trigger" ( );
DROP FUNCTION "finished_update_task_status_trigger" ( );