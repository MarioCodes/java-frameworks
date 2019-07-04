DELETE FROM Taco_Order_Tacos;
DELETE FROM Taco_Ingredients;
DELETE FROM Taco;
DELETE FROM Taco_Order;
DELETE FROM Ingredient;

INSERT INTO Ingredient (id, name, type)
    VALUES ("FLTO", "Flour Tortilla", "Wrap");
INSERT INTO Ingredient (id, name, type)
    VALUES ("COTO", "Corn Tortilla", "Wrap");
INSERT INTO Ingredient (id, name, type)
    VALUES ("GRBF", "Ground Beef", "Protein");
INSERT INTO Ingredient (id, name, type)
    VALUES ("CARN", "Carnitas", "Protein");
INSERT INTO Ingredient (id, name, type)
    VALUES ("TMTO", "Diced Tomatoes", "Veggies");
INSERT INTO Ingredient (id, name, type)
    VALUES ("LETC", "Lettuce", "Veggies");
INSERT INTO Ingredient (id, name, type)
    VALUES ("CHED", "Cheddar", "Cheese");
INSERT INTO Ingredient (id, name, type)
    VALUES ("JACK", "Monterrey Jack", "Cheese");
INSERT INTO Ingredient (id, name, type)
    VALUES ("SLSA", "Salsa", "Sauce");
INSERT INTO Ingredient (id, name, type)
    VALUES ("SRCR", "Sour Cream", "Sauce");
