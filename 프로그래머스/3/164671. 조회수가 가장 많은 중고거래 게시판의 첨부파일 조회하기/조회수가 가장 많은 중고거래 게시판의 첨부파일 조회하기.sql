select concat("/home/grep/src/", b.board_id, "/", f.file_id, f.file_name, f.file_ext) as file_path
from used_goods_board b
join used_goods_file f on b.board_id = f.board_id
where b.board_id = (
    select board_id 
    from used_goods_board 
    order by views desc
    limit 1
)
order by f.file_id desc