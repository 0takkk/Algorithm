SELECT i.REST_ID, i.REST_NAME, i.FOOD_TYPE, i.FAVORITES, i.ADDRESS, round(avg(r.review_score), 2) as SCORE
from rest_info i 
join rest_review r on i.rest_id = r.rest_id
where i.address like '서울%'
group by i.rest_id
order by SCORE desc, i.views desc;