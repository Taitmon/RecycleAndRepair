
CREATE TABLE Employee (
                EmployeeId INT AUTO_INCREMENT NOT NULL,
                EmployeeName VARCHAR(30) NOT NULL,
                PRIMARY KEY (EmployeeId)
);


CREATE TABLE Category (
                CategoryId INT AUTO_INCREMENT NOT NULL,
                CategoryName VARCHAR(30) NOT NULL,
                PRIMARY KEY (CategoryId)
);


CREATE TABLE Item (
                ItemId INT NOT NULL,
                ItemName VARCHAR(30) NOT NULL,
                CategoryId INT NOT NULL,
                RetailPrice DECIMAL NOT NULL,
                UnitPrice DECIMAL,
                PRIMARY KEY (ItemId)
);


CREATE TABLE Customer (
                CustomerId INT AUTO_INCREMENT NOT NULL,
                CustomerName VARCHAR(30) NOT NULL,
                PRIMARY KEY (CustomerId)
);


CREATE TABLE Manufacturer (
                ManufacturerId INT NOT NULL,
                ManufacturerName VARCHAR(30) NOT NULL,
                PRIMARY KEY (ManufacturerId)
);


CREATE TABLE Model (
                ModelId INT AUTO_INCREMENT NOT NULL,
                ModelName VARCHAR(30) NOT NULL,
                ManufacturerId INT NOT NULL,
                PRIMARY KEY (ModelId)
);


CREATE TABLE ItemModel (
                ItemModelId INT AUTO_INCREMENT NOT NULL,
                ModelId INT,
                ItemId INT NOT NULL,
                PRIMARY KEY (ItemModelId)
);


CREATE TABLE Device (
                DeviceId INT AUTO_INCREMENT NOT NULL,
                IMEI VARCHAR(15) NOT NULL,
                ModelId INT NOT NULL,
                PRIMARY KEY (DeviceId)
);


CREATE TABLE WorkOrder (
                WorkOrderId INT AUTO_INCREMENT NOT NULL,
                EmployeeId INT NOT NULL,
                DeviceId INT NOT NULL,
                CustomerId INT NOT NULL,
                PRIMARY KEY (WorkOrderId)
);


CREATE TABLE WorkOrderDetail (
                WorkOrderDetailId INT AUTO_INCREMENT NOT NULL,
                ItemId INT NOT NULL,
                Quantity INT NOT NULL,
                WorkOrderId INT NOT NULL,
                Discount DECIMAL(8,2) NOT NULL,
                PRIMARY KEY (WorkOrderDetailId)
);


ALTER TABLE WorkOrder ADD CONSTRAINT employee_workorder_fk
FOREIGN KEY (EmployeeId)
REFERENCES Employee (EmployeeId)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE Item ADD CONSTRAINT category_item_fk
FOREIGN KEY (CategoryId)
REFERENCES Category (CategoryId)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE WorkOrderDetail ADD CONSTRAINT item_workorderdetail_fk
FOREIGN KEY (ItemId)
REFERENCES Item (ItemId)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE ItemModel ADD CONSTRAINT item_itemmodel_fk
FOREIGN KEY (ItemId)
REFERENCES Item (ItemId)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE WorkOrder ADD CONSTRAINT customer_workorder_fk
FOREIGN KEY (CustomerId)
REFERENCES Customer (CustomerId)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE Model ADD CONSTRAINT manufacturer_model_fk
FOREIGN KEY (ManufacturerId)
REFERENCES Manufacturer (ManufacturerId)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE Device ADD CONSTRAINT model_device_fk
FOREIGN KEY (ModelId)
REFERENCES Model (ModelId)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE ItemModel ADD CONSTRAINT model_itemmodel_fk
FOREIGN KEY (ModelId)
REFERENCES Model (ModelId)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE WorkOrder ADD CONSTRAINT device_workorder_fk
FOREIGN KEY (DeviceId)
REFERENCES Device (DeviceId)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE WorkOrderDetail ADD CONSTRAINT workorder_workorderdetail_fk
FOREIGN KEY (WorkOrderId)
REFERENCES WorkOrder (WorkOrderId)
ON DELETE NO ACTION
ON UPDATE NO ACTION;
