# User Management Project

Java + MySQL を使った、ユーザー管理Webシステムのサンプルです。

## 機能一覧

- ユーザー一覧表示
- ユーザー登録
- ユーザー情報の編集・削除
- 都道府県・市区町村の動的選択（JavaScript使用）
- JDBC によるDB接続

## ディレクトリ構成
```
user-management-prject/
└src/
|    └controller/
|    |   └InfoSelect.java
|    |   └Form.java
|    |   └Select.java
|    |   └Insert.java
|    |   └Update.java
|    |   └Delete.java
|    └dao/
|    |   └UsersDAO.java
|    |   └PrefecturesDAO.java
|    |   └CitiesDAO.java
|    └entity/
|    |   └Users.java
|    |   └Prefectures.java
|    |   └Cities.java
|    └config/
|        └DBConectTemplate.java(GitHub公開用テンプレート)
└webapp/
|    └WEB-INF
|    |   └lib
|    |       └mysql-connector-j-9.2.0.jar
|    |       └jakarta.servlet.jsp.jstl-3.0.0.jar
|    |       └jakarta.servlet.jsp.jstl-api-3.0.0.jar
|    └view/
|    |   └info_table.jsp
|    |   └form.jsp
|    |   └update.jsp
|    |   └delete.jsp
|    |   └success_delete.jsp
|    |   └success_update.jsp
|    └js/
|        └address_select.js
└README.md
└CHANGELOG.md
```
## データベース
 テーブル：users, prefectures, cities

## 注意事項

- `config/DBConnectTemplate.java` は公開用のテンプレートであり、実際の接続情報（ユーザー名・パスワードなど）は含まれていません。
- 実行環境に合わせて `url`, `user`, `password` を書き換えてください。

## 動作環境

- Java 17
- Tomcat 10
- MySQL 8.x
- Eclipse 2023-12
- XAMPP 3.3.0

## 使用ライブラリ

- mysql-connector-j-9.2.0.jar

## 使い方

1. このリポジトリをクローン
2. Eclipse でプロジェクトをインポート
3. `DBConnectTemplate.java` を実際の接続情報に変更
4. Tomcat にデプロイして実行

## 今後の予定
JUnitなどによる単体テストを予定。

## ライセンス

このプロジェクトは学習用で作成されたものです。自由に使用・改変いただけます。
