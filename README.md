# 概要
 Spring Securityを使って簡単なログイン画面をform認証で実装<br>
 認可などはまた次回足していくかも<br>
 ログインボタン押下して登録されている認証情報と一致すればログアウト画面に遷移<br>
<br>
ログイン画面　　　　　　　　　　　　　　　　 　　ログアウト画面<br>
![image](https://github.com/Katsu0424/securityDemo/assets/69413292/b9046444-5c3e-49d1-b993-5d63701d71bc)　![image](https://github.com/Katsu0424/securityDemo/assets/69413292/0dc10d6d-41ca-4722-a43b-d7d129d2f882)
<br><br>

## 起動方法
プロジェクトをクローン後、以下の手順<br>
※MySQLをローカルで実行できること前提<br>

1. MySQL起動
```
net start MySQL80
```
2. Spring bootで起動
3. 画面開く
```
http://localhost:8080/
```

### ログイン情報(初期データ)
| ユーザー名 | パスワード | パスワード有効期限 | アカウント有効期限 |
| ---- | ---- | ---- | ---- |
| user | password | 9999-12-31 | 9999-12-31 |
