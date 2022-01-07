INSERT INTO roles (id, name) VALUES (1, 'USER_ROLE')





-- Оливье
INSERT INTO recipes (id, name, kcal, proteins, fats, carbohydrates, cooking_time, image)
VALUES (2, 'Оливье', 165, 100, 100, 100, '1 hour', 'https://www.gastronom.ru/binfiles/images/20180119/b17ee29a.jpg');

INSERT INTO recipe_steps (id, image, text, recipe)
VALUES (1, 'https://www.gastronom.ru/binfiles/images/20180119/b5997a05.jpg', 'Сварить морковь и картофель', 2),
 (2, 'https://www.gastronom.ru/binfiles/images/20180119/b89ab25e.jpg', 'Сварить яйца вкрутую', 2),
 (3, 'https://www.gastronom.ru/binfiles/images/20180119/b6eb13de.jpg', 'Нарезать равными по размеру мелкими кубиками яйца, картофель, колбасу, морковку', 2),
 (4, 'https://www.gastronom.ru/binfiles/images/20180119/b88101c7.jpg', 'Огурцы нарезать и немного отжать, чтобы не было много жидкости в салате', 2),
 (5, 'https://www.gastronom.ru/binfiles/images/20180119/b7378693.jpg', 'В нарезанные ингредиенты всыпать горошек (слив воду из него), нарезать зелень, смешать с майонезом. Посолить и поперчить. Убрать в холодильник на 1 ч.', 2);


INSERT INTO recipes (id, name, kcal, proteins, fats, carbohydrates, cooking_time, image)
VALUES (3, 'Салат Цезарь', 232.6, 6.4, 16.7, 15.3, '30 minutes', 'https://www.gastronom.ru/binfiles/images/20180116/bd80b965.jpg');

INSERT INTO recipe_steps (id, image, text, recipe)
VALUES (6, '', 'Листья салата аккуратно промойте, обсушите бумажным полотенцем и уберите в холодильник до подачи', 3),
 (7, '', 'С белого хлеба срежьте корочку, мякоть нарежьте кубиками со стороной 1 см и подсушите до золотистого цвета в разогретой до 180С духовке. Пару раз крутоны в духовке нужно будет перевернуть.', 3),
 (8, '', 'Раздавите чеснок и разотрите его с солью. Добавьте 1 ст.л. оливкового масла и прогрейте чеснок на небольшом огне. Добавьте в смесь крутоны и, перемешав, держите на огне 1-2 мин.', 3),
 (9, '', 'Крупное сырое яйцо наколите с тупого конца и опустите на 1 мин. в кастрюлю с едва кипящей водой.', 3),
 (10, '', 'Натрите салатную миску чесноком и выложите в нее зелень', 3),
 (11, '', 'Сбрызните маслом и аккуратно перемешайте. Приправьте солью и перцем, добавьте лимонный сок и несколько капель вустерского соуса и снова перемешайте.', 3),
 (12, '', 'Разбейте яйцо и вылейте на салат, перемешайте, чтобы оно покрыло салатные листья. Посыпьте салат сыром, перемешайте, добавьте крутоны и еще раз перемешайте', 3)

INSERT INTO products (id, name)
VALUES (1, 'салат ромэн'),
       (2, 'багет'),
       (3, 'чеснок молодой'),
       (4, 'масло оливковое extra virgin'),
       (5, 'крупное яйцо'),
       (6, 'сок лимона'),
       (7, 'соус вустерский'),
       (8, 'сыр пармезан тертый'),
       (9, 'соль, свежемолотый черный перец');


INSERT INTO ingredients (id, product_id, count, recipe_id)
VALUES (1, 1, '200 г', 3),
       (2, 2, '100 г', 3),
       (3, 3, '1 зубчик', 3),
       (4, 4, '50 г', 3),
       (5, 5, '1 шт', 3),
       (6, 6, '1', 3),
       (7, 7, '', 3),
       (8, 8, '2 ст.л.', 3),
       (9, 9, '', 3)


INSERT INTO recipes (id, name, kcal, proteins, fats, carbohydrates, cooking_time, image)
VALUES (4, 'Салат Цезарь2', 232.6, 6.4, 16.7, 15.3, '30 minutes', 'https://www.gastronom.ru/binfiles/images/20180116/bd80b965.jpg');

INSERT INTO menus (id, name, image, description)
VALUES (1, 'Легкое меню', 'https://static.mk.ru/upload/entities/2020/05/17/20/articles/detailPicture/f9/20/65/6d/2791f88205d6c1f158784037c400a4a9.jpg', 'Легкое меню для ...');

INSERT INTO menus_monday (menu_id, monday_id)
VALUES (1, 3),(1,2);

INSERT INTO menus_tuesday (menu_id, tuesday_id)
VALUES (1, 3),(1,2);


INSERT INTO menus (id, name, image, description)
VALUES (2, 'Меню', 'https://static.mk.ru/upload/entities/2020/05/17/20/articles/detailPicture/f9/20/65/6d/2791f88205d6c1f158784037c400a4a9.jpg', 'Легкое меню для ...');

INSERT INTO menus (id, name, image, description)
VALUES (3, 'Ресторан', 'https://www.restoran.ru/upload/resize_cache/iblock/2fb/1920_1080_11a88371ca9e7ba72ce6f5767ba9eff1a/3.jpg', 'Ресторан ...');
