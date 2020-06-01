
<%@ page contentType="text/html; charset=UTF-8" %>

<html>
<h1>
    Hello ${user.name}!!!
</h1>
</html>


<%--resquest를 날렸어요 web.xml을 뒤져보니 dispatcherservlet이 요청을 다 받아요 데--%>
<%--얘는 dispatcher-servlet.xml의 명령을 따른다. bean의 이름이 /user 인데 우리가 요청한 /user와 동일하니깐 --%>
<%--bean name handler mapping을 가동해 Controller라고 정의된 handleAdpaer를 수행한다.--%>
<%--그 이후 user오브젝트에 담아서 viewResolver에게 넘겨줘 그러면 얘가 화면에 뿌려준다--%>