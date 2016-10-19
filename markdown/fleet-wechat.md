# 小型车队微信介绍
## 1.首页
+	HTML 文件位置:  webpack/app/template/home.html
+	JS文件位置:  webpack/app/controller/home.js
+ HTML文件主要代码介绍：
```javascript
<table class="home-nav">
    <tr>
        <td><a href="#/vehicle" class="vehicle"><i></i><br>车辆</a></td>
        <td><a href="#/driver" class="driver"><i></i><br>司机</a></td>
        <td><a href="#/mapview" class="map"><i></i><br>地图</a></td>
    </tr>
    <tr>
        <td><a href="#/alarm" class="alarm"><i></i><br>警报</a></td>
        <td><a href="#/dispatch" class="record"><i></i><br>出车记录</a></td>
        <td><a href="#/settings" class="settings"><i></i><br>设置</a></td>
    </tr>
    <tr>
        <td><a href="#/account" class="account"><i></i><br>帐户</a></td>
        <td><a href="#/stat" class="stat"><i></i><br>统计</a></td>
        <td><a href class="help"><i></i><br>帮助</a></td>
    </tr>
</table>
```
 
为每个模块设定访问路径，在 webpack/app/main.js 文件中通过路由配置了各个访问路径所对应的HTML和JS文件，实现页面的切换。代码如下所示 ： 	
```javascript
router.push({
    url      : '/',
    className: 'Home',
    render   : require('./template/home.html'),
    bind     : require('./controller/home'),
}).push({
    url      : '/login',
    className: 'Login',
    render   : require('./template/login.html'),
    bind     : require('./controller/login'),
}).push({
    url      : '/vehicle',
    className: 'Vehicle',
    render   : require('./template/vehicle.html'),
    bind     : require('./controller/vehicle'),
}).push({
    url      : '/vehicle/:id(\\d+)?/edit',
    className: 'VehicleEdit',
    render   : require('./template/vehicle-edit.html'),
    bind     : require('./controller/vehicle-edit'),
}).push({
    url      : '/vehicle/:id(\\d+)/detail',
    className: 'VehicleDetail',
    render   : require('./template/vehicle-detail.html'),
    bind     : require('./controller/vehicle-detail'),
```
 
