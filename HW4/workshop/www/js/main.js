document.addEventListener("DOMContentLoaded", function(){
    //Do this when DOM is loaded

    //Set event listeners/handlers for buttons
    document.getElementById('home').onclick = dohome;
    document.getElementById('weather').onclick = doweather;
    document.getElementById('map').onclick = domap;
    document.getElementById('currency').onclick = docurrency;

    document.addEventListener("DOMContentLoaded", function(){

        //Add event listeners for buttons
        document.getElementById("gobutton").onclick = onGoButtonClick;
        document.getElementById("locbutton").onclick = onLocationClick;
        });
        
        //Weather API KEY
        const WEATHER_API_KEY = "d769f4675a3ac80235ff8faa8eb100a3"
        
        //reads input from input box, called when the go button is pushed.
        function onGoButtonClick(){
            
            //find the element
            var val = document.getElementById("textbox").value
            if(val === ""){ //Check for empty string, if empty provide error message
                var e = document.createElement('p');
                e.innerHTML = "Can't search for empty string";
                resetMapContent()
                addMapContent(e);
                return;
        
            }
            if(isNaN(val)){ //If value is not a number, call weather API by CITY
                console.log("CITY Detected");
                var url = "https://api.openweathermap.org/data/2.5/weather?q=" + val + "&appid=" + WEATHER_API_KEY;
                xmlRequest(url,onWeatherSuccess,onWeatherFail);
                
            }
            else{ //ELSE CALL BY ZIP CODE
                console.log("ZIP Detected");
                var url = "https://api.openweathermap.org/data/2.5/weather?zip=" + val + "&appid=" + WEATHER_API_KEY;
                xmlRequest(url,onWeatherSuccess,onWeatherFail);
            }
        
        }
        
        //Some helpers to aid in repeated tasks
        function resetMapContent(){
            var r = document.getElementById("map");
            r.innerHTML = "";
        }
        
        function addMapContent(element){
            var r = document.getElementById("map");
            r.appendChild(element);
        
        }
        
        function addWeatherContent(element){
            var r = document.getElementById("weather");
            r.appendChild(element);
        }
        
        function resetWeatherContent(){
            var r = document.getElementById("weather");
            r.innerHTML = "";
        }
        
        //This function is called when the location button is pushed.
        function onLocationClick(){
            navigator.geolocation.getCurrentPosition(onLocationSuccess,onLocationError,{
                enableHighAccuracy: true,
                timeout: 30000}
        
        
            );
        }
        
        
        function xmlRequest(url,onSuccess,onFailure){
            var request = new XMLHttpRequest();
        
            request.onreadystatechange = function() {
                if (this.readyState == 4 && this.status == 200) {
                  onSuccess(JSON.parse(this.responseText));        
                }
                else if(this.readyState == 4){
                    onFailure(this.status);
                }
              };
        
            request.open("GET", url, true);
            request.send();
              
        
            
        }
        
        function onWeatherSuccess(data){
            var line1 = "Weather for: " + data.name;
            var line2 = "Temperature: " + data.main.temp + " K";
            var line3 = "Feels like: " + data.main.feels_like + " K";
            var line4 = "Min Temp: " + data.main.temp_min + " K";
            var line5 = "Max Temp: " + data.main.temp_max + " K";
            var line6 = "Humidity: " + data.main.humidity + "%";
            var line7 = "Longitude: " + data.coord.lon;
            var line8 = "Latitude: " + data.coord.lat;
            var ele1 = document.createElement('p');
            var ele2 = document.createElement('p');
            var ele3 = document.createElement('p');
            var ele4 = document.createElement('p');
            var ele5 = document.createElement('p');
            var ele6 = document.createElement('p');
            var ele7 = document.createElement('p');
            var ele8 = document.createElement('p');
            ele1.innerHTML = line1;
            ele2.innerHTML = line2;
            ele3.innerHTML = line3;
            ele4.innerHTML = line4;
            ele5.innerHTML = line5;
            ele6.innerHTML = line6;
            ele7.innerHTML = line7;
            ele8.innerHTML = line8;
            addWeatherContent(ele1);
            addWeatherContent(ele2);
            addWeatherContent(ele3);
            addWeatherContent(ele4);
            addWeatherContent(ele5);
            addWeatherContent(ele6);
            addWeatherContent(ele7);
            addWeatherContent(ele8);
            resetMapContent();
            var mapp = new Gmap(data.coord.lat,data.coord.lon,12,300,300);
            addMapContent(mapp);
        }
        
        function onWeatherSuccessNoMap(data){
            var line1 = "Weather for " + data.name;
            var line2 = "Temperature " + data.main.temp + "K";
            var line3 = "Feels like: " + data.main.feels_like + " K";
            var line4 = "Min Temp: " + data.main.temp_min + " K";
            var line5 = "Max Temp: " + data.main.temp_max + " K";
            var line6 = "Humidity: " + data.main.humidity + "%";
            var line7 = "Longitude: " + data.coord.lon;
            var line8 = "Latitude: " + data.coord.lat;
            var ele1 = document.createElement('p');
            var ele2 = document.createElement('p');
            var ele3 = document.createElement('p');
            var ele4 = document.createElement('p');
            var ele5 = document.createElement('p');
            var ele6 = document.createElement('p');
            var ele7 = document.createElement('p');
            var ele8 = document.createElement('p');
            ele1.innerHTML = line1;
            ele2.innerHTML = line2;
            ele3.innerHTML = line3;
            ele4.innerHTML = line4;
            ele5.innerHTML = line5;
            ele6.innerHTML = line6;
            ele7.innerHTML = line7;
            ele8.innerHTML = line8;
            addWeatherContent(ele1);
            addWeatherContent(ele2);
            addWeatherContent(ele3);
            addWeatherContent(ele4);
            addWeatherContent(ele5);
            addWeatherContent(ele6);
            addWeatherContent(ele7);
            addWeatherContent(ele8);
        }
        
        function onWeatherFail(status){
            alert("Failed to get weather on Code " + toString(status));
        }
        
        
        //Getting location was successful, make a new Gmap element and add it to the content after resetting the content.
        function onLocationSuccess(p){
            resetMapContent();
            addMapContent(new Gmap(p.coords.latitude,p.coords.longitude,14,300,300));
            var url = "https://api.openweathermap.org/data/2.5/weather?lat=" + p.coords.latitude.toString() + 
                       "&lon=" + p.coords.longitude.toString() + "&appid=" + WEATHER_API_KEY;
            xmlRequest(url,onWeatherSuccessNoMap,onWeatherFail);
            
        };
        
        function onLocationError(e){
            alert("Error getting location");
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
            make_row("Feels Like",feelslikeF,table);
            make_row("Today's Low",lowF,table);
            make_row("Today's High",highF,table);
            make_row("Current Conditions",conditions.weather[0].description,table);
            make_row("Sunset",sunset,table);
            make_row("Sunrise",sunrise,table);
            appendme.appendChild(table);
        }
        
        function initMap() {
            const mapOptions = {
              zoom: 8,
              center: { lat: -34.397, lng: 150.644 },
            };
            map = new google.maps.Map(document.getElementById("map"), mapOptions);
            const marker = new google.maps.Marker({
              // The below line is equivalent to writing:
              // position: new google.maps.LatLng(-34.397, 150.644)
              position: { lat: -34.397, lng: 150.644 },
              map: map,
            });
            // You can use a LatLng literal in place of a google.maps.LatLng object when
            // creating the Marker object. Once the Marker object is instantiated, its
            // position will be available as a google.maps.LatLng object. In this case,
            // we retrieve the marker's position using the
            // google.maps.LatLng.getPosition() method.
            const infowindow = new google.maps.InfoWindow({
              content: "<p>Marker Location:" + marker.getPosition() + "</p>",
            });
            google.maps.event.addListener(marker, "click", () => {
              infowindow.open(map, marker);
            });
          }
    
    dohome();
});
