function MyImageUpload() {
	this.init = function(param) {
		var config = {
			containerId: 'container',
			fileInputName: 'file',
			acceptTypes: 'image/png,image/jpeg,image/jpg,image/gif',
			maxFileCount: 6,
			singleFileSize: 2 * 1024 * 1024,
			reviewInit: [],
			homeUrl: '',
			canDelete: false,
			canAdd: true,
			canAddFileCount: 6
		};
		$.extend(config, param);
		config.canAddFileCount = config.maxFileCount - config.reviewInit.length;
		MyImageUpload.instanceCount++;
		var instanceIndex = MyImageUpload.instanceCount;
		var $queue = null;
		var $appendImgBtn = null;
		var $innerFile = null;
		var $container = $('#' + config.containerId);
		if ($container == undefined || $container == null) {
			return false;
		}
		
		$('<div class="uploader">' +
				'<div class="queueList">' +
					'<ul class="filelist" id="filelist' + instanceIndex + '"></ul>' +
				'</div>' +
				'<div class="statusBar">' +
					'<div class="info"></div>' +
					'<div class="btns">' +
						'<input type="file" name="' + config.fileInputName + '" id="innerFile' + instanceIndex + '" multiple="multiple" accept="' + config.acceptTypes + '" style="display: none;" />' +
						(config.canAdd && config.canAddFileCount > 0 ? ('<a href="javascript:void(0);" id="appendImg' + instanceIndex + '">添加图片</a>') : '') +
					'</div>' +
				'</div>' +
		  '</div>').replaceAll($container);
		
		$queue = $("#filelist" + instanceIndex);
		$innerFile = $("#innerFile" + instanceIndex);
		
		$appendImgBtn = $("#appendImg" + instanceIndex);
		$appendImgBtn.on("click", function() {
			$innerFile.trigger("click");
		});
		
		$innerFile.on("change", function() {
			var fileList = this.files;
			// 解决chrome 56 第二次打开文件选择器，然后点击取消，依然会触发change事件的问题
			if (fileList.length === 0) {
				return false;
			}
			
			$queue.find('li:not(.review)').remove();
			if (fileList.length > config.canAddFileCount) {
				alert('数量超出限制，请重新选择');
				this.value = "";
				return false;
			}
			for (var i = 0; i < fileList.length; i++) {
				if (!fileList[i].type.match(/^image/)) {
					alert(fileList[i].name + '是非法图片文件，请重新选择');
					this.value = "";
					return false;
				}
				addFile(fileList[i]);
			}
		});
		
		// 是否有图片需要回显
		for (var i = 0; i < config.reviewInit.length; i++) {
			var reviewUrl = config.reviewInit[i];
			if (reviewUrl == '' || reviewUrl == null) {
				continue;
			}
			addReviewImage(reviewUrl);
		}

		function addFile(file) {
			var $li = $('<li>' +
							'<p class="imgWrap"></p>' +
						'</li>');
			var $wrap = $li.find('p.imgWrap');
			var $info = $('<p class="error"></p>');

			var reader = new FileReader();
			reader.readAsDataURL(file);  
			reader.onload = function(e) {
				var img = $('<a href="' + this.result + '" data-lightbox="example"><img src="' + this.result + '" style="width: 100px;"></a>');
				$wrap.append(img);
			}
			$li.appendTo($queue);
		}

		function addReviewImage(url) {
			var $li = $('<li class="review">' +
					'<p class="imgWrap"></p>' +
				'</li>');
			var $wrap = $li.find('p.imgWrap');
			var img = $('<a href="' + url + '" data-lightbox="example"><img src="' + url + '?x-oss-process=image/resize,w_100" style="width: 100px;"></a>');
			$wrap.append(img);
			if (config.canDelete) {
				var $btns = $('<div class="file-panel">' +
						'<span class="cancel">删除</span>' +
						'</div>').appendTo($li);
				$li.on('mouseenter', function() {
					$btns.stop().animate({height: 30});
				});
				$li.on('mouseleave', function() {
					$btns.stop().animate({height: 0});
				});
				$btns.on('click', 'span', function() {
					var $li = $(this).closest('li.review');
					layer.confirm('确认要删除图片吗？', function(index) {
						var url = $li.find('a[data-lightbox="example"]').attr('href');
						$.ajax({
							type: 'POST',
							url: config.homeUrl + '/common/functions.json?fn=deleteFile',
							data: {url: url},
							async: false,
							success: function(data) {
								if (data.code == 200) {
									$li.remove();
									config.canAddFileCount++;
									if (config.canAdd && config.canAddFileCount > 0 && !$appendImgBtn[0]) {
										$('.btns').append('<a href="javascript:void(0);" id="appendImg' + instanceIndex + '">添加图片</a>');
										$appendImgBtn = $("#appendImg" + instanceIndex);
										$appendImgBtn.on("click", function() {
											$innerFile.trigger("click");
										});
									}
								} else {
									layer.msg('操作失败\n' + data.msg, {icon: 5, time: 2000});
								}
							}
						});
						layer.close(index);
					});
				});
			}
			$li.appendTo($queue);
		}
	}
}

MyImageUpload.instanceCount = 0;