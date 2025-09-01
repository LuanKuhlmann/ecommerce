-- Pedidos para ADMIN
INSERT INTO orders (id, status, total, created_at, user_id)
VALUES
(UUID(), 'PENDENTE', 0.00, DATE_SUB(NOW(), INTERVAL 10 DAY), 1);

-- Pedidos para usuários comuns
INSERT INTO orders (id, status, total, created_at, user_id)
VALUES
(UUID(), 'PAGO', 0.00, DATE_SUB(NOW(), INTERVAL 15 DAY), 2),
(UUID(), 'PAGO', 0.00, DATE_SUB(NOW(), INTERVAL 10 DAY), 2),
(UUID(), 'PAGO', 0.00, DATE_SUB(NOW(), INTERVAL 5 DAY), 3),
(UUID(), 'PAGO', 0.00, DATE_SUB(NOW(), INTERVAL 3 DAY), 3),
(UUID(), 'PAGO', 0.00, DATE_SUB(NOW(), INTERVAL 2 DAY), 4),
(UUID(), 'PAGO', 0.00, DATE_SUB(NOW(), INTERVAL 1 DAY), 5),
(UUID(), 'PENDENTE', 0.00, NOW(), 6);

-- OrderItems: 2 a 3 itens por pedido, preço variado
-- Admin
INSERT INTO order_item (id, order_id, product_id, quantity, price)
SELECT UUID(), o.id, UUID(), 1, 100.00
FROM orders o WHERE o.user_id = 1;
INSERT INTO order_item (id, order_id, product_id, quantity, price)
SELECT UUID(), o.id, UUID(), 2, 200.00
FROM orders o WHERE o.user_id = 1;

-- Usuário 2
INSERT INTO order_item (id, order_id, product_id, quantity, price)
SELECT UUID(), o.id, UUID(), 1, 50.00
FROM orders o WHERE o.user_id = 2;
INSERT INTO order_item (id, order_id, product_id, quantity, price)
SELECT UUID(), o.id, UUID(), 2, 75.00
FROM orders o WHERE o.user_id = 2;

-- Usuário 3
INSERT INTO order_item (id, order_id, product_id, quantity, price)
SELECT UUID(), o.id, UUID(), 1, 60.00
FROM orders o WHERE o.user_id = 3;
INSERT INTO order_item (id, order_id, product_id, quantity, price)
SELECT UUID(), o.id, UUID(), 1, 80.00
FROM orders o WHERE o.user_id = 3;
INSERT INTO order_item (id, order_id, product_id, quantity, price)
SELECT UUID(), o.id, UUID(), 1, 90.00
FROM orders o WHERE o.user_id = 3;

-- Usuário 4
INSERT INTO order_item (id, order_id, product_id, quantity, price)
SELECT UUID(), o.id, UUID(), 3, 20.00
FROM orders o WHERE o.user_id = 4;
INSERT INTO order_item (id, order_id, product_id, quantity, price)
SELECT UUID(), o.id, UUID(), 2, 15.00
FROM orders o WHERE o.user_id = 4;

-- Usuário 5
INSERT INTO order_item (id, order_id, product_id, quantity, price)
SELECT UUID(), o.id, UUID(), 1, 200.00
FROM orders o WHERE o.user_id = 5;
INSERT INTO order_item (id, order_id, product_id, quantity, price)
SELECT UUID(), o.id, UUID(), 1, 100.00
FROM orders o WHERE o.user_id = 5;

-- Usuário 6
INSERT INTO order_item (id, order_id, product_id, quantity, price)
SELECT UUID(), o.id, UUID(), 2, 125.00
FROM orders o WHERE o.user_id = 6;
INSERT INTO order_item (id, order_id, product_id, quantity, price)
SELECT UUID(), o.id, UUID(), 1, 0.00
FROM orders o WHERE o.user_id = 6;

-- Atualiza o total de cada pedido
UPDATE orders o
JOIN (
    SELECT order_id, SUM(price * quantity) AS totalSum
    FROM order_item
    GROUP BY order_id
) oi ON o.id = oi.order_id
SET o.total = oi.totalSum;
