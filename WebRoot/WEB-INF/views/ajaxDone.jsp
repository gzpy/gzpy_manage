<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp"%>
{
	"statusCode":"${statusCode}", 
	"message":"${callbackType ne 'forwardConfirm' ? message : ''}", 
	"confirmMsg":"${empty confirmMsg ? '' : forwardUrl}",
	"navTabId":"${navTabId}", 
	"rel":"${empty rel ? '' : rel}",
	"callbackType":"${callbackType}",
	"forwardUrl":"${empty forwardUrl ? '' : forwardUrl}"
}