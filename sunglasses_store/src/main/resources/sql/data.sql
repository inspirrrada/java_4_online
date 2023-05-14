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
insert into colors values (12, '2023-01-01', '2023-01-01', true, 'DARK_GREY');


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
insert into lens_categories values (4, '2023-01-01', '2023-01-01', true, 'ORDINARY');


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
        'https://luxoptica.ua/upload/products/compiled/images/Soncezahisni_okuljari/RAY-BAN/d21412b6-1645-41f7-be6e-03aab272aa4e.webp',
        'https://luxoptica.ua/upload/products/compiled/images/Soncezahisni_okuljari/RAY-BAN/4813d722-16f3-4293-82ad-7c2b310dbd36.jpg',
        'https://luxoptica.ua/upload/products/compiled/images/Soncezahisni_okuljari/RAY-BAN/a54cddfd-b653-4dfa-9868-43dc2b81e35c.jpg',
        'https://luxoptica.ua/upload/products/compiled/images/Soncezahisni_okuljari/RAY-BAN/603c34b7-b67c-4f4b-9cd8-738576b3be18.jpg',
        53,                  --lens_width
        '3565 001/51 53',    --model_code
        155,                 --price
        10,                  --quantity
        145,                 --temple_length
        1,                   --brand_id
        10,                  --color
        2,                   --frame_material
        4,                   --frame_shape
        1,                   --lens_category
        2,                   --lens_material
        4);                  --sex_category

-- insert into brands_sunglasses(1, 1);

/*RAY-BAN 9565S 212/80 47 DARK BLUE*/
insert into sunglasses values (
                                  2, '2023-01-01', '2023-01-01', true,
                                  19,                  --bridge_width
                                  'https://luxoptica.ua/upload/products/compiled/images/Soncezahisni_okuljari/RAY-BAN/fc8b12ca-84a8-4a26-b6e5-9ec880f19a88.jpg',
                                  'https://luxoptica.ua/upload/products/compiled/images/Soncezahisni_okuljari/RAY-BAN/16db1790-129d-424f-888a-3b8c4d2996ba.jpg',
                                  'https://luxoptica.ua/upload/products/compiled/images/Soncezahisni_okuljari/RAY-BAN/896e46f8-f460-450a-9e54-47063ff5fe47.jpg',
                                  'https://luxoptica.ua/upload/products/compiled/images/Soncezahisni_okuljari/RAY-BAN/89aee2e8-d360-43a1-b371-924f8f001bf4.jpg',
                                  47,                  --lens_width
                                  '9565S 212/80 47',    --model_code
                                  81,                 --price
                                  5,                  --quantity
                                  130,                 --temple_length
                                  1,                   --brand_id
                                  4,                  --color
                                  2,                   --frame_material
                                  4,                   --frame_shape
                                  2,                   --lens_category
                                  2,                   --lens_material
                                  4);                  --sex_category

/*RAY-BAN 3654 004/9A 60 DARK GREEN*/
insert into sunglasses values (
                                  3, '2023-01-01', '2023-01-01', true,
                                  18,                  --bridge_width
                                  'https://luxoptica.ua/upload/products/compiled/images/Soncezahisni_okuljari/RAY-BAN/2de25560-b614-406b-bf38-e1b24caf131c.jpg',
                                  'https://luxoptica.ua/upload/products/compiled/images/Soncezahisni_okuljari/RAY-BAN/62ff6ea0-d859-4987-8e13-701d1ee292ef.jpg',
                                  'https://luxoptica.ua/upload/products/compiled/images/Soncezahisni_okuljari/RAY-BAN/35a7f1fb-e768-47e3-adcf-552defafd669.jpg',
                                  'https://luxoptica.ua/upload/products/compiled/images/Soncezahisni_okuljari/RAY-BAN/2de25560-b614-406b-bf38-e1b24caf131c.jpg',
                                  60,                  --lens_width
                                  '3654 004/9A 60',    --model_code
                                  175,                 --price
                                  4,                  --quantity
                                  145,                 --temple_length
                                  1,                   --brand_id
                                  2,                  --color
                                  2,                   --frame_material
                                  1,                   --frame_shape
                                  3,                   --lens_category
                                  1,                   --lens_material
                                  1);                  --sex_category

/*RAY-BAN 9070S 707787 46 DARK GREY*/
insert into sunglasses values (
                                  4, '2023-01-01', '2023-01-01', true,
                                  16,                  --bridge_width
                                  'https://luxoptica.ua/upload/products/compiled/images/Soncezahisni_okuljari/RAY-BAN/ff7185a2-8e3f-4271-8c7c-26d81726cc64.jpg',
                                  'https://luxoptica.ua/upload/products/compiled/images/Soncezahisni_okuljari/RAY-BAN/298f6742-cfa4-415c-94b0-9202c700fab6.jpg',
                                  'https://luxoptica.ua/upload/products/compiled/images/Soncezahisni_okuljari/RAY-BAN/72ae292a-a9a6-4ed0-a455-bbde1ba18c42.jpg',
                                  'https://luxoptica.ua/upload/products/compiled/images/Soncezahisni_okuljari/RAY-BAN/9917e7af-133e-4fad-a667-e3963ccb16eb.jpg',
                                  46,                  --lens_width
                                  '9070S 707787 46',    --model_code
                                  71,                 --price
                                  2,                  --quantity
                                  130,                 --temple_length
                                  1,                   --brand_id
                                  12,                  --color
                                  3,                   --frame_material
                                  4,                   --frame_shape
                                  4,                   --lens_category
                                  2,                   --lens_material
                                  3);                  --sex_category

