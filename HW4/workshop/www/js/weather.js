var doweather = function() { //This function should be used to build your weather information in the content element
	//TODO : ADD WEATHER CONTENT

	console.log("GETTING CURRENCY");
    var xmlhttp = new XMLHttpRequest();
    xmlhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            var weather = JSON.parse(this.responseText);
            console.log(weather);
            maketable(weather);
        }
        else
        {
            console.log("this.readyState=",this.readyState);
            console.log("this.status=",this.status)
        }
    };
    var dataprefix = "https://api.openweathermap.org/data/2.5/weather?zip="
    var datasuffix = "&appid=952e175e6eed5483ac6b6870d1c1fd56"
    var data = dataprefix + document.getElementById("zipinput").value + datasuffix;
    console.log("requesting:",data);
    xmlhttp.open("POST", data, true)
    xmlhttp.send();
}

function make_row(arg1,arg2,table){
    var tr = document.createElement('tr');
    var td1 = document.createElement('td');
    var td2 = document.createElement('td');
    var text = document.createTextNode(arg1);
    td1.appendChild(text);
    text = document.createTextNode(arg2);
    td2.appendChild(text);
    tr.appendChild(td1);
    tr.appendChild(td2);
    table.appendChild(tr);
}

function maketable(conditions) { // Builds out the table of data
    var sunset = new Date(conditions.sys.sunset * 1000); //Setup condition varaibles
    var lowF = Math.floor(conditions.main.temp_min * (9.0/5) - 459.67);
    var highF = Math.floor(conditions.main.temp_max * (9.0/5) - 459.67);
    var sunrise = new Date(conditions.sys.sunrise * 1000);
    var tempF = Math.floor(conditions.main.temp * (9.0 / 5) - 459.67); 
    var feelslikeF = Math.floor(conditions.main.feels_like * (9.0/5) - 459.67);

    var header = document.createElement('h4'); //Prep the table header
    var textnode = document.createTextNode("Weather in " + conditions.name);
    header.appendChild(textnode);
    var appendme = document.getElementById("weather");
    appendme.innerHTML = ""; //Gut the contents of the previous DIV
    appendme.appendChild(header);

    //Start of table construction
     var table = document.createElement('table');
     make_row("Degrees F",tempF,table);
     //make_row("Feels Like",feelslikeF,table);
     //make_row("Today's Low",lowF,table);
     //make_row("Today's High",highF,table);
     //make_row("Current Conditions",conditions.weather[0].description,table);
     //make_row("Sunset",sunset,table);
    // make_row("Sunrise",sunrise,table);
     appendme.appendChild(table);
}


