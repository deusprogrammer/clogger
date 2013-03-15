<%@ page import="com.jpcf.blog.BlogReply" %>
<g:textArea name="content" cols="40" rows="5" maxlength="4096" value="${blogReplyInstance?.content?.replaceAll("<br/>", "\n")}"/>
<g:hiddenField id="post" name="post.id" value="${post.id}" />

