<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>시세 그래프</title>
<head>
	<link type="text/css" rel="stylesheet" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css">
	<link type="text/css" rel="stylesheet" href="https://tech.shutterstock.com/rickshaw/src/css/graph.css">
	<link type="text/css" rel="stylesheet" href="https://tech.shutterstock.com/rickshaw/src/css/detail.css">
	<link type="text/css" rel="stylesheet" href="https://tech.shutterstock.com/rickshaw/src/css/legend.css">
	<link type="text/css" rel="stylesheet" href="https://tech.shutterstock.com/rickshaw/examples/css/extensions.css?v=2">

	<script src="https://tech.shutterstock.com/rickshaw/vendor/d3.v3.js"></script>

	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.6.2/jquery.min.js"></script>

	<script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.8.15/jquery-ui.min.js"></script>

	<script src="https://tech.shutterstock.com/rickshaw/src/js/Rickshaw.js"></script>
	<script src="https://tech.shutterstock.com/rickshaw/src/js/Rickshaw.Class.js"></script>
	<script src="https://tech.shutterstock.com/rickshaw/src/js/Rickshaw.Compat.ClassList.js"></script>
	<script src="https://tech.shutterstock.com/rickshaw/src/js/Rickshaw.Graph.js"></script>
	<script src="https://tech.shutterstock.com/rickshaw/src/js/Rickshaw.Graph.Renderer.js"></script>
	<script src="https://tech.shutterstock.com/rickshaw/src/js/Rickshaw.Graph.Renderer.Area.js"></script>
	<script src="https://tech.shutterstock.com/rickshaw/src/js/Rickshaw.Graph.Renderer.Line.js"></script>
	<script src="https://tech.shutterstock.com/rickshaw/src/js/Rickshaw.Graph.Renderer.Bar.js"></script>
	<script src="https://tech.shutterstock.com/rickshaw/src/js/Rickshaw.Graph.Renderer.ScatterPlot.js"></script>
	<script src="https://tech.shutterstock.com/rickshaw/src/js/Rickshaw.Graph.Renderer.Stack.js"></script>
	<script src="https://tech.shutterstock.com/rickshaw/src/js/Rickshaw.Graph.RangeSlider.js"></script>
	<script src="https://tech.shutterstock.com/rickshaw/src/js/Rickshaw.Graph.RangeSlider.Preview.js"></script>
	<script src="https://tech.shutterstock.com/rickshaw/src/js/Rickshaw.Graph.HoverDetail.js"></script>
	<script src="https://tech.shutterstock.com/rickshaw/src/js/Rickshaw.Graph.Annotate.js"></script>
	<script src="https://tech.shutterstock.com/rickshaw/src/js/Rickshaw.Graph.Legend.js"></script>
	<script src="https://tech.shutterstock.com/rickshaw/src/js/Rickshaw.Graph.Axis.Time.js"></script>
	<script src="https://tech.shutterstock.com/rickshaw/src/js/Rickshaw.Graph.Behavior.Series.Toggle.js"></script>
	<script src="https://tech.shutterstock.com/rickshaw/src/js/Rickshaw.Graph.Behavior.Series.Order.js"></script>
	<script src="https://tech.shutterstock.com/rickshaw/src/js/Rickshaw.Graph.Behavior.Series.Highlight.js"></script>
	<script src="https://tech.shutterstock.com/rickshaw/src/js/Rickshaw.Graph.Smoother.js"></script>
	<script src="https://tech.shutterstock.com/rickshaw/src/js/Rickshaw.Fixtures.Time.js"></script>
	<script src="https://tech.shutterstock.com/rickshaw/src/js/Rickshaw.Fixtures.Time.Local.js"></script>
	<script src="https://tech.shutterstock.com/rickshaw/src/js/Rickshaw.Fixtures.Number.js"></script>
	<script src="https://tech.shutterstock.com/rickshaw/src/js/Rickshaw.Fixtures.RandomData.js"></script>
	<script src="https://tech.shutterstock.com/rickshaw/src/js/Rickshaw.Fixtures.Color.js"></script>
	<script src="https://tech.shutterstock.com/rickshaw/src/js/Rickshaw.Color.Palette.js"></script>
	<script src="https://tech.shutterstock.com/rickshaw/src/js/Rickshaw.Graph.Axis.Y.js"></script>

	<script src="https://tech.shutterstock.com/rickshaw/examples/js/extensions.js"></script>
</head>
<body>

