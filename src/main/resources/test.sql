INSERT INTO roles (id, name) VALUES (1, 'USER_ROLE')


-- Оливье
INSERT INTO recipes (id, name, kcal, proteins, fats, carbohydrates, cooking_time)
VALUES (2, 'Оливье', 165, 100, 100, 100, '1 hour');

INSERT INTO recipe_steps (id, image, text, recipe)
VALUES (1, 'https://www.gastronom.ru/binfiles/images/20180119/b5997a05.jpg', 'Сварить морковь и картофель', 2),
 (2, 'https://www.gastronom.ru/binfiles/images/20180119/b89ab25e.jpg', 'Сварить яйца вкрутую', 2),
 (3, 'https://www.gastronom.ru/binfiles/images/20180119/b6eb13de.jpg', 'Нарезать равными по размеру мелкими кубиками яйца, картофель, колбасу, морковку', 2),
 (4, 'https://www.gastronom.ru/binfiles/images/20180119/b88101c7.jpg', 'Огурцы нарезать и немного отжать, чтобы не было много жидкости в салате', 2),
 (5, 'https://www.gastronom.ru/binfiles/images/20180119/b7378693.jpg', 'В нарезанные ингредиенты всыпать горошек (слив воду из него), нарезать зелень, смешать с майонезом. Посолить и поперчить. Убрать в холодильник на 1 ч.', 2);
