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
insert into colors values (13, '2023-01-01', '2023-01-01', true, 'GREY');
insert into colors values (14, '2023-01-01', '2023-01-01', true, 'YELLOW');
insert into colors values (15, '2023-01-01', '2023-01-01', true, 'GRADIENT_GREEN');
insert into colors values (16, '2023-01-01', '2023-01-01', true, 'GRADIENT_SMOKE');
insert into colors values (17, '2023-01-01', '2023-01-01', true, 'GREY_PINK');
insert into colors values (18, '2023-01-01', '2023-01-01', true, 'ORANGE');



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
        20,                  --  bridge_width
        'https://luxoptica.ua/upload/products/compiled/images/Soncezahisni_okuljari/RAY-BAN/d21412b6-1645-41f7-be6e-03aab272aa4e.webp',
        'https://luxoptica.ua/upload/products/compiled/images/Soncezahisni_okuljari/RAY-BAN/4813d722-16f3-4293-82ad-7c2b310dbd36.jpg',
        'https://luxoptica.ua/upload/products/compiled/images/Soncezahisni_okuljari/RAY-BAN/a54cddfd-b653-4dfa-9868-43dc2b81e35c.jpg',
        'https://luxoptica.ua/upload/products/compiled/images/Soncezahisni_okuljari/RAY-BAN/603c34b7-b67c-4f4b-9cd8-738576b3be18.jpg',
        53,                  --  lens_width
        '3565 001/51 53',    --  model_code
        155,                 --  price
        10,                  --  quantity
        145,                 --  temple_length
        1,                   --  brand_id
        10,                  --  color
        2,                   --  frame_material
        4,                   --  frame_shape
        1,                   --  lens_category
        2,                   --  lens_material
        4);                  --  sex_category

--  insert into brands_sunglasses(1, 1);

/*RAY-BAN 9565S 212/80 47 DARK BLUE*/
insert into sunglasses values (
                                  2, '2023-01-01', '2023-01-01', true,
                                  19,                  --  bridge_width
                                  'https://luxoptica.ua/upload/products/compiled/images/Soncezahisni_okuljari/RAY-BAN/fc8b12ca-84a8-4a26-b6e5-9ec880f19a88.jpg',
                                  'https://luxoptica.ua/upload/products/compiled/images/Soncezahisni_okuljari/RAY-BAN/16db1790-129d-424f-888a-3b8c4d2996ba.jpg',
                                  'https://luxoptica.ua/upload/products/compiled/images/Soncezahisni_okuljari/RAY-BAN/896e46f8-f460-450a-9e54-47063ff5fe47.jpg',
                                  'https://luxoptica.ua/upload/products/compiled/images/Soncezahisni_okuljari/RAY-BAN/89aee2e8-d360-43a1-b371-924f8f001bf4.jpg',
                                  47,                  --  lens_width
                                  '9565S 212/80 47',   --  model_code
                                  81,                  --  price
                                  5,                   --  quantity
                                  130,                 --  temple_length
                                  1,                   --  brand_id
                                  4,                   --  color
                                  2,                   --  frame_material
                                  4,                   --  frame_shape
                                  2,                   --  lens_category
                                  2,                   --  lens_material
                                  4);                  --  sex_category

/*RAY-BAN 3654 004/9A 60 DARK GREEN*/
insert into sunglasses values (
                                  3, '2023-01-01', '2023-01-01', true,
                                  18,                  --  bridge_width
                                  'https://luxoptica.ua/upload/products/compiled/images/Soncezahisni_okuljari/RAY-BAN/2de25560-b614-406b-bf38-e1b24caf131c.jpg',
                                  'https://luxoptica.ua/upload/products/compiled/images/Soncezahisni_okuljari/RAY-BAN/62ff6ea0-d859-4987-8e13-701d1ee292ef.jpg',
                                  'https://luxoptica.ua/upload/products/compiled/images/Soncezahisni_okuljari/RAY-BAN/35a7f1fb-e768-47e3-adcf-552defafd669.jpg',
                                  'https://luxoptica.ua/upload/products/compiled/images/Soncezahisni_okuljari/RAY-BAN/2de25560-b614-406b-bf38-e1b24caf131c.jpg',
                                  60,                  --  lens_width
                                  '3654 004/9A 60',    --  model_code
                                  175,                 --  price
                                  4,                  --  quantity
                                  145,                 --  temple_length
                                  1,                   --  brand_id
                                  2,                  --  color
                                  2,                   --  frame_material
                                  1,                   --  frame_shape
                                  3,                   --  lens_category
                                  1,                   --  lens_material
                                  1);                  --  sex_category

