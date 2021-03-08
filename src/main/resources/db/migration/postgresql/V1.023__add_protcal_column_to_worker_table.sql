alter table worker add column protocol text;
update worker set protocol='http' where protocol is null;
ALTER table worker alter column protocol set not null;
alter table worker alter column protocol set default 'http';