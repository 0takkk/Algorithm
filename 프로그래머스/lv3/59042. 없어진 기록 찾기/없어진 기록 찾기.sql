-- 코드를 입력하세요
# SELECT o.ANIMAL_ID, o.NAME from ANIMAL_OUTS o 
# left join ANIMAL_INS i on o.ANIMAL_ID = i.ANIMAL_ID
# where o.ANIMAL_ID != i.ANIMAL_ID;

select o.ANIMAL_ID, o.NAME from ANIMAL_OUTS o
where o.ANIMAL_ID not in (select i.ANIMAL_ID from ANIMAL_INS i) 
order by o.ANIMAL_ID;
