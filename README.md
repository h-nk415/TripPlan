# 旅行計画アプリ　「旅びより」

## 📌 プロジェクト概要

このプロジェクトは、架空の飲食店向けに開発した予約管理システムです。主な目的は、業務の効率化と顧客サービスの向上です。

### 目指すもの
- **24時間予約可能**: ウェブを通じていつでも予約ができる環境を提供し、新規顧客の獲得を促進します。
- **管理の簡素化**: 電話予約とウェブ予約を一元管理することで、スタッフの負担を軽減します。
- **リピート促進**: マイページ機能を通じてクーポンを提供し、顧客の再来店を促します。

### 店舗設定
- **営業時間**: 16時～23時
- **席数**: 50席
- **現在の予約管理方法**: 紙ベースで電話受付のみ

### 課題
1. 電話予約のみで新規顧客を逃すリスクが高い。
2. 紙ベースの管理が非効率で、スタッフに負担がかかる。
3. リピート促進策が不足しており、既存顧客の再来店率が低い。

### 解決策
このシステムを導入することで、上記の課題を解決し、飲食店の運営をスムーズにすることが期待されます。

## 🖼️ 画面遷移

### ユーザー側の画面

<details>
<summary>ログイン</summary>

<br>
<img src="TripPlan/images/login.png" alt="ログイン">
</details>

<details>
<summary>新規登録</summary>
<br>
こちらから会員登録をします。
<br><br>
<img src="/images/login_entry.png" alt="新規登録">
</details>

<details>
<summary>ホーム画面</summary>
<br>
ホーム画面です。旅行プラン一覧を確認できます。
<br><br>
<img src="TripPlan/images/plans_home.png" alt="ホーム画面">
</details>

<details>
<summary>旅行プラン詳細</summary>
<br>
スケジュールを確認できます。
<br><br>
<img src="TripPlan/images/plans_id.png" alt="旅行プラン詳細">
</details>

<details>
<summary>旅行プラン編集</summary>
<br>
<img src="TripPlan/images/plans_edit.png" alt="旅行プラン編集">
</details>

<details>
<summary>スケジュール新規作成</summary>
<br>
<img src="TripPlan/images/schedules_entry.png" alt="スケジュール新規作成">
</details>


<details>
<summary>スケジュール編集</summary>
<br>
イベントを押すとスケジュールが編集できます。
<br><br>
<img src="TripPlan/images/schedules_edit.png" alt="スケジュール編集">
</details>

<details>
<summary>持ち物リスト</summary>
<br>

<br><br>
<img src="TripPlan/images/items_entry.png" alt="持ち物リスト">
</details>

<details>
<summary>TODOリスト</summary>
<br>

<br><br>
<img src="BookSystem/images/todos_entry.png" alt="TODOリスト">
</details>


## 🛠️ 使用技術

- Java
- HTML
- CSS
- JavaScript
- Eclipse
- PostgreSQL
- Gradle

## ✨ こだわりポイント

このプロジェクトでは、以下の点に特にこだわりました：

1. **ユーザーインターフェイス**:
   - シンプルで直感的なデザインを採用し、誰でも簡単に使えるようにしました。

2. **予約管理機能**:
   - 電話での予約も簡単に追加できる管理者機能を実装し、従業員がスムーズに業務を行えるよう配慮しました。

3. **クーポンシステム**:
   - JavaScriptを活用してクーポン獲得時のインタラクションを向上させ、不正利用防止策も講じています。

4. **会計との連携**:
   - バーコード機能を導入し、会計システムとの連携を強化しました。これにより、スムーズな会計処理が可能になります。

## 🚀 今後の計画

- 予約情報の編集機能の追加
- 以前の予約も確認できるようにする
- 管理者がクーポンの種類を追加できるようにする
- ユーザーの退会ページ作成
- 管理者権限のあるアカウントの追加機能
- パスワードを忘れた際の処理
