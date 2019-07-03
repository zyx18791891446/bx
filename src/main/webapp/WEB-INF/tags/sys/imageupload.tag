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

<script type="text/javascript">include('ckeditor_lib','${ctxStatic}/ckeditor/',['ckeditor.js']);</script>

<script>
    window.FileAPI = {
        debug: false // debug mode
        , staticPath: '${ctxStatic}/jquery-fileapi/FileAPI/' // path to *.swf
    };
</script>
<script type="text/javascript">include('fileapi_lib${id}','${ctxStatic}/jquery-fileapi/FileAPI/',['FileAPI.min.js','FileAPI.exif.js']);</script>
<script type="text/javascript">include('jquery_fileapi${id}','${ctxStatic}/jquery-fileapi/',['jquery.fileapi.min.js']);</script>

<div id="fileapi${id}" class="file-api">
    <div class="thumb">
        <c:if test="${not empty value}">
        <c:forEach items="${fn:split(value,'|')}" var="urls">
        <div>
        <img  width="100" src="${urls}">
        <a  class="delete file-input" >x</a>
        </div>
        </c:forEach>
        </c:if>
        <c:if test="${empty value}">
            <img id="imageuploadtag" width="100" src="${ctxStatic}/images/touxiang.png">
        </c:if>

    </div>
    <div class="btn btn-default file-input required">
        <input type="file"  />
        <span>&nbsp;图片上传&nbsp;</span>
    </div>
    <input id="${input}value"  type="hidden" name="${input}" value="${value}">
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
    
    
    
    $(document).ready(function(){
  
    	$(document).on('click', '#fileapi${id} .thumb .delete', function(){
        	var tmp = $(this);
        	var urls = tmp.prev().attr('src');
        	var allUrls = $('#${input}value').val();
        	var url = allUrls.split('|');
        	var newUrls = '';
        	for(var i=0;i<url.length;i++){
        		if(url[i] != '' && url[i]!=urls){
        			newUrls += '|'+url[i];
        		}
        	}
    		$('#${input}value').val(newUrls);
        	$('#fileapi${id} .thumb').empty();
        	for(var i=1;i<url.length;i++){
        		if(url[i]!=urls){
               		var con  = $('<div/>');
                	var img = $('<img/>').attr({'src':url[i]}); 
                	var btn = $('<a/>').attr('class','delete').attr('class','file-input').text("x");
                	con.append(img); 
                	con.append(btn);
    				$('#fileapi${id} .thumb').append(con);
        		}
        	}	
    	})
    })

    jQuery(function ($){
        $('#fileapi${id}').fileapi({
            url: '${uploadPath}',
            autoUpload: true,
            accept: accept,
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
               		var con  = $('<div/>');
                	var img = $('<img/>').attr({'class':uiEvt.result.url, 'src':uiEvt.result.url});
                	var btn = $('<a/>').attr('class','delete').text("x");
                	con.append(img); 
                	con.append(btn);
                	var selectMultiple = '${selectMultiple}';
                	if('true'== selectMultiple){
                		console.log(selectMultiple); 
                   		$('#fileapi${id} .thumb #imageuploadtag').remove();
                    	$('#fileapi${id} .thumb').append(con);
                    	var v =  $('#fileapi${id}').find('[type=hidden]').val();
                    	$('#fileapi${id}').find('[type=hidden]').val(v+'|'+uiEvt.result.url);
                   		return null; 
                	}else{
                		$('#fileapi${id} .thumb').empty();
                		$('#fileapi${id} .thumb').append(con);
                		$('#fileapi${id}').find('[type=hidden]').val('|'+uiEvt.result.url);
                    	return null; 
                	}
                }
            }
        });
    });
    
    
  
    
</script>