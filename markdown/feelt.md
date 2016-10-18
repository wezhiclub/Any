# 小型车队页面介绍
## 1．首页	
+ HTML文件位置：packages/fleet/html/index.html  
+ JS 文件位置：packages/fleet/html/scripts/mainView.js
+ index.html主要代码介绍：

```html
            <div ng-repeat=" fun in funList">
                <div ng-class="fun.funClass" >
                    <div class="col-xs-10 g-h-120 g-padding-lr0 g-margin-l27 {{fun.funLine}} g-line-a2 text-center g-left-nav-style{{fun.funFlag}}">
                        <a ng-href="{{fun.path}}" ng-click="changeTemplate($index,false);" class="g-padding-t45 {{fun.funBg}}"><h2 class="g-font-height-55" ng-bind="fun.funName"></h2></a>
                    </div>
                </div>
            </div>
```
通过ng-repeat 标签 对mainView.js中 $scope.funList 的元素做循环输出，并通过ng-href 和ng-click标签实现菜单栏(首页，车辆管理，司机管理，行程管理)的点击以及各个页面之间的跳转。	
+ mainView.js主要代码介绍:

```javascript
requirejs(['./fleet','jquery', './icBaiduMap','./icBaiduMapTrack','./myCurrentTime','./fleetService','../'+fleetId+'/track-replay/script/icBaiduTrack',
    './vehicleControl','./vRecordControl','./vRecordDControl','./vCareControl','./tripControl','./staffControl',
    './sRecordControl','./settingControl','./statisticsControl','./alarmControl','./outsideReactControl',
    'bootstrap','datetimepicker','highchart','exporting','sha1', '../fence/icFence'], function(module, $){
    var pathname = 'fleet';
    module
        .config(function($routeProvider,$locationProvider){
            $routeProvider.
                when('/fleet/:fleetId/index', {
                    controller: 'indexCtrl',
                    templateUrl: '/'+pathname+'/views/index.html'
                })
                .when('/fleet/:fleetId/setting', {
                    controller: 'settingCtrl',
                    templateUrl: '/'+pathname+'/views/setting.html'
                })
                .when('/fleet/:fleetId/statistics', {
                    controller: 'statisticsCtrl',
                    templateUrl: '/'+pathname+'/views/statistics/index.html'
                })
                .when('/fleet/:fleetId/alarm', {
                    controller: 'alarmCtrl',
                    templateUrl: '/'+pathname+'/views/alarm/index.html'
                })

```
 该段代码用于配置浏览器中请求路径对应的页面，以及相应的js文件，通过ng-view标签实现模块的展示。	
 
## 2．车辆管理
+ HTML文件位置: packages/fleet/html/views/vehicle/index.html		
+ JS文件位置:packages/fleet/html/scripts/vehicleControl.js
+ index.html主要代码介绍:
```html
        <!--展示类型-->
        <div class="g-w-30 g-float-l" ng-click="changeView('list');"><img src="/{{pathname}}/styles/images/ico-j{{templateId == 'listView'?2:1}}.png" width="30" height="30"></div>
        <div class="g-w-30 g-float-l g-padding-l15" ng-click="changeView('map');"><img src="/{{pathname}}/styles/images/ico-k{{templateId == 'mapView'?2:1}}.png" width="30" height="30"></div>
        <!--展示类型 end-->

```
 	
车辆信息的展现类型有列表，地图两种类型，在车辆管理页面可以通过点击图标进行切换，通过ng-click标签调用js文件中的 function changeView(id) 方法实现。
+ vehicleControl.js 主要代码介绍:
```javascripts
        var apiVehicleId = $resource('/'+pathname+'/api/fleet/:fleetId/vehicle/:vehicleId');
        var apiPostion = $resource('/'+pathname+'/api/fleet/:fleetId/pos');
        var apiUpdateVehicleId = $resource('/'+pathname+'/api/fleet/:fleetId/vehicle/:vehicleId',null,{'update': { method:'PUT' }});
        var apiDispatchTeam = $resource('/'+pathname+'/api/fleet/:fleetId/dispatchTeam'); //出车
        var apiDispatchTeamId = $resource('/'+pathname+'/api/fleet/:fleetId/dispatchTeam/:dispatchTeamId',null,{'update': { method:'PUT' }}); //收车
        var analysisApi = $resource('/'+pathname+'/api/fleet/:fleetId/vehicleAnalysis/:vehicleId');
        var detectApi = $resource('/'+pathname+'/api/fleet/:fleetId/vehicle/:vehicleId/detect');


        var apiAdvancedObd = $resource('/'+pathname+'/api/fleet/:fleetId/vehicle/:vehicleId/setting/:obd_code'); //obd级别 获取，删除
        var apiAdvancedUpdate = $resource('/'+pathname+'/api/fleet/:fleetId/vehicle/:vehicleId/setting/:obd_code',null,{'update': { method:'PUT' }}); //obd级别 修改
        var apiAdvancedAdd = $resource('/'+pathname+'/api/fleet/:fleetId/vehicle/:vehicleId/setting');   //添加高级设置
        var apiMileage = $resource('/'+pathname+'/api/fleet/:fleetId/vehicle/:vehicleId/mileage'); //当前里程
        var apiTodayMileage = $resource('/'+pathname+'/api/fleet/:fleetId/vehicle/:vehicleId/todayMileage'); //日里程统计
        var apiMonthMileage = $resource('/'+pathname+'/api/fleet/:fleetId/vehicle/:vehicleId/monthMileage'); //月里程统计
        var apiYearMileage = $resource('/'+pathname+'/api/fleet/:fleetId/vehicle/:vehicleId/yearMileage');  //年里程统计

```
 
 定义API的请求路径以及请求方式，在车辆管理中通过发送这些请求并根据返回的数据实现车辆管理中各个功能。
## 3.司机管理		
+ HTML文件位置：packages/fleet/html/views/staff/index.html  
+ JS 文件位置：packages/fleet/html/scripts/staffControl.js
+ 司机管理模块主要功能：1，在搜索栏输入司机姓名，点击查询按钮可以查询到司机的行车记录。2，在操作栏点击“收车”按钮可以修改车辆状态。3，点击“分析” 按钮 可以查询出在一个时段内，改车辆的启动次数，平均油耗，日里程，最高车速，急加减速，平均车速等相关信息。4，修改司机基本信息，删除司机的信息。

## 4.行程管理
+ HTML文件位置：packages/fleet/html/views/trip/index.html
+ JS 文件位置：packages/fleet/html/trip/tripControl.js
+ 行程管理模块主要功能：1 ,在搜索栏按照司机姓名、车牌号、开始日期、结束日期等搜索条件查找行程记录。2，点击查看栏目的下按钮可以查看行程详情。


