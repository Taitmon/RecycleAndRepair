INSERT INTO Employee
(EmployeeId, EmployeeName )
VALUES  (1, 'Taitmon Lynch'),
        (2, 'Obed Stamp'),
        (3, 'Susan Tran'),
        (4, 'Dayton Thomas'),
        (5, 'Akeem Payne'),
        (6, 'Kaleb Walker')

INSERT INTO Category
(CategoryId, CategoryName)
VALUES  (1, Part),
        (2, Accessory),
        (3, Labor)

INSERT INTO Item
(ItemId, ItemName, CategoryId, RetailPrice, UnitPrice)
VALUES  (1, 'Iphone 5 Glass/LCD', 1, 49.99, 14.99),
        (2, 'Iphone 5c Glass/LCD', 1, 49.99, 14.99),
        (3, 'Iphone 5s Glass/LCD', 1, 59.99, 24.99),
        (4, 'Iphone 6 Glass/LCD', 1, 69.99, 29.99),
        (5, 'Iphone 6 Plus Glass/LCD', 1, 79.99, 31.99),
        (6, 'Iphone 6s Glass/LCD', 1, 79.99, 36.99),
        (7, 'Iphone 6s Plus Glass/LCD', 1, 89.99, 37.99),
        (8, 'Iphone 7 Glass/LCD', 1, 99.99, 40.99),
        (9, 'Iphone 7 Plus Glass/LCD', 1, 109.99, 45.99),
        (10, 'Iphone 8 Glass/LCD', 1, 129.99, 49.99),
        (11, 'Iphone 8 Plus Glass/LCD', 1, 139.99, 53.99),

        (12, 'Iphone 5 Case', 2, 19.99, 14.99),
        (13, 'Iphone 5c Case', 2, 19.99, 14.99),
        (14, 'Iphone 5s Case', 2, 19.99, 14.99),
        (15, 'Iphone 6 Case', 2, 24.99, 16.99),
        (16, 'Iphone 6 Plus Case', 2, 25.99, 16.99),
        (17, 'Iphone 6s Case', 2, 27.99, 19.99),
        (18, 'Iphone 6s Plus Case', 2, 29.99, 21.99),
        (19, 'Iphone 7 Case', 2, 34.99, 23.99),
        (20, 'Iphone 7 Case', 2, 39.99, 24.99),
        (21, 'Iphone 8 Case', 2, 45.99, 26.99),
        (22, 'Iphone 8 Plus Case', 2, 49.99, 27.99),

        (23, 'Iphone 5 Tempered Glass', 2, 29.99, 4.99),
        (24, 'Iphone 5c Tempered Glass', 2, 29.99, 4.99),
        (25, 'Iphone 5s Tempered Glass', 2, 29.99, 4.99),
        (26, 'Iphone 6 Tempered Glass', 2, 29.99, 4.99),
        (27, 'Iphone 6 Plus Tempered Glass', 2, 29.99, 4.99),
        (28, 'Iphone 6s Tempered Glass', 2, 29.99, 4.99),
        (29, 'Iphone 6s Plus Tempered Glass', 2, 29.99, 4.99),
        (30, 'Iphone 7 Tempered Glass', 2, 29.99, 4.99),
        (31, 'Iphone 7 Tempered Glass', 2, 29.99, 4.99),
        (32, 'Iphone 8 Tempered Glass', 2, 29.99, 4.99),
        (33, 'Iphone 8 Plus Tempered Glass', 2, 29.99, 4.99),

        (34, 'Galaxy s5 Glass/LCD', 1, 99.99, 44.99),
        (35, 'Galaxy s6 Glass/LCD', 1, 139.99, 76.99),
        (36, 'Galaxy s7 Glass/LCD', 1, 149.99, 79.99),
        (37, 'Galaxy s8 Glass/LCD', 1, 179.99, 82.99),
        (38, 'Galaxy s9 Glass/LCD', 1, 199.99, 89.99),
        (39, 'Galaxy s10 Glass/LCD', 1, 299.99, 166.99),
        (40, 'Galaxy Note Edge Glass/LCD', 1, 199.99, 99.99),
        (41, 'Galaxy Note5 Glass/LCD', 1, 159.99, 84.99),
        (42, 'Galaxy Note7 Glass/LCD', 1, 559.99, 248.99),
        (43, 'Galaxy Note9 Glass/LCD', 1, 249.99, 144.99),

        (44, 'Galaxy s5 Case', 2, 29.99, 14.99),
        (45, 'Galaxy s6 Case', 2, 29.99, 14.99),
        (46, 'Galaxy s7 Case', 2, 29.99, 14.99),
        (47, 'Galaxy s8 Case', 2, 39.99, 14.99),
        (48, 'Galaxy s9 Case', 2, 39.99, 17.99),
        (49, 'Galaxy s10 Case', 2, 49.99, 19.99),
        (50, 'Galaxy Note Edge Case', 2, 29.99, 14.99),
        (51, 'Galaxy Note5 Case', 2, 39.99, 19.99),
        (52, 'Galaxy Note7 Case', 2, 69.99, 34.99),
        (53, 'Galaxy Note9 Case', 2, 49.99, 19.99),

        (54, 'Galaxy s5 Tempered Glass', 2, 29.99, 14.99),
        (55, 'Galaxy s6 Tempered Glass', 2, 29.99, 14.99),
        (56, 'Galaxy s7 Tempered Glass', 2, 39.99, 17.99),
        (57, 'Galaxy s8 Tempered Glass', 2, 39.99, 17.99),
        (58, 'Galaxy s9 Tempered Glass', 2, 39.99, 17.99),
        (59, 'Galaxy s10 Tempered Glass', 2, 49.99, 19.99),
        (60, 'Galaxy Note Edge Tempered Glass', 2, 39.99, 17.99),
        (61, 'Galaxy Note5 Tempered Glass', 2, 29.99, 14.99),
        (62, 'Galaxy Note7 Tempered Glass', 2, 59.99, 24.99),
        (63, 'Galaxy Note9 Tempered Glass', 2, 39.99, 17.99),
        (64, '1 Hour Labor Charge', 3, 19.99, 0),
        (65, '2 Hour Labor Charge', 3, 29.99, 0),
        (66, '3 Hour Labor Charge', 3, 39.99, 0),
        (67, 'Liquid Damage Repair', 3, 69.99, 0),
        (68, 'Frame Repair', 3, 29.99, 0),
        (69, 'Battery Replacement', 3, 39.99, 14.99)

