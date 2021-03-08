ALTER FUNCTION "insert_retry_column" ( ) RENAME TO insert_retry_number_column;

CREATE OR REPLACE FUNCTION "insert_retry_number_column" ()  RETURNS trigger
  VOLATILE
AS $$
BEGIN
    NEW.retry_number = 0;
    RETURN NEW;   
END;
$$ LANGUAGE plpgsql;