(select date_format(sales_date, "%Y-%m-%d") as sales_date, product_id, user_id, sales_amount from ONLINE_SALE
where year(sales_date) = 2022 and month(sales_date) = 3
union
select date_format(sales_date, "%Y-%m-%d") as sales_date, product_id, null as user_id, sales_amount from OFFLINE_SALE
where year(sales_date) = 2022 and month(sales_date) = 3)
order by sales_date, product_id, user_id