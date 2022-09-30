-- categories
INSERT INTO categories (id_category, description, is_available) VALUES (1, 'The most rented', true);
INSERT INTO categories (id_category, description, is_available) VALUES (2, 'Family cars', true);
INSERT INTO categories (id_category, description, is_available) VALUES (3, 'The newest cars', true);
INSERT INTO categories (id_category, description, is_available) VALUES (4, 'The cheapest cars', true);
INSERT INTO categories (id_category, description, is_available) VALUES (5, 'The most powerful', false);
INSERT INTO categories (id_category, description, is_available) VALUES (6, 'Old cars', true);
INSERT INTO categories (id_category, description, is_available) VALUES (7, 'The fastest cars', true);
INSERT INTO categories (id_category, description, is_available) VALUES (8, 'College cars', true);

-- cars
INSERT INTO cars (brand, id_category, model, production_year, car_type, color, engine_size, power, mileage)	VALUES( 'Mercedes', 1, 'W204', 2012, 'SUV', 'white', 1796, 170, 120000);
INSERT INTO cars (brand, id_category, model, production_year, car_type, color, engine_size, power, mileage)	VALUES( 'Nissan', 2, 'W205', 2017, 'Hatchback', 'white', 1796, 170, 150000);
INSERT INTO cars (brand, id_category, model, production_year, car_type, color, engine_size, power, mileage)	VALUES( 'Nissan', 3, 'W204', 2014, 'Crossover', 'red', 2200, 224, 200000);
INSERT INTO cars (brand, id_category, model, production_year, car_type, color, engine_size, power, mileage)	VALUES( 'Mercedes', 4, 'W205', 2017, 'Convertible', 'black', 2200, 224, 199999);
INSERT INTO cars (brand, id_category, model, production_year, car_type, color, engine_size, power, mileage)	VALUES( 'Toyota', 5, 'W204', 2012, 'Sedan', 'black', 2200, 224, 300000);
INSERT INTO cars (brand, id_category, model, production_year, car_type, color, engine_size, power, mileage)	VALUES( 'Mercedes', 6, 'W204', 2012, 'Sport', 'silver', 2200, 224, 300001);
INSERT INTO cars (brand, id_category, model, production_year, car_type, color, engine_size, power, mileage)	VALUES( 'Mercedes', 7, 'W212', 2010, 'Coupe', 'gray', 3196, 278, 299999);
INSERT INTO cars (brand, id_category, model, production_year, car_type, color, engine_size, power, mileage)	VALUES( 'Toyota', 8, 'W212', 2012, 'Minivan', 'yellow', 3196, 278, 80000);
INSERT INTO cars (brand, id_category, model, production_year, car_type, color, engine_size, power, mileage)	VALUES( 'Mercedes', 1, 'W212', 2011, 'StationWagon', 'red', 3196, 278, 123100);
INSERT INTO cars (brand, id_category, model, production_year, car_type, color, engine_size, power, mileage)	VALUES( 'Fiat', 2, 'W212', 2010, 'PickupTruck', 'black', 3196, 278, 145900);
INSERT INTO cars (brand, id_category, model, production_year, car_type, color, engine_size, power, mileage)	VALUES( 'Fiat', 3, 'Seicento', 1995, 'PickupTruck', 'blue', 900, 80, 390000);
INSERT INTO cars (brand, id_category, model, production_year, car_type, color, engine_size, power, mileage)	VALUES( 'Fiat', 4, 'Tico', 1996, 'SUV', 'blue', 800, 63, 480000);
INSERT INTO cars (brand, id_category, model, production_year, car_type, color, engine_size, power, mileage)	VALUES( 'Fiat', 5, 'Tipo', 2005, 'Crossover', 'orange', 1198, 98, 150220);



