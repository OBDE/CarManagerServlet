<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
	
<div class="col-md-offset-5" id="loadinghourglass"><img src="includes/images/hourglass.gif" /></div>	
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
		<th>Tulajdonos</th>
		</tr>
		</thead>
		<tbody id="cars">		
		</tbody>	
	</table>
</div>

<script>
$(document).ready(function(){

	
	$.post("/crudzz/getallcars",
			{
				userID : $('#userid').val()
				
			}).done(function(data){				
				try
				{
					var json = $.parseJSON(data);
					console.log("kapott Objectek száma: " + json.allcars.length);
					console.log(data);
					var parenttbody = document.getElementById("cars");
					
					for (i = 0; i < json.allcars.length; i++)
					{
						var newtr = document.createElement('tr');
						newtr.className = "success";
						newtr.id = "car-"+i;
						newtr.innerHTML = "<td>"+json.allcars[i].carID+"</td>";
						newtr.innerHTML += "<td>"+json.allcars[i].carVendor+"</td>";
						newtr.innerHTML += "<td>"+json.allcars[i].carName+"</td>";
						newtr.innerHTML += "<td>"+json.allcars[i].carType+"</td>";
						newtr.innerHTML += "<td>"+json.allcars[i].carYear+"</td>";
						newtr.innerHTML += "<td>"+json.allcars[i].carHp+"</td>";
						newtr.innerHTML += "<td>"+json.allcars[i].carCcm+"</td>";
						newtr.innerHTML += "<td>"+json.allcars[i].carOwnerLastName+" "+json.allcars[i].carOwnerFirstName + "</td>";
						parenttbody.appendChild(newtr);
					}
					console.log("lefutott");
					
				}
				catch(err){
					console.log(err.message);
				}
				
				$('#loadinghourglass').fadeOut(1000,function(){
					$(this).remove();
					$('#showtable').fadeIn('normal');					
				});
			});	
	
	
	
});



</script>