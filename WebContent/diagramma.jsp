<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<meta charset="utf-8">
<head>
  <meta charset="ISO-8859-1">
  <title>Home</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="plugins/fontawesome-free/css/all.min.css">
  <link rel="stylesheet" href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">
  <link rel="stylesheet" href="dist/css/adminlte.min.css">
  <link rel="stylesheet" href="plugins/overlayScrollbars/css/OverlayScrollbars.min.css">
	<!-- style per il diagramma -->
	<style>

		.links line {
		  stroke: #999;
		  stroke-opacity: 0.6;
		}
		
		path{
			stroke: #999;
		}
		path.selected {
			stroke: #FF0000;
		}
		
		text {
		  font-family: sans-serif;
		  font-size: 12px;
		}
		
	</style>
</head>
<body>
 <div class="card-body">
   <form method="post" enctype="multipart/form-data" role="form">
     <div class="form-group">
       <label for="path">Selezionare file .xmi</label>
       <div class="input-group">
         <div class="custom-file">
         	 <input type="file" id="path" name="file" class="custom-file-input">
           <label class="custom-file-label" for="path">Choose file</label>
         </div>
         <div class="input-group-append">
           <span class="btn btn-default" style="background-color: #e9ecef;" onclick="writeDatabase()">
             Upload
           </span>
         </div>
       </div>
     </div>
     <input type="hidden" id="frame" value="">
   </form>
 </div>
 <svg width="900" height="1200" style="display: none;"></svg>
</body>
<script src="plugins/jquery/jquery.min.js"></script>
<script src="plugins/jquery-ui/jquery-ui.min.js"></script>
<script src="https://d3js.org/d3.v4.min.js"></script>
<script src="plugins/bs-custom-file-input/bs-custom-file-input.min.js"></script>
<!-- script per nome file nel form -->
<script type="text/javascript">
  $(document).ready(function () {
    bsCustomFileInput.init();
  });
</script>
<!-- script per disegnare il diagramma -->
<script type="text/javascript">


var data = [];
var links = [];
var nodes = [];
var svg = d3.select("svg"),width = 900, height = 600;

function writeDatabase() {
	
	var xmlhttp = new XMLHttpRequest();
	xmlhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {loadSchemaER(this);}
	};
	
	var formData = new FormData();
	formData.append("file", document.getElementById("path").files[0]);

	xmlhttp.open("POST", "JSONSchemaER", true);
	xmlhttp.send(formData);
}
//Continuare a implementare la funzione su onclick
		
