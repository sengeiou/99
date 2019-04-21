$.validator.messages = {
    required: "此字段不可为空.",
    remote: "Please fix this field.",
    email: "请输入有效的email地址.",
    url: "请输入有效的URL.",
    date: "请输入有效的日期.",
    dateISO: "请输入有效的日期(ISO).",
    number: "请输入有效的数值.",
    digits: "只允许输入数字",
    equalTo: "请再次输入相同的值.",
    maxlength: $.validator.format("不应超过{0}个字符."),
    minlength: $.validator.format("至少需要{0}个字符."),
    rangelength: $.validator.format("请输入{0}到{1}字符之间的值."),
    range: $.validator.format("请输入{0}到{1}之间的值."),
    max: $.validator.format("请输入不大于{0}的值."),
    min: $.validator.format("请输入不小于{0}的值."),
    telno: "请输入有效的电话号码",
    creditcard: "请输入正确的信用卡卡号!"
};

$.validator.messages.maxLength = $.validator.messages.maxlength;
$.validator.messages.minLength = $.validator.messages.minlength;
$.validator.messages.integer = '请输入整数';

$.validator.methods.maxLength = $.validator.methods.maxlength;
$.validator.methods.minLength = $.validator.methods.minlength;
$.validator.methods.integer = function(value, element, param) {
    if (this.optional(element)) return true;
    value = $.trim(value);
    return Math.floor(value) == value;
};

$.validator.__attributeRules = $.validator.attributeRules;
$.validator.attributeRules = function() {
    var rules = $.validator.__attributeRules.apply(this, arguments);
    if (rules.maxLength && /-1|2147483647|524288/.test(rules.maxLength)) {
        delete rules.maxLength;
    }
    return rules;
};

$.validator.addMethod('telno', function(value, element) {
    return this.optional(element) || /^\d{11}$/.test(value);
}, '请输入有效的电话号码');

$.validator.addMethod("regex", function(value, element, param) {
	param = param.replace(/longshun/g, '\\');
	param = param.replace(/\//g, '\\\/');
	param = "/^" + param + "$/";
	var regex = eval(param);
	return this.optional(element) || (regex.test(value));
}, '非法的值');

$.validator.addMethod("dateLessThan", function(value, element, param) {
	if (value == '' || value == null) {
		return true;
	}
	var targetDate = $(('#' + param).replace(/\./g, '\\.')).val();
	if (targetDate == '' || targetDate == null) {
		return true;
	}
	return this.optional(element) || (value < targetDate);
}, '日期应该小于指定日期');

$.fn.__validate = $.fn.validate;
$.fn.validate = function () {
    var validator = $.data(this[0], 'validator');
    if (validator) {
        return validator;
    }
    validator = $.fn.__validate.apply(this, arguments);
    if (!window.disableRequiredStar) {
        validator.elements().each(function(n, e) {
            if ($(e).rules().required) {
                var $tdLabel = $(e).parent().prev('label');
                if (!$tdLabel[0]) {
                	$tdLabel = $(e).closest('div.layui-form-item').find('label:first');
                }
                if ($tdLabel.text().indexOf('*') == -1) {
                    $tdLabel.prepend('<span style="color: #FF0000">*</span>');
                }
            }
        });
    }
    return validator;
};

$.validator.setDefaults({
	errorElement: "span",
	errorClass: "error",
	highlight: function(element, errorClass, validClass) {
		$(element).addClass("layui-form-danger");
	},
	unhighlight: function(element, errorClass, validClass) {
		$(element).removeClass("layui-form-danger");
	},
	errorPlacement: function(error, element) {
		error.appendTo($(element).closest('div'));
	}
});
