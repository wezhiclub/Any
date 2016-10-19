# 一、平台PC网页网站
平台PC网页网站用于后台管理员与4S店对客户的管理。


## Admin页面路由(wsite/js/fun_js/adminApp.js)
```javascript
$routeProvider.
        when('/main', {
            controller: 'deviceCtrl',
            templateUrl: '/admin/partials/deviceManage.html'//设备管理
        })
        .when('/driveData',{
            controller:'driveDataCtrl',
            templateUrl:'/admin/partials/driveData.html'//行车数据
        })
        .when('/customer4s',{
            controller:'customerCtrl',
            templateUrl:'/admin/partials/customer4s.html'//4s店客户
         })
        .when('/carOwners',{
            controller:'carOwnersCtrl',
            templateUrl:'/admin/partials/carOwners.html'//车主
        })
        .when('/userManage',{
            controller:'userManageCtrl',
            templateUrl:'/admin/partials/userManage.html'//系统用户
        })
        .when('/knowledgeBase',{
            controller:'knowledgeBaseCtrl',
            templateUrl:'/admin/partials/knowledgeBase.html'//行车手册
        })
        .when('/paramSetting',{
            controller:'s_paramCtrl',
            templateUrl:'/admin/partials/paramSetting.html'//设置
        })
        .otherwise({
           redirectTo:'/main'//跳转到设备管理
        });
```
	通过登录管理员帐号进入到admin帐户首页，会有7个超链接按钮可以选择，选择每个按钮会跳转到相对应的页面。


## 1.设备管理(/wsite/admin/partials/deviceManage.html   /wsite/js/fun_js/deviceController.js)
```javascript
	GetFirstPageInfo(), getInits4() //初始化分页展示数据。

	$scope.SearchDriveInfo =functhion(){...}  //根据条件查询行程数据

	function PagingInfo(totalCount,id){...}  //获取分页参数信息
	
	$scope.changePage = function(changeId,id){...} //分页跳转页面

	$scope.add = function(){...} //展开添加设备页面

	$scope.addDevice = function(){...} //确定添加设备

	function GetOwnerInfo(obd_code,s4_id){...}  //查询车主与车的信息

	$scope.GetDriveInfo = function(obd_code,s4_id,flag){...}   //查看一个OBD行车数据

	$scope.GetDriveDetail = function(obd_code,drive_id,id,flag){...} //查看一个OBD某一次行车数据

	$scope.GetOneMinuteDetail = function(index){...}   //一分钟内的行车数据流记录

	$scope.modify = function(index){...}  //展开修改OBD信息页面

    $scope.ModifyConfrim = function(sim_number,obd_code){...}  //修改OBD信息

	function getAjaxLink(url,query,type,id){...}  //利用$http封装访问，并解决防盗链问题

	function getIndexData(id,data){...}  //在访问之后对数据进行处理

	$scope.gotBack = function(id) {...}  // 返回上一页
```

## 2.行车数据(/wsite/admin/partials/driveDate.thml   /wsite/js/fun_js/driveDataController.js)
```javascript
	function GetFirstPageInfo(){..}  //初始化分页展示数据

	$scope.SearchDriveInfo = function (){...}  //按条件筛选行车数据

	function PagingInfo(totalCount,id){...}  //获取分页参数信息

	$scope.changePage = function(changeId,id){...} //分页跳转页面

	function GetOwnerInfo(obd_code,s4_id){...}  //查询车主与车的信息

	$scope.GetDriveDetail = function(obd_code,drive_id,id,s4_Id,flag){...}  //查看一个OBD某一次行车数据

	$scope.GetOneMinuteDetail = function(index){...} //一分钟内的行车数据流记录

	$scope.deleteRecord = function(index){...}  //删除
```
	
