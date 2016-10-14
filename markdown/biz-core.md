
# 英卡核心业务

## Org
代表一个组织

### Org.FromJson(src)
从一个JSON对象创建一个Org对象  
src: 一个JSON对象,包含一组Org的属性  
return: 新创建的Org对象
``` javascript
var bizCore = require('@incar/biz-core');
var orgNew = bizCore.Org.FromJson({ name: 'Renault 4S', short_name: 'R4S' });
```

### Org.FromIdG*(id)
Generator方法.从id生成一个对应的Org对象  
id: 组织的id.  
return:  Org对象
```javascript
var co = require('co');
co(function*(){
    var bizCore = require('@incar/biz-core');
    var orgS1 = yield bizCore.Org.FromIdG(1);
    console.log(orgS1);
}).catch(console.error);
```

### Org.GetOrgsG*(filter, pageSize, nextMarker, sortBy)
Generator方法.获取所有的Org对象.
filter: 过滤条件, 如{ name: /s/i, biz_mode: 1 }, 代表返回所有name包含s(不区分大小写)且biz_mode等于1的对象.
如果过滤条件里是一个正则表达式,那么把属性转换为字符串,做字符串匹配;
如果过滤条件里不是正则表达式,那么直接比较值是否相等匹配.  
pageSize: 可选.分页大小,默认不分页,返回全部.  
nextMarker: 可选.填入上下页返回的第一个org.id, 默认不填表示从第1页开始返回.
sortBy: 可选.排序属性,默认按id升序.如name-表示按name降序, id+或id表示按id升序.
return: { total: 34, orgs: [] }. total,符合过滤条件的对象总数;orgs,对象Org的数组

```javascript
var co = require('co');
co(function*(){
    var bizCore = require('@incar/biz-core');
    var total = yield bizCore.Org.GetRowsCount({name:'北京移动望京车队',biz_mode:1});
    var orgAlls = yield bizCore.Org.GetOrgsG({name:'北京移动望京车队',biz_mode:1},10,0,"name+");
     res.json({total:total.rowsCount,orgs:orgAlls});
}).catch(console.error);
```

### Org.prototype.toSafe()
创建一个可以返回给客户端的安全对象,安全对象指删除了密码等安全相关的属性  
return: 一个新的Org对象,已剔除了所有安全敏感属性
```javascript
var bizCore = require('@incar/biz-core');
var orgNotSafe = bizCore.Org.FromJson({ name: 'Renault 4S', wx_pwd: '123456' });
var orgSafe = orgNotSafe.toSafe();
```

### Org.addG*(org)
Generator方法.向数据库中插入一个Org对象
return: 包含了id的新Org对象
```javascript
var co = require('co');
co(function*(){
    var bizCore = require('@incar/biz-core');
    var orgNew = bizCore.Org.FromJson({ name: 'Renault 4S', short_name: 'R4S' });
    var orgAdded = yield bizCore.Org.addG(orgNew);
}).catch(console.error);
```

### Org.prototype.updateG*()
Generator方法.向数据库修改Org记录
return: 无
```javascript
co(function*(){
   var bizCore = require('@incar/biz-core');
   var orgNew = yield bizCore.Org.FromIdG(199);
   orgNew.name="北京移动望京车队K";
   yield orgNew.updateG();
}).catch(console.error);
```


### Org.deleteG*(orgId)
Generator方法.删除一条Org对象的数据
return: 无
```javascript
co(function*(){
    var bizCore = require('@incar/biz-core');
     var orgNew = bizCore.Org.FromJson({
          name: '测试ORG',
          short_name: 'TEST'
      });
      var orgAdded = yield bizCore.Org.deleteG(orgNew);
      yield bizCore.Org.deleteG(orgAdded.id);
}).catch(console.error);
```

