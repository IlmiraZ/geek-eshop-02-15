alter table products modify column brand_id bigint null;
GO

update products set brand_id = NULL;
GO

insert into brands(`name`)
values('Brand-A'), ('Brand-B'), ('Brand-C'), ('Brand-D'), ('Brand-E'),
      ('Brand-G'), ('Brand-J'), ('Brand-K'), ('Brand-L'), ('Brand-M');
GO