/*RAY-BAN 4323 710/83 51 POLAR BROWN*/
insert into sunglasses values (
                                  5, '2023-01-01', '2023-01-01', true,
                                  20,                  --bridge_width
                                  'https://luxoptica.ua/upload/products/compiled/images/Soncezahisni_okuljari/RAY-BAN/15d32eac-d2fc-47cd-ab2f-6e0f6529b900.jpg',
                                  'https://luxoptica.ua/upload/products/compiled/images/Soncezahisni_okuljari/RAY-BAN/374f5742-9e24-46a2-98ef-4654ed21f910.jpg',
                                  'https://luxoptica.ua/upload/products/compiled/images/Soncezahisni_okuljari/RAY-BAN/8361ee39-7c15-4732-a67a-48532ee332bf.jpg',
                                  'https://luxoptica.ua/upload/products/compiled/images/Soncezahisni_okuljari/RAY-BAN/15d32eac-d2fc-47cd-ab2f-6e0f6529b900.jpg',
                                  51,                  --lens_width
                                  '4323 710/83 51',    --model_code
                                  165,                 --price
                                  3,                  --quantity
                                  150,                 --temple_length
                                  1,                   --brand_id
                                  6,                  --color
                                  3,                   --frame_material
                                  1,                   --frame_shape
                                  3,                   --lens_category
                                  2,                   --lens_material
                                  4);                  --sex_category

/*RAY-BAN 3565 001/51 53 CLEAR GRADIENT BROWN*/
insert into sunglasses values (
                                  6, '2023-01-01', '2023-01-01', true,
                                  20,                  --bridge_width
                                  'https://luxoptica.ua/upload/products/compiled/images/Soncezahisni_okuljari/RAY-BAN/d21412b6-1645-41f7-be6e-03aab272aa4e.jpg',
                                  'https://luxoptica.ua/upload/products/compiled/images/Soncezahisni_okuljari/RAY-BAN/4813d722-16f3-4293-82ad-7c2b310dbd36.jpg',
                                  'https://luxoptica.ua/upload/products/compiled/images/Soncezahisni_okuljari/RAY-BAN/a54cddfd-b653-4dfa-9868-43dc2b81e35c.jpg',
                                  'https://luxoptica.ua/upload/products/compiled/images/Soncezahisni_okuljari/RAY-BAN/603c34b7-b67c-4f4b-9cd8-738576b3be18.jpg',
                                  53,                  --lens_width
                                  '3565 001/51 536',    --model_code
                                  155,                 --price
                                  10,                  --quantity
                                  145,                 --temple_length
                                  1,                   --brand_id
                                  10,                  --color
                                  2,                   --frame_material
                                  4,                   --frame_shape
                                  1,                   --lens_category
                                  2,                   --lens_material
                                  4);                  --sex_category

/*RAY-BAN 3565 001/51 53 CLEAR GRADIENT BROWN*/
insert into sunglasses values (
                                  7, '2023-01-01', '2023-01-01', true,
                                  20,                  --bridge_width
                                  'https://luxoptica.ua/upload/products/compiled/images/Soncezahisni_okuljari/RAY-BAN/d21412b6-1645-41f7-be6e-03aab272aa4e.jpg',
                                  'https://luxoptica.ua/upload/products/compiled/images/Soncezahisni_okuljari/RAY-BAN/4813d722-16f3-4293-82ad-7c2b310dbd36.jpg',
                                  'https://luxoptica.ua/upload/products/compiled/images/Soncezahisni_okuljari/RAY-BAN/a54cddfd-b653-4dfa-9868-43dc2b81e35c.jpg',
                                  'https://luxoptica.ua/upload/products/compiled/images/Soncezahisni_okuljari/RAY-BAN/603c34b7-b67c-4f4b-9cd8-738576b3be18.jpg',
                                  53,                  --lens_width
                                  '3565 001/51 537',    --model_code
                                  155,                 --price
                                  10,                  --quantity
                                  145,                 --temple_length
                                  1,                   --brand_id
                                  10,                  --color
                                  2,                   --frame_material
                                  4,                   --frame_shape
                                  1,                   --lens_category
                                  2,                   --lens_material
                                  4);                  --sex_category

/*RAY-BAN 3565 001/51 53 CLEAR GRADIENT BROWN*/
insert into sunglasses values (
                                  8, '2023-01-01', '2023-01-01', true,
                                  20,                  --bridge_width
                                  'https://luxoptica.ua/upload/products/compiled/images/Soncezahisni_okuljari/RAY-BAN/d21412b6-1645-41f7-be6e-03aab272aa4e.jpg',
                                  'https://luxoptica.ua/upload/products/compiled/images/Soncezahisni_okuljari/RAY-BAN/4813d722-16f3-4293-82ad-7c2b310dbd36.jpg',
                                  'https://luxoptica.ua/upload/products/compiled/images/Soncezahisni_okuljari/RAY-BAN/a54cddfd-b653-4dfa-9868-43dc2b81e35c.jpg',
                                  'https://luxoptica.ua/upload/products/compiled/images/Soncezahisni_okuljari/RAY-BAN/603c34b7-b67c-4f4b-9cd8-738576b3be18.jpg',
                                  53,                  --lens_width
                                  '3565 001/51 538',    --model_code
                                  155,                 --price
                                  10,                  --quantity
                                  145,                 --temple_length
                                  1,                   --brand_id
                                  10,                  --color
                                  2,                   --frame_material
                                  4,                   --frame_shape
                                  1,                   --lens_category
                                  2,                   --lens_material
                                  4);                  --sex_category


