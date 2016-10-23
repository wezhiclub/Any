# 服务平台
车辆数据由数据收发服务器接收以后，就被存储在数据库中，
服务平台利用数据库里的数据，向用户提供服务。
我们只支持2种类型的客户：`PC上的浏览器` 和 `微信`
![](https://camo.githubusercontent.com/4e3d07db8e5680e49df5f383f61328a3e9fee3e4/68747470733a2f2f646f63732e676f6f676c652e636f6d2f64726177696e67732f642f317a52355a4b3152325f6a47707a556475447371457165586c4875703941666271556b33454c4e32726258552f7075623f773d34383026683d333630)

服务平台由nodejs撰写，后台建筑在expressjs框架之上，前端建筑在angularjs之上
+ nodejs http://nodejs.org
+ expressjs http://expressjs.com
+ angularjs https://angularjs.org

服务平台最早有2个对称的组成部分
+ wsite + wservice : 对应于PC端的WEB
+ msite + mservice : 对应于微信端

其中[w|m]site里是纯前端部分，由HTML和javascript前端代码组成;
而[w|m]service是纯后台部分，由javascript的nodejs代码构成;
后台通过以HTTP返回JSON形式的API接口和前端进行数据交换

## typescript
由于javascript语言本身的缺陷，nodejs代码开发效率比较低下，因此引入了typescript。
+ typescript http://www.typescriptlang.org

typescript撰写的这一部分位于 wservice/motor 内

## promise
nodejs只支持回调，因此会导致非常深的回调，代码非常不易理解，于是后来又引入了promise
+ promise https://www.promisejs.org/

这一部分结合了promise的代码也由typescript撰写，位于 srv/promiseSrv 内

## gulp
包含typescript和promise的代码均只涉及PC后台服务，不涉及微信后台服务

因此，后台部分共计包含4种不同时期，不同风格的代码
+ 纯nodejs代码，包括所有的微信后台，和一部分PC后台
+ typescript风格代码，部分PC后台
+ promise式的typescript风格代码，部分PC后台
+ 分拆成单独npm包的代码，位于`packages`内，每个子目录即是一个npm包
![](https://camo.githubusercontent.com/85f12534600bf5cbd8e124b9d4e826e2e5baeeb9/68747470733a2f2f646f63732e676f6f676c652e636f6d2f64726177696e67732f642f31717a66336d474642414839544b51515638717a63664168506b334f676c6f6d474d794b61485774793671382f7075623f773d34383026683d333630)

typescript的代码需要经过一个编译过程，这一过程最早是由grunt来执行，
现在已经全部改由gulp来执行

+ gulp http://gulpjs.com

gulp的脚本位于 gulpfile.js 中，除了用于上述提到的编译typescript的ts代码外，
还有用于编译bootstrape的less代码为css样式表
+ bootstrape http://getbootstrap.com

```SHELL
gulp # 执行编译过程
gulp clean # 清除编译出的文件
```
`wservice/motor`里所有的`*.ts`文件都会被编译为一个单独的`wservice/motor.js`，
另外还会生成一个用于调试目的`motor.js.map`

`srv/promiseSrv`里所有的`*.ts`文件都会被编译为一个单独的`srv/promiseSrv.js`，
另外还会生成一个用于调试目的的`promiseSrv.js.map`

## 微信服务
微信服务由腾讯公司提供，位于api下的代码用于和微信服务器进行通信

我们使用了微信公众服务中的一部分功能，
最主要的是接入一个微信公众号，
监听用户关注，取消关注事件
自定义服务号菜单，
响应用户的菜单点击事件

这部分的详情可以参考微信官方文档

http://mp.weixin.qq.com/wiki/home/index.html

## startup
1. 程序入口点由`package.json`指定为`server.js`
2. 程序读取全局配置信息`config/config.js`,主要为数据库连接，
根据启动的环境变量判明启动的是正式环境还是测试环境，从而决定连接正式数据库还是测试数据库
3. 启动`express`,并配置express模块`config/express.js`
4. 配置API路由表，从而决定某个请求由那个方法响应`config/routes.js`
5. 开始监听请求
6. 注册一个延迟初始化方法`delayedInitializer`，在该方法中，向微信服务器发起公众号菜单定义

## 业务逻辑定义
+ 4S店`[t_4s]`：一个独立的实体，开展销售汽车业务。4S店之间完全隔离，不能共享任何信息。
4S店是资源的拥有者，它拥有车辆，拥有客户，拥有OBD设备，拥有车辆产生的数据，拥有公众号（每个4S店可以有至多1个公众号）。
另外，4S店有一个演化的类型“车队”，车队主要开展运输任务，没有客户，但拥有司机，其它方面和4S店非常类似。ID=1的4S店有特别
含义，它代表平台自身。
+ 车辆`[t_car]`：代表实际中的一台车辆。
+ 设备`[t_car]`：代表安装在车辆中的OBD设备
+ 员工`[t_account]`：4S店的职员
+ 客户`[t_car_user]`：4S店的顾客，可能已经购买了车辆，也可能仅仅只是注册了帐号，而没有车辆。
用户可以是车主，即车辆的拥有者，也可以是共享驾驶人，并不拥有车辆
+ 数据`[t_obd_*]`：车辆的各种数据

## 程序组织
+ wservice or mservice 都相当直接，都是一个个单独的function，参见`route/routes.js`一个个被简单映射成某个URL上的请求
+ srv 映射成标准的RESTful式的URL
   - 有4种操作方法
       - GET 查询
       - PUT 修改
       - POST 增加
       - DELETE 删除
   - 有若干种资源，最主要的有3种
       - 4S 或简写为 S 代表4S店
       - USER 或简写为 U 代表客户
       - CAR 或简写为 C 代表车辆 
   - 这几种可以任意组合，例如
       - GET /srv/4S 代表查询所有的4S店
       - GET /srv/s 代表查询所有的4S店
       - GET /srv/s/6 查询ID=6的4S店
       - DELETE /srv/s/6/u/12 删除ID=6的4S店里ID=12的用户
