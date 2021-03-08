INSERT INTO status (name) VALUES ('DONE') ON CONFLICT DO NOTHING;

CREATE OR REPLACE FUNCTION check_job_status(bigint)
  RETURNS boolean AS
$func$
BEGIN
   IF $1 in (SELECT id FROM status WHERE name = 'NEW' OR name = 'WIP' OR name = 'ERROR' OR name = 'DONE') THEN
      RETURN TRUE;
   ELSE
      RETURN FALSE;
   END IF;
END
$func$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION check_task_status(bigint)
  RETURNS boolean AS
$func$
BEGIN
   IF $1 in (SELECT id FROM status WHERE name = 'NEW' OR name = 'WIP' OR name = 'ERROR' OR name = 'DONE') THEN
      RETURN TRUE;
   ELSE
      RETURN FALSE;
   END IF;
END
$func$ LANGUAGE plpgsql;