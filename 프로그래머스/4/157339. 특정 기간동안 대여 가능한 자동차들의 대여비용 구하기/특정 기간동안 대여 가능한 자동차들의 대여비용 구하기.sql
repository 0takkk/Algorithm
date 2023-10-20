select c.car_id, c.car_type, round(30 * c.daily_fee * (100 - p.discount_rate) / 100) as fee
from CAR_RENTAL_COMPANY_CAR c
join CAR_RENTAL_COMPANY_RENTAL_HISTORY h on c.car_id = h.car_id
join CAR_RENTAL_COMPANY_DISCOUNT_PLAN p on c.car_type = p.car_type
where c.car_type in ('세단', 'SUV') 
    and c.car_id not in (
        select car_id from CAR_RENTAL_COMPANY_RENTAL_HISTORY
        where end_date > '2022-11-01' and start_date < '2022-12-01'
    )
    and p.duration_type = '30일 이상'
group by c.car_id
having fee >= 500000 and fee < 2000000
order by fee desc, c.car_type asc, c.car_id desc


# select * from CAR_RENTAL_COMPANY_DISCOUNT_PLAN