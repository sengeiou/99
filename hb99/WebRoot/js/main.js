((win,doc)=>{
    var button = doc.querySelector(".oprationBtn button");
    var items = doc.querySelectorAll(".leftContent ul li");
    var imgs = doc.querySelectorAll(".imgContainer div img:first-child");
    var areaEle = doc.querySelector(".rightContent");
    var closeBtns = doc.querySelectorAll(".imgContainer div img:last-child");
    var coordinateX = 0;
    var coordinateY = 0;
    var isDisabled = true;  

    //禁用浏览器默认拖拽
    doc.ondragstart = ()=> false

    //给右下角操作按钮添加事件切换对应作用
    button.onclick = function(){
        isDisabled = true;
        if(this.innerHTML=="操作"){
            imgs.forEach((item,index)=>{
                if(item.style.display == "block"){
                    closeBtns[index].style.display = "block";
                    item.style.cursor = "move";
                    item.style.border = "1px solid #CFCFCF";
                    isDisabled = false
                }
            })
            if(isDisabled){
                return
            }
            this.innerHTML = "保存";
            this.className = "active";
        }else{
            closeBtns.forEach(item=>{
                item.style.display = "none";
              
            })
            imgs.forEach(item=>{
                item.style.cursor = "default";
                item.style.border = "none";
        
            })
            this.innerHTML = "操作";
            this.className = ""
        }
    }

    //关闭按钮的操作
    closeBtns.forEach((item,index)=>{
        item.onclick = ()=>{
            
            item.style.display = "none";
            imgs[index].style.display = "none"
            isExistImg()

        }
    })

    //判断地图内是否还存在设备图片，没有则把按钮设成禁用，按钮的指针设成禁用指针，还要判断一下是否存在有可操作的图片没有就恢复成默认样式
    function isExistImg(){
        isDisabled = true;
        imgs.forEach(item=>{
            if(item.style.display=="block"){
                isDisabled = false
            }
        })
        if(isDisabled){
            button.innerHTML = "操作"
            button.style.cursor = "not-allowed"
            button.className = ""
        }
        isDisabled = true
        closeBtns.forEach(item=>{
            if(item.style.display=="block"){
                isDisabled = false
            }
        })
        if(isDisabled){
            button.className = "";
            button.innerHTML = "操作"
        }
    }
   
    //鼠标移入元素时判断左边区域的设备图片是否还存在，可拖拽就把指针变成可移动的指针
    doc.onmouseover = event=>{
    
        items.forEach((item,index)=>{
            item.style.cursor = "default"
            if(event.target==item||event.target.parentNode==item){
                if(imgs[index]&&(imgs[index].style.display==""||imgs[index].style.display=="none")){
                    item.style.cursor = "move"
                
                }
            }
            
        })
   
    }

    //给整个页面添加鼠标按下事件，判断鼠标点的位置的元素的索引对应的图片显示出来再通过计算移动位置
    doc.onmousedown = event=>{
        isDisabled = false
        imgs.forEach((item,index)=>{
            if(event.target==item&&closeBtns[index].style.display=="none"){
                
               isDisabled = true
            }
        })
        if(isDisabled){
            return
        }
        if(event.button==0){
          
            items.forEach((item,index)=>{
                if(event.target==items[index]||event.target.parentNode==items[index]){
                    if(imgs[index]){
                        if(imgs[index].style.display=="block"){
                            return
                        }
                    }
                }

                //判断点击目标元素是否为左边区域的设备名称以及右边区域的设备图片
                if(event.target==item||event.target.parentNode==item||event.target==imgs[index]){
                    
                    if(imgs[index]){
                        coordinateX = event.clientX-win.parseInt(win.getComputedStyle(imgs[index]).width)/2;
                        coordinateY = event.clientY-win.parseInt(win.getComputedStyle(imgs[index]).height)/2;
                        imgs[index].style.cssText = `display:block;left:${coordinateX}px;top:${coordinateY}px`;
                        closeBtns[index].style.left = `${coordinateX+imgs[index].offsetWidth-closeBtns[index].offsetWidth/2}px`
                        closeBtns[index].style.top = `${coordinateY-closeBtns[index].offsetWidth/2}px`
                        // closeBtns[index].style.display = "none"
                        event.preventDefault()

                        //整个页面内移动时不能超过页面的边界
                        doc.onmousemove = event =>{
                            coordinateX = event.clientX-win.parseInt(win.getComputedStyle(imgs[index]).width)/2;
                            coordinateY = event.clientY-win.parseInt(win.getComputedStyle(imgs[index]).height)/2;
                            if(coordinateX<=0){
                                coordinateX = 0
                            }
                            if(coordinateY<=0){
                                coordinateY = 0
                            }
                            
                            if(coordinateX>=doc.documentElement.clientWidth-imgs[index].offsetWidth){
                                coordinateX = doc.documentElement.clientWidth - imgs[index].offsetWidth
                               
                            }
                            if(coordinateY>=doc.documentElement.clientHeight-imgs[index].offsetHeight){
                                coordinateY = doc.documentElement.clientHeight - imgs[index].offsetHeight
                               
                            }
                            closeBtns[index].style.left = `${coordinateX+imgs[index].offsetWidth-closeBtns[index].offsetWidth/2}px`
                            closeBtns[index].style.top = `${coordinateY-closeBtns[index].offsetWidth/2}px`
                            imgs[index].style.cssText = `display:block;left:${coordinateX}px;top:${coordinateY}px`;
                            
                        }

                        //鼠标松开时要判断图标是不是没移到范围内，没移到范围内就要隐藏
                        doc.onmouseup = event =>{
                            doc.onmousemove = null;
                            doc.onmouseup = null;
                            if(imgs[index].offsetLeft<areaEle.offsetLeft||imgs[index].offsetTop<areaEle.offsetTop||imgs[index].offsetLeft+imgs[index].offsetWidth>areaEle.offsetLeft+areaEle.offsetWidth||imgs[index].offsetTop+imgs[index].offsetHeight>areaEle.offsetTop+areaEle.offsetHeight){
                                 imgs[index].style.display = "none"
                                 closeBtns[index].style.display = "none"
                                 isExistImg();
                               
                            }else{
                                button.innerHTML = "保存"
                                button.style.cursor = "pointer"
                                button.className = "active"
                                closeBtns[index].style.cssText = `display:block;left:${event.clientX+imgs[index].offsetWidth/2-win.parseInt(win.getComputedStyle(closeBtns[index]).width)/2}px;top:${event.clientY-imgs[index].offsetHeight/2-win.parseInt(win.getComputedStyle(closeBtns[index]).width)/2}px`
                           
                               
                            }
                            
                        }
                    }
                  
                }
            })
        }
        
    }


})(window,document)