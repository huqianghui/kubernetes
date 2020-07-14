# kustomize更多的使用模版的简化书写yaml

在使用helm之前，可以优先考kustomize来复用模版，简化yaml资源文件定义。

1. Kustomize 和 Kubernetes 一样，它完全就是声明式的，想要什么，系统就提供给什么，不需要遵循命令方式来描述希望构建的对象。

2. 和 Docker 比较类似，有很多层组成，每个层都是修改以前的层。构建的最终结果由基础部分和在上面配置的其他层组成 。

3. 和 Git 一样，你可以使用一个远程的基础配置作为最原始的配置，然后在该基础上添加一些自定义的配置。

## 定制

创建一个新的文件夹k8s/overlays/dev
创建一个新的文件夹k8s/overlays/test
创建一个新的文件夹k8s/overlays/prod，其中包含一个名为kustomzization.yaml的文件

1. 替换任何属性设置
2. 替换环境变量值
3. 修改副本数量
下面两者稍微特殊，需要额外书写：
4. 通过命令行定义 secret
    secretGenerator:
    - literals:
    - db-password=12345
    name: sl-demo-app
    type: Opaque
5. 修改镜像
    images:
    - name: foo/bar
    newName: foo/bar
    newTag: 3.4.5

在上面的示例中，我们了解到了如何使用 Kustomize 的强大功能来定义你的 Kuberentes 资源清单文件，而不需要使用什么额外的模板系统，创建的所有的修改的块文件都将被应用到原始基础模板文件之上，而不用使用什么花括号之类的修改来更改它。
Kustomize 中还有很多其他高级用法，比如 mixins 和继承或者允许为每一个创建的对象定义一个名称、标签或者 namespace 等等，你可以在官方的 Kustomize GitHub 代码仓库中查看高级示例和文档。