### Org.prototype.addStaffG*(staff)
Generator方法.向Org中新增一个Staff对象
return:staff对象
```javascript
co(function*(){
 var suffix = String(Math.random()).substr(2, 6);
    var staffNew = Staff.FromJson({
        s4_id: 1,
        name: 'yourname'+suffix,
        phone:,
        pwd:'password',
        nick:'nick'
    });
    orgNew = yield Org.FromIdG(5);
    staffAdded = yield orgNew.addStaffG(staffNew);
}).catch(console.error);
```
### Org.prototype.removeStaffG*(staffId)
Generator方法.从Org中删除一个Staff对象
return:无
```javascript
co(function*(){
  yield orgNew.removeStaffG(staffAdded.id);
}).catch(console.error);
```

### Org.prototype.getStaffByIdG*(staffId)
Generator方法.获取一个Staff对象
return:staff对象
```javascript
co(function*(){
    var orgNew = yield Org.FromIdG(5);
    var staffFind = yield orgNew.getStaffByIdG(20);
}).catch(console.error);
```

### Org.prototype.getStaffsG*(filter, pageSize, nextMarker, sortBy)
Generator方法.获取所有Staff对象
return:staff对象集合
```javascript
co(function*(){
   var orgNew = yield Org.FromIdG(5);
   var result = yield orgNew.getStaffsG({s4_id:5},3,0,"id-");
}).catch(console.error);
```

### Org.prototype.addVehicleG*(vehicle){
Generator方法.向Org中新增一台车Vehicle
return:vehicle对象
```javascript
co(function*(){
   var suffix = String(Math.random()).substr(2, 5);
   var vehicleNew = Vehicle.FromJson({
       s4_id: 199,
       license:"鄂A"+suffix,
       obd_code:"TEST00000"+suffix,
       brand:11,
       series:13
   });
   orgNew = yield Org.FromIdG(vehicleNew.s4_id);
   vehicleAdded = yield orgNew.addVehicleG(vehicleNew);
}).catch(console.error);
```

### Org.prototype.removeVehicleG*(vehicleId)
Generator方法.从Org中删除一台车Vehicle
return:无
```javascript
co(function*(){
   yield orgNew.removeVehicleG(vehicleAdded.id);
}).catch(console.error);
```

### Org.prototype.getVehicleByIdG*(vehicleId)
Generator方法.获取一个Vehicle对象
return:vehicle对象
```javascript
co(function*(){
    var orgNew = yield Org.FromIdG(199);
    var vehicleFind = yield orgNew.getVehicleByIdG(161);
}).catch(console.error);
```

### Org.prototype.getVehiclesG*(filter, pageSize, nextMarker, sortBy)
Generator方法.获取所有Vehicles对象
return:ehicle对象集合
```javascript
co(function*(){
  var orgNew = yield Org.FromIdG(5);
  var result = yield orgNew.getVehiclesG({s4_id:1},3,0,"id-");
}).catch(console.error);
```

## Staff
代表一名雇员

### Staff.FromJson(src)
从一个JSON对象创建一个Staff对象
```javascript
var bizCore = require('@incar/biz-core');
var staff = bizCore.Staff.FromJson({
    s4_id: 1,
    name: 'boss',
    pwd: '48058e0c99bf7d689ce71c360699a14ce2f99663',
    nick: 'Old Wang',
    status: 1,
    email: 'wang@incardata.com.cn',
    phone: '13912345678',
    userType: 1 });
```
### Staff.FromTokenG*(token)
Generator方法.从一个加密的token中创建一个Staff对象,通常用于登录验证过程中
```javascript
var co = require('co');
co(function*(){
    var token = "z+y95pv0nIxkz0c+dzj23VeaSSrdBgYmTNb76iSEobVfacdnKwViNR59sHNLlLgPwQzmutxCD7teW/f86YqUMORu44Lr6gEQPEy/pqjYjdE=";
    var staff = yield Staff.FromTokenG(token);
    console.log(staff);
}).catch(console.error);
```

### Staff.prototype.toSafe();
创建一个可以返回给客户端的安全对象,安全对象指删除了密码等安全相关的属性  
return: 一个新的Staff对象,已剔除了所有安全敏感属性

### Staff.prototype.isDisabled()
Staff对象是否已启用.
return: true,已禁用; false,已启用.

### Staff.prototype.isSystemAdmin()
Staff对象是否是系统级别的超级管理员.
return: true,是超级管理员; false,不是超级管理员.

