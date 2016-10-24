# 英卡车队业务
DispatchTeam
是一个派遣小队
DispatchTeam.FromJson
从一个JSON对象创建一个DispatchTeam对象
Src：一个JSON对象，包含一组dispatcheTeam属性
Return：新的dispatchTeam对象


DispatchTeam.FromIdG*(fleetId, teamId) 
通过车队ID和小队ID查询指定的派遣小队
FleetId：车队ID
TeamId： 小队ID

DispatchTeam.GetDispatchTeamsG*(filter , pageSize , nextMaker)
查询某个车队下的所有车辆
Filter ： 过滤条件如{ name: /s/i, biz_mode: 1 }, 代表返回所有name包含s(不区分大小写)且biz_mode等于1的对象. 如果过滤条件里是一个正则表达式,那么把属性转换为字符串,做字符串匹配; 如果过滤条件里不是正则表达式,那么直接比较值是否相等匹配.
Pagesize:分页大小，默认不分页，返回全部
nextMarker: 可选.填入上下页返回的第一个org.id, 默认不填表示从第1页开始返回.

DispatchTeam.GetDispatchTeamsCountG*(filter)
查询车队里面车辆的数目
Filter ： 过滤条件如{ name: /s/i, biz_mode: 1 }, 代表返回所有name包含s(不区分大小写)且biz_mode等于1的对象. 如果过滤条件里是一个正则表达式,那么把属性转换为字符串,做字符串匹配; 如果过滤条件里不是正则表达式,那么直接比较值是否相等匹配.
Return ： 车辆的数目

Dispatheam.prototype.Add*(staff , vehicle)
向小队中追加司机和车辆、
Staff:代表一名雇员
Vechicle： 车辆对象

DispatchTeam.prototype.deleteG*()
删除派遣小队

DispatchTeam.prototype.dispatchG*(tmDispatch,remark)
增加一个派遣小队
tmDispatch: 一个派遣小队对象
remark：备注信息

DispatchTeam.prototype.recoverG*(tmRecover)
回收派遣小队
TmRecover:派遣小队对象

Fence.js
fence.prototype*()
查询车队的围栏信息

Fence.prototype.saveG*(fences)
更新围栏信息（先删除，然后再插入）
Fences：围栏对象

Fence.markStatusG*(s4_id , fence_id , vechicle_id , is_out , exInfo)
记录车辆的越界信息
S4_id : 车队ID
Fence_id: 围栏ID
Vehicle_id:车辆ID
Is_out : 是否越界
exInfo : 越界信息


fence.isPointInpolygon*(point , polygon)
百度地图工具类
Point:点对象
Polygon: 多边形对象

Fence.pointGPS2BaiduG*(point)
转换GPS坐标为百度坐标系
Point:点对象

Fleet.js
### Fleet.prototype.StaDispatchTimesG*(fleetId,tm_start,tm_end,isChart);
Generator方法.统计车队中对车辆的出车次数
fleetId:车队的id.
tm_start:开始时间
tm_end:结束时间.
isChart:为true，查询前五条数据；为false,查询全部数据
return : 一个Fleet对象
```javascript
co(function*(){
    var fleetCtx = req.incar.fleet;
    var statisticsData  = yield fleetCtx.StaDispatchTimesG(199,"2015-04-01","2015-04-07",true);
    res.json({status:'ok',statisticsData:statisticsData});
}).catch(function(ex){
    res.status(500).send(ex.stack);
});
```

### Fleet.prototype.StaDispatchTimesCountG*(fleetId,tm_start,tm_end);
Generator方法.统计车队中对全部车辆的出车次数
fleetId:车队的id.
tm_start:开始时间
tm_end:结束时间.
return : 一个车辆全部出车次数
```javascript
co(function*(){
    var fleetCtx = req.incar.fleet;
    var statisticsData  = yield fleetCtx.StaDispatchTimesCountG(199,"2015-04-01","2015-04-07");
    res.json({status:'ok',statisticsData:statisticsData});
}).catch(function(ex){
    res.status(500).send(ex.stack);
});
```