function loadSchemaER(xmlhttp) {
	//variabile che distingue i due frame
	var frame = document.getElementById("frame").value;
	sessionStorage.setItem(frame, '[]');
	var selectedNodes = [];
	
	data = JSON.parse(xmlhttp.responseText);
	
	var i = 0;
	while(data.entity[i] != null){
		var j = 0;
		nodes.push({id:data.entity[i].name, size:4000, img:  "imgForme/entita.png",h: 120, w:120});
		while(data.entity[i].attributes[j] != null){
			var w = 0, control = true;
			while(nodes[w] != null){
				if(data.entity[i].attributes[j] == nodes[w].id) control = false;
				w++;
			}
			if(control == true) {
				nodes.push({id:data.entity[i].attributes[j], size:4000,  img:  "imgForme/attributo.png", h: 60, w:60});
				links.push({source: data.entity[i].attributes[j], target: data.entity[i].name , value: 4});	
			}
			else {
				nodes.push({id: data.entity[i].name + "-" + data.entity[i].attributes[j], size:4000,  img:  "imgForme/attributo.png",h: 60, w:60});
				links.push({source: data.entity[i].name + "-" + data.entity[i].attributes[j], target: data.entity[i].name , value: 4});	
			}
			
			j++;
		}
		i++;
	}
		
	
	i = 0;
	while(data.association[i] != null){
		links.push({source: data.association[i].entity1, target: data.association[i].name , value: 1});	
		links.push({source: data.association[i].entity2, target: data.association[i].name , value: 1});	
		nodes.push({id:data.association[i].name, size:4000,  img:  "imgForme/relazione.png", h: 120, w:120});
		i++;
	}
	
	var simulation = d3.forceSimulation()
    					.force("link", d3.forceLink().distance(200).id(function(d) { return d.id; }))
    					.force("charge", d3.forceManyBody())
    					.force("center", d3.forceCenter(width / 2, height));


	var link = svg.append("g")
  					.attr("class", "links")
	 				.selectAll("line")
					.data(links)
					.enter().append("line")
  					.attr("stroke-width", function(d) { return Math.sqrt(d.value); });
	

	var node = svg.append("g")
					.attr("class", "nodes")
					.attr("opacity",1)
					.selectAll("g")
					.data(nodes)
					.enter().append("g")
					.on("click", function(){
						//Funzione per la selezione e la deselezione di entità e relazioni
						selectedNodes = JSON.parse(sessionStorage.getItem(frame));
						
						if(d3.select(this)._groups[0][0].__data__.type == "symbolSquare"){
							var numAttributi = 0;
							var indexEntity = d3.select(this)._groups[0][0].__data__.index;
							var j = indexEntity;
							var nomeEntity = d3.select(this)._groups[0][0].__data__.id;
							var length = d3.selectAll(link)._groups[0]._groups[0].length;
							
							var entity = {name: nomeEntity, attributes: [], type: "entity"}
							
								while(true){
									j++;
									if(d3.selectAll(node)._groups[0]._groups[0][j].__data__.type == "symbolCircle"){
										numAttributi++;
									} else {
										break;
									}
								}
								for(var i = indexEntity + 1; i<=numAttributi+indexEntity ;i++){

									if(d3.select(this)._groups[0][0].childNodes[0].style.stroke == "red"){
										d3.selectAll(node)._groups[0]._groups[0][i].childNodes[0].style.stroke = "#999";
										d3.selectAll(node)._groups[0]._groups[0][i].childNodes[0].style.strokeWidth = 1;
									} else {	
										d3.selectAll(node)._groups[0]._groups[0][i].childNodes[0].style.stroke = "red";
										d3.selectAll(node)._groups[0]._groups[0][i].childNodes[0].style.strokeWidth = 4;
										entity.attributes.push(d3.selectAll(node)._groups[0]._groups[0][i].childNodes[2].textContent)
									}
								}
								if(d3.select(this)._groups[0][0].childNodes[0].style.stroke == "red"){
									checkSelected(entity);

									d3.select(this)._groups[0][0].childNodes[0].style.stroke = "#999";
									d3.select(this)._groups[0][0].childNodes[0].style.strokeWidth = 1;
								} else {
									d3.select(this)._groups[0][0].childNodes[0].style.stroke = "red";
									d3.select(this)._groups[0][0].childNodes[0].style.strokeWidth = 4;
									selectedNodes.push(entity);
								}
						} else if(d3.select(this)._groups[0][0].__data__.type == "symbolDiamond"){
							var relation = {name: d3.select(this)._groups[0][0].childNodes[2].textContent,
									 type: "relation"}
							
							if(d3.select(this)._groups[0][0].childNodes[0].style.stroke == "red"){
								checkSelected(relation);

								d3.select(this)._groups[0][0].childNodes[0].style.stroke = "#999";
								d3.select(this)._groups[0][0].childNodes[0].style.strokeWidth = 1;
							} else {
								d3.select(this)._groups[0][0].childNodes[0].style.stroke = "red";
								d3.select(this)._groups[0][0].childNodes[0].style.strokeWidth = 4;
								
								selectedNodes.push(relation);
							}
						} else{
							var attribute = {name: d3.select(this)._groups[0][0].childNodes[2].textContent,
															 type: "attribute"};
							
							if(d3.select(this)._groups[0][0].childNodes[0].style.stroke == "red"){
								checkSelected(attribute);
								
								d3.select(this)._groups[0][0].childNodes[0].style.stroke = "#999";
								d3.select(this)._groups[0][0].childNodes[0].style.strokeWidth = 1;
							} else {
								d3.select(this)._groups[0][0].childNodes[0].style.stroke = "red";
								d3.select(this)._groups[0][0].childNodes[0].style.strokeWidth = 4;
								selectedNodes.push(attribute);
							}
						}
						console.log(selectedNodes, selectedNodes.length);
						sessionStorage.setItem(frame, JSON.stringify(selectedNodes));
					})
					.on("dblclick", function(){
						
					// Funzione per far scomparire e ricomparire gli attributi di una entità al doubleclick
						if(d3.select(this)._groups[0][0].__data__.type == "symbolSquare"){
						var numAttributi = 0;
						var indexEntity = d3.select(this)._groups[0][0].__data__.index;
						var j = indexEntity;
						var nomeEntity = d3.select(this)._groups[0][0].__data__.id;
						var length = d3.selectAll(link)._groups[0]._groups[0].length;
							while(true){
								j++;
								if(d3.selectAll(node)._groups[0]._groups[0][j].__data__.type == "symbolCircle"){
									numAttributi++;
								} else {

									break;
								}
							}
						for(var i = indexEntity + 1; i<=numAttributi+indexEntity ;i++){
							if(d3.selectAll(node)._groups[0]._groups[0][i].style.opacity == 1||d3.selectAll(node)._groups[0]._groups[0][i].style.opacity == ""){
							d3.selectAll(node)._groups[0]._groups[0][i].style.opacity = 0;
							}else{
								d3.selectAll(node)._groups[0]._groups[0][i].style.opacity = 1;
							}
							
						}
						for(var j = 0; j<length; j++){
							if(d3.selectAll(link)._groups[0]._groups[0][j].__data__.target.id == nomeEntity){
								if(d3.selectAll(link)._groups[0]._groups[0][j].style.opacity == 1 || d3.selectAll(link)._groups[0]._groups[0][j].style.opacity == ""){
								d3.selectAll(link)._groups[0]._groups[0][j].style.opacity = 0;
								}else{
									d3.selectAll(link)._groups[0]._groups[0][j].style.opacity = 1;
								}
							}
						}
						}
					});

	var symbolGenerator = d3.symbol().size(2000);

	// cancella l'elemento deselezionato dalla lista
	function checkSelected(obj) {
		for(var i=0; i < selectedNodes.length; i++) {
			if(obj.name == selectedNodes[i].name){
				selectedNodes.splice(i, i+1);
				break;
			}
		} 
	}

	// Append a circle
	 node.append("circle")
	     .attr("r", function(d) { return Math.sqrt(d.size) / 10 || 4.5; })
	     .style("fill", "#eee");
	     

	  
	 // Append images
	 var images = node.append("image")
       .attr("xlink:href",  function(d) { return d.img;})
       .attr("x", function(d) { return -(d.h/2);})
       .attr("y", function(d) { return -(d.w/2);})
       .attr("height",function(d) { return d.h;})
       .attr("width", function(d) { return d.w;})
       .call(d3.drag().on("start", dragstarted).on("drag", dragged));
	 
	 var lables = node.append("text")
				.text(function(d) {return d.id;})
				.attr('x', -35)
				.attr('y', 2);

	node.append("title").text(function(d) { return d.id; });

	simulation.nodes(nodes).on("tick", ticked);

	simulation.force("link").links(links);

	function ticked() {
		link.attr("x1", function(d) { return d.source.x; })
	    	.attr("y1", function(d) { return d.source.y; })
    		.attr("x2", function(d) { return d.target.x; })
    		.attr("y2", function(d) { return d.target.y; });

		node.attr("transform", function(d) {return "translate(" + d.x + "," + d.y + ")";})
	}

	function dragstarted(d) {
		if (!d3.event.active) simulation.alphaTarget(0.3).restart();
		d.fx = d.x;
		d.fy = d.y;
	}

	function dragged(d) {
		d.fx = d3.event.x;
		d.fy = d3.event.y;
	}

	//Questa è la funzione per bloccare i nodie va inserita in circles
	function dragended(d) {
  		if (!d3.event.active) simulation.alphaTarget(0);
  		d.fx = null;
  		d.fy = null;
	}
	
	d3.select(".card-body").style("display", "none");
	d3.select("svg").style("display", "inline");
}


</script>