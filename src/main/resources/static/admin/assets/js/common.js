function getOptTargetIds() {
    var ids = [];
    $('.id-col :checked').each(function(i,item){
        ids.push($(item).attr("data-id"));
    });
    return ids;
}

function allSelect() {
    var $trs = $("table.dataTable tr");
    $("table.dataTable tr > td .checkbox").each(function(){
        $(this).addClass("checked");
        $(this).find("input[type=checkbox]").prop("checked", true);
        $(this).find("input[type=checkbox]").trigger("change");
    });
}

function inverseSelect() {
    $("table.dataTable tr > td .checkbox").each(function(){
        var checkbox = $(this).find("input[type=checkbox]").eq(0);
        var f = checkbox.is(":checked")?false:true;
        $(this).toggleClass("checked");
        checkbox.prop("checked", f);
        checkbox.trigger("change");
    });
}