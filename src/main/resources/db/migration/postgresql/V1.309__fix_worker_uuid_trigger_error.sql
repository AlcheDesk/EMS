CREATE OR REPLACE FUNCTION "public"."update_updated_at_created_at_worker_uuid_column" ()  RETURNS trigger
  VOLATILE
AS $body$
BEGIN
    NEW.updated_at = now();
    NEW.created_at = OLD.created_at;
    NEW.uuid = OLD.uuid;
    RETURN NEW;   
END;
$body$ LANGUAGE plpgsql