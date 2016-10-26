# 轨迹回放

## 包名：@incar/track-replay
轨迹回放。

 

## 轨迹回放 ` trackReplayAPI.js`

* ` TrackReplayAPI.prototype.getOrgLicense`  获取组织下所有的车牌号
* `TrackReplayAPI.prototype.getTracks` 获取组织下面一辆车某段时间内的轨迹记录
* `TrackReplayAPI.getOrgLicense`  获取组织下面的车牌号



# 行车轨迹

## 1. 创建新轨迹记录
当车辆在行驶中时, 对车辆行驶轨迹进行记录。

### URL
`POST /api/mobile/track`

### 返回数据
1. 请求成功

    ```
    {
        "status": 'success',
        "trackId": 1 /*本次轨迹记录编号*/
    }
    ```

2. 没有车辆行驶数据

    ```
    {
        "status": "failed",
        "errorNo": "ILLEGAL_REQUEST",
        "error": "没有车辆行驶数据。 请确定车辆正在行驶中, 车载设备收发数据正常。"
    }
    ```

## 2. 刷新轨迹数据
获取到轨迹记录编号后, 可以每分钟向服务器请求一次轨迹数据.每次刷新轨迹数据(refresh参数为true),轨迹终止时间自动刷新为请求时时间。
当refresh参数没有指定或不为true时,不更新轨迹终止时间。
当记录轨迹的行程已经结束时,refresh为true也不会更新轨迹终止时间。

### URL
`GET /api/mobile/track/{id}`

### 请求参数

| 参数名称 | 说明 | 是否必须 | 举例 |
| -------- | ---- | -------- | ---- |
| id | URL模板参数, 轨迹记录ID | 是 | 1 |
| refresh | 是否刷新轨迹截止时间 | 否 | true |
| start | 为了减少每次请求返回数据包大小, 可以加上一个起始时间点 | 否 | 2016-06-01 12:02:23 |

### 返回数据
1. 请求成功

    ```
    {
        "status": "success",
        "record": {  /* 轨迹记录对象 */
            "id": 1,
            "obd_code": "incar-obd-12345",
            "trip_id": 1047,
            "start_time": "2016-05-01 12:00:00", /* 轨迹记录开始时间 */
            "end_time": "2016-06-01 17:54:56", /* 轨迹记录结束时间 */
            "start_mileage": 0, /* 轨迹记录开始时里程数,单位米 */
            "end_mileage": 0,  /* 轨迹记录结束时里程数,单位米 */
            "trip_ended": 1 /* 轨迹记录是否持续到了行程结束。 在用户没有手动停止刷新轨迹情况下, 行程结束时, 
                               此字段会自动从0变为1, 手机端可以据此停止刷新轨迹 */
        },
        "locations": [ /* 轨迹定位点数组 */
            {
                "id": 791226,
                "obdCode": "incar-obd-12345",
                "tripId": 1047,
                "vid": null,
                "vin": null,
                "locationSpeed": null,
                "travelDistance": null,
                "longitude": "E104.620008",
                "latitude": "N31.463333",
                "direction": null,
                "locationTime": "2016-05-01 04:00:05",
                "locationType": null,
                "recordTime": "0000-00-00 00:00:00"
            },
            {
                "id": 791227,
                "obdCode": "incar-obd-12345",
                "tripId": 1047,
                "vid": null,
                "vin": null,
                "locationSpeed": null,
                "travelDistance": null,
                "longitude": "E104.620008",
                "latitude": "N31.463333",
                "direction": null,
                "locationTime": "2016-05-01 04:00:05",
                "locationType": null,
                "recordTime": "0000-00-00 00:00:00"
            }
        ],
        "count": 2 /* 轨迹记录包含的定位点数组长度 */
    }
    ```

2. 没有指定的轨迹信息

    ```
    {
        "status": "failed",
        "errorNo": "NO_RECORD",
        "error": "没有指定的轨迹信息"
    }
    ```

## 3. 获取轨迹记录列表
### URL
`GET /api/mobile/track`

### 请求参数

| 参数名称 | 说明 | 是否必须 | 举例 |
| -------- | ---- | -------- | ---- |
| pageSize | 每页条数, 默认20 | 否 | 20 |
| nextMarker | 起始条目序号, 默认1. 假定请求第N页数据, 则 `nextMarker = ((N-1)*pageSize)+1` | 否 | 1 |

### 返回数据

```
{
    "status": "success",
    "total": 2,
    "tracks": [
        {
            "id": 2,
            "obd_code": "incar-obd-12345",
            "trip_id": 1047,
            "start_time": "2016-06-01 18:28:46",
            "end_time": "2016-06-01 18:37:17",
            "start_mileage": 0,
            "end_mileage": 0,
            "trip_ended": 0,
            "rowNo": 8
        },
        {
            "id": 1,
            "obd_code": "incar-obd-12345",
            "trip_id": 1047,
            "start_time": "2016-05-01 12:00:00",
            "end_time": "2016-05-01 12:15:00",
            "start_mileage": 0,
            "end_mileage": 0,
            "trip_ended": 1,
            "rowNo": 9
        }
    ],
    "count": 2
}
```

## 4. 添加行程
为了在开发期间方便添加行车数据, 特开放了 _添加行程_ 和 _添加定位_ 两个接口.

### URL
`POST /api/mobile/trip/`

### 请求参数

| 参数名称 | 说明 | 是否必须 | 举例 |
| -------- | ---- | -------- | ---- |
| fireTime | 点火时间 | 是 | 2016-06-01 12:25:03 |
| flameOutTime | 熄火时间 | 是 | 2016-06-01 12:31:12 |

### 返回数据

```
{
    "status": "success",
    "tripId": 1 /* 行程编号 */
}
```

## 5. 添加定位

### URL
`POST /api/mobile/location`

### 请求参数

| 参数名称 | 说明 | 是否必须 | 举例 |
| -------- | ---- | -------- | ---- |
| tripId | 行程编号 | 是 | 1 |
| locationTime | 定位时间 | 是 | 2016-06-01 12:25:03 |
| longitude | 经度 | 是 | E104.620008 |
| latitude | 纬度 | 是 | N31.463333 |

### 返回数据

```
{
    "status": "success",
    "location": {
        "id": 791227,
        "obdCode": "incar-obd-12345",
        "tripId": 1047,
        "vid": null,
        "vin": null,
        "locationSpeed": null,
        "travelDistance": null,
        "longitude": "E104.620008",
        "latitude": "N31.463333",
        "direction": null,
        "locationTime": "2016-05-01 04:00:05",
        "locationType": null,
        "recordTime": "0000-00-00 00:00:00"
    }
}
```
 
