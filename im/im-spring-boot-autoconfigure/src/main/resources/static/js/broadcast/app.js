var stompClient = null;
$(function () {
  function connect() {
    var userName = $("#userName").val();
    var socket = new SockJS("/gs-guide-websocket");
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
      console.log('Connected: ' + frame);
      $("#logoutDiv").show();
      $("#loginDiv").hide();

      $("#messageDiv").append(userName + " connected!</br>");
      stompClient.subscribe("/topic/greetings", function (greeting) {
        $("#messageDiv").append(JSON.parse(greeting.body).content+"</br>");
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
    console.log("Disconnected");
  }

  $("#login").click(function () {
    connect();
  });
  $("#logout").click(function () {
    disconnect();
  });
});
