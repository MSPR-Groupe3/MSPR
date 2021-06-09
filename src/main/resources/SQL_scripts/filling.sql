USE mspr;



INSERT INTO `user` (`firstname`, `lastname`, `loginemail`, `passwd`, `is_active`) VALUES
('Maud','Becam','maudus@aol.fr', '$2a$10$L0USrJZRb5V4AOheTfO2ze3FqdBHaK5S0jKOoZ3uF/onjIrFsUxXC', true ),
('Mickaëlle','Green','mi-k@skyblog.fr','$2a$10$DbFTzNCRPUvAYxpWuwoGWemX40DQ.lL5xINfonh5QR5FkQBitgbKe', true ),
('Phi','Tran','filoo@msn.com','$2a$10$.cJ1cTbW2FgUpVEWkGkLO.tMseDXx4LoJJipTGUKrI0vwGZlWLhBu', true ),
('Corentin','Villiermet','coco@wanadoo.fr','$2a$10$6/UzOwWG4ws6AmFnq/2gOuI1PRcNMnTxZ4gbcBrQy.HKZaCchzJBG', true ),
('Mickaël','Gaillard','micky@lycoos.com','$2a$10$adLks4jKcQBE05Lh1xV0dOkfkx9KtaQ4fQRl9cSr5se5ZF86iJX3y', true);

INSERT INTO `roles` (`role`) VALUES
('seller'),
('admin');

INSERT INTO `role_in_user` (`user_id`, `role_id`) VALUES
(1, 1),
(2, 1),
(3, 1),
(4, 1),
(5, 2);



INSERT INTO `organization` (`company_name`, `street_address1`, `street_address2`, `street_address3`, `city_name`, `postal_code`, `country`, `phone_number`, `email`, `user_id`) VALUES
('SPECTRE', '136 Boulevard Haussmann', null , null , 'Paris', '75008', 'France', '01-01-01-666', 'contact@spectre.com', 1),
('Team Rocket', 'Casino', 'entrée derrière le poster' , 'sous-sol' , 'Céladopole', '211-0205', 'Kanto', '+265 1 23 55 76 90', 'info@team-rocket.net', 2),
('Umbrella Corporation', 'Le manoir', null , null , 'Raccoon City', '55131', 'USA', '1-651-435-265', 'contact@umbrellacorp.com', 3);

INSERT INTO `contact` (`firstname`, `lastname`, `phone_number`, `email`, `organization_id`, `is_reachable`) VALUES
('Ernst','Stavro Blofeld','01-01-01-76-50','blofeld_ernst@spectre.com', 1, true),
('Julius','No','01-01-02-67-54','no_julius@spectre.com', 1, true),
('Giovanni','Sakaki','+265 6 87 55 76 99','g.sakaki@team-rocket.net', 2, true),
('Jessie','Musashi','+265 6 87 44 76 88','j.musashi@team-rocket.net', 2, true),
('Ozwell','Spencer','1-651-435-265','ozwellspencer@umbrellacorp.com', 3, true),
('Edward','Ashford','1-651-435-265','edwardashford@umbrellacorp.com', 3, true);

INSERT INTO `category` (`name`) VALUES
('Nettoyage'),
('Logiciels'),
('Matériel');

INSERT INTO `product` (`name`, `reference`, `description`, `unit_price_before_tax`, `tax_rate`, `quantity_available`, `is_sellable`, `category_id`) VALUES
('Magali', 'MAG-NET-001', 'Spécialisations : Journalistes, Politiques Techniques : armes à feu, poison, &#34accidents&#34 Localisation : France & International', 20000.00, 0.20, 1, true, 1),
('Mike', 'MIK-NET-002', 'Spécialisations : Particuliers Techniques : armes à feu, poison, &#34accidents&#34 Localisation : France & International', 10000.00, 0.15, 1, true, 1),
('Linda', 'LIN-NET-003', 'Spécialisations : Journalistes, Politiques Techniques : armes à feu, poison, &#34accidents&#34 Localisation : France & International', 10000.00, 0.10, 1, true, 1),
('Keylogger', 'KEY-LOG-004', 'Envie de savoir ce que votre belle-mère tape sur ses appareils ?', 50.76, 0.18, 6, true, 2),
('Spyware', 'SPY-LOG-005', 'Lancez-vous dans la collecte d''informations des institutions les plus prestigieuses de votre pays', 87.31, 0.15, 23, true, 2),
('Botnet software', 'LIN-LOG-006', 'Transformez des ordinateurs et serveurs peu sécurisés en une armée prête à se lancer à l''assaut de votre concurrence !', 99.99, 0.10, 46, true, 2),
('Acide fluorhydrique', 'ACI-MAT-007', 'Un voisin ennuyeux ?', 20000.00, 0.20, 1, true, 3),
('Kit de perçage', 'PER-MAT-008', 'Pour répondre aux questions que vous vous poserez une fois devant le coffre de la banque', 749.00, 0.20, 1, true, 3),
('Char d''assaut', 'CHA-MAT-009', 'Pour mener votre propre guerre... rien de personnel ? Si !', 2530000.00, 0.20, 1, true, 3);

-- datetime au format aaaammjj hh:mm:ss ex: '20120618 10:34:09 AM'
INSERT INTO `purchase` (`reference`, `date_of_order`, `comment`, `seller_id`, `contact_id`) VALUES
('2021C0001','20210401',null, 1, 1),
('2021C0002','20210402',null, 1, 2),
('2021C0003','20210403',null, 2, 3),
('2021C0004','20210404','attendre le 17 mars avant la livraison', 2, 3),
('2021C0005','20210405',null, 2, 4),
('2021C0006','20210406',null, 3, 5),
('2021C0007','20210407',null, 3, 6),
('2021C0008','20210408',null, 4, 2),
('2021C0009','20210409',null, 4, 6);

INSERT INTO `product_in_purchase` (`quantity`, `price`, `purchase_id`, `product_id`) VALUES
(1, 23.76, 1, 4),
(1, 34.98, 2, 5),
(1, 98.23, 3, 6),
(1, 54378.98, 4, 9),
(1, 112.78, 5, 3),
(1, 73.01, 6, 4),
(1, 82.34, 7, 5),
(1, 481.54, 8, 1),
(1, 26.87, 9, 8);
