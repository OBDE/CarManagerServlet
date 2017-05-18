function loadMyCars(){
	$('#loadinghourglass').show().delay(500);
	$.post("/crudzz/getmycars",{				
	}).done(function(data){				
		try
		{
			console.log(data);
			var json = $.parseJSON(data);					
			if(json.hasOwnProperty("error"))
			{
				$('#loadinghourglass').fadeOut(1000,function(){
					$(this).remove();
					$('#msg').removeClass();
					$('#msg').html(json.error);
					$('#msg').addClass("alert alert-danger");
					$('#msg').fadeIn('normal');
					
				});
				if(json.error.indexOf("Nincsenek autóid") > -1)
				{
					if($('#showtable').is(':visible'))
					{
						$('#showtable').fadeOut('normal');				
					}
				}
				$('#showadd').fadeIn('normal');
				
			}
			if(json.hasOwnProperty("success"))
			{
				$('#loadinghourglass').fadeOut(1000,function(){
					$(this).remove();
					$('#msg').removeClass();
					$('#msg').html(json.success);
					$('#msg').addClass("alert alert-success");
					$('#msg').fadeIn('normal');
					$('#showadd').fadeIn('normal');
				});
			}
			if(json.hasOwnProperty("mycars"))
			{	if(!$('#loadinghourglass').is(':visible'))
				if(!$('#showtable').is(':visible'))
				{
					$('#showtable').fadeIn('normal');				
				}
				
				var parenttbody = document.getElementById("cars");
				if($('#cars').length > 0)
					$('#cars').empty();
				
				for (i = 0; i < json.mycars.length; i++)
				{
					var newtr = document.createElement('tr');
					newtr.className = "success";
					newtr.id = "car-"+i;
					newtr.innerHTML = "<td>"+json.mycars[i].carID+"</td>";
					newtr.innerHTML += "<td>"+json.mycars[i].carVendor+"</td>";
					newtr.innerHTML += "<td>"+json.mycars[i].carName+"</td>";
					newtr.innerHTML += "<td>"+json.mycars[i].carType+"</td>";
					newtr.innerHTML += "<td>"+json.mycars[i].carYear+"</td>";
					newtr.innerHTML += "<td>"+json.mycars[i].carHp+"</td>";
					newtr.innerHTML += "<td>"+json.mycars[i].carCcm+"</td>";
					newtr.innerHTML += "<td>"
												+"<div onclick='deletecar("+i+")' class='btn btn-danger'><span class='glyphicon glyphicon-trash' aria-hidden='true'></span></div>"
												+"<div onclick='editcar("+i+")' class='btn btn-primary'><span class='glyphicon glyphicon-pencil' aria-hidden='true'></span></div>"
												+"</td>";
					parenttbody.appendChild(newtr);
				}
				$('#loadinghourglass').fadeOut(1000,function(){
					$(this).remove();
					$('#showtable').fadeIn('normal');
					$('#showadd').fadeIn('normal');
				});
			}
			console.log("lefutott");
			
		}
		catch(err){
			console.log(err.message);
		}
	});
}