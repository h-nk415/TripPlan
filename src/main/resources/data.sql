-- usersテーブルにダミーデータを挿入
INSERT INTO users (email, password, displayname)
VALUES
  ('example1@example.com', '$2a$10$A7MbabscmadFQD26O3SmM.ryrtLhY3sI9vgZKB3x6UjoKeLwuc17e', 'ユーザー1'),
  ('example2@example.com', '$2a$10$A7MbabscmadFQD26O3SmM.ryrtLhY3sI9vgZKB3x6UjoKeLwuc17e', 'ユーザー2'),
  ('example3@example.com', '$2a$10$A7MbabscmadFQD26O3SmM.ryrtLhY3sI9vgZKB3x6UjoKeLwuc17e', 'ユーザー3'),
  ('example4@example.com', '$2a$10$A7MbabscmadFQD26O3SmM.ryrtLhY3sI9vgZKB3x6UjoKeLwuc17e', 'ユーザー4'),
  ('example5@example.com', '$2a$10$A7MbabscmadFQD26O3SmM.ryrtLhY3sI9vgZKB3x6UjoKeLwuc17e', 'ユーザー5');

-- planテーブルにダミーデータを挿入
INSERT INTO plan (title, title_detail, start_date, end_date, destination1, destination2, destination3, users_id)
VALUES
  ('京都観光', '京都での観光とグルメを楽しむ旅行', '2024-12-01', '2024-12-05', '京都', '大阪', '奈良', 1),
  ('沖縄ビーチリゾート', '沖縄の美しいビーチでリラックス', '2025-01-15', '2025-01-20', '沖縄', NULL, NULL, 2),
  ('北海道冬の旅', '冬の北海道でスキーと温泉を楽しむ', '2025-02-10', '2025-02-14', '北海道', NULL, NULL, 3);

-- scheduleテーブルにダミーデータを挿入
INSERT INTO schedule (schedule_time, event, memo, url, plan_id, flag)
VALUES
  ('2024-12-01 10:00:00', '金閣寺訪問', '金閣寺の見学', 'https://www.kinkakuji.jp', 1, '観光'),
  ('2024-12-01 13:00:00', '昼食（京都料理）', '京都料理を楽しむ', NULL, 1, '食事'),
  ('2025-01-16 12:00:00', 'ビーチでリラックス', '沖縄ビーチでのんびり過ごす', NULL, 2, '観光'),
  ('2025-02-11 09:00:00', 'スキー', '北海道でスキー', NULL, 3, '観光'),
  ('2025-01-15 10:00:00', '空港到着・チェックイン', '沖縄空港到着後、ホテルに移動', 'https://www.naha-airport.co.jp/', 2, '移動');

-- itemテーブルにダミーデータを挿入
INSERT INTO item (name, quantity, checked, plan_id)
VALUES
  ('カメラ', 1, FALSE, 1),
  ('水着', 2, FALSE, 2),
  ('スキー板', 1, FALSE, 3),
  ('モバイルバッテリー', 1, FALSE, 1);

-- todoテーブルにダミーデータを挿入
INSERT INTO todo (task, completed, plan_id)
VALUES
  ('ホテルの予約', FALSE, 1),
  ('飛行機のチケット確認', TRUE, 1),
  ('レンタカーの手配', FALSE, 2),
  ('スキー用具の確認', FALSE, 3);
