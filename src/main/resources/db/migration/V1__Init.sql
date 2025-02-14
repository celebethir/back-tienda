CREATE TABLE IF NOT EXISTS "role" (
  id SERIAL NOT NULL PRIMARY KEY,
  name VARCHAR(20) NOT NULL CHECK (name IN ('Admin', 'User'))
);

CREATE TABLE IF NOT EXISTS "user" (
    id SERIAL NOT NULL PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    role INT NOT NULL REFERENCES role(id)
);

CREATE TABLE IF NOT EXISTS "user_roles" (
    id SERIAL NOT NULL PRIMARY KEY,
    user_id INT REFERENCES user(id),
    role_id INT REFERENCES role(id)
);

CREATE TABLE IF NOT EXISTS "client" (
    id SERIAL NOT NULL PRIMARY KEY,
    user_id INT REFERENCES user(id),
    email VARCHAR(255),
    name VARCHAR(50) NOT NULL,
    lastname VARCHAR(100) NOT NULL,
    phone VARCHAR(20) NOT NULL,
    banned_list BOOLEAN
);

CREATE TABLE IF NOT EXISTS "category" (
    id SERIAL NOT NULL PRIMARY KEY,
    name VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS "supplier" (
    id SERIAL NOT NULL PRIMARY KEY,
    name VARCHAR(100),
    contact VARCHAR(100),
    email VARCHAR(255),
    web VARCHAR(255),
    phone VARCHAR(15),
    minimum_order INT
);

CREATE TABLE IF NOT EXISTS "product" (
    id SERIAL NOT NULL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    category_id INT NOT NULL REFERENCES category(id),
    supplier_id INT NOT NULL REFERENCES supplier(id),
    buy_price NUMERIC(10, 2) NOT NULL,
    sell_price NUMERIC(10, 2) NOT NULL,
    bar_code INT,
    inventory INT,
    image TEXT
);

CREATE TABLE IF NOT EXISTS "wishlist" (
    id SERIAL NOT NULL PRIMARY KEY,
    client_id INT NOT NULL REFERENCES client(id),
);

CREATE TABLE IF NOT EXISTS "wishlist_products" (
    id SERIAL NOT NULL PRIMARY KEY,
    wishlist_id INT NOT NULL REFERENCES wishlist(id) ON DELETE CASCADE,
    product_id INT NOT NULL REFERENCES product(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS "order_status" (
    id SERIAL NOT NULL PRIMARY KEY,
    status VARCHAR(20) NOT NULL CHECK (status IN ('Pending', 'Confirmed', 'Shipped', 'Delivered', 'Cancelled'))
);

CREATE TABLE IF NOT EXISTS "reservation" (
    id SERIAL NOT NULL PRIMARY KEY,
    client_id INT NOT NULL REFERENCES client(id),
    product_id INT NOT NULL REFERENCES product(id),
    status_id INT NOT NULL REFERENCES order_status(id),
    order_date TIMESTAMP NOT NULL,
    upfront NUMERIC(10, 2)
);

INSERT INTO role (name) VALUES ('Admin');
INSERT INTO role (name) VALUES ('User');

INSERT INTO "user" (username, password, role) VALUES ('gameshop.levelup@gmail.com', '8.Massieu', 1)
