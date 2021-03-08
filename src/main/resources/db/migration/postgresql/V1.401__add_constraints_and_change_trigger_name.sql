ALTER FUNCTION "insert_retry_number_column" ( ) RENAME TO task_generate_columns;
ALTER TRIGGER "task_insert_retry" ON "task" RENAME TO task_generate_columns;
--------------------------------------------------------------------------------------------------------
ALTER TABLE "task" ADD CONSTRAINT task_ck_finished_execution_end_at CHECK 
(
    (is_finished IS FALSE AND execution_end_at IS NULL)
    OR  
    (is_finished IS TRUE AND execution_end_at IS NOT NULL)
);
--------------------------------------------------------------------------------------------------------
ALTER TABLE "job" ADD COLUMN execution_start_at TIMESTAMP WITH TIME zone;
ALTER TABLE "job" ADD COLUMN execution_end_at TIMESTAMP WITH TIME zone;
UPDATE "job" target SET execution_start_at = (SELECT MIN(execution_start_at) FROM task WHERE job_id = target.id); 
UPDATE "job" target SET execution_end_at = (SELECT MAX(execution_end_at) FROM task WHERE job_id = target.id);
UPDATE "job" SET is_finished = TRUE WHERE execution_end_at IS NOT NULL; 
ALTER TABLE "job" ADD CONSTRAINT job_ck_finished_end_at CHECK 
(
    (is_finished IS FALSE AND execution_end_at IS NULL)
    OR  
    (is_finished IS TRUE AND execution_end_at IS NOT NULL)
);