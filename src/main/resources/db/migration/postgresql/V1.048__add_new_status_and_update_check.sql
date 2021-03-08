INSERT INTO status (id,name) VALUES (14,'RESERVED') ON CONFLICT DO NOTHING;

CREATE OR REPLACE FUNCTION check_worker_status(bigint)
  RETURNS boolean AS
$func$
BEGIN
   IF $1 in (SELECT id FROM status WHERE name = 'WORKING' OR name = 'FREE' OR name = 'DOWN' OR name = 'HOLD' OR name = 'RESERVED') THEN
      RETURN TRUE;
   ELSE
      RETURN FALSE;
   END IF;
END
$func$ LANGUAGE plpgsql;