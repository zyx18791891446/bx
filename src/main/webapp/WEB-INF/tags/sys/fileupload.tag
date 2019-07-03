<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ attribute name="id" type="java.lang.String" required="true" description="id"%>
<%@ attribute name="input" type="java.lang.String" required="true" description="输入框"%>
<%@ attribute name="value" type="java.lang.String" required="false" description="默认值"%>
<%@ attribute name="type" type="java.lang.String" required="true" description="files、images"%>
<%@ attribute name="selectMultiple" type="java.lang.Boolean" required="false" description="是否允许多选"%>
<%@ attribute name="savePath" type="java.lang.String" required="true" description="文件上传路径后保存的路径前缀"%>
<%@ attribute name="uploadPath" type="java.lang.String" required="true" description="文件上传路径URL"%>
<%@ attribute name="maxSize" type="java.lang.String" required="false" description="最大允许上传大小，单位M"%>
<%@ attribute name="isRequired" type="java.lang.String" required="false" description="是否必须填写，默认为false"%>

<script type="text/javascript">include('ckeditor_lib','${ctxStatic}/ckeditor/',['ckeditor.js']);</script>

<script>
    window.FileAPI = {
        debug: false // debug mode
        , staticPath: '${ctxStatic}/jquery-fileapi/FileAPI/' // path to *.swf
    };
</script>
<script type="text/javascript">include('fileapi_lib','${ctxStatic}/jquery-fileapi/FileAPI/',['FileAPI.min.js','FileAPI.exif.js']);</script>
<script type="text/javascript">include('jquery_fileapi','${ctxStatic}/jquery-fileapi/',['jquery.fileapi.min.js']);</script>

<div id="fileapi${id}" class="file-api">
    <div class="btn btn-default file-input file-btn-wj">
        <input type="file"  />
        <span>&nbsp;文件上传&nbsp;</span>
            <input id="${input}value" type="hidden" name="${input}" value="${value}">
    </div>
    <c:if test="${not empty value}">
    	<c:forEach items="${fn:split(value,'|')}" var="urls" varStatus="i">
    		<div class="${input}files">
    			<a id="fileuploadname${id}_${i.index+1}" href="${urls}" url="${urls}" target="_blank">
    			<c:set var = "fileName" value="${fn:split(urls,'/')}"></c:set>
    			<c:out value="${fileName[fn:length(fileName)-1]}"></c:out>
    			</a>  
    			<a class="file-input delete" onclick="deleteOne(this);">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;x</a>
    		</div>
    	</c:forEach>
    </c:if>
    <div data-fileapi="active.show" class="progress">
        <div data-fileapi="progress" class="progress__bar"></div>
    </div>
</div>

<script>

    var accept = "*/*";
    var maxSize = 10;

    <c:if test="${type == 'image'}">
    accept = "image/*";
    </c:if>

    <c:if test="${maxSize != null}">
    maxSize = ${maxSize};
    </c:if>

    jQuery(function ($){
        $('#fileapi${id}').fileapi({
            url: '${uploadPath}',
            autoUpload: true,
            accept: '.jpg,.pdf,.png,.gif',
            multiple: true,
            maxSize: FileAPI.MB*maxSize,
            onUpload:function(evt, uiEvt) {
                var fileInput = $(this).find('[type=file]');
                var text = fileInput.siblings('span').text();
                fileInput.data('text', text).siblings('span').text('上传中');
            },
            onProgress:function(evt, uiEvt) {
                $(this).find('[type=file]').siblings('span').text('上传中(' + parseInt(uiEvt.loaded / uiEvt.total * 100) + '%)...');
            },
            onComplete:function(evt, uiEvt) {
                var _this = $(this);
                var fileInput = _this.find('[type=file]');
                var text = fileInput.data('text');
                if (text) {
                    _this.find('[type=file]').siblings('span').text(text)
                }
                if (uiEvt.error) {
                    return alert('上传错误:' + uiEvt.error);
                } else if (uiEvt.result.type != 'success') {
                	var urls = uiEvt.result.url.split("/");
                    var files = '<div class="${input}files"><a  href="'+uiEvt.result.url+'" url="'+uiEvt.result.url+'" target="_blank">'+decodeURI(urls[urls.length-1])+'</a><a class="file-input delete" onclick="deleteOne(this);">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;x</a></div>';
                	var selectMultiple = '${selectMultiple}';
                	if('true'== selectMultiple){
                		console.log(selectMultiple); 
                   		_this.parents('form').find('.file-btn-wj').after(files);
                    	var v =  _this.find('[type=hidden]').val();
                   		_this.find('[type=hidden]').val(v+'|'+uiEvt.result.url);
                   		return null; 
                	}else{
                    	_this.parents('form').find('.${input}files').remove();
                    	_this.parents('form').find('.file-btn-wj').after(files);
                    	_this.find('[type=hidden]').val('|'+uiEvt.result.url);
                    	return null; 
                	}
                }
            }
        });
    });
    
    
    function deleteOne(obj){
    	var tmp = obj;
    	var urls = $(tmp).prev().attr('url');
    	var allUrls = $('#${input}value').val();
    	var url = allUrls.split('|');
    	var newUrls = '';
    	for(var i=1;i<url.length;i++){
    		if(url[i]!=urls){
    			newUrls += '|'+url[i];
    		}
    	}
		$('.${input}files').remove();
		$('#${input}value').val(newUrls);
    	for(var i=1;i<url.length;i++){
    		if(url[i]!=urls){
    			var list = url[i].split('/');
    			var files = '<div class="${input}files"><a herf="'+url[i]+'" url="'+url[i]+'" target="_blank">'+decodeURI(list[list.length-1])+'</a><a class="file-input delete" onclick="deleteOne(this);">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;x</a></div>';
				$('.file-btn-wj').after(files);
    		}
    	}	
		
    }
    
    
    $(document).ready(function(){   
    	var id = '${id}';
    	var urls = '${value}';
    	var url = urls.split("|");
    	for(var i=1;i<url.length;i++){
    		var fileName = $('#fileuploadname'+id+"_"+i).html();
    		$('#fileuploadname'+id+"_"+i).html(decodeURI(fileName));
    	}	
    });
    
    
</script>