# springboot application对应kubernetes申明文件自动化生成

## 通过读取springboot的配置文件以及默认配置生成对应的yaml申明文件。

通过配置application property或者annotation在编译时生成yaml文件。随着工程的发展，
不仅仅是支持annotion，而且也支持属性配置所有叫做ap4k已经不合适了，改成了dekorate。

对于流行的框架像springBoot，quarkus，thornail只需添加依赖就可以自动生成。
对于一般的java工程，需要增加一个@Dekorate。

从最新的版本测试来看，推荐用application property来实现配置（Annotation less configuration）：
依次加载顺序：

1. Annotations

2. application.properties

3. application.yaml

4. application.yml

5. application-kubernetes.properties

6. application-kubernetes.yaml

7. application-kubernetes.yml

属性配置样例：

dekorate.docker.group=testme

dekorate.jvm.server=true

dekorate.jvm.xmx=1024

dekorate.jvm.prefer-ipv4-stack=true

dekorate.jvm.gc=GarbageCollector.SerialGC

dekorate.kubernetes.pvc-volumes[0].volume-name=mysql-volume

dekorate.kubernetes.pvc-volumes[0].claim-name=mysql-pvc

dekorate.kubernetes.mounts[0].name=mysql-volume

dekorate.kubernetes.mounts[0].path=/var/lib/mysql

dekorate.kubernetes.env-vars[0].name=key1

dekorate.kubernetes.env-vars[0].value=key1

dekorate.kubernetes.env-vars[0].secret=my-config

dekorate.kubernetes.init-containers[0].image=foo/bar:latest

dekorate.kubernetes.init-containers[0].command=foo

dekorate.kubernetes.sidecars[0].image=jaegertracing/jaeger-agent

dekorate.kuberentes.args=--collector.host-port=jaeger-collector.jaeger-infra.svc:14267

********* 与docker build集成 ***********

1. 在目录下有Dockerfile,通过传入参数实现镜像的生成
    ''' mvn clean install -Ddekorate.build=true '''

2. 使用jib，没有Dockerfile，增加依赖

```xml
    <dependency>
    <groupId>io.dekorate</groupId>
    <artifactId>jib-annotations</artifactId>
    </dependency>

```

然后通过命令
 ''' mvn clean install -Ddekorate.build=true '''