INSERT INTO Customer
(CustomerId, CustomerName )
VALUES  (1, 'Tohon Lynch'),
        (2, 'Lisa Stamp'),
        (3, 'Latika Wong'),
        (4, 'Patricia Thomas'),
        (5, 'Ron Turley'),
        (6, 'Luke Walker'),
        (7, 'Lenard Nimoy'),
        (8, 'James Kirk'),
        (9, 'Lenard McCoy'),
        (10, 'Schn Spock'),
        (11, 'Elijah King'),
        (12, 'Jessica Walker')


INSERT INTO Manufacturer
(ManufacturerId, ManufacturerName)
VALUES  (1, 'Apple'),
        (2, 'Samsung')

INSERT INTO Model
(ModelId, ModelName, ManufacturerId)
VALUES  (1, 'Iphone 5', 1),
        (2, 'Iphone 5c', 1),
        (3, 'Iphone 5s', 1),
        (4, 'Iphone 6', 1),
        (5, 'Iphone 6 Plus', 1),
        (6, 'Iphone 6s', 1),
        (7, 'Iphone 6s Plus', 1),
        (8, 'Iphone 7', 1),
        (9, 'Iphone 7 Plus', 1),
        (10, 'Iphone 8', 1),
        (11, 'Iphone 8 Plus', 1),
        (34, 'Galaxy s5', 2),
        (35, 'Galaxy s6', 2),
        (36, 'Galaxy s7', 2),
        (37, 'Galaxy s8', 2),
        (38, 'Galaxy s9', 2),
        (39, 'Galaxy s10', 2),
        (40, 'Galaxy Note Edge', 2),
        (41, 'Galaxy Note5', 2),
        (42, 'Galaxy Note7', 2),
        (43, 'Galaxy Note9', 2)

