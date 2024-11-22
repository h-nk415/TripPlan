-- 依存するテーブルを削除する（外部キー制約を解除）
DROP TABLE IF EXISTS schedule CASCADE;
DROP TABLE IF EXISTS item CASCADE;
DROP TABLE IF EXISTS todo CASCADE;
DROP TABLE IF EXISTS plan CASCADE;
DROP TABLE IF EXISTS users CASCADE;

-- ユーザーテーブルの作成
CREATE TABLE users (
    id SERIAL PRIMARY KEY,                          -- ユーザーID（主キー）
    email VARCHAR(255) UNIQUE NOT NULL,              -- ユーザーのメールアドレス（ユニーク制約、NULL不許可）
    password VARCHAR(255) NOT NULL,                  -- パスワード（NULL不許可）
    displayname VARCHAR(255) NOT NULL                -- ユーザーの表示名（NULL不許可）
);

-- 旅行プランテーブルの作成
CREATE TABLE plan (
    id SERIAL PRIMARY KEY,                          -- 旅行プランのID（主キー）
    title VARCHAR(255) NOT NULL,                     -- 旅行プランのタイトル
    title_detail VARCHAR(255) NOT NULL,              -- 旅行プランの詳細情報
    start_date DATE NOT NULL,                        -- 旅行の開始日
    end_date DATE NOT NULL,                          -- 旅行の終了日
    destination1 VARCHAR(255) NOT NULL,              -- 旅行先1
    destination2 VARCHAR(255),                       -- 旅行先2（オプション）
    destination3 VARCHAR(255),                       -- 旅行先3（オプション）
    icon_image VARCHAR(255),                        -- アイコン画像（オプション）
    users_id INTEGER REFERENCES users(id)           -- ユーザーID（外部キー制約）
);

-- スケジュールテーブルの作成
CREATE TABLE schedule (
    id SERIAL PRIMARY KEY,                          -- スケジュールのID（主キー）
    schedule_time TIMESTAMP NOT NULL,                -- スケジュールの時間
    event VARCHAR(255) NOT NULL,                     -- 予定内容
    memo VARCHAR(255),                               -- メモ（オプション）
    url VARCHAR(255),                                -- 関連URL（オプション）
    flag VARCHAR(255),                               -- 予定の種類（例: 食事、観光など）
    plan_id INTEGER REFERENCES plan(id)             -- 旅行プランID（外部キー制約）
);

-- 持ち物リストテーブルの作成
CREATE TABLE item (
    id SERIAL PRIMARY KEY,                          -- アイテムID（主キー）
    name VARCHAR(255) NOT NULL,                      -- アイテム名
    quantity INTEGER NOT NULL,                       -- アイテムの個数
    checked BOOLEAN NOT NULL,                        -- アイテムが確認されたかどうか
    plan_id INTEGER REFERENCES plan(id)             -- 旅行プランID（外部キー制約）
);

-- Todoリストテーブルの作成
CREATE TABLE todo (
    id SERIAL PRIMARY KEY,                          -- TodoアイテムID（主キー）
    task VARCHAR(255) NOT NULL,                      -- Todoアイテムの内容
    completed BOOLEAN NOT NULL,                      -- Todoアイテムの完了状態
    plan_id INTEGER REFERENCES plan(id)             -- 旅行プランID（外部キー制約）
);
