CREATE TABLE execution_control_type
    (
        id bigserial NOT NULL,
        name text NOT NULL,
        PRIMARY KEY (id)
    );
    
INSERT INTO execution_control_type (id, name) VALUES (1, 'Singleton');
INSERT INTO execution_control_type (id, name) VALUES (2, 'Parallel');
INSERT INTO execution_control_type (id, name) VALUES (3, 'Sequence');


CREATE TABLE task_execution_control
    (
        id bigserial NOT NULL,
        created_at TIMESTAMP WITH TIME zone DEFAULT now() NOT NULL,
        updated_at TIMESTAMP WITH TIME zone DEFAULT now() NOT NULL,
        primary_task_uuid uuid NOT NULL,
        execution_control_type_id bigint DEFAULT 1 NOT NULL,
        singleton_uuid uuid,
        secondary_task_uuid bigint,
        sync_time TIMESTAMP WITH TIME zone,
        is_finished BOOLEAN DEFAULT false NOT NULL,
        time_to_release TIMESTAMP WITH TIME zone,
        PRIMARY KEY (id),
        CONSTRAINT task_execution_control_fk_execution_control_type FOREIGN KEY
        (execution_control_type_id) REFERENCES "execution_control_type" ("id"),
        CONSTRAINT task_execution_control_ck_singleton CHECK ((execution_control_type_id = 1 AND singleton_uuid IS NOT NULL) OR (execution_control_type_id != 1 AND singleton_uuid IS NULL))
    );
    
CREATE TRIGGER "task_execution_control_update_created_at_updated_at"
  BEFORE UPDATE ON task_execution_control
  FOR EACH ROW
EXECUTE PROCEDURE update_created_at_updated_at_trigger();
