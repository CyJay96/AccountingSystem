/*
Придумать для своей БД и продемонстрировать:
1. 2 запроса с различными WHERE условиями.
2. 2 запроса с применением встроенных (кроме агрегатных) функций.
3. Запросы на соединения таблиц (JOIN): INNER, LEFT (или RIGHT).
4. Запрос c GROUP BY, HAVING и агрегатными функциями (один, где есть это всё).
5. Запрос c UNION.
6. 2 запроса с подзапросами: один взаимосвязанный, второй - нет.
*/

SELECT * FROM houses;
SELECT * FROM floors;
SELECT * FROM apartments;

-- 1 Запросы с различными WHERE условиями
-- Квартиры с площадью от 47 до 53:
SELECT id AS ApartmentNumber, area AS ApartmentArea FROM apartments WHERE area BETWEEN 47 AND 53;

-- Одновременно является запросом с применением встроенных функций
-- Средняя площадь квартир в первом по списку доме:
SELECT ROUND(AVG(area), 2) AS AverageApartmentArea FROM apartments
WHERE floor_id = (SELECT floor_id FROM apartments LIMIT 1);
-- 1

-- 2 Второй запрос с применением встроенных (кроме агрегатных) функций
-- Отношение количества комнат к количеству человек, проживающих в квартирах:
SELECT id AS ApartmentNumber, TRUNCATE(count_rooms / count_people, 2) AS RoomsToPeople FROM apartments;
-- 2

-- 3 Запросы на соединения таблиц (JOIN): INNER, LEFT
-- Объединение таблиц Houses и Floors:
SELECT floors.id AS FloorNumber, houses.id AS HouseNumber,
houses.count_flats_on_floor AS CountFlatsOnFloor
FROM houses INNER JOIN floors ON houses.id = floors.house_id;

-- Объединение таблиц Houses, Floors и Apartments:
SELECT apartments.id AS ApartmentNumber, apartments.count_people AS CountPeople,
apartments.count_rooms AS CountRooms, apartments.area AS ApartmentArea,
apartments.floor_id AS FloorNumber, floors.house_id AS HouseNumber
FROM apartments LEFT JOIN floors ON apartments.floor_id = floors.id;
-- 3

-- 4 Запрос c GROUP BY, HAVING и агрегатными функциями
-- Одновременно является невзаимосвязанным запросом с подзапросом
-- Количество людей, проживающих на каждом этаже в первом по списку доме:
SELECT floor_id AS FloorNumber, SUM(count_people) AS AllPeople
FROM apartments GROUP BY floor_id HAVING floor_id <=
(SELECT MAX(id) FROM floors WHERE house_id = (SELECT MIN(house_id) FROM floors));
-- 4

-- 5 Запрос c UNION
-- Объединение таблицы домов, имеющих кол-во этажей >= 10, с таблицей домов, имеющих кол-во этажей < 10:
SELECT house_id AS HouseNumber, COUNT(id) AS CountFloors FROM floors GROUP BY house_id HAVING COUNT(id) >= 10
UNION
SELECT house_id AS HouseNumber, COUNT(id) AS CountFloors FROM floors GROUP BY house_id HAVING COUNT(id) < 10;
-- 5

-- 6 Взаимосвязанный запрос с подзапросом
-- Таблица этажей в первом по списку доме, кол-во проживающих людей на которых < 8:
SELECT id AS FloorNumber FROM floors
WHERE 8 < (SELECT SUM(count_people) FROM apartments WHERE floor_id = floors.id AND house_id = (SELECT MIN(house_id) FROM floors));
-- 6
