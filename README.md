# 饿了么外卖平台微服务项目

## 项目简介
本项目为仿饿了么外卖平台的微服务架构实现，包含前端（Vue3）、后端（Spring Boot + Spring Cloud）、服务注册与网关、业务服务、用户、订单、购物车、商家、食品、地址等多个微服务模块，并支持Docker Compose一键部署。

## 目录结构
```
├── admin/                # 管理端服务
├── business/             # 商家服务
├── cart/                 # 购物车服务
├── common/               # 通用实体与工具
├── deliveryAddress/      # 配送地址服务
├── food/                 # 食品服务
├── gateway/              # 网关服务
├── orders/               # 订单服务
├── register/             # Eureka注册中心
├── user/                 # 用户服务
├── elm-front-master/     # 前端Vue3项目
├── compose.yml           # Docker Compose编排文件
├── pom.xml               # Maven聚合父工程
```

## 技术栈
- 前端：Vue3、Vite、Vue Router、Axios、Font Awesome
- 后端：Spring Boot 3.3、Spring Cloud、Spring Cloud Netflix Eureka、OpenFeign、Resilience4j、MyBatis
- 注册中心：Eureka（三节点高可用）
- API文档：Springdoc OpenAPI
- 构建工具：Maven
- 容器化：Docker、Docker Compose

## 服务说明
- **register**：Eureka注册中心，三节点高可用
- **gateway**：API网关，统一入口
- **user**：用户服务
- **orders**：订单服务
- **food**：食品服务
- **cart**：购物车服务
- **business**：商家服务
- **admin**：管理端服务（多实例）
- **deliveryAddress**：地址服务
- **common**：通用实体与工具
- **elm-front-master**：前端项目

## 快速开始

### 1. 前端启动
```bash
cd elm-front-master
npm install
npm run dev
```
访问：http://localhost:5173

### 2. 后端本地启动（开发模式）
各服务均为Spring Boot项目，推荐使用IDEA分别启动。启动顺序建议：
1. register（Eureka注册中心）
2. common（如有依赖）
3. 其他服务（user、orders、food、cart、business、admin、deliveryAddress、gateway）

### 3. Docker Compose一键部署
确保已安装Docker和Docker Compose。

1. 构建所有服务镜像（可用Maven打包+docker build，或在各模块下执行）：
   ```bash
   mvn clean package -DskipTests
   # 进入每个服务目录，构建镜像
   docker build -t 服务名:latest .
   # 例如：cd user && docker build -t user:latest .
   ```
2. 在项目根目录执行：
   ```bash
   docker compose up -d
   ```
3. 访问服务：
   - Eureka注册中心：http://localhost:9901、9902、9903
   - 网关：http://localhost:9906
   - 其他服务端口见compose.yml

### 4. 其他说明
- 前端页面截图见 `elm-front-master/前端页面截图/`
- API文档可通过各服务的 `/swagger-ui.html` 访问

## 联系方式
如有问题欢迎提issue或联系作者。 