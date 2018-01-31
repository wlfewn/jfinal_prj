1、项目package说明
config是JFinal的项目配置
controller是控制器
handler可以设置全局处理器，例如判断用户请求中是否直接请求
interceptor拦截器，例如后台admin的每项操作拦截判断admin是否有权限或者是否已经登录
model.base用于JFinal的Generator生成器自动生成数据库映射基础模型类
model用于继承基础模型类的dao操作
routes用于设置单独路由配置，例如大项目可单独设计后端路由AdminRoutes、前端路由FrontRoutes
service作为controller与model的层，处理业务逻辑
test用于项目测试
utils用于公共的工具类，例如apache poi的word在线生成打印工具
validate用于后端验证，比前端JS验证安全、靠谱 

2、webapp下static存放js资源

3、html中form表单，input name绑定与struts一样，例如,object.filed,详情参考form.html 

4、 JFinal Template Engine模板引擎使用查看 http://www.jfinal.com/doc/6-1

