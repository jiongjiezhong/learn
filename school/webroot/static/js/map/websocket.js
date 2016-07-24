
var websocket;
var isCreatw = false;
var sessionId=Math.random();
var win;
var input;
var isQj = true;
var toUser="";

initWebSocket();

//初始话WebSocket
function initWebSocket() {
	if (window.WebSocket) {
		websocket = new WebSocket(encodeURI("ws://127.0.0.1:8887"));
		websocket.onopen = function() {
			//连接成功
			console.log(sessionId + '(已连接)   【现在全局对话】');
			websocket.send('LOGIN'+sessionId);
		}
		websocket.onerror = function() {
			//连接失败
			console.log(title + '&nbsp;&nbsp;(连接发生错误)');
		}
		websocket.onclose = function() {
			//连接断开
			console.log(sessionId + '(已经断开连接)');
		}
		//消息接收
		websocket.onmessage = function(message) {
			var message = JSON.parse(message.data);
//			console.log(message.data);
			//接收用户发送的消息
			if (message.status == '100') {
				addMarker(message.data);
			}
		}
	}
};

			
function getSessionId(){
   var c_name = 'JSESSIONID';
   if(document.cookie.length>0){
      c_start=document.cookie.indexOf(c_name + "=")
      if(c_start!=-1){ 
        c_start=c_start + c_name.length+1 
        c_end=document.cookie.indexOf(";",c_start)
        if(c_end==-1) c_end=document.cookie.length
        return unescape(document.cookie.substring(c_start,c_end));
      }
   }
  }

