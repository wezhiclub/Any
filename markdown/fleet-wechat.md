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
+ JS文件代码介绍：
```javascript
$('#btn-trace').click(() => {
        const license = $('#f_license').val();
        if (!license.length) alert('请输入车牌号');
        $.get(`${window.domain}/api/admin/dispatch`, {pageSize: 1, license: license}, res => {
            if (res.status !== 'success') alert(res.error);
            if (!res.data.length) {
                alert('该车还没有出车记录');
            } else if (res.data[0].recover_time) {
                location.hash = '#/vehicle/' + res.data[0].vehicle_id + '/detail';
            } else {
                $.get(window.domain + '/api/admin/trip', {obdCode: res.data[0].obd_code, pageSize: 1}, _res => {
                    if (!_res.data.length) {
                        alert('该车还没有行车数据');
                    } else {
                        const trip = _res.data[0];
                        location.hash = `#/track?tripId=${trip.id}&obdCode=${trip.obdCode}`;
                    }
                });
            }
        });
    });
```
为html页面中的 “车辆跟踪” 按钮实现点击事件，`const license = $('#f_license').val();`获取输入框中的传入参数，并发送请求。如果响应结果中的`status=”success”` 则跳转的行车轨迹页面，反之则会出现弹出框说明失败原因。
+ 主要功能：可以在车队管理首页用，通过输入车牌号点击车辆跟踪按钮查看改车辆的行车轨迹，点击模块按钮跳转到相应页面。  

## 2.车辆
+ HTML页面位置：webpack/app/template/vehicle.html
+ JS页面位置: webpack/app/controller/vehicle.js
+ HTML代码介绍：
```javascript
<div class="dialog weui_alert" id="dialog_popup-vehicle" style="display: none;">
    <div class="weui_mask"></div>
    <div class="weui_alert_box">
        <div class="weui_dialog_hd"><strong class="weui_dialog_title">确认消息</strong></div>
        <div class="weui_dialog_bd">确定要删除车辆: <b style="color: #000;">{license}</b> ?</div>
        <div class="weui_dialog_ft">
            <span class="weui_btn_dialog default">取消</span>
            <span class="weui_btn_dialog primary btn-confirmed-delete" data-id="{id}">确定</span>
        </div>
    </div>
</div>
```
车辆管理首页先将删除车辆的弹出框隐藏，在在进行删除车辆操作时js文件中通过id=“dialog_popup-vehicle“讲弹出框显现出来，点击确认删按钮开始向后台发送删除车辆请求。
+ 主要功能：在车辆模块中可以查看车队中的所有车辆以及车辆详情，并有添加，删除，修改车辆信息的功能，在车辆详情页面中可以查看车辆的行车记录和改车辆的二维码。

## 3.司机
+ HTML文件位置：webpack/app/template/driver.html
+ JS文件位置：webpack/app/controller/driver.js
+ 主要功能:在司机模块中可以查看车队中的所有司机以及司机详情，并有添加，删除，修改司机信息，查看司机行车记录的功能，在司机详情页面中可以查看司机的出车记录。主要功能：

## 4.地图
+ HTML文件位置: webpack/app/template/map-view.html
+ JS文件位置: webpack/app/controller/map-view.js	
+ 主要功能介绍：在地图中显示改车队中车辆的位置，点击车辆图标可以查看车辆详情，在详情页面可以修改车辆信息，查看出车记录，以及该车辆的二维码。

## 5.警报
+ HTML 文件位置:	webpack/app/template/alarm.html
+ JS 文件位置: webpack/app/controller/alarm.js
+ 主要功能介绍： 在警报页面中可以根据起始时间，截止时间，车牌号，报警类型搜索报警信息。

## 6.出车记录
+ HTML文件位置：webpack/app/template/dispatch.html
+ JS 文件位置: webpack/app/controller/dispatch.js
+ 主要功能：根据车牌或司机手机号查询某一时间段的出车记录，点击出车记录可查看出车详情，在出车详情页面点击查看行车记录按钮可查看行车记录详情以及行车轨迹图。

## 7.设置
+ HTML文件位置：webpack/app/template/settings.html
+ JS 文件位置: webpack/app/controller/settings.js
+ 主要功能：在报警微信消息中可设置向微信发送警报的消息类型，洪水位严重警报，黄色为一般警报。在警报短信中可设置向手机发送警报的消息类型。在推送消息部分可设置消息推送的打开或者关闭。

## 8.账户
+ HTML文件位置：webpack/app/template/account.html
+ JS 文件位置: webpack/app/controller/account.js
+ 主要功能：更新账户资料，修改密码，重置密码，解绑微信

## 9.统计
+ HTML文件位置：webpack/app/template/stat.html
+ JS 文件位置: webpack/app/controller/stat.js
+ 主要功能：可以根据统计类型，统计唯独查询在某一时间段内的统计数据。
