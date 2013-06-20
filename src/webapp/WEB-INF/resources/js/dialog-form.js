$(function () {
    var url = document.location.href.replace("details", "edit");

    $("#edit").button().click(function () {
        $("body").append("<div id=\"dialog-form\"></div>")
        $("#dialog-form").load(url, function () {
            $("#dialog-form").dialog({
                autoOpen: true,
                height: 'auto',
                width: 400,
                modal: true,
                title: "Edit",
                buttons: {
                    "Save" : function () {
                        $.post(url, $("#edit-form").serialize(), function () {
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
        var url = window.location.href = "/batches/";
        window.location.href = url;
    });
});
