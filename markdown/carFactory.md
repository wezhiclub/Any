# 广本试驾车管理

## 包名：@incar/carFactory
广本试驾车管理。

 

## 车厂管理 ` CarFactoryAPI.js`

* `CarFactoryAPI.getAll`  查询所有经销商集团
* `CarFactoryAPI.post` 增加一个试乘试驾
* `CarFactoryAPI.prototype.getAllDealers` 获取所有的经销商
* `CarFactoryAPI.prototype.getTripsStatistic` 获取行程统计
* `CarFactoryAPI.prototype.getTripsStatisticExcel` 获取行程统计
* `CarFactoryAPI.prototype.getAllTdRecords` 获取试驾记录
* `CarFactoryAPI.prototype.getAllTdAlarms` 获取报警信息
* `CarFactoryAPI.prototype.getTdAlarmReport`  获取报警统计报表信息
* `CarFactoryAPI.prototype.get4sRegion` 获取车厂下面的大区
* `CarFactoryAPI.prototype.getAllRegion` 获取所有大区
* `CarFactoryAPI.prototype.get4sProvince` 获取车厂下面的省
* `CarFactoryAPI.prototype.getAllProvince` 获取大区下面所有的省
* `CarFactoryAPI.prototype.get4sCity`  获取车厂下面的城市
* `CarFactoryAPI.prototype.getAllCity` 获取省下面所有的城市
* `CarFactoryAPI.prototype.get` 获取一个试乘试驾
* `CarFactoryAPI.prototype.update` 修改一个试乘试驾
* `CarFactoryAPI.prototype.getAllDevices` 查找所有的设备
* `CarFactoryAPI.prototype.saveDealer` 添加4S店
* `CarFactoryAPI.prototype.updateDeale` 修改4S店
* `CarFactoryAPI.prototype.getAllVehicles` 查看整车厂一个城市的所有车辆
* `CarFactoryAPI.exportExcel` 导出Excel
* `CarFactoryAPI.transferAlarmType` 判断报警类型


## 经销商管理 ` DealerAPI.js`
* `DealerAPI.prototype.getAllVehicles` 获取试乘试驾店的所有车辆
* `DealerAPI.prototype.getAllStaffs` 获取试乘试驾店的所有账号

## 围栏 ` FenceAPI.js`
* `FenceAPI.prototype.get` 查询围栏
* `FenceAPI.prototype.save` 保存围栏


## 员工 ` StaffAPI.js`
* `StaffAPI.prototype.get` 查询员工
* `StaffAPI.prototype.update` 修改员工
* `StaffAPI.prototype.remove` 删除员工
* `StaffAPI.getStaffLogin`   查询当前登录用户信息

## 行程 ` TripAPI.js` 
* `TripAPI.prototype.get`  获取单独1个行程数据
* `TripAPI.prototype.getTripPath`  查询某台车的所有行程数据
* `TripAPI.prototype.getDetail` 查询某台车的所有行程详情

##  车辆 ` VehicleAPI.js` 
* `VehicleAPI.prototype.get` 查询车辆
* `VehicleAPI.prototype.update` 修改车辆
* `VehicleAPI.prototype.remove` 删除车辆
* `VehicleAPI.prototype.getVehicleStatus` 获取车辆状态
* `VehicleAPI.prototype.getVehiclePos` 获取车辆位置
* `VehicleAPI.prototype.getTrips` 查询某台车的所有行程数据


##  权限 ` Authority.js` 
* `Authority.requireSystemAdmin` 验证系统管理员
* `Authority.requireStaffOrSystemAdmin` 验证系统管理员或者员工


