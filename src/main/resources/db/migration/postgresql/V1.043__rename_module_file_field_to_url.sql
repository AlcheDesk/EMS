ALTER TABLE "worker_module_link" RENAME TO worker_type_module_link;
ALTER SEQUENCE worker_module_link_id_seq RENAME TO worker_type_module_link_id_seq;
ALTER TABLE "module" RENAME COLUMN "file" TO url;