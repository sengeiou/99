/* 
 */

/* 基于List<IdText>的json数据填充下拉框
 * 参数：
 *   combo: select对象
 *   nullItem: 空项的显示值  
 */
function fillCombo(combo, url, data, nullItem, completeCallback) {
    $.getJSON(url, data, function(data) {
        combo = $(combo)[0];
        var baseIndex = 0;
        if (nullItem) {
            combo.options[baseIndex++] = new Option(nullItem, '');
        }
        combo.options.length = baseIndex;
        $.each(data.data, function(index, item) {
            combo.options[index + baseIndex] = new Option(item.text, item.id);
        });
        if(completeCallback) {
            completeCallback();
        }
    });
}

/* 基于List<IdText>的json数据填充checkbox
 * 参数：
 *   chbox: checkbox对象
 *   oldbox: 原来的区域ID串,用逗号
 */
function fillCheckbox(chbox,url,data,completeCallback) {
	$.getJSON(url, data, function(idTexts) {
		var html = chbox.html();
		for (var n = 0; n < idTexts.length; n++) {
			var idText = idTexts[n];
			var chkid = 'units' + (n + 1);
            if(chbox.find('input[value='+idText.id+']').length) {
               continue;
            }
			var chkhtml = '<span><input id="' + chkid + '" name="units" ' +
										'type="checkbox" value="' + idText.id + '"/>' +
										'<label for="' + chkid + '">' +
										idText.text + '</label></span>';			
			html += chkhtml;
		}
		chbox.html(html);
		//回调
		if(completeCallback) {
            completeCallback();
        }		
	});
}