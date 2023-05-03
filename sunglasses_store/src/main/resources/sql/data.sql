insert into colors values (1, '2023-01-01', '2023-01-01', true, 'GREEN');
insert into colors values (2, '2023-01-01', '2023-01-01', true, 'DARK_GREEN');
insert into colors values (3, '2023-01-01', '2023-01-01', true, 'BLUE');
insert into colors values (4, '2023-01-01', '2023-01-01', true, 'DARK_BLUE');
insert into colors values (5, '2023-01-01', '2023-01-01', true, 'PINK');
insert into colors values (6, '2023-01-01', '2023-01-01', true, 'BROWN');
insert into colors values (7, '2023-01-01', '2023-01-01', true, 'BLACK');
insert into colors values (8, '2023-01-01', '2023-01-01', true, 'PINK_GRADIENT_BLUE');
insert into colors values (9, '2023-01-01', '2023-01-01', true, 'BLUE_GRADIENT_GREY');
insert into colors values (10, '2023-01-01', '2023-01-01', true, 'CLEAR_GRADIENT_BROWN');
insert into colors values (11, '2023-01-01', '2023-01-01', true, 'CLEAR_GRADIENT_BLUE');


insert into frame_materials values (1, '2023-01-01', '2023-01-01', true, 'WOOD');
insert into frame_materials values (2, '2023-01-01', '2023-01-01', true, 'METAL');
insert into frame_materials values (3, '2023-01-01', '2023-01-01', true, 'PLASTIC');


insert into frame_shapes values (1, '2023-01-01', '2023-01-01', true, 'RECTANGLE');
insert into frame_shapes values (2, '2023-01-01', '2023-01-01', true, 'SQUARE');
insert into frame_shapes values (3, '2023-01-01', '2023-01-01', true, 'ROUND');
insert into frame_shapes values (4, '2023-01-01', '2023-01-01', true, 'GEOMETRIC');
insert into frame_shapes values (5, '2023-01-01', '2023-01-01', true, 'AVIATOR');
insert into frame_shapes values (6, '2023-01-01', '2023-01-01', true, 'CAT_EYE');
insert into frame_shapes values (7, '2023-01-01', '2023-01-01', true, 'PANTO');
insert into frame_shapes values (8, '2023-01-01', '2023-01-01', true, 'BUTTERFLY');
insert into frame_shapes values (9, '2023-01-01', '2023-01-01', true, 'MASK');


insert into lens_materials values (1, '2023-01-01', '2023-01-01', true, 'MINERAL');
insert into lens_materials values (2, '2023-01-01', '2023-01-01', true, 'POLYMERIC');


insert into lens_categories values (1, '2023-01-01', '2023-01-01', true, 'GRADIENT');
insert into lens_categories values (2, '2023-01-01', '2023-01-01', true, 'MIRRORED');
insert into lens_categories values (3, '2023-01-01', '2023-01-01', true, 'POLARIZED');


insert into sex_categories values (1, '2023-01-01', '2023-01-01', true, 'MEN');
insert into sex_categories values (2, '2023-01-01', '2023-01-01', true, 'WOMEN');
insert into sex_categories values (3, '2023-01-01', '2023-01-01', true, 'KIDS');
insert into sex_categories values (4, '2023-01-01', '2023-01-01', true, 'UNISEX');


insert into brands values (1, '2023-01-01', '2023-01-01', true, 'RAY-BAN');
insert into brands values (2, '2023-01-01', '2023-01-01', true, 'POLAROID');
insert into brands values (3, '2023-01-01', '2023-01-01', true, 'CASTA');
insert into brands values (4, '2023-01-01', '2023-01-01', true, 'GUESS');
insert into brands values (5, '2023-01-01', '2023-01-01', true, 'LUXOPTICA');


/*RAY-BAN 3565 001/51 53 CLEAR GRADIENT BROWN*/
insert into sunglasses values (
        1, '2023-01-01', '2023-01-01', true,
        20,                  --bridge_width
        10,                  --color
        'https://luxoptica.ua/upload/products/compiled/images/Soncezahisni_okuljari/RAY-BAN/d21412b6-1645-41f7-be6e-03aab272aa4e.jpg',
        53,                  --lens_width
        '3565 001/51 53',    --model_code
        155,                 --price
        10,                  --quantity
        145,                 --temple_length
        1,                   --brand_id
        2,                   --frame_material
        4,                   --frame_shape
        1,                   --lens_category
        2,                   --lens_material
        4);                  --sex_category

-- insert into brands_sunglasses(1, 1);