### Staff.prototype.updateG*();
Generator方法.修改一个staff的信息
return 无
```javascript
    co(function*(){
       var suffix = String(Math.random()).substr(2, 6);
       var staffNew = yield Staff.FromIdG(20);
       staffNew.name="limei"+suffix;
       yield staffNew.updateG();
       var orgNew = yield Org.FromIdG(5);
       var staffFind = yield orgNew.getStaffByIdG(20);
    }).catch(console.error);
```
### Staff.prototype.dispatchVehicleG*();
Generator方法.派遣出车
return 新增的出车记录
```javascript
    co(function*(){
        var staffNew = yield Staff.FromIdG(21);
         var vehicleNew =  Vehicle.FromJson({
            car_id:158,
            staff_id:21,
            dispatch_time:new Date()
        })
        dispatchAdded = yield staffNew.dispatchVehicleG(vehicleNew);
    }).catch(console.error);
```
### Staff.prototype.deleteDispatchingRecordG*();
Generator方法.删除一条派遣记录
return 无
```javascript
    co(function*(){
       var staffNew = yield Staff.FromIdG(dispatchAdded.staff_id);
       var vehicleNew = yield Vehicle.FromIdG(dispatchAdded.car_id);
       yield staffNew.deleteDispatchingRecordG(vehicleNew,"2015-03-04 18:44:40");
    }).catch(console.error);
```
### Staff.prototype.getDispatchingVehicleG*();
Generator方法.获取当前正在由该司机驾驶的派遣车辆
return 一个Vehicle对象
```javascript
    co(function*(){
       var staffNew = yield Staff.FromIdG(21);
       var result =  yield staffNew.getDispatchingVehicleG();
    }).catch(console.error);
```
### Staff.prototype.getDispatchingHistoryG*();
Generator方法.获取该司机的派遣历史记录
return 出车记录数组
```javascript
    co(function*(){
       var staffNew = yield Staff.FromIdG(21);
       var result =  yield staffNew.getDispatchingHistoryG(10,1);
    }).catch(console.error);
```
### Staff.prototype.recoverVehicleG*();
Generator方法.回收车
return 无
```javascript
    co(function*(){
        var staffNew = yield Staff.FromIdG(21);
        var vehicleNew = yield Vehicle.FromIdG(158);
        yield staffNew.recoverVehicleG(vehicleNew,"2015-03-05 08:00:21");
    }).catch(console.error);
```

## Vehicle
代表一辆车

### Vehicle.FromJson(src)
从一个JSON对象创建一辆车的对象
```javascript
var bizCore = require('@incar/biz-core');
var vehicle = bizCore.Vehicle.FromJson({
    s4_id: 1,
    license: '鄂A88888',
    obd_code: 'TEST00000212',
    brand: 11,
    series: 13,
     });
```

### Vehicle.prototype.updateG*();
Generator方法.修改一辆车的信息
return 无
```javascript
    co(function*(){
      var suffix = String(Math.random()).substr(2, 6);
      var vehicleNew = yield Vehicle.FromIdG(161);
      vehicleNew.license="鄂B"+suffix;
      yield vehicleNew.updateG();
      var orgNew = yield Org.FromIdG(199);
      var vehicleFind = yield orgNew.getVehicleByIdG(161);
    }).catch(console.error);
```

### Vehicle.prototype.getTripsG*();
Generator方法.查询一辆车的行车数据集合
pageSize: 可选.分页大小,默认不分页,返回全部.
nextMarker: 可选.填入上下页返回的第一个rowNo, 默认不填表示从第1页开始返回.
sortBy: 默认fireTime-.
return: { total: 34, trips: [] }. total,符合过滤条件的对象总数;trips,行车数据集合
```javascript
    co(function*(){
         var vehicleNew = yield Vehicle.FromIdG(158);
         var result =  yield vehicleNew.getTripsG(5,1);
    }).catch(console.error);
```

### Vehicle.prototype.getTripPathG*();
Generator方法.获取行车位置数据
return 位置数据数组
```javascript
    co(function*(){
       var vehicleNew = yield Vehicle.FromIdG(158);
       var result =  yield vehicleNew.getTripPathG(34);
    }).catch(console.error);
```

