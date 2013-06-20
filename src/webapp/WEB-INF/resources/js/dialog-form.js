$(function () {
    var pathArray = window.location.pathname.split('/');

    var formURL = window.location.href.replace("details", "edit");
    var saveURL = '/' + pathArray[0] + '/save/';

    $("#edit").button().click(function () {
        $("body").append("<div id=\"dialog-form\"></div>")
        $("#dialog-form").load(formURL, function () {
            $("#dialog-form").dialog({
                autoOpen: true,
                height: 'auto',
                width: 400,
                modal: true,
                title: "Edit",
                buttons: {
                    "Save" : function () {
                        $.post($("#edit-form").attr("action"), $("#edit-form").serialize(), function () {
                            window.location.reload();
                        });
                    },
                    "Cancel": function () {
                        $("#dialog-form").remove();
                        $(this).dialog('destroy');
                    }
                },
                close: function () {
                    $("#dialog-form").remove();
                    $(this).dialog('destroy');
                }
            });
        });
    });

    $("#back").button().click(function () {
        window.location.href = '/' + pathArray[0];
    });
});
