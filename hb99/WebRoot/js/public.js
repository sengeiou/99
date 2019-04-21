$(document).ready(function() {
	// 处理表格内容只显示一部分时，点击显示完整内容
	$('td').on('click', function() {
		if (this.scrollWidth > this.clientWidth) {
			layer.open({
			  type: 4,
			  shadeClose: true,
			  content: [$(this).html(), this]
			});
		}
	});
	
	// 表格列宽可调整
	if ($.fn.colResizable) {
		$('table').colResizable({
			liveDrag: true
		});
	}
	
	// 将label中的标签设置到其父级div的title属性，解决某些label内容太长显示不完整的问题
	$(".layui-form-label").each(function () {  
	       $(this).closest('div').attr("title", $(this).text().replace(/：|:$/, ""));  
	});
	
	// 点击表格列头排序
	var $orderBy = $('#orderBy');
	if ($orderBy[0]) {
		var orderBy = $orderBy.val().split(' ');
		$('.sort-column').each(function() {
			$(this).html($(this).html() + ' <i class=\'fa fa-sort\'></i>');
			if ($(this).hasClass(orderBy[0])) {
				orderBy[1] = orderBy[1] && orderBy[1].toUpperCase() == 'DESC' ? 'down' : 'up';
				$(this).find('i').remove();
				$(this).html($(this).html() + ' <i class=\'fa fa-sort-' + orderBy[1] + '\'></i>');
			}
		});
		
		$('.sort-column').click(function() {
			var order = $(this).attr('class').split(' ');
			var sort = $('#orderBy').val().split(' ');
			for (var i = 0; i<order.length; i++) {
				if (order[i] == 'sort-column'){order = order[i+1]; break;}
			}
			if (order == sort[0]) {
				sort = (sort[1] && sort[1].toUpperCase() == 'DESC' ? 'ASC' : 'DESC');
				$('#orderBy').val(order + ' DESC' != order + ' ' + sort ? '' : order + ' ' + sort);
			} else {
				$('#orderBy').val(order + ' ASC');
			}
			$('.layui-form .search_btn').click();
		});
	}
});

function gotoUrl(url, extra_params) {
	var pos = url.indexOf('javascript:');
	if (pos != -1) {
		url = url.substr(pos + 'javascript:'.length);
		eval(url);
		return;
	} 
	pos = url.indexOf('_x_');
	if (pos != -1) {
		url = url.substr(0, pos - 1);
	}
	if (extra_params) {
		url = url + (url.indexOf('?') > -1 ? '&' : '?') + '_x_&' + extra_params;
	} 
	window.location = url;
}
