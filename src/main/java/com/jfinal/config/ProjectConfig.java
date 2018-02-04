package com.jfinal.config;

import java.util.List;


import com.jfinal.common.Constant;
import com.jfinal.controller.FileController;
import com.jfinal.controller.ImageCodeController;
import com.jfinal.core.JFinal;
import com.jfinal.directive.GetSessionDirective;
import com.jfinal.ext.interceptor.SessionInViewInterceptor;
import com.jfinal.handler.BasePathHandler;
import com.jfinal.interceptor.ExceptionInterceptor;
import com.jfinal.interceptor.LoginInterceptor;
import com.jfinal.json.JacksonFactory;
import com.jfinal.kit.PropKit;
import com.jfinal.kit.StrKit;
import com.jfinal.model._MappingKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.tx.TxByMethods;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.plugin.ehcache.CacheKit;
import com.jfinal.plugin.ehcache.EhCachePlugin;
import com.jfinal.routes.admin.AdminRoutes;
import com.jfinal.routes.member.MemberRoutes;
import com.jfinal.template.Engine;
import com.jfinal.utils.Config;
import com.jfinal.utils.MenuUtil;
import com.jfinal.weixin.sdk.api.ApiConfigKit;
import com.jfinal.wx.controller.WeixinApiController;
import com.jfinal.wx.controller.WeixinMsgController;
import com.jfinal.wx.controller.WeixinPayController;

/**
 * jfinal全局配置
 */
public class ProjectConfig extends JFinalConfig{

	/**
	 * 运行此 main 方法可以启动项目，此main方法可以放置在任意的Class类定义中，不一定要放于此
	 * 
	 * 使用本方法启动过第一次以后，会在开发工具的 debug、run config 中自动生成
	 * 一条启动配置，可对该自动生成的配置再添加额外的配置项，例如 VM argument 可配置为：
	 * -XX:PermSize=64M -XX:MaxPermSize=256M
	 */
	public static void main(String[] args) {
		/**
		 * 特别注意：Eclipse 之下建议的启动方式
		 */
		JFinal.start("src/main/webapp", 8080, "/", 5);
	}
	
	@Override
	public void configConstant(Constants me) {
		// 加载少量必要配置，随后可用PropKit.get(...)获取值
		PropKit.use("a_little_config.txt");
		//me.setViewType(ViewType.FREE_MARKER);
		me.setDevMode(PropKit.getBoolean("devMode", false));
		me.setEncoding("utf-8");
		//指定json处理工具
		me.setJsonFactory(new JacksonFactory());
		//异常展示视图
		//me.setError401View(Config.getStr("PAGES.401"));
		//me.setError403View(Config.getStr("PAGES.403"));
		me.setError404View(Config.getStr("PAGES.404"));
		me.setError500View(Config.getStr("PAGES.500"));
		//微信部分配置
		// ApiConfigKit 设为开发模式可以在开发阶段输出请求交互的 xml 与 json 数据
		//ApiConfigKit.setDevMode(me.getDevMode());
		
	}

	/**
	 * 配置路由,两种配置方法
	 * (1)直接在这里配置
	 * (2)继承com.jfinal.config.Routes,然后在这里配置
	 */
	@Override
	public void configRoute(Routes me) {
		//第三个参数-->视图位置,省略时默认与第一个参数值相同
		me.add(new AdminRoutes());
		me.add(new MemberRoutes());
		//验证码请求
		me.add("/imageCode", ImageCodeController.class);
		me.add("/file",FileController.class);//文件上传
		//微信部分
		//me.add("/msg", WeixinMsgController.class);
		//me.add("/api", WeixinApiController.class, "/api");
		//me.add("/pay", WeixinPayController.class);
	}
	
	/**
	 * 模板学习参考 http://www.jfinal.com/doc/6-2
	 */
	@Override
	public void configEngine(Engine me) {
		// devMode 配置为 true，将支持模板实时热加载
		me.setDevMode(true);
		//加入扩展的指令,要在标签引用之前加入
		me.addDirective("getSession", GetSessionDirective.class);
		//如果模板中通过 #define 指令定义了 template function，并且希望这些 template function 可以在其它模板中直接调用的话，可以进行如下配置：
		// 添加共享函数，随后可在任意地方调用这些共享函数
		me.addSharedFunction("/pages/admin/common/_paginate.html");
		me.addSharedFunction("/pages/admin/common/message.html");
		
		//加入addSharedMethod后可以在模板其他地方直接调用方法名
		me.addSharedMethod(MenuUtil.class);
		me.addSharedMethod(StrKit.class);//框架自带工具类
		
		//下面方法已过时
		//me.addDirective("getSession",new GetSessionDirective());
	}

	/**
	 * 数据库连接插件配置
	 */
	@Override
	public void configPlugin(Plugins me) {
		// 配置 druid 数据库连接池插件
		DruidPlugin druidPlugin = new DruidPlugin(PropKit.get("jdbcUrl"), 
				PropKit.get("user"), 
				PropKit.get("password").trim(),
				PropKit.get("driverClass").trim());
		//启动插件,不启动会出错
		druidPlugin.start();
		// 配置ActiveRecord插件
		ActiveRecordPlugin arp = new ActiveRecordPlugin(druidPlugin);
		//显示sql语句
		arp.setShowSql(true);
		
		// 所有映射在 MappingKit 中自动化搞定
		_MappingKit.mapping(arp);
		me.add(arp);
		
		//启用缓存插件,插件使用参考http://www.jfinal.com/doc/7-3
		me.add(new EhCachePlugin());
	}

	/**
	 * 配置全局拦截器
	 */
	@Override
	public void configInterceptor(Interceptors me) {
		// 异常拦截器，跳转到500页面
		me.add(new ExceptionInterceptor());
		//开启通用事务，也可以在方法前面添加@Before(Tx.class)声明
		me.add(new TxByMethods("save", "update","delete"));
		me.add(new LoginInterceptor());
		//用于获取session数据
		me.add(new SessionInViewInterceptor());
		
	}
	
	/**
	 * 系统启动完成后，
	 */
	@Override
	public void afterJFinalStart() {
		super.afterJFinalStart();
		
		List<Menu> menuList = Constant.createMenus();
		
		//菜单放入缓存
		CacheKit.put("menuList", "menuList", menuList);
	}
	
	/**
	 * 配置全局处理器
	 */
	@Override
	public void configHandler(Handlers me) {
		me.add(new BasePathHandler(Config.getStr("PATH.BASE_PATH")));
		
	}

	public static DruidPlugin createDruidPlugin() {
		return new DruidPlugin(PropKit.get("jdbcUrl"), 
				PropKit.get("user"), 
				PropKit.get("password").trim());
	}
}
