# data-validator-service
数据库配置数据约束检查服务。

将从定制的ad hoc系统开始，然后逐步的把hardcoding的语义，从代码中转移到协议消息里，接着，修改代码，从人类用户的交互中获取检查的约束规则，并记录下来；最后形成一个通用的、与具体业务无关的数据检查系统。

## 业务背景

以电商为背景的产品介绍、评论、及打分系统。 数据的录入可能并没有经过校验，所以会出现脏数据。检查服务就是要找到和处理这些脏数据。
* 管理员可以上下架产品
* 管理员可以对上架产品添加打折活动，打折活动开始后不允许修改，但可以关停。
* 用户可以对产品或者打折活动进行评论

## 数据结构
假设数据结构如下：

* Product
```
  id
  name
  prize
  description
  status
```

* Promotion
```
  id
  productId,  Product.id
  description
  status
  beginTime
  endTime
  discount
```

* Comment
```
  id
  targetId, Product.id || Promotion.id
  type,  "product"||"promotion"
  title
  comment
  author, User.id
```

* User
```
  id
  name
```


## 要检查的内容

* 某个Promotion指向的Product不存在。


# 测试

* create database vd_online_store

* run sql "data.sql"

* change db configuations in system.properties

* run service:

```bash
cd ${project-root-dir}
mvn clean package
mvn spring-boot:run
```

* access the following url via web browser or postman
http://localhost:8091/validator/abnormalPromotion
