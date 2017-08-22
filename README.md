# SSH

Ziv Wu
 <br/> email : ken62144@gmail.com
 
### 前言 : ### 
  SSH 為 Struts2 + Spring + Hibernate 架設, 使用 Eclipse 與 Tomcat 即可運行。
  <br/>包含 Struts2 Json 資料轉換, 後端第一層由 Spring 控制, DB 連線由 Spring 控制, 
  <br/>Spring 與 Hibernate 的 Annotation 應用, AngularJs 應用 : Menu 自動更新等等,
  <br/>希望此範例對架設 SSH 有需求或興趣者有所幫助。
  + 環境相關
  + AngularJs 應用  : Menu 自動更新
#### 環境相關  ####
+ IDE : Eclipse neon 4.6.2
+ DB : MariaDB 10.2 [Scirpt](https://github.com/Zivpp/SSH/blob/master/ssh/src/main/resources/txt/script/scirpt.txt)
+ Server : Tomcat 7.0.75
+ Java : 1.8.0_131
+ Strust2、Spring、Hibernate 請查閱 pom.xml
#### AngularJs 應用  Menu 自動更新 ####
設計目的 : 不必修改 Menu 頁面的 code, 使 Menu 可快速編輯與擴充
+ Menu 應用 AngularJs 產生, 透過參數修改自動改變
+ [HTML](https://github.com/Zivpp/SSH/blob/master/ssh/src/main/webapp/pages/hall.html) 段落於註解 SIDE BAR 區塊
![Aaron 1](https://github.com/Zivpp/SSH/blob/master/ssh/src/main/resources/txt/markdown/004.jpg)
![Aaron 2](https://github.com/Zivpp/SSH/blob/master/ssh/src/main/resources/txt/markdown/002.jpg)


