<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<%@ page contentType="text/html; charset=utf8"%>
<%@ page import="java.util.*"%>
<html>
<head>
<title>ホーム｜シアトルライブラリ｜シアトルコンサルティング株式会社</title>
<link href="<c:url value="/resources/css/reset.css" />" rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/css?family=Noto+Sans+JP" rel="stylesheet">
<link href="<c:url value="/resources/css/default.css" />" rel="stylesheet" type="text/css">
<link href="https://use.fontawesome.com/releases/v5.6.1/css/all.css" rel="stylesheet">
<link href="<c:url value="/resources/css/home.css" />" rel="stylesheet" type="text/css">
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
    <main>
        <div class="book">
            <h1>Home</h1>
            <form action="searchBook" method="post">
                <input type="radio" name="radiobutton" value="partial matching"> 部分一致 <input type="radio" name="radiobutton" value="perfect_matching"> 完全一致<input type="search" class="search1" name="searchtitle" placeholder="本の検索">
                <button type="submit">検索</button>
            </form>
        </div>
        <a href="<%=request.getContextPath()%>/addBook" class="btn_add_book">書籍の追加</a> <a href="<%=request.getContextPath()%>/bulkRegist" class="btn_bulk_book">書籍の一括登録</a><a href="<%=request.getContextPath()%>/borrowinghistoryBook" class="btn_history_book">貸出履歴一覧</a> 
        <div class="content_body">
            <c:if test="${!empty resultMessage}">
                <div class="error_msg">${resultMessage}</div>
            </c:if>
            <div class="booklist">
                <c:forEach var="bookInfo" items="${bookList}">
                    <div class="books">
                        <form method="post" class="book_thumnail" action="<%=request.getContextPath()%>/details">
                            <a href="javascript:void(0)" onclick="this.parentNode.submit();"> <c:if test="${bookInfo.thumbnail == 'null'}">
                                    <img class="book_noimg" src="resources/img/noImg.png">
                                </c:if> <c:if test="${bookInfo.thumbnail != 'null'}">
                                    <img class="book_noimg" src="${bookInfo.thumbnail}">
                                </c:if>
                            </a> <input type="hidden" name="bookId" value="${bookInfo.bookId}">
                        </form>
                        <ul>
                            <li class="book_title">${bookInfo.title}</li>
                            <li class="book_author">${bookInfo.author}(著)</li>
                            <li class="book_publisher">出版社:${bookInfo.publisher}</li>
                            <li class="book_publish_Date">出版日:${bookInfo.publishDate}</li>
                        </ul>
                    </div>
                </c:forEach>
                <c:if test="${empty bookList}">
                    <div class="error">書籍がありません</div>
                </c:if>
            </div>
        </div>
    </main>
</body>
</html>
