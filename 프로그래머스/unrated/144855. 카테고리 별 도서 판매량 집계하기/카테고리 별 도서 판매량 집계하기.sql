select b.category, sum(bs.sales) as total_sales
from book b
join book_sales bs on b.book_id = bs.book_id
where bs.sales_date between '2022-01-01' and '2022-01-31'
group by b.category
order by b.category