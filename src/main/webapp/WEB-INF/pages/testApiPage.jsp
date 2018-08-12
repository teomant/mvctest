<%--
  Created by IntelliJ IDEA.
  User: Teomant
  Date: 12.08.2018
  Time: 21:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--@elvariable id="user" type="org.teomant.entity.UserEntity"--%>

<html>
<head>
    <title>TestAPI</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script>

        function testfunction() {
            var errorHandler = function () {
                alert('Can`t send message')
            };
            $.ajax({
                url: "${pageContext.request.contextPath}/rest/test/2",
                type: 'get',
                timeout: 5 * 1000,
                contentType: "application/json",
                success: function (response) {
                    if (response === "fail")
                        errorHandler();
                    else {
                        $("#test").append(JSON.stringify(response));
                    }
                },
                error: errorHandler
            });
        }

        $(document).ready(function() {
            $('#btnSubmit').click(testfunction);
        });
    </script>
</head>

<body>
    <div id="test" style="margin:10px;">

    </div>
    <input type="submit" class="btn" id="btnSubmit" value="test"/>
</body>
</html>
