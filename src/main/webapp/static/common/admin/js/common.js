/**
 * 通用js,直接扩展jquery
 * 需要使用bootbox 
 */

(function(jq){
	
	jq.extend({
		productEdit: function(url,marketState,id){
			//console.log('marketState',marketState);
			if(marketState && marketState === true){//已上架
				bootbox.alert("产品未下架，无法编辑");
			}else{
				//使用form表单post提交
				var $form=$(document.createElement('form')).css({display:'none'}).attr("method","post").attr("action",url);
				var $input=$(document.createElement('input')).attr('name','id').val(id);
				$form.append($input);
				$("body").append($form);
				$form.submit();
			}
		},
		productDelete: function(url,marketState,id){
			
		},
		/**
		 * 生成uuid
		 */
		uuidCreator: function(){
			function S4() {
		       return (((1+Math.random())*0x10000)|0).toString(16).substring(1);
		    }
			return S4()+S4()+S4()+S4()+S4()+S4()+S4()+S4();
		}
	});
	
})(jQuery)

//$(function(){
//	
//	var myAdmin = {
//		/**
//		 * 编辑产品
//		 */
//		productEdit: function(url,marketState,id){
//			if(marketState){//已上架
//				bootbox.alert("产品未下架，无法编辑");
//			}else{
//				window.location.href = url+"?id="+id;
//			}
//		},
//		/**
//		 * 删除产品
//		 */
//		productDelete: function(url,marketState,id){
//			if(marketState){//已上架
//				bootbox.alert("产品未下架，无法编辑");
//			}else{
//				bootbox.comfirm("确定删除该产品吗?",function(result){
//					if(result){
//						
//					}
//				})
//			}
//		}
//	}
//})