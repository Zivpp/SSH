# SSH

Ziv Wu
 <br/> email : ken62144@gmail.com
 
### 前言 : ### 
  SSH 為 Struts2 + Spring + Hibernate 架設, 使用 Eclipse 與 Tomcat 即可運行。
  <br/>包含 Struts2 Json 資料轉換, 後端第一層由 Spring 控制, DB 連線由 Spring 控制, 
  <br/>Spring 與 Hibernate 的 Annotation 應用, AngularJs 應用 : Menu 自動更新等等,
  <br/>希望此範例對架設 SSH 有需求或興趣者有所幫助。
  + 環境相關
  + AngularJs 應用  : Menu 自動更新 (如有面試榮幸, 可當場 Demo)
#### 環境相關  ####
+ IDE : Eclipse neon 4.6.2
+ DB : MariaDB 10.2 [Scirpt](https://github.com/Zivpp/SSH/blob/master/ssh/src/main/resources/txt/script/scirpt.txt)
+ Server : Tomcat 7.0.75
+ Java : 1.8.0_131
+ Strust2、Spring、Hibernate 請查閱 pom.xml
#### AngularJs 應用  Menu 自動更新 ####
設計理念 : 希望透過修改參數,使 Menu 可快速編輯與擴充,不必修改 code，示意圖如下 :
1. Menu 中新增一個連結
![Aaron Swartz](https://github.com/Zivpp/SSH/blob/master/ssh/src/main/resources/txt/markdown/01.jpg)
2. 在同組織中新增一個連結並且儲存
![Aaron Swartz](https://github.com/Zivpp/SSH/blob/master/ssh/src/main/resources/txt/markdown/02.jpg)
3. Menu 新增出新連結, 不需更動 Menu Code
![Aaron Swartz](https://github.com/Zivpp/SSH/blob/master/ssh/src/main/resources/txt/markdown/03.jpg)