## 3.4s店客户(/wsite/admin/partials/customer4s.html   /wsite/js/fun_js/customerComtroller.js)
 创建资源对象，与后端服务进行交互。 
 ```javascript
	  var apiAdvanced = $resource(baseurl +'initialization/:s4Id/getAdvanced');
    var apiMaxTripid = $resource(baseurl +'initialization/:s4Id/getMaxTripid');
    var apiCarePeriod = $resource(baseurl +'initialization/:s4Id/getCarePeriod');
    var apiActivityTemplate = $resource(baseurl +'initialization/:s4Id/getActivityTemplate');
    var apiIndicatorLamp = $resource(baseurl +'initialization/:s4Id/getIndicatorLamp');
    var apiInitialType = $resource(baseurl +'initialization/:s4Id/updateInitialType',null,{'update': { method:'PUT' }});

	$scope.init = function(index){...}  // 客户列表初始化
	
	$scope.initOper = function(id,s4Id,biz_mode){...}  //单个初始化操作
	
	$scope.addConfirm = function(){...｝ //确定新增事件
	
	$scope.modify = function(id){
	...
		//上传图片
        $("#formId_edit2").ajaxForm({
            dataType:"json",
            success:function(data){
            $("#loading_2").css("display","none");
            $("#loaded_2").css("display","block");
            $scope.filename = data.files.edit_pro_img_2;
          }
        });
	...
	}   // 修改按钮
	
	$scope.modifyConfirm = function(oneCustomerInfo){...}  //确认修改
	
	$scope.addAdvanced = function(id,index){...}  //新增高级设置
	
	$scope.addCarePeriod = function(id){...}  //新增保养项目
	
	$scope.addActivityTemplate = function(id){...}  //新增活动模版
	
	$scope.addIndicatorLamp = function(id){...}  //新增4s指示灯
	
	$scope.updateInitialType = function(){...}  //客户为已经初始化数据
	
	$scope.getInitData = function(s4Id,index){...}  //获取客户的初始化数据
```

## 4.	车主(/wsite/admin/partials/carOwners.html  /wsite/js/fun_js/carownerController.js)
```javascript
	 $scope.AddConfirm = function()
    {
        var sha1_password =hex_sha1($scope.acc_pwd);  //SHA1进行加密 
		...
	}  // 添加一个车主
```

## 5. 系统用户(/wsite/admin/partials/knowledgeBase.html  /wsite/js/fun_js/knowledgeController.js)
```javascript
	$scope.deleteRecord = function(index){...}  //删除
```
	
## 6. 设置(/wsite/admin/partials/paramSetting.html  /wsite/js/fun_js/s_paramController.js)
```javascript
	$scope.changeBrand = function(brand_id){...} //查找品牌
	
	$scope.changeSeries = function(series_id){...}  //查找车型
```	
	
	
# 二、4S客户管理页面路由(/wsite/js/fun_js/S_indexController.js)
```javascript
$routeProvider.
            when('/4sStore/:s4_id/index', {
                controller: 's_indexCtrl',
                templateUrl: '/4sStore/index_main.html'
            })
            .when('/4sStore/:s4_id/order', {
                controller: 's_orderCtrl',
                templateUrl: '/4sStore/order.html'
            })
            .when('/4sStore/:s4_id/order/maintain', {
                controller: 's_orderCtrl',
                templateUrl: '/4sStore/order.html'
            })
            .when('/4sStore/:s4_id/order/maintain/:id', {
                controller: 's_orderCtrl',
                templateUrl: '/4sStore/order.html'
            })
            .when('/4sStore/:s4_id/order/drivetry', {
                controller: 's_orderCtrl',
                templateUrl: '/4sStore/order.html'
            })
			...
```

## 4S客户管理页面共有7个大模块分别为：预约管理、提醒管理、活动管理、客户管理、行车数据、数据分析、设置。
  1. 预约管理：包含保养预约、试乘试驾、其他预约三个模块。支持查询与其他操作并导出Excel表格。
  2. 提醒管理：包含保养提醒、车险提醒、车况提醒、碰撞提醒、水温提醒、充电提醒六个模块。可以实时提醒车主车辆发生了哪些故障。
  3. 活动管理：包含特价工位、资讯信息、节油大赛、自驾游大赛四个模块。可发布相应活动并查看活动状态。
  4. 客户管理：包含车系、用途、用车频率、驾驶偏好、车龄等十个模块。可从各个角度轻松管理客户。
  5. 行车数据：可以查看车辆历史的行车数据和行车状态与轨迹。
  6. 数据分析：包含图文分析、用户分析两个模块。可从曲线图查看历史各个模块被点击阅读或转发次数。还能查看客户车辆行驶信息与忠臣度。
  7. 设置：包含联系信息、订阅号同步、保养设置、指示灯、车型介绍、用户管理、修改密码七个模块。可修改4S店一系列展示信息。 
