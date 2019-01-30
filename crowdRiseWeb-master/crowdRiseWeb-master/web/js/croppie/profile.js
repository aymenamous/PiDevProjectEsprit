$(document).ready(function () {
    $("#intro").css({
        'background-image': 'none',
        'height': 'auto'
    });
});
$(document).ready(function () {
    $('.tooltipped').tooltip({delay: 50});
    $(".upload-trigger").click(function(){
        $("#uploadImg").openModal();
    });
    $('#upimage').hide();
    $('#result').hide();
    var $upload;
    $('#upload').change(function (event) {
        $('#result').show();
        $upload = $('.demo').croppie({
            viewport: {
                width: 200,
                height: 200,
                type: 'circle'
            },
            boundary: {
                width: 300,
                height: 300
            },
            exif: false
        });

        var files = event.target.files;
        var fileReader = new FileReader();
        fileReader.onload = function (e) {

            $upload.croppie('bind', {
                url: e.target.result
            });
        };
        fileReader.readAsDataURL(files[0]);
    });
    $('#result').click(function () {
        $upload.croppie('result', {
            type: 'canvas',
            size: 'viewport'
        }).then(function (resp) {
            console.log('resp: ' + resp);
            $('#upimage').attr('src', resp);
            $('#inputHidden').val(resp);
            $('#upimage').show();
            $upload.hide();
            $("#formImg").submit();
            $("#uploadImg").closeModal();
        });
    });
    $("#intro").css({
        'background-image': 'none',
        'height': 'auto'
    });
    $('.modal-action').click(function () {
        $("#skill").openModal();
    });
    $(".chose").click(function () {
        $(".modal-content").append(constructChip($(this).html(), $(this).attr('id')));
        $(this).hide();
    });
    $(document).on('click', '.sakker', function () {

        $("#" + $(this).parent().attr('id')).show();
    });
    var comps = [];
    $("#send").click(function () {
        
        
        console.log("Sending...");
        $(".chosen > span").each(function (i) {
            comps.push($.trim($(this).text()));

        });
        
        comps = comps.slice(0, comps.length / 3 + comps.length % 3);
        console.log(comps);
        $.each(comps, function (i) {
            $("#form").append(generateInput(i, comps[i]));
        });
        comps.splice(0);
        $("#form").submit();
    });
});
function constructChip(text, id) {
    var chip = '<div id ="' + id + '" class="chip chosen"><span>' + text + '</span><i class="material-icons sakker">close</i></div>';
    return chip;
}
function generateInput(id, val) {
    var input = '<input type="hidden" name="comp' + id + '" value="' + val + '">';
    return input;
}


