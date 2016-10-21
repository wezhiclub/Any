# 80迈数据处理

## 包名：@incar/biz-eightymiles
完成数据库数据的读写操作，以及分析和统计。

## 依赖项

参见 package.json 文件，主要的依赖项如下：

* __@incar/biz-core__ 英卡核心业务服务
* __@incar/mysql__ MySQL数据库读写服务

## 车辆 `Vehicle.js`
车辆信息记录。

* `Vehicle.FromJson` 从一个json对象实例化一个`Vehicle`对象
* `Vehicle.FromIdG` 根据车辆id从数据库中取车`Vehicle`对象
* `Vehicle.GetBrandsG` 获取所有的车辆品牌
* `Vehicle.GetSeriesForBrandG` 获取某个车辆品牌下的所有车系
* `Vehicle.GetVehiclesG` 按指定条件查询车辆
* `Vehicle.GetVehiclesCountG` 查询符合指定条件车辆的数目
* `Vehicle.GetTotalMileageG` 获取指定车辆的总行驶量程
* `Vehicle.prototype.addG` 将车辆对象写入数据库
* `Vehicle.prototype.updateG` 将车辆对象刷新至数据库
* `Vehicle.prototype.getCaresG` 按条件查询一个车辆对象的保养记录
* `Vehicle.prototype.getCaresCountG` 获取一个车辆对象的保养记录中符合查询条件的条数
* `Vehicle.prototype.addCareG` 添加一条保养记录
* `Vehicle.prototype.updateCareG ` 更新一条保养记录
* `Vehicle.prototype.deleteCareG ` 删除一条保养记录
* `Vehicle.prototype.getCareStatusG` 获取一个车辆对象的各项保养条目的保养状态， 及是否需要保养、超出保养计划的天数或里程数
* `Vehicle.prototype.getTripsG ` 查询一个车辆对象的行程数据
* `Vehicle.prototype.getTripsCountG` 获取一个车辆对象的行程数据中符合查询条件的条数
* `Vehicle.prototype.addTrips` 批量添加行程数据， 用于设备数据同步
* `Vehicle.prototype.getFaultCodesG` 查询故障警报记录
* `Vehicle.prototype.getFaultCodesCountG` 获取一个车辆对象的故障警报记录中符合查询条件的条数
* `Vehicle.prototype.addFaultCodes` 批量添加故障提醒， 用于设备数据同步

## 用户 `User.js`
车辆所有者称为用户`User`。

* `User.FromJson` 从一个json对象实例化一个`User`对象
* `User.FromIdG` 根据用户id从数据库中取车`User`对象
* `User.FromTokenG` 从用户登录cookie中的token获取用户信息
* `User.AuthenticateG` 用户登录认证
* `User.EncryptPassword` 用户密码加密
* `User.GetUsersG` 查询用户
* `User.GetUsersCountG` 获取符合条件的用户数
* `User.GetVehicleG` 获取指定用户的车辆信息
* `User.prototype.toSafe` 返回一个不包含用户密码等敏感数据的`User`对象
* `User.prototype.makeToken` 生成一个用户登录token，用于cookie中
* `User.prototype.addG` 将一个用户对象写入数据库
* `User.prototype.bindVehicleG` 用户绑定一辆车
* `User.prototype.unbindVehicleG` 用户解绑车辆
* `User.prototype.updateG` 将用户对象信息刷新至数据库

## 行程 `Trip.js`
车辆从点火到熄火称为一个行程`Trip`。

* `Trip.FromJson` 从一个json对象实例化一个`Trip`对象
* `Trip.BulkAddG` 批量向数据库插入行程数据，用于设备数据同步
* `Trip.GetTripsG` 查询行程记录
* `Trip.GetTripsCountG` 获取符合查询条件的行程记录条数
* `Trip.GetTripsExG` 查询行程记录，及行程记录对应的车辆、用户信息
* `Trip.GetTripsExCountG` 获取符合查询条件的行程记录条数
* `Trip.prototype.addG` 添加行程对象信息插入数据库

# 故障码 `FaultCode.js`
车辆故障报警记录。

* `FaultCode.FromJson` 从一个JSON对象构造`FaultCode`对象
* `FaultCode.BulkAddG` 批量向数据库插入故障警报数据，用于设备数据同步
* `FaultCode.GetFaultCodesG` 查询故障警报记录
* `FaultCode.GetFaultCodesCountG` 获取故障警报记录条数
* `FaultCode.GetFaultCodesExG` 查询故障警报记录，及关联的车、用户信息
* `FaultCode.GetFaultCodesExCountG` 获取故障警报记录条数
* `FaultCode.prototype.addG` 将一个故障警报对象插入数据库

# 车辆保养 `Care.js`
车辆保养记录。

* `Care.FromJson` 从一个JSON对象构造`Care`对象
* `Care.GetCaresG` 查询保养记录
* `Care.GetCaresCountG` 获取保养记录条数
* `Care.GetCaresExG` 查询保养记录， 及关联的用户、车辆信息
* `Care.GetCaresExCountG` 获取保养记录条数
* `Care.GetCareConfigG` 获取保养项目的保养里程、保养时间等配置信息
* `Care.AddG` 向数据库插入一条保养记录
* `Care.UpdateG` 更新一次保养记录中的明细数据
* `Care.DeleteG` 删除一条保养记录
* `Care.GetCareStatusG` 获取保养状态及对应的保养要求
* `Care.GetCareAdviceG` 获取保养建议
* `Care.GetAlarmsG` 查询保养警报记录
* `Care.GetAlarmsCountG` 获取保养警报数量

# 行车数据统计 `Stat.js`

* `Stat.DayAvgOilRateG` 指定车辆每日平均百公里油耗
* `Stat.DayAvgOilRateOfEveryoneG` 系统内所有人每日日均百公里油耗
* `Stat.DayMileageG` 指定车辆每日行车里程
* `Stat.DayMileageOfEveryoneG` 系统内所有车辆的每日行车里程均值
* `Stat.DaySpeedUpDownTimesG` 每日急加减速次数
* `Stat.DaySpeedUpDownTimesOfEveryoneG` 系统内所有车辆的每日急加速次数均值
* `Stat.DayMaxSpeedG` 每日最高车速
* `Stat.DayMaxSpeedOfEveryoneG` 系统内所有车辆的每日最高车速最大值

# 用户反馈意见 `Feedback.js`

* `Feedback.FromJson` 从一个JSON对象构造`Feedback`对象
* `Feedback.GetByUserIdG` 查询指定用户的反馈意见
* `Feedback.GetByUserIdCountG` 获取指定用户的反馈意见条数
* `Feedback.prototype.addG` 将`Feedback`对象插入数据库

# 工具方法 `Utils.js`

* `Utils.sendSmsP` 发送短信消息， 已废弃
* `Utils.sendSmsCode` 使用阿里大鱼短信服务发送短信验证码
* `Utils.addCacheData` 写入redis缓存
* `Utils.getCacheDataP` 获取redis缓存数据
* `Utils.getTableFieldsG` 获取数据表中的各列名称
* `Utils.mergeTableFilterG` 合并多张表的过滤条件
* `makeQuerySql` 拼接SQL语句
* `extractProps` 从对象中提取指定的属性构成的json对象
* `extend` 向对象写入属性
* `excludeProps` 从对象中删除属性