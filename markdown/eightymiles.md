# 80迈APP后台

## 包名：@incar/eightymiles
80迈向个人车主提供行车数据同步、故障提醒、保养提醒、驾驶行为数据统计分析等服务。  
行车数据包括车辆实时行驶车速、行车里程、使用油耗、故障警报， 以及驾驶行为数据：最高车速、急转弯次数、急加速次数、急减速次数。

## 依赖项
参见 package.json 文件，主要的依赖项如下：

* __@incar/biz-core__ 英卡核心业务服务
* __@incar/biz-eightymiles__ 80迈数据处理

## 用户 `UserAPI.js`

* `UserAPI.getStaffRouter` 获取后台用户管理的路由对象（express router）
* `UserAPI.getUserRouter` 获取车主用户的路由对象（express router）
* `UserAPI.resolveUser` 获取当前WEB请求对应用户信息的express中间件（middleware）
* `UserAPI.get` 获取当前用户信息
* `UserAPI.update` 更新用户信息
* `UserAPI.changePwd` 修改用户密码
* `UserAPI.resetPwd` 重围用户密码
* `UserAPI.sendVerifyCodeSms` 发送验证码短信
* `UserAPI.checkPhoneNameEmail` 检查手机号、用户名、邮箱是否已注册
* `UserAPI.register` 用户注册
* `UserAPI.login` 用户登录
* `UserAPI.getUsers` 查询用户

## 车辆 `VehicleAPI.js`

* `VehicleAPI.getStaffRouter` 获取后台用户管理的路由对象（express router）
* `VehicleAPI.getUserRouter` 获取车主用户的路由对象（express router）
* `VehicleAPI.resolveVehicle` 获取当前WEB请求对应车辆信息的express中间件（middleware）
* `VehicleAPI.checkObdCode` 检测obd设备编码是否已注册
* `VehicleAPI.checkLicense` 检测车牌号是否已注册
* `VehicleAPI.bind` 用户绑定车辆信息
* `VehicleAPI.unbind` 用户帐户解绑车辆
* `VehicleAPI.get` 获取用户的车辆信息
* `VehicleAPI.update` 更新用户的车辆信息
* `VehicleAPI.getBrands` 获取所有汽车品牌列表
* `VehicleAPI.getSeriesForBrand` 获取一个汽车品牌的所有车系
* `VehicleAPI.getTrips` 查询车辆行程记录
* `VehicleAPI.addTrips` 指添加车辆行程数据， 用于设备数据同步
* `VehicleAPI.getFaultCodes` 查询故障报警记录
* `VehicleAPI.addFaultCodes` 批量添加故障报警数据， 用于设备数据同步
* `VehicleAPI.getCareConfig` 获取所有保养项目的保养里程、保养时间等配置数据
* `VehicleAPI.getCares` 获取保养记录列表
* `VehicleAPI.addCare` 添加保养记录
* `VehicleAPI.updateCare` 更新保养记录
* `VehicleAPI.deleteCare` 删除保养记录
* `VehicleAPI.getCareStatus` 获取和各保养条目的最近一次保养里程和保养时间，及对应的保养要求
* `VehicleAPI.getCareAdvice` 获取车辆的保养建议
* `VehicleAPI.dayAvgOilRate` 查询每日平均百公里油耗
* `VehicleAPI.dayMileage` 查询每日行驶里程
* `VehicleAPI.dayMaxSpeed` 查询每日最高时速
* `VehicleAPI.daySpeedUpDownTimes` 查询每日急加速次数

## 后台管理员 `StaffAPI.js`

* `StaffAPI.getStaffRouter` 获取后台用户管理的路由对象（express router）
* `StaffAPI.resolveStaff` 获取当前WEB请求对应管理员信息的express中间件（middleware）
* `StaffAPI.get` 获取管理员信息
* `StaffAPI.update` 更新管理员信息
* `StaffAPI.changePwd` 修改管理员密码
* `StaffAPI.login` 管理员登录
* `StaffAPI.logout` 管理员注销
* `StaffAPI.register` 管理员注册（已废弃）

## 行程数据 `TripAPI.js`

* `TripAPI.getStaffRouter` 获取后台用户管理的路由对象（express router）
* `TripAPI.get` 查询车辆行程数据

## 故障报警 `FaultCodeAPI.js`

* `FaultCodeAPI.getStaffRouter` 获取后台用户管理的路由对象（express router）
* `FaultCodeAPI.get` 查询车辆故障报警数据

## 车辆保养 `CareAPI.js`
* `CareAPI.getStaffRouter` 获取后台用户管理的路由对象（express router）
* `CareAPI.get` 查询保养记录
* `CareAPI.getAlarms` 查询保养提醒

## 反馈意见 `FeedbackAPI.js`

* `FeedbackAPI.getStaffRouter` 获取后台用户管理的路由对象（express router）
* `FeedbackAPI.getUserRouter` 获取车主用户的路由对象（express router）
* `FeedbackAPI.getByUser` 查询指定用户的反馈意见
* `FeedbackAPI.addByUser` 添加用户的反馈意见

## 定时任务 `CronJob.js`
每天凌晨1点执行保养提醒任务计划。对所有用户的保养状态进行检查，生成保养提醒记录写入数据库。用户打开APP后，主动拉取保养提醒内容。