### Vehicle.prototype.isDispatchedG*();
Generator方法.车辆是否已被派遣出去
return  true/false
```javascript
    co(function*(){
      var vehicleNew = yield Vehicle.FromIdG(158);
      var result =  yield vehicleNew.isDispatchedG();
    }).catch(console.error);
```

### Vehicle.prototype.getDispatchingDriverG*();
Generator方法.车辆派遣出去的驾驶司机
return 一个Staff对象
```javascript
    co(function*(){
       var vehicleNew = yield Vehicle.FromIdG(158);
       if(yield vehicleNew.isDispatchedG()){
          var result =  yield vehicleNew.getDispatchingDriverG();
           debug(result);
       }else{
           debug("该车还未被派遣出去！");
       }
    }).catch(console.error);
```

### Vehicle.prototype.getDispatchingHistoryG*();
Generator方法.获取该车的派遣历史记录
return 出车记录数组
```javascript
    co(function*(){
       var vehicleNew = yield Vehicle.FromIdG(161);
       var result =  yield vehicleNew.getDispatchingHistoryG(10,2);
    }).catch(console.error);
```

## Statistics
已车辆、司机分别进行分析

### Statistics.StaMileageG*(fleetId,tm_start,tm_end,isChart);
Generator方法.统计车辆里程
fleetId:车队的id.
tm_start:开始时间
tm_end:结束时间.
isChart:为true，查询前五条数据；为false,查询全部数据
return : 一个statistics对象
```javascript
co(function*(){
    var statisticsData  = yield bizCore.Statistics.StaMileageG(199,"2015-04-01","2015-04-07",true);
    res.json({status:'ok',statisticsData:statisticsData});
}).catch(function(ex){
    res.status(500).send(ex.stack);
});
```

### Statistics.StaMileageCountG*(fleetId,tm_start,tm_end);
Generator方法.统计车辆总里程
fleetId:车队的id.
tm_start:开始时间
tm_end:结束时间.
return : 一个statistics对象
```javascript
co(function*(){
    var count = yield bizCore.Statistics.StaMileageCountG(199,"2015-04-01","2015-04-07");
    res.json({status:'ok',total:count});
}).catch(function(ex){
    res.status(500).send(ex.stack);
});
```

### Statistics.StaSpeedMaxG*(fleetId,tm_start,tm_end,isChart);
Generator方法.统计车辆里程
fleetId:车队的id.
tm_start:开始时间
tm_end:结束时间.
isChart:为true，查询前五条数据；为false,查询全部数据
return : 一个statistics对象
```javascript
co(function*(){
    var statisticsData  = yield bizCore.Statistics.StaSpeedMaxG(199,"2015-04-01","2015-04-07",true);
    res.json({status:'ok',,statisticsData:statisticsData});
}).catch(function(ex){
    res.status(500).send(ex.stack);
});
```

### Statistics.StaSpeedMaxCountG*(fleetId,tm_start,tm_end);
Generator方法.统计车辆总里程
fleetId:车队的id.
tm_start:开始时间
tm_end:结束时间.
return : 一个statistics对象
```javascript
co(function*(){
    var count = yield bizCore.Statistics.StaSpeedMaxCountG(199,"2015-04-01","2015-04-07");
    res.json({status:'ok',total:count});
}).catch(function(ex){
    res.status(500).send(ex.stack);
});
```

### Statistics.StaStartTimesG*(fleetId,tm_start,tm_end,isChart);
Generator方法.统计车辆里程
fleetId:车队的id.
tm_start:开始时间
tm_end:结束时间.
isChart:为true，查询前五条数据；为false,查询全部数据
return : 一个statistics对象
```javascript
co(function*(){
    var statisticsData  = yield bizCore.Statistics.StaStartTimesG(199,"2015-04-01","2015-04-07",true);
    res.json({status:'ok',,statisticsData:statisticsData});
}).catch(function(ex){
    res.status(500).send(ex.stack);
});
```

