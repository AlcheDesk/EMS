UPDATE worker SET status_id = (SELECT id from status WHERE name = 'FREE') WHERE id IS NOT NULL;
CREATE OR REPLACE FUNCTION check_worker_status(bigint)
  RETURNS boolean AS
$func$
BEGIN
   IF $1 in (SELECT id FROM status WHERE name = 'WORKING' OR name = 'FREE' OR name = 'DOWN' OR name = 'HOLD') THEN
      RETURN TRUE;
   ELSE
      RETURN FALSE;
   END IF;
END
$func$ LANGUAGE plpgsql;

ALTER TABLE "worker" ADD CONSTRAINT worker_ck_status CHECK (check_worker_status(status_id));
--------------------------------------------------------------------------------------------
UPDATE job SET status_id = (SELECT id from status WHERE name = 'NEW') WHERE id IS NOT NULL;
CREATE OR REPLACE FUNCTION check_job_status(bigint)
  RETURNS boolean AS
$func$
BEGIN
   IF $1 in (SELECT id FROM status WHERE name = 'NEW' OR name = 'WIP' OR name = 'FAIL' OR name = 'ERROR' OR name = 'PASS') THEN
      RETURN TRUE;
   ELSE
      RETURN FALSE;
   END IF;
END
$func$ LANGUAGE plpgsql;

ALTER TABLE "job" ADD CONSTRAINT job_ck_status CHECK (check_job_status(status_id));
-----------------------------------------------------------------------------------
UPDATE task SET status_id = (SELECT id from status WHERE name = 'NEW') WHERE id IS NOT NULL;
CREATE OR REPLACE FUNCTION check_task_status(bigint)
  RETURNS boolean AS
$func$
BEGIN
   IF $1 in (SELECT id FROM status WHERE name = 'NEW' OR name = 'WIP' OR name = 'FAIL' OR name = 'ERROR' OR name = 'PASS') THEN
      RETURN TRUE;
   ELSE
      RETURN FALSE;
   END IF;
END
$func$ LANGUAGE plpgsql;

ALTER TABLE "task" ADD CONSTRAINT task_ck_status CHECK (check_task_status(status_id));