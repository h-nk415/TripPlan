-- 依存するテーブルを削除する（外部キー制約を解除）
DROP TABLE IF EXISTS schedule CASCADE;
DROP TABLE IF EXISTS item CASCADE;
DROP TABLE IF EXISTS todo CASCADE;
DROP TABLE IF EXISTS plan CASCADE;

-- planテーブル
CREATE TABLE plan (
    id SERIAL PRIMARY KEY,                       -- 旅行プランのID（主キー）
    title VARCHAR(255) NOT NULL,                  -- 旅行プランのタイトル
    title_detail VARCHAR(255) NOT NULL,           -- 旅行プランの詳細情報
    start_date DATE NOT NULL,                     -- 旅行の開始日
    end_date DATE NOT NULL,                       -- 旅行の終了日
    destination1 VARCHAR(255) NOT NULL,           -- 旅行先の1つ目の行き先
    destination2 VARCHAR(255),                    -- 旅行先の2つ目の行き先
    destination3 VARCHAR(255),                     -- 旅行先の3つ目の行き先
    icon_image VARCHAR(255)                       -- アイコン画像のファイル名（追加）
);

-- scheduleテーブル
CREATE TABLE schedule (
    id SERIAL PRIMARY KEY,                       -- スケジュールのID（主キー）
    schedule_time TIMESTAMP NOT NULL,             -- 時間
    event VARCHAR(255) NOT NULL,                  -- 予定内容
    memo VARCHAR(255),                            -- メモ
    url VARCHAR(255),                             -- 関連URL
    plan_id INTEGER REFERENCES plan(id)           -- 関連する旅行プラン（外部キー）
);

-- itemテーブル（持ち物リスト）
CREATE TABLE item (
    id SERIAL PRIMARY KEY,                        -- 持ち物アイテムのID（主キー）
    name VARCHAR(255) NOT NULL,                    -- アイテム名
    quantity INTEGER NOT NULL,                     -- アイテムの個数
    checked BOOLEAN NOT NULL,                      -- アイテムが確認されたかどうか
    plan_id INTEGER REFERENCES plan(id)           -- 関連する旅行プラン（外部キー）
);

-- todoテーブル（Todoリスト）
CREATE TABLE todo (
    id SERIAL PRIMARY KEY,                        -- TodoアイテムのID（主キー）
    task VARCHAR(255) NOT NULL,                    -- Todoアイテムの内容
    completed BOOLEAN NOT NULL,                    -- Todoアイテムが完了したかどうか
    plan_id INTEGER REFERENCES plan(id)          -- 外部キー制約
);
