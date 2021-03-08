ALTER TABLE "task" ADD COLUMN singleton BOOLEAN;
UPDATE "task" SET singleton = false WHERE singleton IS NULL;
ALTER TABLE "task" ALTER COLUMN singleton SET NOT NULL;
ALTER TABLE "task" ALTER COLUMN singleton SET DEFAULT false;
