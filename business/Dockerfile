FROM eclipse-temurin:17-jre-alpine
LABEL authors="DELL"

# 设置时区为亚洲/上海
ENV TZ=Asia/Shanghai
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone
# 设置语言环境为中文
ENV LANG=zh_CN.UTF-8
ENV LANGUAGE=zh_CN:zh
ENV LC_ALL=zh_CN.UTF-8
# 将本地构建的 Java 应用 JAR 文件复制到容器中
ADD target/business-0.0.1-SNAPSHOT.jar app.jar
# 暴露容器的端口，这里是 80
EXPOSE 80


# 定义容器启动时执行的命令，运行 Java 应用
ENTRYPOINT ["java","-jar","app.jar"]