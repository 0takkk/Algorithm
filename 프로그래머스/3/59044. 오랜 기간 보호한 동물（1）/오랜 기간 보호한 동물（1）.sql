select name, datetime
from ANIMAL_INS
where animal_id not in (
    select animal_id from ANIMAL_OUTS
)
order by datetime asc
limit 3