//TODO
INSERT INTO ItemModel
(ItemModelId, ModelId, ItemId)
VALUES  (1, 1, 1),
        (2, 2, 2),
        (3, 3, 3),
        (4, 4, 4),
        (5, 5, 5),
        (6, 6, 6),
        (7, 7, 7),
        (8, 8, 8),
        (9, 9, 9),
        (10, 10, 10),
        (11, 11, 11),
        (12, 1, 12),
        (13, 2, 13),
        (14, 3, 14),
        (15, 4, 15),
        (16, 5, 16),
        (17, 6, 17),
        (18, 7, 18),
        (19, 8, 19),
        (20, 9, 20),
        (21, 10, 21),
        (22, 11, 22),
        (23, 1, 23),
        (24, 2, 24),
        (25, 3, 25),
        (26, 4, 26),
        (27, 5, 27),
        (28, 6, 28),
        (29, 7, 29),
        (30, 8, 30),
        (31, 9, 31),
        (32, 10, 32),
        (33, 11, 33),
        (34, 34, 34),
        (35, 35, 35),
        (36, 36, 36),
        (37, 37, 37),
        (38, 38, 38),
        (39, 39, 39),
        (40, 40, 40),
        (41, 41, 41),
        (42, 42, 42),
        (43, 43, 43),
        (44, 34, 44),
        (45, 35, 45),
        (46, 36, 46),
        (47, 37, 47),
        (48, 38, 48),
        (49, 39, 49),
        (50, 40, 50),
        (51, 41, 51),
        (52, 42, 52),
        (53, 43, 53),
        (54, 34, 54),
        (55, 35, 55),
        (56, 36, 56),
        (57, 37, 57),
        (58, 38, 58),
        (59, 39, 59),
        (60, 40, 60),
        (61, 41, 61),
        (62, 42, 62),
        (63, 43, 63),
        (64, null, 64),
        (65, null, 65),
        (66, null, 66),
        (67, null, 67),
        (68, null, 68),
        (69, null, 69)

INSERT INTO Device
(DeviceId, IMEI, ModelId)
VALUES  (1, 509752806028116, 1),
        (2, 997589348315885, 2),
        (3, 518716134451042, 3),
        (4, 452106539694859, 4),
        (5, 452728890062770, 5),
        (6, 010239625211950, 6),
        (7, 912734224074469, 34),
        (8, 998244723917819, 35),
        (9, 447115822814288, 36),
        (10, 459752406279929, 37),
        (11, 525316689554471, 38),
        (12, 865745847219635, 39)

INSERT INTO WorkOrder
(WorkOrderId, EmployeeId, DeviceId, CustomerId)
VALUES  (1, 1, 1, 1),
        (2, 2, 2, 2),
        (3, 3, 3, 3),
        (4, 4, 4, 4),
        (5, 5, 5, 5),
        (6, 6, 6, 6),
        (7, 1, 7, 7),
        (8, 2, 8, 8),
        (9, 3, 9, 9),
        (10, 4, 10, 10),
        (11, 5, 11, 11),
        (12, 6, 12, 12)

INSERT INTO WorkOrderDetail
(WorkOrderDetailId, ItemId, Quantity, WorkOrderId, Discount)
VALUES  (1, 1, 1, 1, 0.10),
        (2, 12, 2, 1, 0.10),
        (3, 2, 1, 2, 0.10),
        (4, 13, 1, 2, 0.10),
        (5, 3, 1, 3, 0.15),
        (6, 14, 1, 3, 0.15),
        (7, 39, 1, 3, 0.15),
        (8, 4, 1, 4, 0.15),
        (9, 5, 1, 5, 0.10),
        (10, 16, 2, 5, 0.10),
        (11, 6, 1, 6, 0.15),
        (12, 17, 5, 6, 0.15),
        (13, 34, 1, 7, 0),
        (14, 44, 1, 7, 0),
        (15, 35, 1, 8, 0),
        (16, 45, 1, 8, 0),
        (17, 36, 1, 9, 0),
        (18, 46, 2, 9, 0),
        (19, 37, 1, 10, 0),
        (20, 47, 1, 10, 0),
        (21, 38, 1, 11, 0),
        (22, 48, 1, 11, 0),
        (23, 39, 1, 12, 0),
        (24, 48, 1, 12, 0)








