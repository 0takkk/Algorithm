-- 코드를 입력하세요
SELECT substring(o.sales_date, 1, 4) as year, 
    substring(o.sales_date, 6, 2) as month, 
    u.gender, 
    count(distinct(u.user_id)) as users 
from online_sale o 
join user_info u on o.user_id = u.user_id
where u.gender is not null
# group by o.sales_date
group by substring(o.sales_date, 1, 7), u.gender
order by year, month, u.gender

# SELECT *
# from online_sale o 
# join user_info u on o.user_id = u.user_id
# where u.gender is not null
# # group by o.sales_date
# group by substring(o.sales_date, 1, 4), substring(o.sales_date, 6, 2), u.gender