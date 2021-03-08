ALTER TABLE "worker" RENAME COLUMN "uuid" TO worker_uuid;

DROP TRIGGER "uuid_after_update" ON "worker";

CREATE OR REPLACE FUNCTION "update_worker_uuid_trigger" ()  RETURNS trigger
  VOLATILE
AS $$
BEGIN
    NEW.worker_uuid = OLD.worker_uuid;
    RETURN NEW;   
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER worker_uuid_after_update AFTER UPDATE ON worker FOR EACH ROW EXECUTE PROCEDURE update_worker_uuid_trigger();