### Fleet.prototype.StaStaffDispatchTimesG*(fleetId,tm_start,tm_end,isChart);
Generator方法.统计车队中对车辆的出车总次数
fleetId:车队的id.
tm_start:开始时间
tm_end:结束时间.
isChart:为true，查询前五条数据；为false,查询全部数据
return : 一个车辆出车总次数
```javascript
co(function*(){
    var fleetCtx = req.incar.fleet;
    var statisticsData  = yield fleetCtx.StaStaffDispatchTimesG(199,"2015-04-01","2015-04-07",true);
    res.json({status:'ok',statisticsData:statisticsData});
}).catch(function(ex){
    res.status(500).send(ex.stack);
});
```
Fleet.prototype.getAllTripG* (filter, pageSize, nextMarker)
Generator方法获取所有的行程
Filter ： 过滤条件如{ name: /s/i, biz_mode: 1 }, 代表返回所有name包含s(不区分大小写)且biz_mode等于1的对象. 如果过滤条件里是一个正则表达式,那么把属性转换为字符串,做字符串匹配; 如果过滤条件里不是正则表达式,那么直接比较值是否相等匹配.
Pagesize:分页大小，默认不分页，返回全部
nextMarker: 可选.填入上下页返回的第一个org.id, 默认不填表示从第1页开始返回.

Fleet.prototype.getDispatchTeamsG*(filter, pageSize , nextMark)
Generator 按时间顺序查询派遣小队，支持分页 
Return : 一个车队对象


Fleet.prototype.getAllTripCountG*(filter);
获取行程数据
Filter:过滤条件
Return 查询里程数

Fleet.prototype.StadispatchTimesG(fleetId , tm_start , tm_end , isChart)
统计出车次数
fleetedId 车队ID
tm_start 开始时间
tm_end 结束时间
isChart为true，查询前五条数据；为false,查询全部数据


FleetStaff.js
Fleetstaff.FormJson*(src)
由一个json对象创建一个fleetStaff对象；
Src: 一个包含了fleetStaff对象的json对象

FleetStaff.GetFleetStaffsByIdArrayG*(idArray)
通过id 的数组集合查询车队员工的对象
isArray：员工的ID集合

FleetVechicle.js
FleetVechicle.FromJson*(Src)
由一个json对象创建一个fleetVechicle对象
Src：一个包含fleetVechicle对象属性的json对象


fleetVechicle.fromIdG*(id)
通过id 构造一个fleetVechicle对象
Id ：车队车辆ID

FleetVechicle.GetFleetVechiclesByIdArrayG *(idArrays)
通过ID的集合查询fleetVechicle对象集合
idArray：id集合
return：fleetVechicle集合

StaffAnalysis.js
StaffAnalysis.getCountingData*(fleeted , staffed , tm_start , tm_end)
获取司机分析数据-----默认查询七天有效数据
fleeted:车队ID
staffed：司机ID
tm_start: 起始时间
tm_end: 结束时间

staffAnlysis.getDispatchCount*(fleetId , staffed , tm_start , tm_end)
获取司机的出车次数
fleeted: 车队ID
staffed: 司机ID
tm_start:起始时间
tm_end：结束时间


VechicleAnaLysis.js
VechicleAnalysis.getCountingData*(fleeted , vehicleId , tm_start ,tm_end)
获取车辆分析数据-----默认查询7天有效数据
FleetID： 车队ID
VehicleID： 车辆ID
tm_start:起始时间
tm_end: 结束时间

VechicleAnalysis.getDispatchCount*(fleetId , vechicleID , tm_start , tm_end)
查询车辆的出车次数
FleetId:车队ID
VechicleID：车辆ID
tm_start:开始时间
tm_end:结束时间


