# 國泰世華面試題目
## 如何執行
已經透過 maven 編譯成 jar 檔並且放在 jar 資料夾。
如果有安裝 maven 的話可以使用 maven 的 install，但記得修改 Dockefile

執行以下指令即可執行:

```docker build -t coindesk .```

```docker run -p 8080:8080 coindesk```

swagger 網址: http://localhost:8080/coindesk/swagger-ui/index.html

## 初始化 sql 的位置:
https://github.com/crate19970523/coindesk/blob/master/src/main/resources/db/schema.sql

## API 的 request
可以使用vscode 或 IDEA 進行測試。
```http request
GET http://localhost:8080/coindesk/getCoinDesk
Accept: application/json
Api-key: demo_for_test

### query all currency
GET http://localhost:8080/coindesk/currency/getInfo
Accept: application/json
Api-key: demo_for_test

### query urd currency
GET http://localhost:8080/coindesk/currency/getInfo/currencyCode/USD
Accept: application/json
Api-key: demo_for_test

### insert new currency
POST http://localhost:8080/coindesk/currency/insert
Accept: application/json
Api-key: demo_for_test
Content-Type: application/json
Accept: */*

{
  "currencyCode": "test",
  "twCurrencyName": "測試",
  "enCurrencyName": "test"
}

### update test currency
POST http://localhost:8080/coindesk/currency/update
Accept: application/json
Api-key: demo_for_test
Content-Type: application/json
Accept: application/json

{
  "currencyCode": "USD",
  "twCurrencyName": "測試2",
  "enCurrencyName": "test321"
}

### delete currency
DELETE http://localhost:8080/coindesk/currency/delete/currencyCode/test
Accept: application/json
Api-key: demo_for_test
```

## unit test 注意
所有的 unit test 都是 SpringBootTest，如果有需要可以在將來新增使用 Mockito 做更完整的單元測試。

## 注意事項:
* H2 DB 使用嵌入式會保留資料的模式。
* 有在 yml 檔設定初始化 DB 的功能，將會在第一次執行時建立 table 和新增資料
* 第二次以後的執行將不會刪除 DB 的資料
* 有加入 swagger，啟動服務之後使用瀏覽器導向: 127.0.0.1:8080/coindesk/swagger-ui.html
* 會寫 log，log 文建會放在專案資料夾中的 log 資料夾，檔名為 coindesk.log。
* 基於安全考量有新增 interceptor ，使用者必須在 header 輸入 api-key ，且參數必須存在於 DB 才可以使用 API，但目前參數是明碼存在 DB 中，或許該考慮是否使用 MD5 等雜湊去儲存。
* api-key 同時也會用於紀錄是誰更新了資料。

## 架構說明:
架構主要有 interceptor controller service utils dao handler ，且每一層至少都會有一個 Dto 用於降低耦合。
### interceptor
原先沒有考慮加入，但因為這個服務對幣別的 CRUD，如果每個人都可以使用的話很不切實際，因此新增 interceptor 用於對 request 控管。

### controller
主要負責處理 request 轉成 dto 給 service，再將 service 的 dto 轉成 response 傳出去。
controller 的 try/catch 也很重要，所有的錯誤 log 會由 controller 這邊輸出，避免同一個錯誤到處都是 error 堆疊。

### service
這邊的 service 都有介面，目的是如果將來會需要一個服務處理多個版本 API 的話可以藉由 getBean 等方式讓 controller 可以透過傳入的 api-version 決定使用哪個 Service 實作，同時因為 controller 和 service 中間有 Dto 做溝通，因此也不用擔心新 function 有舊 function 沒有的參數。
商業邏輯都是由這邊處理，不管是呼叫第三方或 Dao 操作都會在這邊完成。

### utils
目前只有放一個 httpClient 而已，會額外寫是如果將來 request 和 response 需要寫到額外的 log 檔案時可以統一修改，另外 Java11 之後有提供 JDK 自己的 httpClient 升級的時候可以更改。

### dao
所有持久層的東西都是放在這邊，過去沒有使用過 Spring jpa 框架(過去只有使用過 Spring jdbc 和 mybatis)，如果有使用錯誤歡迎在 github 的 issue 寫下留言。
dao 的 update 除了會以 id 作為 where 判斷，同時會將 version 當作判斷依據之一，主要是為了避免線程競爭的問題。

### handler
這是刻意分另外一層的，由於第三方 API 的參數 URL 等是不可控的，實際上也有遇過幾次 API 更新導致交易出現問題的情況，從 cxf 轉 rest API 也曾經聽過，因此將呼叫第三方的邏輯分層並且透過 Dto 傳遞可以大幅降低修改程式時因為耦合導致邏輯出現錯誤，同時 unit test 會更好透過 mockito 去專注在 service 的邏輯。