### Statistics.StaStartTimesCountG*(fleetId,tm_start,tm_end);
Generator方法.统计车辆总里程
fleetId:车队的id.
tm_start:开始时间
tm_end:结束时间.
return : 一个statistics对象
```javascript
co(function*(){
    var count = yield bizCore.Statistics.StaStartTimesCountG(199,"2015-04-01","2015-04-07");
    res.json({status:'ok',total:count});
}).catch(function(ex){
    res.status(500).send(ex.stack);
});
```

### Statistics.StaSpeedUpDownG*(fleetId,tm_start,tm_end,isChart);
Generator方法.统计车辆里程
fleetId:车队的id.
tm_start:开始时间
tm_end:结束时间.
isChart:为true，查询前五条数据；为false,查询全部数据
return : 一个statistics对象
```javascript
co(function*(){
    var statisticsData  = yield bizCore.Statistics.StaSpeedUpDownG(199,"2015-04-01","2015-04-07",true);
    res.json({status:'ok',,statisticsData:statisticsData});
}).catch(function(ex){
    res.status(500).send(ex.stack);
});
```

### Statistics.StaSpeedUpDownCountG*(fleetId,tm_start,tm_end);
Generator方法.统计车辆总里程
fleetId:车队的id.
tm_start:开始时间
tm_end:结束时间.
return : 一个statistics对象
```javascript
co(function*(){
    var count = yield bizCore.Statistics.StaSpeedUpDownCountG(199,"2015-04-01","2015-04-07");
    res.json({status:'ok',total:count});
}).catch(function(ex){
    res.status(500).send(ex.stack);
});
```

### Statistics.StaOilUsedG*(fleetId,tm_start,tm_end,isChart);
Generator方法.统计车辆里程
fleetId:车队的id.
tm_start:开始时间
tm_end:结束时间.
isChart:为true，查询前五条数据；为false,查询全部数据
return : 一个statistics对象
```javascript
co(function*(){
    var statisticsData  = yield bizCore.Statistics.StaOilUsedG(199,"2015-04-01","2015-04-07",true);
    res.json({status:'ok',,statisticsData:statisticsData});
}).catch(function(ex){
    res.status(500).send(ex.stack);
});
```

### Statistics.StaOilUsedCountG*(fleetId,tm_start,tm_end);
Generator方法.统计车辆总里程
fleetId:车队的id.
tm_start:开始时间
tm_end:结束时间.
return : 一个statistics对象
```javascript
co(function*(){
    var count = yield bizCore.Statistics.StaOilUsedCountG(199,"2015-04-01","2015-04-07");
    res.json({status:'ok',total:count});
}).catch(function(ex){
    res.status(500).send(ex.stack);
});
```

### Statistics.StaStaffMileageG*(fleetId,tm_start,tm_end,isChart);
Generator方法.统计车辆里程
fleetId:车队的id.
tm_start:开始时间
tm_end:结束时间.
isChart:为true，查询前五条数据；为false,查询全部数据
return : 一个statistics对象
```javascript
co(function*(){
    var statisticsData  = yield bizCore.Statistics.StaStaffMileageG(199,"2015-04-01","2015-04-07",true);
    res.json({status:'ok',,statisticsData:statisticsData});
}).catch(function(ex){
    res.status(500).send(ex.stack);
});
```

### Statistics.StaStaffMileageCountG*(fleetId,tm_start,tm_end);
Generator方法.统计车辆总里程
fleetId:车队的id.
tm_start:开始时间
tm_end:结束时间.
return : 一个statistics对象
```javascript
co(function*(){
    var count = yield bizCore.Statistics.StaStaffMileageCountG(199,"2015-04-01","2015-04-07");
    res.json({status:'ok',total:count});
}).catch(function(ex){
    res.status(500).send(ex.stack);
});
```

### Statistics.StaStaffSpeedMaxG*(fleetId,tm_start,tm_end,isChart);
Generator方法.统计车辆里程
fleetId:车队的id.
tm_start:开始时间
tm_end:结束时间.
isChart:为true，查询前五条数据；为false,查询全部数据
return : 一个statistics对象
```javascript
co(function*(){
    var statisticsData  = yield bizCore.Statistics.StaStaffSpeedMaxG(199,"2015-04-01","2015-04-07",true);
    res.json({status:'ok',,statisticsData:statisticsData});
}).catch(function(ex){
    res.status(500).send(ex.stack);
});
```

