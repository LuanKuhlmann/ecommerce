-- 1️⃣ Criar produtos
INSERT INTO product (id, name, description, price, stock)
VALUES
(UUID(), 'Produto 1', 'Descrição Produto 1', 100.00, 10),
(UUID(), 'Produto 2', 'Descrição Produto 2', 200.00, 5),
(UUID(), 'Produto 3', 'Descrição Produto 3', 50.00, 20);

-- 2️⃣ Criar pedidos para ADMIN e usuários
INSERT INTO orders (id, status, total, created_at, user_id)
VALUES
(UUID(), 'PENDING', 0.00, DATE_SUB(NOW(), INTERVAL 10 DAY), 1),
(UUID(), 'PAID', 0.00, DATE_SUB(NOW(), INTERVAL 15 DAY), 2),
(UUID(), 'PAID', 0.00, DATE_SUB(NOW(), INTERVAL 10 DAY), 3);

-- 3️⃣ Inserir order_items usando IDs reais de produtos
-- Pegar IDs reais de produtos
SET @prod1 = (SELECT id FROM product LIMIT 1 OFFSET 0);
SET @prod2 = (SELECT id FROM product LIMIT 1 OFFSET 1);
SET @prod3 = (SELECT id FROM product LIMIT 1 OFFSET 2);

-- Admin
INSERT INTO order_item (id, order_id, product_id, quantity, price)
SELECT UUID(), o.id, @prod1, 2, p.price
FROM orders o
JOIN product p ON p.id = @prod1
WHERE o.user_id = 1;

-- Usuário 2
INSERT INTO order_item (id, order_id, product_id, quantity, price)
SELECT UUID(), o.id, @prod2, 1, p.price
FROM orders o
JOIN product p ON p.id = @prod2
WHERE o.user_id = 2;

-- Atualizar total dos pedidos
UPDATE orders o
JOIN (
    SELECT order_id, SUM(price * quantity) AS totalSum
    FROM order_item
    GROUP BY order_id
) oi ON o.id = oi.order_id
SET o.total = oi.totalSum;
