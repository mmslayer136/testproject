$(document).ready(function () {
 
    $('#term').focus(function () {
        var full = $("#poster").has("img").length ? true : false;
        if (full == false) {
            $('#poster').empty();
        }
    });
 
    /* 
    ************************
    *ADD YOUR API KEY BELOW*
    ************************
    */ 
    var API_KEY = 'ffbd1defc8826d90fba6eb9311dd6b72';
 
    var getPoster = function () {
 
        var film = $('#term').val();
 
        if (film == "") {
 
            $('#poster').html("<h2 class='loading'>Ha! We haven't forgotten to validate the form! Please enter something.</h2>");
 
        } else {
 
            $('#poster').html("<h2 class='loading'>Your poster is on its way!</h2>");
 
            $.getJSON("https://api.themoviedb.org/3/search/movie?api_key=" + API_KEY + "&language=en-US&query=" + film + "&page=1&include_adult=false", function (json) {
                if (json != "Nothing found.") {
                    console.log(json);
                    $('#poster').html('<h2 class="loading">Well, gee whiz! We found you a poster, skip!</h2><img id="thePoster" src="' + "http://image.tmdb.org/t/p/w185" + json.results[0].poster_path + '" />');
                } else {
                    $.getJSON("http://api.themoviedb.org/2.1/Movie.search/en/json/23afca60ebf72f8d88cdcae2c4f31866/goonies?callback=?", function (json) {
                        console.log(json);
                        $('#poster').html('<h2 class="loading">We\'re afraid nothing was found for that search. Perhaps you were looking for The Goonies?</h2><img id="thePoster" src=' + json[0].posters[0].image.url + ' />');
                    });
                }
            });
 
        }
 
        return false;
    }
 
    $('#search').click(getPoster);
    $('#term').keyup(function (event) {
        if (event.keyCode == 13) {
            getPoster();
        }
    });
 
});