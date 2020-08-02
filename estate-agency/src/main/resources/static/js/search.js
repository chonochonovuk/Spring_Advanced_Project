$(document).ready(function () {

    $("#search-form").submit(function (event) {

        //stop submit the form, we will post it manually.
        event.preventDefault();

        fire_ajax_submit();

    });

});

function fire_ajax_submit() {

    let search = {}
    search["keyword"] = $("#keyword").val();

    $("#btn-search").prop("disabled", true);

    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/search",
        data: JSON.stringify(search),
        dataType: 'json',
        cache: false,
        timeout: 600000,
        success: function (data) {


            let next = 1;
            let collectAll = "";

            if (data.result == null){

                collectAll +=  '<div class="col-md-12 heading-section text-center">' +
                  '<h4 class="mb-2 text-danger">'+ data.message +'</h4>' +
                    '</div>';
            }else {
                $.each(data.result, function (index,value) {

                    let e = "/images/image_" + next +".jpg";
                    let im = '<img class="card-img-top" src='+ e +' alt="Card image cap">';

                    collectAll +=  '<div class="col-md-4">' +
                        '<div class="card text-white bg-dark mb-3" style="width: 18rem;">' +
                        im +
                        '<div class="card-body">' +
                        '<h5 class="card-title text-white">'+ value.address.town.name +
                        '<p class="card-text">'+ value.address.street +'</p>' +'</h5>' +
                        '<p class="card-text">'+ value.description +'</p>' +
                        '<a href="#" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal" data-name="Pussy">Click here for more details!</a>' +
                        '</div>'+
                        '</div>' +
                        '</div>';
                    next++;
                });
            }



             $('#feedback').html(collectAll);

            console.log("SUCCESS : ", data);
            $("#btn-search").prop("disabled", false);

        },
        error: function (e) {

            let json = "<h4>Ajax Response</h4>"
             + "<p>"  + e.responseText + "</p>";
            $('#feedback').html(json);

            console.log("ERROR : ", e);
            $("#btn-search").prop("disabled", false);

        }
    });

}