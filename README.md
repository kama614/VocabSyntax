CREATE DATABASE vocab_syntax_db
CHARACTER SET utf8mb4
COLLATE utf8mb4_general_ci;

USE vocab_syntax_db;

CREATE TABLE vocab_types(
id int PRIMARY KEY,
name VARCHAR(30) UNIQUE NOT NULL
);

CREATE TABLE vocabs(
id int PRIMARY KEY AUTO_INCREMENT,
word VARCHAR(1000) NOT NULL,
meaning VARCHAR(1000) NOT NULL,
type_id int NOT NULL,
registered_at DATETIME NOT NULL,
updated_at DATETIME NOT NULL
);

INSERT INTO vocab_types VALUES
(1,"演算・変数"),
(2,"分岐処理"),
(3,"反復処理"),
(4,"配列"),
(5,"オブジェクト"),
(6,"クラスの継承"),
(7,"抽象クラスとインターフェース"),
(8,"例外");

INSERT INTO vocabs VALUES
(NULL,"+","加算",1,NOW(),NOW()),
(NULL,"-","減算",1,NOW(),NOW()),
(NULL,"*","乗算",1,NOW(),NOW()),
(NULL,"/","除算",1,NOW(),NOW()),
(NULL,"%","剰余算",1,NOW(),NOW()),
(NULL,"++","インクリメント",1,NOW(),NOW()),
(NULL,"--","デクリメント",1,NOW(),NOW()),
(NULL,"new","オブジェクトの生成",1,NOW(),NOW()),
(NULL,"==","等しい",1,NOW(),NOW()),
(NULL,"!=","等しくない",1,NOW(),NOW());