/*RAY-BAN 9070S 707787 46 DARK GREY*/
insert into sunglasses values (
                                  4, '2023-01-01', '2023-01-01', true,
                                  16,                  -- bridge_width
                                  'https://luxoptica.ua/upload/products/compiled/images/Soncezahisni_okuljari/RAY-BAN/ff7185a2-8e3f-4271-8c7c-26d81726cc64.jpg',
                                  'https://luxoptica.ua/upload/products/compiled/images/Soncezahisni_okuljari/RAY-BAN/298f6742-cfa4-415c-94b0-9202c700fab6.jpg',
                                  'https://luxoptica.ua/upload/products/compiled/images/Soncezahisni_okuljari/RAY-BAN/72ae292a-a9a6-4ed0-a455-bbde1ba18c42.jpg',
                                  'https://luxoptica.ua/upload/products/compiled/images/Soncezahisni_okuljari/RAY-BAN/9917e7af-133e-4fad-a667-e3963ccb16eb.jpg',
                                  46,                  -- lens_width
                                  '9070S 707787 46',    -- model_code
                                  71,                 -- price
                                  2,                  -- quantity
                                  130,                 -- temple_length
                                  1,                   -- brand_id
                                  12,                  -- color
                                  3,                   -- frame_material
                                  4,                   -- frame_shape
                                  4,                   -- lens_category
                                  2,                   -- lens_material
                                  3);                  -- sex_category

/*RAY-BAN 4323 710/83 51 POLAR BROWN*/
insert into sunglasses values (
                                  5, '2023-01-01', '2023-01-01', true,
                                  20,                  -- bridge_width
                                  'https://luxoptica.ua/upload/products/compiled/images/Soncezahisni_okuljari/RAY-BAN/15d32eac-d2fc-47cd-ab2f-6e0f6529b900.jpg',
                                  'https://luxoptica.ua/upload/products/compiled/images/Soncezahisni_okuljari/RAY-BAN/374f5742-9e24-46a2-98ef-4654ed21f910.jpg',
                                  'https://luxoptica.ua/upload/products/compiled/images/Soncezahisni_okuljari/RAY-BAN/8361ee39-7c15-4732-a67a-48532ee332bf.jpg',
                                  'https://luxoptica.ua/upload/products/compiled/images/Soncezahisni_okuljari/RAY-BAN/15d32eac-d2fc-47cd-ab2f-6e0f6529b900.jpg',
                                  51,                  -- lens_width
                                  '4323 710/83 51',    -- model_code
                                  165,                 -- price
                                  3,                  -- quantity
                                  150,                 -- temple_length
                                  1,                   -- brand_id
                                  6,                  -- color
                                  3,                   -- frame_material
                                  1,                   -- frame_shape
                                  3,                   -- lens_category
                                  2,                   -- lens_material
                                  4);                  -- sex_category

/*LUXOPTICA LOS 203 MCRGRY 58 GREY*/
insert into sunglasses values (
                                  6, '2023-01-01', '2023-01-01', true,
                                  15,                  -- bridge_width
                                  'https://luxoptica.ua/upload/products/compiled/images/Soncezahisni_okuljari/LUXOPTICA/e0099fa3-e150-45a9-9412-b06ec711cb35.jpg',
                                  'https://luxoptica.ua/upload/products/compiled/images/Soncezahisni_okuljari/LUXOPTICA/502b6b8e-53bd-43e9-a110-58cdca465a4a.jpg',
                                  'https://luxoptica.ua/upload/products/compiled/images/Soncezahisni_okuljari/LUXOPTICA/f20de972-17e7-4501-aa3c-0655438dc778.jpg',
                                  'https://luxoptica.ua/upload/products/compiled/images/Soncezahisni_okuljari/LUXOPTICA/a4a83245-339f-4682-bdd0-368367b30e84.jpg',
                                  58,                  -- lens_width
                                  'LOS 203 MCRGRY 58',    -- model_code
                                  15,                 -- price
                                  1,                  -- quantity
                                  141,                 -- temple_length
                                  5,                   -- brand_id
                                  13,                  -- color
                                  3,                   -- frame_material
                                  1,                   -- frame_shape
                                  3,                   -- lens_category
                                  2,                   -- lens_material
                                  1);                  -- sex_category

