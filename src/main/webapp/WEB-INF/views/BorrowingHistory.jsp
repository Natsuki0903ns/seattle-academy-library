<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<html>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
<head>
<meta charset="UTF-8">
<title>書籍の追加｜シアトルライブラリ｜シアトルコンサルティング株式会社</title>
<link href="<c:url value="/resources/css/reset.css" />" rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/css?family=Noto+Sans+JP" rel="stylesheet">
<link href="<c:url value="/resources/css/default.css" />" rel="stylesheet" type="text/css">
<link href="https://use.fontawesome.com/releases/v5.6.1/css/all.css" rel="stylesheet">
<link href="<c:url value="/resources/css/home.css" />" rel="stylesheet" type="text/css">
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script src="resources/js/thumbnail.js"></script>
</head>
<body class="wrapper">
    <header>
        <div class="left">
            <img class="mark" src="resources/img/logo.png" />
            <div class="logo">Seattle Library</div>
        </div>
        <div class="right">
            <ul>
                <li><a href="<%=request.getContextPath()%>/home" class="menu">Home</a></li>
                <li><a href="<%=request.getContextPath()%>/">ログアウト</a></li>
            </ul>
        </div>
    </header>
    <h1 class="my-3 ml-3">履歴一覧</h1>
    <div class="container">
        <div class="col-5 ml-3">
            <table class="table table-bordered">
                <thead>
                    <tr class="table-primary">
                        <th scope="col">書籍名</th>
                        <th scope="col">貸出日</th>
                        <th scope="col">返却日</th>
                    </tr>
                </thead>
                <c:forEach var="BorrowingHistoryInfo" items="${BorrowingHistoryList}">
                    <tbody>
                        <tr>
                            <td><form method="get" action="<%=request.getContextPath()%>/historyBook">
                                    <a href="javascript:void(0)" onclick="this.parentNode.submit();">${BorrowingHistoryInfo.title}</a>
                                    <input type="hidden" name="bookId" value="${BorrowingHistoryInfo.bookId}">
                                </form></td>
                            <td>${BorrowingHistoryInfo.rentDate}</td>
                            <td>${BorrowingHistoryInfo.returnDate}</td>
                        </tr>
                    </tbody>
                </c:forEach>
            </table>
        </div>
    </div>