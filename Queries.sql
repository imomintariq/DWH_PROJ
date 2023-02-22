#query 1:
select * from (select rank() over (order by sum(case when date.mm = 9 then sales.total_sale end) desc) as `rank`, 
	store.store_name as `store_name`, sum(case when date.mm = 9 then sales.total_sale end) as `sale`
	FROM dwh.sales , dwh.date , dwh.store where sales.DATE_ID = date.DATE_ID and sales.STORE_ID = store.STORE_ID group by store.STORE_NAME) as result limit 3;

#query 2:
select * from (select rank() over (order by sum(case when date.weekday = 'SATURDAY' then sales.total_sale end) + sum(case when date.weekday = 'SUNDAY' then sales.total_sale end)desc) as `rank`,
	supplier.supplier_name as `supplier name`, sum(case when date.weekday = 'SATURDAY' then sales.total_sale end) + sum(case when date.weekday = 'SUNDAY' then sales.total_sale end)as `sale`
    FROM dwh.sales , dwh.date, dwh.supplier where sales.date_id = date.DATE_ID and sales.supplier_id = supplier.supplier_id group by supplier.SUPPLIER_NAME) as result limit 10; 

#query 3:
select sum(sales.total_sale) as `total_sales` , supplier.supplier_name as `supplied_by`, date.mm as `month`, date.qtr as `qtr`
from dwh.sales, dwh.supplier, dwh.date
where dwh.sales.DATE_ID = dwh.date.DATE_ID and dwh.sales.SUPPLIER_ID = dwh.supplier.SUPPLIER_ID 
group by supplier.SUPPLIER_NAME;

#query 4:
select product.product_name as `product_name`, store.store_name as `store`, sum(sales.total_sale) as `total_sales`  
from dwh.sales, dwh.product , dwh.store
where dwh.sales.PRODUCT_ID = dwh.product.PRODUCT_ID and dwh.sales.STORE_ID = dwh.store.store_id
group by PRODUCT_NAME, STORE_NAME
order by STORE_NAME, PRODUCT_NAME;


#query 5:
SELECT  STORE_NAME AS `STORE NAME`,
        SUM(CASE WHEN Q=1 THEN TOTAL END) AS `Q1_2017`,
        SUM(CASE WHEN Q=2 THEN TOTAL END) AS `Q2_2017`,
        SUM(CASE WHEN Q=3 THEN TOTAL END) AS `Q3_2017`,
        SUM(CASE WHEN Q=4 THEN TOTAL END) AS `Q4_2017`
FROM 
    ( SELECT  store.store_name, date.qtr AS `Q`,
              SUM(sales.total_sale) AS `TOTAL`
      FROM dwh.sales, dwh.store, dwh.date
        WHERE sales.store_id = store.store_id
          AND sales.date_id = date.date_id
      GROUP BY store.store_name, date.qtr
    ) as result
GROUP BY store_name
ORDER BY store_name;
hms_appointments
#query 6:
select * from (select rank() over (order by sum(case when date.weekday = 'SATURDAY' then sales.total_sale end) + sum(case when date.weekday = 'SUNDAY' then sales.total_sale end)desc) as `rank`,
product.product_name as `product_name`, sum(case when date.weekday = 'SATURDAY' then sales.total_sale end) + sum(case when date.weekday = 'SUNDAY' then sales.total_sale end)as `sale`
    FROM dwh.sales , dwh.date, dwh.product where sales.date_id = date.DATE_ID and sales.PRODUCT_ID = product.product_id group by product.PRODUCT_NAME) as result limit 5; 
    
#query 7:
SELECT store_id , COUNT(*) from dwh.sales GROUP BY store_id WITH ROLLUP;
SELECT supplier_id , COUNT(*) from dwh.sales GROUP BY supplier_id WITH ROLLUP;
SELECT product_id , COUNT(*) from dwh.sales GROUP BY product_id WITH ROLLUP;

#query 8:
select product.product_name as `product`, 
(CASE WHEN date.qtr =1 or date.qtr =2 THEN TOTAL END) as `Sales in first half`,
(CASE WHEN date.qtr =2 or date.qtr =3 THEN TOTAL END) as `Sales in second half`,
(CASE WHEN date.yyyy =2017 THEN TOTAL END) as `Total Sales`
FROM dwh.product, dwh.date,
    ( SELECT  product.PRODUCT_ID , date.qtr AS `Q1`,
              SUM(sales.total_sale) AS `TOTAL`
      FROM dwh.sales, dwh.product, dwh.date
        WHERE sales.product_id = product.product_id
          AND sales.date_id = date.date_id
      GROUP BY product.product_name, date.qtr
    ) as result
GROUP BY product.PRODUCT_NAME
ORDER BY product.PRODUCT_NAME;

#query 9
select sales.total_sale 
from dwh.sales
where sales.total_sale not between
(select avg(sales.total_sale)-2 *stddev(sales.total_sale) 
from dwh.sales ) 
and
(select avg(sales.total_sale)+3*stddev(sales.total_sale) 
from dwh.sales);


#query 10:
DROP  view dwh.STOREANALYSIS;
create view dwh.STOREANALYSIS as
  select store.store_name as `store_name`, product.product_name as `product_name`, sum(sales.total_sale) as `store_quarter_sales` 
  from dwh.store, dwh.sales, dwh.product where
  store.store_id = sales.store_id and
  sales.product_id = product.product_id
  group by store.store_name, product.product_name
  order by store_name, product_name
;

select* from hms_users;
select* from hms_appointments;