/*LUXOPTICA LOS 308 MDEMI 52 GREEN*/
insert into sunglasses values (
                                  7, '2023-01-01', '2023-01-01', true,
                                  18,                  -- bridge_width
                                  'https://luxoptica.ua/upload/products/compiled/images/Soncezahisni_okuljari/LUXOPTICA/3e2fc801-5f6f-4e65-a679-62c7bd259d6e.jpg',
                                  'https://luxoptica.ua/upload/products/compiled/images/Soncezahisni_okuljari/LUXOPTICA/4331e173-9f4d-4ed1-8fb6-1aeb9639cf3f.jpg',
                                  'https://luxoptica.ua/upload/products/compiled/images/Soncezahisni_okuljari/LUXOPTICA/3155f611-8061-416d-97fe-6eb8400e746a.jpg',
                                  'https://luxoptica.ua/upload/products/compiled/images/Soncezahisni_okuljari/LUXOPTICA/26e757d8-217b-4320-9ef2-935219e8a7f7.jpg',
                                  52,                  -- lens_width
                                  'LOS 308 MDEMI 52',    -- model_code
                                  15,                 -- price
                                  3,                  -- quantity
                                  146,                 -- temple_length
                                  5,                   -- brand_id
                                  1,                  -- color
                                  3,                   -- frame_material
                                  7,                   -- frame_shape
                                  3,                   -- lens_category
                                  2,                   -- lens_material
                                  4);                  -- sex_category

/*LUXOPTICA LOS 109 GLD 58 BROWN*/
insert into sunglasses values (
                                  8, '2023-01-01', '2023-01-01', true,
                                  18,                  -- bridge_width
                                  'https://luxoptica.ua/upload/products/compiled/images/Soncezahisni_okuljari/LUXOPTICA/0d9b72c1-7a9d-451b-8952-0c653924846a.jpg',
                                  'https://luxoptica.ua/upload/products/compiled/images/Soncezahisni_okuljari/LUXOPTICA/8d816401-ff47-4567-a1d3-eb53f00dbc56.jpg',
                                  'https://luxoptica.ua/upload/products/compiled/images/Soncezahisni_okuljari/LUXOPTICA/d47b87e1-2f67-4a68-a45a-192ef8ef0485.jpg',
                                  'https://luxoptica.ua/upload/products/compiled/images/Soncezahisni_okuljari/LUXOPTICA/0d9b72c1-7a9d-451b-8952-0c653924846a.jpg',
                                  58,                  -- lens_width
                                  'LOS 109 GLD 588',    -- model_code
                                  25,                 -- price
                                  8,                  -- quantity
                                  140,                 -- temple_length
                                  5,                   -- brand_id
                                  6,                  -- color
                                  2,                   -- frame_material
                                  2,                   -- frame_shape
                                  1,                   -- lens_category
                                  1,                   -- lens_material
                                  2);                  -- sex_category

/*LUXOPTICA LOS 110 SLBK 57 GREY*/
insert into sunglasses values (
                                  9, '2023-01-01', '2023-01-01', true,
                                  18,                  -- bridge_width
                                  'https://luxoptica.ua/upload/products/compiled/images/Soncezahisni_okuljari/LUXOPTICA/56b4fd06-8d4a-47cc-bcc4-726d86ec9de7.jpg',
                                  'https://luxoptica.ua/upload/products/compiled/images/Soncezahisni_okuljari/LUXOPTICA/3c3b50fd-a7fa-467d-86c6-8f24285ea8b8.jpg',
                                  'https://luxoptica.ua/upload/products/compiled/images/Soncezahisni_okuljari/LUXOPTICA/b785088c-b13c-4e0d-a26b-dee8dc7c1427.jpg',
                                  'https://luxoptica.ua/upload/products/compiled/images/Soncezahisni_okuljari/LUXOPTICA/56b4fd06-8d4a-47cc-bcc4-726d86ec9de7.jpg',
                                  58,                  -- lens_width
                                  'LOS 110 SLBK 57',    -- model_code
                                  25,                 -- price
                                  4,                  -- quantity
                                  140,                 -- temple_length
                                  5,                   -- brand_id
                                  13,                  -- color
                                  2,                   -- frame_material
                                  8,                   -- frame_shape
                                  1,                   -- lens_category
                                  2,                   -- lens_material
                                  2);                  -- sex_category