### Statistics.StaStaffSpeedMaxCountG*(fleetId,tm_start,tm_end);
Generator方法.统计车辆总里程
fleetId:车队的id.
tm_start:开始时间
tm_end:结束时间.
return : 一个statistics对象
```javascript
co(function*(){
    var count = yield bizCore.Statistics.StaStaffSpeedMaxCountG(199,"2015-04-01","2015-04-07");
    res.json({status:'ok',total:count});
}).catch(function(ex){
    res.status(500).send(ex.stack);
});
```

### Statistics.StaStaffStartTimesG*(fleetId,tm_start,tm_end,isChart);
Generator方法.统计车辆里程
fleetId:车队的id.
tm_start:开始时间
tm_end:结束时间.
isChart:为true，查询前五条数据；为false,查询全部数据
return : 一个statistics对象
```javascript
co(function*(){
    var statisticsData  = yield bizCore.Statistics.StaStaffStartTimesG(199,"2015-04-01","2015-04-07",true);
    res.json({status:'ok',,statisticsData:statisticsData});
}).catch(function(ex){
    res.status(500).send(ex.stack);
});
```

### Statistics.StaStaffStartTimesCountG*(fleetId,tm_start,tm_end);
Generator方法.统计车辆总里程
fleetId:车队的id.
tm_start:开始时间
tm_end:结束时间.
return : 一个statistics对象
```javascript
co(function*(){
    var count = yield bizCore.Statistics.StaStaffStartTimesCountG(199,"2015-04-01","2015-04-07");
    res.json({status:'ok',total:count});
}).catch(function(ex){
    res.status(500).send(ex.stack);
});
```

### Statistics.StaStaffSpeedUpDownG*(fleetId,tm_start,tm_end,isChart);
Generator方法.统计车辆里程
fleetId:车队的id.
tm_start:开始时间
tm_end:结束时间.
isChart:为true，查询前五条数据；为false,查询全部数据
return : 一个statistics对象
```javascript
co(function*(){
    var statisticsData  = yield bizCore.Statistics.StaStaffSpeedUpDownG(199,"2015-04-01","2015-04-07",true);
    res.json({status:'ok',,statisticsData:statisticsData});
}).catch(function(ex){
    res.status(500).send(ex.stack);
});
```

### Statistics.StaStaffSpeedUpDownCountG*(fleetId,tm_start,tm_end);
Generator方法.统计车辆总里程
fleetId:车队的id.
tm_start:开始时间
tm_end:结束时间.
return : 一个statistics对象
```javascript
co(function*(){
    var count = yield bizCore.Statistics.StaStaffSpeedUpDownCountG(199,"2015-04-01","2015-04-07");
    res.json({status:'ok',total:count});
}).catch(function(ex){
    res.status(500).send(ex.stack);
});
```

### Statistics.StaStaffOilUsedG*(fleetId,tm_start,tm_end,isChart);
Generator方法.统计车辆里程
fleetId:车队的id.
tm_start:开始时间
tm_end:结束时间.
isChart:为true，查询前五条数据；为false,查询全部数据
return : 一个statistics对象
```javascript
co(function*(){
    var statisticsData  = yield bizCore.Statistics.StaStaffOilUsedG(199,"2015-04-01","2015-04-07",true);
    res.json({status:'ok',,statisticsData:statisticsData});
}).catch(function(ex){
    res.status(500).send(ex.stack);
});
```

### Statistics.StaStaffOilUsedCountG*(fleetId,tm_start,tm_end);
Generator方法.统计车辆总里程
fleetId:车队的id.
tm_start:开始时间
tm_end:结束时间.
return : 一个statistics对象
```javascript
co(function*(){
    var count = yield bizCore.Statistics.StaStaffOilUsedCountG(199,"2015-04-01","2015-04-07");
    res.json({status:'ok',total:count});
}).catch(function(ex){
    res.status(500).send(ex.stack);
});
```

