window.onload = function() {
	let carouselObj = new Carousel();
	carouselObj.init();
	carouselObj.sizeAuto();
	setTimeout(()=>{
		carouselObj.toggle();
	},1000)
	
}

function Carousel() {
	this.carouselWrap = document.querySelector(".carouselWrap");
	this.imgs = this.carouselWrap.querySelector(".moveBox ul");
	this.items = this.imgs.querySelectorAll("li");
	this.moveCircle = this.carouselWrap.querySelector(".circle .moveCircle");
	this.circleItem = this.carouselWrap.querySelectorAll(".circle a:nth-of-type(n+2)");
	this.timer = null;
	this.circleIndex = 0;
	this.moveWidth = 0;
}
Carousel.prototype = {
	constructor: Carousel,
	init: function() {
		let obj = this;

		obj.carouselWrap.onmouseover = function() {
			clearTimeout(obj.timer)

		}
		obj.carouselWrap.onmouseout = function() {
			clearTimeout(obj.timer)
			obj.timer = setTimeout(function() {
				obj.scroll(1);
			}, 3000);

		}
		obj.timer = setTimeout(function() {
			obj.scroll(1);
		}, 3000);
	},
	sizeAuto: function() {
		let obj = this;
		obj.imgs.insertBefore((obj.items[obj.items.length-1]).cloneNode(true),obj.items[0]);
		obj.items = obj.imgs.querySelectorAll("li");
		obj.imgs.style.width = obj.carouselWrap.offsetWidth * obj.items.length + "px";
		obj.moveCircle.style.width = obj.moveCircle.parentNode.offsetWidth/obj.circleItem.length +"px";
		obj.items.forEach((value, index) => {
			obj.items[index].firstElementChild.style.width = obj.carouselWrap.offsetWidth + "px"
		})
		obj.imgs.style.left = -obj.carouselWrap.offsetWidth + "px";
		window.onresize = function() {
			obj.moveCircle.style.width = obj.moveCircle.parentNode.offsetWidth/obj.circleItem.length +"px";
	
			obj.imgs.style.width = obj.carouselWrap.offsetWidth * obj.items.length + "px";	
			obj.imgs.style.transition = "none";
			obj.items.forEach((value, index) => {
				obj.items[index].firstElementChild.style.width = obj.carouselWrap.offsetWidth + "px"
			})
			obj.imgs.style.left = -obj.carouselWrap.offsetWidth + "px";
		}
	},
	scroll: function(num) {
		let obj = this;
		if((-obj.imgs.offsetLeft == obj.carouselWrap.offsetWidth * (obj.items.length - 1)) && obj.timer) {

			obj.imgs.style.transition = "none";
			obj.imgs.style.left = 0;
			obj.scroll(1);
			return

		} else {
			if((obj.circleIndex == obj.circleItem.length - 1) && obj.timer) {
				obj.moveCircle.style.transition = "none";
				obj.moveCircle.style.transform = "scale(0) rotate(360deg)"
				obj.moveCircle.style.left =0;
				setTimeout(function() {
					obj.moveCircle.style.transition = "1s linear"
						obj.moveCircle.style.transform = "scale(1) rotate(0)"
				}, 0)
				obj.circleIndex = 0
			} else {

				// obj.moveCircle.style.left = obj.moveCircle.offsetLeft + obj.circleItem[1].offsetLeft * num + "px";
				obj.moveCircle.style.left = obj.moveCircle.offsetLeft + obj.moveCircle.offsetWidth*num + "px"
				obj.circleIndex++
			}

		}
		obj.imgs.style.transition = "1s linear";
		obj.imgs.style.left = obj.imgs.offsetLeft - obj.carouselWrap.offsetWidth * num + "px";
		obj.timer = setTimeout(function() {
			obj.scroll(1);
		}, 2000)

	},
	toggle: function() {

		let obj = this;
		obj.circleItem.forEach((item, index) => {

			item.onclick = function() {
				if(obj.imgs.offsetLeft % obj.carouselWrap.offsetWidth == 0) {

					obj.timer = null;
					obj.scroll(index - obj.circleIndex);
					clearTimeout(obj.timer);
					obj.circleIndex = index;

				}
			}
		})
	}
}