-- travel_category 초기 데이터
INSERT INTO travel_category (category_id, category_name, sort_order, reg_user_id, reg_dtt)
VALUES (1, '카테고리1', 1, 'testUser', NOW()),
       (2, '카테고리2', 2, 'testUser', NOW()),
       (3, '카테고리3', 3, 'testUser', NOW());

-- travel 초기 데이터
INSERT INTO travel (travel_id, title, region, category_id, travel_start_dt, travel_end_dt, cost_type, season, vehicle, contents, reg_user_id, reg_dtt)
VALUES (1, '제목1', 'BUSAN', 1, '2025-03-18', '2025-03-20', 'UNDER_100K', 'SPRING', 'WALK', '내용1', 1, NOW()),
       (2, '제목2', 'SEOUL', 2, '2025-03-18', '2025-03-20', 'BETWEEN_200K_300K', 'SPRING', 'PUBLIC_TRANSPORT', '내용2', 1, NOW()),
       (3, '제목3', 'INCHEON', 3, '2025-12-01', '2025-12-31', 'OVER_500K', 'WINTER', 'CAR', '내용3', 1, NOW()),
       (4, '제목4', 'BUSAN', 1, '2025-12-01', '2025-12-31', 'UNDER_100K', 'SUMMER', 'CAR', '내용4', 1, NOW()),
       (5, '제목5', 'SEOUL', 2, '2025-12-01', '2025-12-31', 'BETWEEN_200K_300K', 'WINTER', 'CAR', '내용5', 1, NOW()),
       (6, '제목6', 'INCHEON', 3, '2025-12-01', '2025-12-31', 'OVER_500K', 'WINTER', 'CAR', '내용6', 1, NOW());

-- course 초기 데이터
INSERT INTO course (place_name, address, lon, lat, open_time, close_time, reg_user_id, mdfc_user_id, travel_id, reg_dtt)
VALUES
('장소1', '주소1', '126.9780', '37.5665', '09:00', '18:00', 'testUser', 'testUser', 3, NOW()),
('장소2', '주소2', '126.9850', '37.5700', '10:00', '19:00', 'testUser', 'testUser', 5, NOW()),
('장소3', '주소3', '126.9900', '37.5710', '08:30', '17:30', 'testUser', 'testUser', 2, NOW()),
('장소4', '주소4', '127.0000', '37.5730', '09:00', '18:00', 'testUser', 'testUser', 1, NOW()),
('장소5', '주소5', '127.0100', '37.5740', '10:00', '20:00', 'testUser', 'testUser', 4, NOW()),
('장소6', '주소6', '127.0200', '37.5750', '09:30', '18:30', 'testUser', 'testUser', 6, NOW()),
('장소7', '주소7', '127.0250', '37.5760', '08:00', '17:00', 'testUser', 'testUser', 1, NOW()),
('장소8', '주소8', '127.0300', '37.5770', '09:00', '19:00', 'testUser', 'testUser', 2, NOW()),
('장소9', '주소9', '127.0350', '37.5780', '09:00', '18:00', 'testUser', 'testUser', 3, NOW()),
('장소10', '주소10', '127.0400', '37.5790', '10:00', '20:00', 'testUser', 'testUser', 4, NOW()),
('장소11', '주소11', '127.0450', '37.5800', '09:30', '18:30', 'testUser', 'testUser', 5, NOW()),
('장소12', '주소12', '127.0500', '37.5810', '08:30', '17:30', 'testUser', 'testUser', 6, NOW()),
('장소13', '주소13', '127.0550', '37.5820', '09:00', '18:00', 'testUser', 'testUser', 1, NOW()),
('장소14', '주소14', '127.0600', '37.5830', '10:00', '19:00', 'testUser', 'testUser', 2, NOW()),
('장소15', '주소15', '127.0650', '37.5840', '09:00', '18:00', 'testUser', 'testUser', 3, NOW()),
('장소16', '주소16', '127.0700', '37.5850', '09:30', '18:30', 'testUser', 'testUser', 4, NOW()),
('장소17', '주소17', '127.0750', '37.5860', '08:00', '17:00', 'testUser', 'testUser', 5, NOW()),
('장소18', '주소18', '127.0800', '37.5870', '09:00', '18:00', 'testUser', 'testUser', 6, NOW()),
('장소19', '주소19', '127.0850', '37.5880', '10:00', '20:00', 'testUser', 'testUser', 1, NOW()),
('장소20', '주소20', '127.0900', '37.5890', '09:30', '18:30', 'testUser', 'testUser', 2, NOW()),
('장소21', '주소21', '127.0950', '37.5900', '08:30', '17:30', 'testUser', 'testUser', 3, NOW()),
('장소22', '주소22', '127.1000', '37.5910', '09:00', '18:00', 'testUser', 'testUser', 4, NOW()),
('장소23', '주소23', '127.1050', '37.5920', '09:00', '19:00', 'testUser', 'testUser', 5, NOW()),
('장소24', '주소24', '127.1100', '37.5930', '10:00', '20:00', 'testUser', 'testUser', 6, NOW());

-- AUTO_INCREMENT 초기화 (MariaDB 방식)
ALTER TABLE travel_category AUTO_INCREMENT = 4;
ALTER TABLE travel AUTO_INCREMENT = 7;
ALTER TABLE course AUTO_INCREMENT = 25;
