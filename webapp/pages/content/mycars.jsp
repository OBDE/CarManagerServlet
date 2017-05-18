<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
<div class="col-md-offset-5" id="loadinghourglass"><img src="includes/images/hourglass.gif" /></div>	
	<div id="msg" style="display:none;"></div>
	<div class="btn btn-success" id="showadd" style="display:none;" ><span class='glyphicon glyphicon-plus' aria-hidden='true'></span> Hozzáadás</div>
	
	
	<br><br>
	<div class="table-responsive">
	<!-- LOTS OF ROWS INCOMING -->
	<table class="table" id="showtable" style="display:none;">
		<thead>
		<tr>
		<th>#</th>
		<th>Gyártó</th>
		<th>Fajta</th>
		<th>Típus</th>
		<th>Évjárat</th>
		<th>LE</th>
		<th>ccm</th>
		<th>Művelet</th>
		</tr>
		</thead>
		<tbody id="cars">		
		</tbody>	
	</table>

<div class="col-md-12">	
	<form id="carmodify" class="form-inline" style="display:none;" onsubmit="return false">
		<div class="row">
		  <div class="form-group">
		  	<input type="hidden" class="form-control" id="modifyCarId" name="modifyCarId"> 
		  </div>
		  </div>
		  <div class="row">
		  <div class="form-group">		    
		    <input type="text" class="form-control" id="modifyCarVendor" name="modifyCarVendor">
		  </div>
		  <div class="form-group">
		  	    <input type="text" class="form-control" id="modifyCarName" name="modifyCarName">
		  </div>
		  <div class="form-group">
		  	    <input type="text" class="form-control" id="modifyCarType" name="modifyCarType">
		  </div>
		  </div>
		  <div class="row">
		  <div class="form-group">
		  	    <input type="text" class="form-control" id="modifyCarYear" name="modifyCarYear">
		  </div>
		  <div class="form-group">
		  	    <input type="text" class="form-control" id="modifyCarHp" name="modifyCarHp">
		  </div>		
		  <div class="form-group">
		  	    <input type="text" class="form-control" id="modifyCarCcm" name="modifyCarCcm">
		  </div>
		  </div>
		  <div class="row"> 
		   <button id="sendmodify" class="btn btn-primary">Módosítás</button>	
		  </div>
		</form>
		<!-- ADD CAR FORM -->
		<div id="caradd" class="form-inline" style="display:none;">		
		  <div class="row">
		  <div class="form-group">		    
		    <input type="text" class="form-control" name="addCarVendor" placeholder="Gyártó (OPEL)">
		  </div>
		  <div class="form-group">
		  	    <input type="text" class="form-control" name="addCarName" placeholder="Fajta (ASTRA)">
		  </div>
		  <div class="form-group">
		  	    <input type="text" class="form-control" name="addCarType" placeholder="Típus (COUPE)">
		  </div>
		  </div>
		  <div class="row">
		  <div class="form-group">
		  	    <input type="number" class="form-control" name="addCarYear" placeholder="Éjvárat (2004)">
		  </div>
		  <div class="form-group">
		  	    <input type="number" class="form-control" name="addCarHp" placeholder="Lóerő (125)">
		  </div>		
		  <div class="form-group">
		  	    <input type="number" class="form-control" name="addCarCcm" placeholder="Köbcenti (1796)">
		  </div>
		  </div>
		  <div class="row"> 		   
		   <div class="btn btn-success" id="sendadd" ><span class='glyphicon glyphicon-plus' aria-hidden='true'></span> Felvitel</div>	
		  </div>
		</div>
	</div>
	</div>
	
<script src="includes/js/mycars.js"></script>
<script>

$(document).ready(function(){	
		
		loadMyCars();
	
		$('#sendmodify').click(function()
		{	
			$.post("/crudzz/modifycar",{
				modifyCarId:$('[name=modifyCarId]').val(),
				modifyCarVendor:$('[name=modifyCarVendor]').val(),				
				modifyCarName:$('[name=modifyCarName]').val(),
				modifyCarType:$('[name=modifyCarType]').val(),
				modifyCarYear:$('[name=modifyCarYear]').val(),
				modifyCarHp:$('[name=modifyCarHp]').val(),
				modifyCarCcm:$('[name=modifyCarCcm]').val()	
			}).done(function(data){
				processAnswer(data);
			});
		});
		$('#sendadd').click(function(){			
			$.post("/crudzz/addnewcar",
					{							
					addCarVendor:$('[name=addCarVendor]').val(),
					addCarName:$('[name=addCarName]').val(),
					addCarType:$('[name=addCarType]').val(),
					addCarYear:$('[name=addCarYear]').val(),
					addCarHp:$('[name=addCarHp]').val(),
					addCarCcm:$('[name=addCarCcm]').val()			
					}).done(function(data){
						processAnswer(data);
						console.log("ide meg visszater");
					});
		});	
		
});

function processAnswer(data){
	try
	{
		var json = $.parseJSON(data);	
		if(json.hasOwnProperty("success"))
		{		
			$('#msg').removeClass();
			$('#msg').html(json.success);
			$('#msg').addClass("alert alert-success");
			$('#msg').fadeIn('normal');									
			loadMyCars();
			$('#msg').delay(1000).fadeOut('normal');
		}
		if(json.hasOwnProperty("error"))
		{
			$('#msg').removeClass();
			$('#msg').html(json.error);
			$('#msg').addClass("alert alert-danger");
			$('#msg').fadeIn('normal');	
			$('#msg').delay(3000).fadeOut('normal');
			
		}
		if(json.hasOwnProperty("mycars"))
		{			
			loadMyCars();
			if(!$('#showtable').is(':visible'))
				$('#showtable').fadeIn('normal');
			
		}
	
	
	}catch(err){
		console.log(err.message);
	}
}


function deletecar(id)
{
	$.post("/crudzz/deletecar",
			{							
				delcarId:$('#car-'+id)[0].childNodes[0].innerHTML		
			}).done(function(data){
						processAnswer(data);
			});
	
}

function editcar(id)
{
	
	if(!$('#carmodify').is(':visible'))
		$('#carmodify').fadeIn('normal');
	
	if($('#caradd').is(':visible'))
	{
		$('#caradd').fadeOut('normal').promise().done(function () {
			$('#carmodify').fadeIn('normal');
		});
	}
		
	    
	$('#modifyCarId').val($('#car-'+id)[0].childNodes[0].innerHTML);
	$('#modifyCarVendor').val($('#car-'+id)[0].childNodes[1].innerHTML);
	$('#modifyCarName').val($('#car-'+id)[0].childNodes[2].innerHTML);
	$('#modifyCarType').val($('#car-'+id)[0].childNodes[3].innerHTML);
	$('#modifyCarYear').val($('#car-'+id)[0].childNodes[4].innerHTML);
	$('#modifyCarHp').val($('#car-'+id)[0].childNodes[5].innerHTML);
	$('#modifyCarCcm').val($('#car-'+id)[0].childNodes[6].innerHTML);

	
	console.log("edit : " + id);
}

$('#showadd').click(function(){
	if($('#error').is(':visible'))
		$('#error').fadeOut('normal');
		
	
	if(!$('#caradd').is(':visible'))
	{
		if($('#carmodify').is(':visible'))
			$('#carmodify').fadeOut('normal');
		$('#caradd').fadeIn('normal');
	}
	
});


</script>