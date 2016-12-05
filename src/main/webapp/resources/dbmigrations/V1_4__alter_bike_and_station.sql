ALTER TABLE `bikerentalservicedb`.`bike` CHANGE COLUMN `type` `type` INT(11) NULL DEFAULT NULL ;

alter table `bikerentalservicedb`.`station` drop column `station`.`type`
