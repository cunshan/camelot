var stompClient = null;
$(function () {
  function connect() {
    var userName = $("#userName").val();
    var socket = new SockJS("/gs-guide-websocket");
    stompClient = Stomp.over(socket);
    stompClient.connect('guest','guest', function (frame) {
      console.log('Connected: ' + frame);
      $("#logoutDiv").show();
      $("#loginDiv").hide();

      $("#messageDiv").append(userName + " connected!</br>");
      stompClient.subscribe("/exchange/greetings/"+userName,
          function (greeting) {
            var message = JSON.parse(greeting.body);
            $("#messageDiv").append(message.from + "说：" + message.content
                + "</br>");
          });
    });
  }

  function disconnect() {
    if (stompClient !== null) {
      stompClient.disconnect();
      $("#messageDiv").append($("#userName").val() + " disconnected!</br>");
    }
    $("#loginDiv").show();
    $("#logoutDiv").hide();
    $("#messageDiv").html("");
    console.log("Disconnected");
  }

  function sendMessage() {
    var name = $("#name").val();
    var fromName = $("#userName").val();
    var message = $("#message").val();
    stompClient.send("/app/say", {'user': name},
        JSON.stringify({'message': message,'name':fromName}));
  }

  $("#login").click(function () {
    connect();
  });
  $("#logout").click(function () {
    disconnect();
  });

  $("#sendMessage").click(function () {
    sendMessage();
  });
});
