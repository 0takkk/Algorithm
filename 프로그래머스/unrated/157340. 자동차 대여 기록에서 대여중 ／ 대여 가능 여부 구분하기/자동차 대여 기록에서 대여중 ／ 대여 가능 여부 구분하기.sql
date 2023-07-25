select distinct(car_id), 
    if(car_id in 
       (
           select distinct(car_id) 
           from car_rental_company_rental_history 
           where "2022-10-16" >= start_date and "2022-10-16" <= end_date
       ),
       "대여중",
       "대여 가능"
      ) as availability
from car_rental_company_rental_history 
order by car_id desc;