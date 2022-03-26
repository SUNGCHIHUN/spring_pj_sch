<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- JSTL 사용 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%-- 기본 프로젝트명으로 path 설정 --%>
<c:set var="path" value="${pageContext.request.contextPath}" />

<%-- JQuery --%>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>