<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>권한 거부</title>
</head>
<body>
<div class="wrap">
   <%@ include file="/WEB-INF/views/manager/common/header.jsp" %>   
   
   <div id="container">
      <div id="contents" style="text-align: center;">
         <h1>관리자 페이지</h1>
         <br>
         
         <!-- UserDeniedHandler에서  errMsg 전달 -->
         <p style="font-size: 18px">${errMsg}</p> <br>
         
         <c:if test="${sessionId !=null}">
            <button value="이동하기" class="inputButton" style="width:200px; font-size:24px; text-align: center;"
               onclick="window.location='logoutAction.do'">이동하기</button>
         </c:if>
         <c:if test="${sessionId == null}">
            <button value="이동하기" class="inputButton" style="width:200px; font-size:24px; text-align: center;"
               onclick="window.location='login.do'">이동하기</button>
         </c:if>
      </div>
   </div>
</div>
<br>
<br>

   <%@ include file="/WEB-INF/views/manager/common/footer.jsp" %>
</body>
</html>