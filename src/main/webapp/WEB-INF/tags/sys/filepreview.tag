<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ attribute name="id" type="java.lang.String" required="true" description="id"%>
<%@ attribute name="value" type="java.lang.String" required="false" description="默认值"%>

<div>
    <%-- <div class="btn btn-default file-input file-btn-wj">
        <input type="file"  />
        <span>&nbsp;文件上传&nbsp;</span>
            <input id="${input}value" type="hidden" name="${input}" value="${value}">
    </div> --%>
    <c:if test="${not empty value}">
    	<c:forEach items="${fn:split(value,'|')}" var="urls" varStatus="i">
    		<div class="${input}files">
    			<a id="fileuploadname${id}_${i.index+1}" href="${urls}" url="${urls}" target="_blank">
    			<c:set var = "fileName" value="${fn:split(urls,'/')}"></c:set>
    			<c:out value="${fileName[fn:length(fileName)-1]}"></c:out>
    			</a>  
    		</div>
    	</c:forEach>
    </c:if>
    <div data-fileapi="active.show" class="progress">
        <div data-fileapi="progress" class="progress__bar"></div>
    </div>
</div>
