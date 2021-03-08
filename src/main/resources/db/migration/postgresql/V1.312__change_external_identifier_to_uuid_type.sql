ALTER TABLE "task" DROP COLUMN "external_identifier";
ALTER TABLE "task" ADD COLUMN external_identifier uuid;
