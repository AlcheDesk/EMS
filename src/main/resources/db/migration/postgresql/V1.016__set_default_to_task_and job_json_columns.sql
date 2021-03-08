ALTER TABLE "task" ALTER COLUMN parameter SET DEFAULT '{}'::json;
ALTER TABLE "task" ALTER COLUMN parameter SET NOT NULL;
ALTER TABLE "task" ALTER COLUMN data SET DEFAULT '{}'::json;
ALTER TABLE "task" ALTER COLUMN execution_result SET DEFAULT '{}'::json;

ALTER TABLE "job" ALTER COLUMN parameter SET NOT NULL;
ALTER TABLE "job" ALTER COLUMN parameter SET DEFAULT '{}'::json;