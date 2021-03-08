ALTER TABLE "job" DROP COLUMN "external_identifier";
ALTER TABLE "job" ADD COLUMN external_identifier uuid;
