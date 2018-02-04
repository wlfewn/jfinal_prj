package com.jfinal.directive;

import javax.servlet.http.HttpSession;

import com.jfinal.template.Directive;
import com.jfinal.template.Env;
import com.jfinal.template.expr.ast.Expr;
import com.jfinal.template.expr.ast.ExprList;
import com.jfinal.template.io.Writer;
import com.jfinal.template.stat.Scope;

/**
 * 扩展指令，获取session内容后，移除session的数据
 * @author geng
 *2018年2月1日
 */
public class GetSessionDirective extends Directive{
	
	private Expr valueExpr;
	
	//获取指令 形参
	public void setExprList(ExprList exprList) {
	   this.valueExpr = exprList.getExprArray()[0];
	}
	
	//主要方法
	@Override
	public void exec(Env env, Scope scope, Writer writer) {
		HttpSession session = (HttpSession) scope.get("session");
		String sessionKey = (String) valueExpr.eval(scope);
		if (null != session && null != sessionKey ) {
			//指令参数名,相当于session key
			Object sessionObject = session.getAttribute(sessionKey);
			//输出到模板
			if( null != sessionObject ){
				//key名称使用放入session的key 名称
				//Object.class.getName() 返回全限定名
				//Object.class.getSimpleName() 返回声明的class 类名
				scope.setLocal(sessionKey,sessionObject);
				stat.exec(env, scope, writer);
				//移除session内容
				session.removeAttribute(sessionKey);
			}
		}
	}
	
	/**
	 * 需要有end结束符号，才能解析模板内容
	 */
	@Override
	public boolean hasEnd() {
		return true;
	}
}
