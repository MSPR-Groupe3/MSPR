USE mspr;

INSERT INTO `user` (`firstName`, `lastName`, `loginEmail`, `passwd`, `role`) VALUES
("Maud","Becam","maudus@aol.fr", "$2y$10$aZ4dOMDeVBZdMEEtbrE1UeB.uGgNbVMOgkF4blMaFssdUm5zPgTBS", "seller"),
("Mickaëlle","Green","mi-k@skyblog.fr","$2y$10$PlfSGdi0KNPTd56.C2Mkk.Am4dLDdtrE0MFyt/Pm8ZE9ANQRsqJgm", "seller"),
("Phi","Tran","filoo@msn.com","$2y$10$BfMtK5f53pEBLk7h0qTDe.MY.tjijMFB4ZWLMv0MdUsv.x3SNwhny", "seller"),
("Corentin","Villiermet","coco@wanadoo.fr","$2y$10$ZPbU3HJ5gQ3/HFh1xWXBYuybb6ZEXulAzm9Cww/5cK88WdNiBFdom", "seller"),
("Mickaël","Gaillard","micky@lycoos.com","$2y$10$7UILhfy1byfq8rSdnnXkuOxfB.29Y/yKtHgc5DH9D4M3iy5Ccr41K", "admin");



INSERT INTO `contact` (`firstName`, `lastName`, `phoneNumber`, `email`) VALUES
("Ernst","Stavro Blofeld","01-01-01-76-50","blofeld_ernst@spectre.com"),
("Julius","No","01-01-02-67-54","no_julius@spectre.com"),
("Giovanni","Sakaki","+265 6 87 55 76 99","g.sakaki@team-rocket.net"),
("Jessie","Musashi","+265 6 87 44 76 88","j.musashi@team-rocket.net"),
("Ozwell","Spencer","1-651-435-265","ozwellspencer@umbrellacorp.com"),
("Edward","Ashford","1-651-435-265","edwardashford@umbrellacorp.com");

INSERT INTO `organization` (`companyName`, `streetAdress1`, `streetAdress2`, `streetAdress3`, `cityName`, `postalCode`, `country`, `phoneNumber`, `email`) VALUES
("SPECTRE", "136 Boulevard Haussmann", null , null , "Paris", "75008", "France", "01-01-01-666", "contact@spectre.com"),
("Team Rocket", "Casino", "entrée derrière le poster" , "sous-sol" , "Céladopole", "211-0205", "Kanto", "+265 1 23 55 76 90", "info@team-rocket.net"),
("Umbrella Corporation", "Le manoir", null , null , "Raccoon City", "55131", "USA", "1-651-435-265", "contact@umbrellacorp.com");



INSERT INTO `product` (`name`, `reference`, `description`, `unitPriceBeforeTax`, `taxRate`, `quantityAvailable`, `isSellable`) VALUES
("Magali", "MAG-NET-001", "Spécialisations : Journalistes, Politiques Techniques : armes à feu, poison, &#34accidents&#34 Localisation : France & International", 20000.00, 0.20, 1, true),
("Mike", "MIK-NET-002", "Spécialisations : Particuliers Techniques : armes à feu, poison, &#34accidents&#34 Localisation : France & International", 10000.00, 0.15, 1, true),
("Linda", "LIN-NET-003", "Spécialisations : Journalistes, Politiques Techniques : armes à feu, poison, &#34accidents&#34 Localisation : France & International", 10000.00, 0.10, 1, true),
("Keylogger", "KEY-LOG-004", "Envie de savoir ce que votre belle-mère tape sur ses appareils ?", 50.76, 0.18, 6, true),
("Spyware", "SPY-LOG-005", "Lancez-vous dans la collecte d'informations des institutions les plus prestigieuses de votre pays", 87.31, 0.15, 23, true),
("Botnet software", "LIN-LOG-006", "Transformez des ordinateurs et serveurs peu sécurisés en une armée prête à se lancer à l'assaut de votre concurrence !", 99.99, 0.10, 46, true),
("Acide fluorhydrique", "ACI-MAT-007", "Un voisin ennuyeux ?", 20000.00, 0.20, 1, true),
("Kit de perçage", "PER-MAT-008", "Pour répondre aux questions que vous vous poserez une fois devant le coffre de la banque", 749.00, 0.20, 1, true),
("Char d'assaut", "CHA-MAT-009", "Pour mener votre propre guerre... rien de personnel ? Si !", 2530000.00, 0.20, 1, true);

INSERT INTO `category` (`name`) VALUES
("Nettoyage"),
("Logiciels"),
("Matériel");


#datetime au format aaaammjj hh:mm:ss ex: '20120618 10:34:09 AM'
INSERT INTO `purchase` (`reference`, `dateOfOrder`, `comment`) VALUES
("2021C0001",'20210401',null),
("2021C0002",'20210402',null),
("2021C0003",'20210403',null),
("2021C0004",'20210404',"attendre le 17 mars avant la livraison"),
("2021C0005",'20210405',null),
("2021C0006",'20210406',null),
("2021C0007",'20210407',null),
("2021C0008",'20210408',null),
("2021C0009",'20210409',null);

INSERT INTO `product_in_purchase` (`quantity`, `price`) VALUES
(1, 23.76),
(1, 34.98),
(1, 98.23),
(1, 54378.98),
(1, 112.78),
(1, 73.01),
(1, 82.34),
(1, 481.54),
(1, 26.87);