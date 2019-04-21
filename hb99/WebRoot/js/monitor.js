//给日历时间点击事件添加你写的后台请求方法请滑到173行之后，然后有不妥的地方望指教后我再修改
((win,doc)=>{
    var tabBtn = doc.querySelectorAll(".tabBar span"),
        tabItem = doc.querySelectorAll(".tabItem"),
        imgItem = doc.querySelectorAll(".imgWrap div"),
        deleteBtn = doc.querySelector(".delete"),
        selectTime = doc.querySelector(".time"),
        calendarEle = doc.querySelector(".calendar"),
        originalTop =0,
        top = 0,
        daysItem = null,
        firstDayWeek = 0,
        prevBtn=null,
        nextBtn = null,
        obj = {};
    
    //选项卡切换，显示对应的内容，隐藏其他的内容
    tabBtn.forEach((item,index)=>{
        item.onclick = ()=>{
            tabItem.forEach((item,index)=>{
                item.style.display = "none";
             
                tabBtn[index].className = ""
            })
            tabItem[index].style.display = "block";
            if(index==2){
                tabItem[index].style.display = "flex";
                
                if(daysItem){
                    daysItem.forEach((item,index)=>{
                        if(index>firstDayWeek-2){
                            item.onclick = null;
                            item.style.background = "none"
                        }
                    })
                }
                obj.toggleTime()   
            }
            if(index==1){
                
                calendarEle.className = calendarEle.className.replace("alert","")+" default";
                if(originalTop){
                    calendarEle.style.top = originalTop;
                    calendarEle.style.opacity = 0
                }
                
                if(daysItem){
                    daysItem.forEach((item,index)=>{
                        if(index>firstDayWeek-2){
                            item.onclick = null
                        }
                    })
                }
                obj.toggleVideo()
            }else{
                calendarEle.style.display  = "none";
                
                calendarEle.className = calendarEle.className.replace(" default","")
            }
            item.className = "active"
        }
    })
    //点击图片勾选
    imgItem.forEach(item=>{
        item.onclick = ()=>{
            if(item.className){
                item.className = ""
            } else{
                item.className = "active"
            }  
        }
    })

    //判断文档点击的时候不等于上一月和下一月的按钮就把日历狂隐藏
    doc.onclick = event=>{
        if((event.target!=prevBtn&&event.target!=nextBtn)&&win.getComputedStyle(calendarEle).opacity==1&&calendarEle.style.display=="block"){
         
            calendarEle.style.opacity = 0;
            setTimeout(()=>{
                calendarEle.style.display = "none";
                calendarEle.style.top = originalTop
            },300)
            
        }
   
    }
    //删除图片功能
    deleteBtn.onclick = ()=>{
        imgItem = doc.querySelectorAll(".imgWrap div");
        
        imgItem.forEach(item=>{
           if(item.className){
               item.parentNode.removeChild(item)
           }
        })
    }
    
    //点击时间然后弹出日历框，加动画效果
    selectTime.onclick = ()=>{
        if(calendarEle.className.indexOf("alert")==-1){
            calendarEle.className = calendarEle.className.replace(" default","")+ " alert";
        }
        top =doc.querySelector(".tabBar").offsetHeight + doc.querySelector(".head").offsetHeight;
        if(!originalTop){
            originalTop = win.getComputedStyle(calendarEle).top;
        }
        
        calendarEle.style.display ="block";
        
        if(win.getComputedStyle(calendarEle).display=="block"){
            calendarEle.style.opacity = 1;
            calendarEle.style.top = top +"px" 
        }
        
        // obj.toggleTime()
    }
    //日历返回的两个不同点击事件函数复制给对象
    obj = calendar();
  
    //日历
    function calendar(){
        var dateContainer = doc.querySelector(".calendar time");
        var daysContainer = doc.querySelector(".calendarBody .days");
        var date = new Date();
        var year = date.getFullYear();
        var month = date.getMonth()+1;
        var day = date.getDate();
        prevBtn = doc.querySelector(".prev");
        nextBtn = doc.querySelector(".next");
        
        selectTime.querySelector("time").innerHTML = `${year}-${month<10?"0"+month:month}-${day<10?"0"+day:day}`
        prevBtn.onclick = () =>{
            month--;
            if(month<1){
                month = 12;
                year--
            }
            render();
            obj.toggleTime()
        }
        nextBtn.onclick = ()=>{
            month++;
            if(month>12){
                month = 1;
                year++
            }
            render();
            obj.toggleTime()
        }

        //渲染日历函数
        render()
        function render(){
            var currentDays = new Date(year,month,0).getDate();
            firstDayWeek = new Date(year,month-1,1).getDay()==0?7:new Date(year,month-1,1).getDay();
            var currentWeek = new Date(year,month-1,day).getDay();
            

            dateContainer.innerHTML = `${year}/${month<10?"0"+month:month}`;
            daysContainer.innerHTML = ""
            for(var i=1;i<firstDayWeek;i++){
                daysContainer.innerHTML +="<div></div>" 
            }
            for(var i=0;i<currentDays;i++){
                daysContainer.innerHTML += `<div>${i+1}</div>`
            }
            if(month==new Date().getMonth()+1){
                daysContainer.children[day+firstDayWeek-2].className = "active"
            }
            
        }
    
        //点击日历天数切换视频列表函数
        function toggleVideo(){
            daysItem = doc.querySelectorAll(`.calendarBody .days div`);
            daysItem.forEach((item,index)=>{
                if(index>firstDayWeek-2){
                    //如果要点击日历获取视频数据然后重新渲染就请放在这个点击事件函数里面！
                    item.onclick = ()=>{
                        daysItem.forEach(item=>{
                            item.style.background = "none"
                        })
                        if(item.innerHTML ==day){
                            return 
                        }
                        item.style.background = "#eee"

                    }
                }
            })
        }

        //点击日历切换时间获取后台图片数据函数
        function toggleTime(){
            daysItem = doc.querySelectorAll(`.calendarBody .days div`);
            daysItem.forEach((item,index)=>{
                if(index>firstDayWeek-2){
                    //如果要切换时间获取数据重新渲染的页面的话请放在这个点击事件函数里面！
                    item.onclick = ()=>{
                        selectTime.querySelector("time").innerHTML = `${dateContainer.innerHTML.replace("/","-")}-${item.innerHTML<10?"0"+item.innerHTML:item.innerHTML}`
                        calendarEle.style.opacity = 0;
                        setTimeout(()=>{
                            calendarEle.style.display = "none";
                            calendarEle.style.top = originalTop
                        },300)

                    }
                }
            })
        }
    
        return {toggleTime,toggleVideo}

    }
})(window,document)