/*LUXOPTICA LOS 906 YEL*/
insert into sunglasses values (
                                  10, '2023-01-01', '2023-01-01', true,
                                  14,                  -- bridge_width
                                  'https://luxoptica.ua/upload/products/compiled/images/Soncezahisni_okuljari/LUXOPTICA/e2d2c955-3198-420e-bca4-4b0305f02d64.jpg',
                                  'https://luxoptica.ua/upload/products/compiled/images/Soncezahisni_okuljari/LUXOPTICA/ab6824f9-5e81-4945-9bd3-776970414777.jpg',
                                  'https://luxoptica.ua/upload/products/compiled/images/Soncezahisni_okuljari/LUXOPTICA/e883bbf4-9c8f-45cc-a377-e1701e43dc23.jpg',
                                  'https://luxoptica.ua/upload/products/compiled/images/Soncezahisni_okuljari/LUXOPTICA/3024e677-f83d-4052-bb7e-0d76246de054.jpg',
                                  57,                  -- lens_width
                                  'LOS 906 YEL',    -- model_code
                                  25,                 -- price
                                  3,                  -- quantity
                                  141,                 -- temple_length
                                  5,                   -- brand_id
                                  13,                  -- color
                                  3,                   -- frame_material
                                  8,                   -- frame_shape
                                  1,                   -- lens_category
                                  2,                   -- lens_material
                                  2);                  -- sex_category

/*GUESS GU00058 95P 59 GRADIENT GREEN*/
insert into sunglasses values (
                                  11, '2023-01-01', '2023-01-01', true,
                                  15,                  -- bridge_width
                                  'https://luxoptica.ua/upload/products/compiled/images/Soncezahisni_okuljari/GUESS/8cb10b54-f2ec-4d7c-95c0-73f13cd0a61c.jpg',
                                  'https://luxoptica.ua/upload/products/compiled/images/Soncezahisni_okuljari/GUESS/d92e9f75-205b-4ef7-8d38-7c2c7cd8727b.jpg',
                                  'https://luxoptica.ua/upload/products/compiled/images/Soncezahisni_okuljari/GUESS/54f66720-5adf-4be2-94ac-16f30e6cbc18.jpg',
                                  'https://luxoptica.ua/upload/products/compiled/images/Soncezahisni_okuljari/GUESS/97e19dce-7042-48d4-9bd4-de1a08f27438.jpg',
                                  59,                  -- lens_width
                                  'GU00058 95P 59',    -- model_code
                                  92,                 -- price
                                  2,                  -- quantity
                                  140,                 -- temple_length
                                  4,                   -- brand_id
                                  15,                  -- color
                                  3,                   -- frame_material
                                  5,                   -- frame_shape
                                  3,                   -- lens_category
                                  2,                   -- lens_material
                                  1);                  -- sex_category

/*GUESS GU7874 01B 54 GRADIENT SMOKE*/
insert into sunglasses values (
                                  12, '2023-01-01', '2023-01-01', true,
                                  21,                  -- bridge_width
                                  'https://luxoptica.ua/upload/products/compiled/images/Soncezahisni_okuljari/GUESS/69a74609-f4a3-43c3-aea4-68d01e3d0c0b.jpg',
                                  'https://luxoptica.ua/upload/products/compiled/images/Soncezahisni_okuljari/GUESS/a71f2e42-7d3b-4744-87a2-0925e353faf5.jpg',
                                  'https://luxoptica.ua/upload/products/compiled/images/Soncezahisni_okuljari/GUESS/4b25e527-96d5-49a8-9260-a301da875f24.jpg',
                                  'https://luxoptica.ua/upload/products/compiled/images/Soncezahisni_okuljari/GUESS/e73a99ea-4394-47b0-9d9e-13a08df1d5ee.jpg',
                                  54,                  -- lens_width
                                  'GU7874 01B 54',    -- model_code
                                  157,                 -- price
                                  1,                  -- quantity
                                  110,                 -- temple_length
                                  4,                   -- brand_id
                                  16,                  -- color
                                  3,                   -- frame_material
                                  3,                   -- frame_shape
                                  1,                   -- lens_category
                                  2,                   -- lens_material
                                  2);                  -- sex_category

/* CASTA F 449 GLD*/
insert into sunglasses values (
                                  13, '2023-01-01', '2023-01-01', true,
                                  21,                  -- bridge_width
                                  'https://luxoptica.ua/upload/products/compiled/images/Soncezahisni_okuljari/CASTA/98f7ef4a-8dc1-444b-9fe9-0eeb4735b0cf.jpg',
                                  'https://luxoptica.ua/upload/products/compiled/images/Soncezahisni_okuljari/CASTA/a5cf04f5-75e3-4b24-a9fe-438eac2b6b11.jpg',
                                  'https://luxoptica.ua/upload/products/compiled/images/Soncezahisni_okuljari/CASTA/539ee0d3-59ee-4a80-961f-1451e545a82e.jpg',
                                  'https://luxoptica.ua/upload/products/compiled/images/Soncezahisni_okuljari/CASTA/98f7ef4a-8dc1-444b-9fe9-0eeb4735b0cf.jpg',
                                  54,                  -- lens_width
                                  'F 449 GLD',    -- model_code
                                  30,                 -- price
                                  4,                  -- quantity
                                  145,                 -- temple_length
                                  3,                   -- brand_id
                                  14,                  -- color
                                  2,                   -- frame_material
                                  9,                   -- frame_shape
                                  4,                   -- lens_category
                                  2,                   -- lens_material
                                  2);                  -- sex_category


