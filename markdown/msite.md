# 平台微信页面介绍

# 1.首页
![img_menu](https://cloud.githubusercontent.com/assets/13936823/19382922/d42f3522-9234-11e6-8140-6f5bdc297f2f.PNG)

#2. 我的车
## 点击首页我的车按钮，进入如下页面：
![img_menu](https://github.com/shuzihao/Shopping/blob/master/image/photo.png)
## 向下滑动会出现如下页面：
![img_menu](https://github.com/shuzihao/Shopping/blob/master/image/photo1.png)
## 用户可以根据不同的需要点击相应的按钮查看信息。
## html主要代码如下(/msite/mviews/summary_new.html)：
```html
<div id="wrap" class="h-b100 ng-cloak" ng-show="showActions">
  <div class="container-a h-b55"  ng-include="templateUrl"  ng-class="classA" ></div><!--up end-->
  <div class="container-a h-b45"  ng-if="showActions && css"  ng-class="classB"   ng-swipe-left="swipeUp();" >
    <div id="divbox01" class="line-f w-b100 h-b1 float-l"></div>
    <div class="menu-title w-b50 h-b33 float-l"><a href="javascript:void(0);" ng-click="buttonAct(2,false);" id="divbox02" class="style-a bgc-a h-b100"><div><span id="spanbox_a02" class="font-c14 font-h30 font-b font-s14">行车分析</span><br /><span id="spanbox_b02" class="font-c14 font-h30" ng-bind="AvgTotalFuel"></span></div></a></div><!--menu-title end-->
    <div class="menu-title w-b50 h-b33 float-l"><a href="javascript:void(0);" ng-click="buttonAct(3,false);" id="divbox03" class="style-b bgc-b h-b100"><div><span id="spanbox_a03" class="font-c13 font-h30 font-b font-s14">行车记录</span><br /><span id="spanbox_b03" class="font-c13 font-h30" ng-bind="CurrentMileage"></span></div></a></div><!--menu-title end-->
    <div class="menu-title w-b50 h-b33 float-l mar-t2" ><a href="javascript:void(0);" ng-click="buttonAct(4,false);" id="divbox04" class="style-a bgc-b h-b100"><div><span id="spanbox_a04" class="font-c13 font-h30 font-b font-s14">远程检测</span><br /><span id="spanbox_b04" class="font-c13 font-h30" ng-bind="currentDetection.result"></span></div></a></div><!--menu-title end-->
    <div class="menu-title w-b50 h-b33 float-l mar-t2"><a href="javascript:void(0);" ng-click="buttonAct(5,false);" id="divbox05" class="style-b bgc-b h-b100"><div><span id="spanbox_a05" class="font-c13 font-h30 font-b font-s14">保养建议</span><br /><span id="spanbox_b05" class="font-c13 font-h30" ng-bind="MileageDelta"></span></div></a></div><!--menu-title end-->
    <div class="menu-title w-b50 h-b33 float-l mar-t2"><a href="javascript:void(0);" ng-click="buttonAct(6,false);" id="divbox06" class="style-a bgc-b h-b100"><div><span id="spanbox_a06" class="font-c13 font-h30 font-b font-s14">最新资讯</span><br /><span id="spanbox_b06" class="font-c13 font-h30" ng-bind="Title"></span></div></a></div><!--menu-title end-->
    <div class="menu-title w-b50 h-b33 float-l mar-t2"><a href="javascript:void(0);" ng-click="buttonAct(7,false);" id="divbox07" class="style-b bgc-b h-b100"><div><span id="spanbox_a07" class="font-c13 font-h30 font-b font-s14">轨迹记录</span><br /><span id="spanbox_b07" class="font-c13 font-h30" ng-bind="Dist"></span></div></a></div><!--menu-title end-->
    <div class="clear"></div>
</div><!--down end-->
<!--end-->
</div>
<div id="wrap" class="ng-cloak">
  <div ng-if="showSecond" ng-class="classC"  ng-swipe-right="swipeDown();" ng-swipe-left="pageToBottom();">
    <div class="container-a">
      <a  ng-href="{{stateUrl.replace('menu','infoConfig')}}">
        <div class="content-pagemenu icon-pagemenu14">我的信息</div>
        <!--content-pagemenu end--></a>
      <a ng-href="{{stateUrl.replace('menu','driveRecord')}}">
        <div class="content-pagemenu icon-pagemenu01">行车记录</div>
        <!--content-pagemenu end--></a>
      <a ng-href="{{stateUrl.replace('menu','myDrive')}}">
        <div class="content-pagemenu icon-pagemenu02">行车分析</div>
        <!--content-pagemenu end--></a>
      <a ng-href="{{stateUrl.replace('menu','page_xcsc')}}">
        <div class="content-pagemenu icon-pagemenu03">行车手册</div>
        <!--content-pagemenu end--></a>
      <a ng-href="{{stateUrl.replace('menu','remoteDetection')}}">
        <div class="content-pagemenu icon-pagemenu04">远程检测</div>
        <!--content-pagemenu end--></a>
      <a ng-href="{{stateUrl.replace('menu','travelReport')}}">
        <div class="content-pagemenu icon-pagemenu05">用车报告</div>
        <!--content-pagemenu end--></a>
            <a ng-href="{{stateUrl.replace('menu','booking')}}">
        <div class="content-pagemenu icon-pagemenu07" ng-show="biz_mode != 3">服务预约</div>
        <!--content-pagemenu end--></a>
      <a ng-href="{{stateUrl.replace('menu','my4sInfo')}}">
        <div class="content-pagemenu icon-pagemenu09">资讯活动</div>
        <!--content-pagemenu end--></a>
      <a ng-href="{{stateUrl.replace('menu','maintain')}}">
        <div class="content-pagemenu icon-pagemenu25" ng-show="biz_mode == 3">添加保养</div>
        <!--content-pagemenu end--></a>
      <a ng-href="{{stateUrl.replace('menu','myCare')}}">
        <div class="content-pagemenu icon-pagemenu10">保养记录</div>
        <!--content-pagemenu end--></a>
      <a ng-href="{{stateUrl.replace('menu','propose')}}">
        <div class="content-pagemenu icon-pagemenu11">保养建议</div>
        <!--content-pagemenu end--></a>
      <a ng-href="{{stateUrl.replace('menu','myActivity')}}">
        <div class="content-pagemenu icon-pagemenu12">我的活动</div>
        <!--content-pagemenu end--></a>
      <a  ng-href="{{stateUrl.replace('menu','myBooking')}}">
        <div class="content-pagemenu icon-pagemenu13" ng-show="biz_mode != 3">我的预约</div>
        <!--content-pagemenu end--></a>
      <a ng-href="{{stateUrl.replace('menu','carAlarm')}}">
        <div class="content-pagemenu icon-pagemenu06">车辆报警</div>
        <!--content-pagemenu end-->
      </a>
      <a  ng-href="{{stateUrl.replace('menu','indicatorLamp')}}">
        <div class="content-pagemenu icon-pagemenu17">指示灯</div>
        <!--content-pagemenu end--></a>
      <a  ng-href="{{stateUrl.replace('menu','peccancy')}}">
        <div class="content-pagemenu icon-pagemenu18">违章查询</div>
        <!--content-pagemenu end--></a>
      <a  ng-href="{{stateUrl.replace('menu','gasStation')}}">
        <div class="content-pagemenu icon-pagemenu19">加油站</div>
        <!--content-pagemenu end--></a>
      <a  ng-href="{{stateUrl.replace('menu','../pos-tracking/tracking')}}">
        <div class="content-pagemenu icon-pagemenu21">轨迹记录</div>
        <!--content-pagemenu end--></a>
        ...
```
##js主要代码如下(/msite/mscripts/wxjs/informationnew.js)：
```javascript
//信息一览微信分享
        var api = new IcWechatAPI();
        var xurl = window.location.href.split('#')[0];
        api.config(state.wx_app_id, xurl, ['onMenuShareTimeline','onMenuShareAppMessage'], function(wx){
            var base = window.location.href.match(/\w+:\/\/[^\/]+/);
            var pic = $("meta[name=wx-share-pic]").attr("content");
            //api.share($("title").text(), $scope.lastUrl.replace('menu','summary_new'), base + pic, "爱车信息的世界之窗");

            //分享到朋友圈
            wx.onMenuShareTimeline({
                title: $("title").text(), // 分享标题
                link:  $scope.lastUrl.toString().replace("menu","summary_new"), // 分享链接
                imgUrl: base + pic, // 分享图标
                success: function () {
                    // 用户确认分享后执行的回调函数
                },
                cancel: function () {
                    // 用户取消分享后执行的回调函数
                }
            });
            //分享给朋友
                wx.onMenuShareAppMessage({
                    title: $("title").text(), // 分享标题
                    desc: '爱车信息的世界之窗', // 分享描述
                    link: $scope.lastUrl.toString().replace("menu","summary_new"), // 分享链接
                    imgUrl: base + pic, // 分享图标
                    type: '', // 分享类型,music、video或link，不填默认为link
                    dataUrl: '', // 如果type是music或video，则要提供数据链接，默认为空
                    success: function () {
                        // 用户确认分享后执行的回调函数
                        console.log("已成功分享给好友!");
                    },
                    cancel: function () {
                        // 用户取消分享后执行的回调函数
                        console.log("已取消分享给好友!");
                    }
                });
            $scope.wx = wx;
        });

//向上滑动
        $scope.swipeUp= function(){
            $scope.bigTitle = "更多功能";
            $scope.showActions =false;
            $scope.templateUrl = "";
            $timeout(function () {
                //if($scope.readonlymode) return;
                $scope.showSecond = true;
                $scope.loading = false;
                $scope.classA="slide-down";
                $scope.lastUrl = new Url($scope.stateUrlTemp);
                state.page = "pagetwo";
                $scope.lastUrl.query.state = decodeURIComponent(JSON.stringify(state));
                wxShare();
            }, 100);

        } ;



        //向下滑动
        $scope.swipeDown = function(){
            $scope.bigTitle = "信息一览";
            $scope.showSecond = false;
            $timeout(function() {
                $scope.showActions = true;
                $scope.loading = false;
                $scope.templateUrl = $scope.tempUrl;
                $timeout(function() {
                    if(state.page && state.pageid != null){
                        $scope.buttonAct(state.pageid,true);
                    }
                    else if($scope.tempId){
                        $scope.buttonAct($scope.tempId,false);
                    }else{
                         $scope.lastUrl = new Url($scope.stateUrlTemp);
                         state.page = "pageone";
                         $scope.lastUrl.query.state = decodeURIComponent(JSON.stringify(state));
                         wxShare();
                    }
                }, 100);
            }, 100);

        };
        if(state.page && state.page == "pagetwo"){
            $scope.swipeUp();
        }else{
            $scope.swipeDown();
        }
```
# 3. 发现
##点击首页发现按钮会出现向下滑动那个页面。
  点击我的信息跳转到如下图页面(/msite/mviews/infoConfig.html):
  ![img_text](https://github.com/shuzihao/Shopping/blob/master/image/photo2.png)
 
##主要html代码：
```html
<script src="../mscripts/jquery-1.7.1.min.js"></script>
    <script type="text/ng-template" id="infoConfig">
        <div ng-include="'mpartials/infoConfig.html'"></div>
    </script>
</head>
<body  id="body" class="ng-cloak">
<div id="wrap" ng-cloak="" >
    <ic-header title="'我的信息'" state="stateUrl" hide-btn="modeReadOnly"></ic-header>
  <div id="header">
      <!--<div class="banner image-infoconfig"></div>-->
      <img src="../mstyles/images/banner/infoConfig.png" width="100%" />
  </div><!--header end-->
  <div id="navbox" class="line-a" ng-show="dataFlag">
    <ul class="nav" ng-show="carownerFlag">
      <li class="w-b33"><a id="menu_icon1" class="font-h30 now" href="#" >车主信息</a></li>
      <li class="w-b34"><a id="menu_icon2" class="font-h30" href="#">车辆信息</a></li>
      <li class="w-b33"><a id="menu_icon3" class="font-h30" href="#">密码修改</a></li>
    </ul><!--ul nav end-->
      <ul class="nav" ng-show="!carownerFlag" >
          <li class="w-b50"><a id="menu_icon1" class="font-h30 now" href="#" >车主信息</a></li>
          <li class="w-b50"><a id="menu_icon2" class="font-h30" href="#">车辆信息</a></li>
      </ul><!--ul nav end-->
    <div class="clear"></div>
  </div><!--navbox end-->
    <div ng-include="'infoConfig'"></div>
</div><!--wrap end-->

<script data-main="../mscripts/wxjs/infoConfig.js" src="../mscripts/require.min.js"></script>

</body>
```
 上述代码使用ng-include标签动态加载子页面，如果用户未注册则使用ng-show标签控制展示不包含密码修改的ul列表。在这里用户可以创建帐号并绑定OBD设备与车辆信息。

##对应js主要代码(/msite/mscripts/wxjs/infoConfig.js)：
```javascript 
if (url.query.code) {
            $scope.modeReadOnly = true;
            // 已认证微信公众号,返回code进行微信身份验证,如非本人,设置只读模式
            $http.post('/mservice/getOpenid2', {code: url.query.code, app_id: state.wx_app_id})
                .then(function (res) {
                    if (res.data.openid == state.wx_uoid) {
                        $scope.modeReadOnly = false;
                    }
                });
        }
        else {
            $scope.modeReadOnly = false;
        }
        
        //修改密码
        $scope.pswInfoSubmit = function () {...}
        
        //车辆信息提交
        $scope.carInfoSubmit = function () {...}
```

#车型介绍
##html主要代码(/msite/mviews/carSeries.html)：

```html
<script type="text/ng-template" id="carSeries_list">
    <div ng-include="'mpartials/carSeries_list.html'"></div>   <!--展示车型列表-->
  </script>
  <script type="text/ng-template" id="carType">
    <div ng-include="'mpartials/carType.html'"></div>  <!--展示车型参考价格与油耗-->
  </script>
  <script type="text/ng-template" id="carType_detail">
    <div ng-include="'mpartials/carTypeDetail.html'"></div>  <!--展示车型参数信息-->
  </script>
  <script type="text/ng-template" id="carType_params">
    <div ng-include="'mpartials/carTypeParams.html'"></div>
  </script>

</head>
<body>
<div id="wrap" class="ng-cloak">
  <ic-header  title="'车型介绍'" state="stateUrl" hide-btn="modeReadOnly" back-flag="backFlag" back-method="backMethod" back-number="backNumber"></ic-header>
  <div id="header"><img ng-src="../mstyles/images/banner/car-type.png" width="100%"/></div><!--header end-->
  <div ng-include="templateUrl"></div>
</div><!--wrap end-->
<script data-main="../mscripts/wxjs/carSeries.js" src="../mscripts/require.min.js"></script>
</body>
```

##js主要代码(/msite/mscripts/wxjs/carSeries.js)：
```javascript
    function getAllSeries (){...} //获取该4S店的所有车系列
    
    $scope.backMethod.changeView = function(id,seriesId,typeId){...}  //切换视图
    
    function changeTml(seriesId){...}   //车型介绍
    
    function changeTml_1(seriesId,typeId){...} //车辆详情
```

#天气预报
##html主要代码(/msite/mviews/weather)：
```html
<script type="text/ng-template" id="weather">
        <div ng-include="'mpartials/weather.html'"></div>
    </script>
</head>
<body ng-controller="weatherCtrl" class="ng-cloak">
<div id="wrap">
    <ic-header title="'天气预报'" state="stateUrl" hide-btn="readonlymode"></ic-header>
    <div ng-include="'weather'"></div>
</div><!--wrap end-->
<script data-main="../mscripts/wxjs/weather.js" src="../mscripts/require.min.js"></script>
```
##js主要代码(/msite/mscripts/wxjs/weather.js):
```javascript
        //调用后台API查询天气
        $scope.init = function(){
        function myFun(result){...}
        ...
        }
        
        //百度地图API获取当前所在城市
        var myCity = new BMap.LocalCity();
        myCity.get(myFun);
        
        //展示天气对应的图标
        $scope.changePic = function(pic){
        if(pic == "qing") $scope.pic = "sun";
        else if(pic.indexOf("xue") !== -1) $scope.pic="snow";
        else if(pic.indexOf("yu") !== -1)$scope.pic = "rain";
        else if(pic == "baoyu")$scope.pic = "rainstorm";
        else if(pic == "mai" || pic == "yin" )$scope.pic = "cloud";
        else if(pic.indexOf("yun") !== -1) $scope.pic="cloudy";
        else if(pic.indexOf("wu") !== -1) $scope.pic="fog";
    }
```