<div id="content">

	<form id="side_panel">
		<h1>시세 데이터</h1>
		<section><div id="legend"></div></section>
		<section>
			<div id="renderer_form" class="toggler">
				<input type="radio" name="renderer" id="area" value="area" checked>
				<label for="area">area</label>
				<input type="radio" name="renderer" id="bar" value="bar">
				<label for="bar">bar</label>
				<input type="radio" name="renderer" id="line" value="line">
				<label for="line">line</label>
				<input type="radio" name="renderer" id="scatter" value="scatterplot">
				<label for="scatter">scatter</label>
			</div>
		</section>
		<section>
			<div id="offset_form">
				<label for="stack">
					<input type="radio" name="offset" id="stack" value="zero" checked>
					<span>stack</span>
				</label>
				<label for="stream">
					<input type="radio" name="offset" id="stream" value="wiggle">
					<span>stream</span>
				</label>
				<label for="pct">
					<input type="radio" name="offset" id="pct" value="expand">
					<span>pct</span>
				</label>
				<label for="value">
					<input type="radio" name="offset" id="value" value="value">
					<span>value</span>
				</label>
			</div>
			<div id="interpolation_form">
				<label for="cardinal">
					<input type="radio" name="interpolation" id="cardinal" value="cardinal" checked>
					<span>cardinal</span>
				</label>
				<label for="linear">
					<input type="radio" name="interpolation" id="linear" value="linear">
					<span>linear</span>
				</label>
				<label for="step">
					<input type="radio" name="interpolation" id="step" value="step-after">
					<span>step</span>
				</label>
			</div>
		</section>
		<section>
			<h6>Smoothing</h6>
			<div id="smoother"></div>
		</section>
		<section></section>
	</form>

	<div id="chart_container">
		<div id="chart"></div>
		<div id="timeline"></div>
		<div id="preview"></div>
	</div>

</div>

<script>

// set up our data series with 150 random data points

var seriesData = [ [] ];

var palette = new Rickshaw.Color.Palette( { scheme: 'classic9' } );



setInterval( function() {
	updateGraph();

}, 3000 );

var graph, xAxis, yAxis;
var graph_inited = false;
var yMin = 0, yMax;
function init_graph() {
	// instantiate our graph!

	graph = new Rickshaw.Graph( {
		element: document.getElementById("chart"),
		width: 900,
		height: 500,
		renderer: 'area',
		stroke: true,
		preserve: true,
		min : yMin,
		max : yMax,
		series: [
			{
				color: palette.color(),
				data: seriesData[0],
				name: ${issueCode}
			}
		]
	} );

	graph.render();

	var preview = new Rickshaw.Graph.RangeSlider( {
		graph: graph,
		element: document.getElementById('preview'),
	} );

	var hoverDetail = new Rickshaw.Graph.HoverDetail( {
		graph: graph,
		xFormatter: function(x) {
			return new Date(x).toString();
		}
	} );

	var annotator = new Rickshaw.Graph.Annotate( {
		graph: graph,
		element: document.getElementById('timeline')
	} );

	var legend = new Rickshaw.Graph.Legend( {
		graph: graph,
		element: document.getElementById('legend')

	} );

	var shelving = new Rickshaw.Graph.Behavior.Series.Toggle( {
		graph: graph,
		legend: legend
	} );

	var order = new Rickshaw.Graph.Behavior.Series.Order( {
		graph: graph,
		legend: legend
	} );

	var highlighter = new Rickshaw.Graph.Behavior.Series.Highlight( {
		graph: graph,
		legend: legend
	} );

	var smoother = new Rickshaw.Graph.Smoother( {
		graph: graph,
		element: document.querySelector('#smoother')
	} );

	var ticksTreatment = 'glow';

	xAxis = new Rickshaw.Graph.Axis.Time( {
		graph: graph,
		ticksTreatment: ticksTreatment,
		timeFixture: new Rickshaw.Fixtures.Time.Local()
	} );

	xAxis.render();

	yAxis = new Rickshaw.Graph.Axis.Y( {
		graph: graph,
		tickFormat: Rickshaw.Fixtures.Number.formatKMBT,
		ticksTreatment: ticksTreatment
	} );

	yAxis.render();


	var controls = new RenderControls( {
		element: document.querySelector('form'),
		graph: graph
	} );

	graph_inited = true;
}

function updateGraph() {
	
	$.get("/marketdata/price/${issueCode}", function(data, status){
		if (status == "success")
		{
			console.info(data);
			seriesData[0].push( { x : Date.now(), y : data.trdPrc });
			console.info(JSON.stringify(seriesData[0]));
			
			if(graph_inited)
				graph.update();
			else {
				yMin = data.opnprc * 0.65;
				yMax = data.opnprc * 1.35;
				init_graph();
			}
		}

	});

}

$(function() {
	updateGraph();
	});
	
</script>
</body>
</html>