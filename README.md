# QinRan
<p align="center">
	<a href="https://github.com/jinqidemiao/QinRan"><img src="logo.jpg" style="zoom:25%;"></a>
</p>
<p align="center">
	<a target="_blank" href="https://www.oracle.com/technetwork/java/javase/downloads/index.html">
		<img src="https://img.shields.io/badge/JDK-8+-green.svg" />
	</a>
    <a href="http://www.apache.org/licenses/LICENSE-2.0.html" target="_blank">
        <img src="https://img.shields.io/hexpm/l/plug.svg">
    </a>
    <a target="_blank" href="https://gitter.im/qin_ran/community?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge">
        <img src="https://badges.gitter.im/qin_ran/community.svg">
    </a>
</p>

## 简介
  **暂无介绍**
## 目标

### 超市预约

#### 1. 用户操作流程（用户首页-商品详情页-确认详情页）

- 预约商品链接界面 
- 点击进入商品界面 
- 显示商品详细信息（选择规格、数量）
- 确认预约
- 取货码（短信的方式发送或者 确认预约之后直接显示取货码）
- 群收款完成之后 【确认预约会显示为预约成功（请在三天内上门拿取货码取件）】
- 取件完成后 取件码作废 商品详情页订单显示交易结束   
#### 2. 商家后台操作流程（上架-商品详细信息-预约数量）
- 输入商品详细信息
- 规格设置 商品数量设置 价格设置  （用户最多加购几件设置）
#### 3. 确定界面
`关于取货码逻辑：`
- 确认预约之后显示取货码
- 用户获取取货码后台同步；
- 取货码在有效时间内只可使用一次；
- 取货成功之后 确认预约界面显示交易结束（商家后台操作或者是自动识别）；
#### 4. 我的
- 用户id
- 订单信息
