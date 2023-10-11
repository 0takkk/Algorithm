select p.product_code, p.price * sum(o.sales_amount) as sales
from product p
join offline_sale o on o.product_id = p.product_id
group by o.product_id
order by sales desc, p.product_code asc