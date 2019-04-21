//根据浏览器分辨率调整焦距，保证不同分辨率下大小布局文字到位
((doc)=>{
    var width = doc.documentElement.clientWidth;
    var height = doc.documentElement.clientHeight;
    var scale = width/1920>height/1080?width/1920:height/1080;
    var styleEle = doc.createElement("style");
    styleEle.type = "text/css";
    styleEle.innerHTML = `body{zoom:${scale}};`;
    doc.querySelector("head").appendChild(styleEle)
})(document)

    
     
