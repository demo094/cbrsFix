ALTER TABLE `bikerentalservicedb`.`station`
ADD COLUMN `latitude` DECIMAL(10,7) NULL AFTER `type`,
ADD COLUMN `longitude` DECIMAL(10,7) NULL AFTER `latitude`;

UPDATE station SET latitude=51.2504459, longitude=22.558109 WHERE id=1;
UPDATE station SET latitude=51.236152, longitude=22.5468376 WHERE id = 2;