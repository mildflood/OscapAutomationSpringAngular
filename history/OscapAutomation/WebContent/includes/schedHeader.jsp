<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html> 
<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

<head>
<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">  

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
<title>Openscap Automation Tool</title>
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">  
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script type="text/javascript" src="https://code.highcharts.com/highcharts.js"></script>
<script src="https://code.highcharts.com/modules/exporting.js"></script>

<!-- Entries  for Tables -->
<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css" rel="stylesheet">   
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
<link rel="stylesheet" href="https://cdn.datatables.net/1.10.2/css/jquery.dataTables.min.css">
<script type="text/javascript" src="https://cdn.datatables.net/1.10.2/js/jquery.dataTables.min.js"></script>  

<script type="text/javascript" src="scripts/scan.js"></script>
<script type="text/javascript" src="scripts/saveData.js"></script>
<script type="text/javascript" src="scripts/singleScanBridge.js"></script>
<script type="text/javascript" src="scripts/cancelSchedTask.js"></script> 
 
<script type="text/javascript" src="scripts/multiScanBridge.js"></script> 
<script type="text/javascript" src="scripts/loadMultiScanData.js"></script> 

<!-- My Own Cascading Style Sheets -->
<Link href="css/schedulerLayout.css" rel="stylesheet" type="text/css" />
<Link href="css/defaultFont.css" rel="stylesheet" type="text/css" />
</head>