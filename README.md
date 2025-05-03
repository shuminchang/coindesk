H2 登入資訊：
- JDBC URL：jdbc:h2:mem:testdb
- 使用者名稱：sa
- 密碼：(留空)

GET /api/coindesk/transformed
轉換 Coindesk 資料，補充本地幣別名稱並格式化日期

幣別管理 API
GET /api/currencies
取得所有幣別資料

GET /api/currencies/{code}
根據幣別代碼取得對應資料

POST /api/currencies
新增一筆幣別資料

PUT /api/currencies/{code}
更新幣別名稱

DELETE /api/currencies/{code}
刪除指定幣別代碼的資料