/* CASTA CS 1030 CRBRN 57 GRAY/PINK */
insert into sunglasses values (
                                  14, '2023-01-01', '2023-01-01', true,
                                  18,                  -- bridge_width
                                  'https://luxoptica.ua/upload/products/compiled/images/Soncezahisni_okuljari/CASTA/3b6d706a-8a52-44d1-84b9-139d0cccaf70.jpg',
                                  'https://luxoptica.ua/upload/products/compiled/images/Soncezahisni_okuljari/CASTA/084ddbf7-3e82-4cde-8ea1-fe024ee17275.jpg',
                                  'https://luxoptica.ua/upload/products/compiled/images/Soncezahisni_okuljari/CASTA/ada87bf9-1493-4efe-9efd-3e14c9235d81.jpg',
                                  'https://luxoptica.ua/upload/products/compiled/images/Soncezahisni_okuljari/CASTA/bda4433a-940d-40a5-b4a6-f258b62aeed5.jpg',
                                  57,                  -- lens_width
                                  'CS 1030 CRBRN 57',    -- model_code
                                  35,                 -- price
                                  2,                  -- quantity
                                  138,                 -- temple_length
                                  3,                   -- brand_id
                                  17,                  -- color
                                  3,                   -- frame_material
                                  3,                   -- frame_shape
                                  4,                   -- lens_category
                                  2,                   -- lens_material
                                  2);                  -- sex_category


/* POLAROID PLD 6067/S 2M5 53 HE */
insert into sunglasses values (
                                  15, '2023-01-01', '2023-01-01', true,
                                  19,                  -- bridge_width
                                  'https://luxoptica.ua/upload/products/compiled/images/Soncezahisni_okuljari/POLAROID/5a3db0c2-ae08-40e9-a3e2-cece3c0a872b.jpg',
                                  'https://luxoptica.ua/upload/products/compiled/images/Soncezahisni_okuljari/POLAROID/569a4a4e-b229-430f-bc8b-aa47504ab32b.jpg',
                                  'https://luxoptica.ua/upload/products/compiled/images/Soncezahisni_okuljari/POLAROID/6537b2d1-4275-4c67-97d6-e781dda88fac.jpg',
                                  'https://luxoptica.ua/upload/products/compiled/images/Soncezahisni_okuljari/POLAROID/27fd474a-a5b6-46f1-ad3e-a7deaf59dad4.jpg',
                                  53,                  -- lens_width
                                  'PLD 6067/S 2M5 53 HE',    -- model_code
                                  51,                 -- price
                                  4,                  -- quantity
                                  145,                 -- temple_length
                                  2,                   -- brand_id
                                  18,                  -- color
                                  2,                   -- frame_material
                                  4,                   -- frame_shape
                                  4,                   -- lens_category
                                  2,                   -- lens_material
                                  4);                  -- sex_category

/* POLAROID PLD 6121/S SMF 58 UC */
insert into sunglasses values (
                                  16, '2023-01-01', '2023-01-01', true,
                                  14,                  -- bridge_width
                                  'https://luxoptica.ua/upload/products/compiled/images/Soncezahisni_okuljari/POLAROID/748a67a0-ad4b-4374-8059-31a21f5494a7.jpg',
                                  'https://luxoptica.ua/upload/products/compiled/images/Soncezahisni_okuljari/POLAROID/5257207f-055b-44b2-b07f-651ad8fc3924.jpg',
                                  'https://luxoptica.ua/upload/products/compiled/images/Soncezahisni_okuljari/POLAROID/e9a8650f-9c3d-4522-be94-c9f864415340.jpg',
                                  'https://luxoptica.ua/upload/products/compiled/images/Soncezahisni_okuljari/POLAROID/748a67a0-ad4b-4374-8059-31a21f5494a7.jpg',
                                  58,                  -- lens_width
                                  'PLD 6121/S SMF 58 UC',    -- model_code
                                  60,                 -- price
                                  2,                  -- quantity
                                  145,                 -- temple_length
                                  2,                   -- brand_id
                                  1,                  -- color
                                  2,                   -- frame_material
                                  2,                   -- frame_shape
                                  4,                   -- lens_category
                                  2,                   -- lens_material
                                  4);                  -- sex_category



